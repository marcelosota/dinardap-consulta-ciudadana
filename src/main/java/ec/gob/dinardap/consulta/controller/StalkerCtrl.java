package ec.gob.dinardap.consulta.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import ec.gob.dinardap.consulta.dtd.InstitucionConsumidora;
import ec.gob.dinardap.consulta.util.ConsumoMicroservicio;

@Named(value = "stalkerCtrl")
@ViewScoped
public class StalkerCtrl extends BaseCtrl implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Declaración de variables
    private String urlMicroservicio;
    //Variables de control visual
    //Variables de Negocio    
    private String cedulaConsulta;
    private InstitucionConsumidora institucionConsumidoraSelected;

    //Listas
    private List<InstitucionConsumidora> institucionConsumidoraList;

    @PostConstruct
    protected void init() {
        urlMicroservicio = getAmbiente().equals("Development") ? getBundleMicroservicios("urlDesarrollo", null) : getBundleMicroservicios("urlProduccion", null);
        //institucionConsumidoraList = new ArrayList<InstitucionConsumidora>();
        cedulaConsulta="1714284856";
        try {
			buscarCedula();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void buscarCedula(){
        try {
			String url = urlMicroservicio + getBundleMicroservicios("metodo1", null) + cedulaConsulta ;
			StringBuilder res = new StringBuilder(ConsumoMicroservicio.peticionHttpGet(url));
			System.out.println("====>Res: " + res);        
			System.out.println("===res: "+res.substring(0,1));
			if (!res.substring(0,1).equals("[")) {
			    res.insert(0, "[");
			    res.append("]");            
			}
			System.out.println("====>Res: " + res);   
			institucionConsumidoraList = new ArrayList<>();
			JSONArray jsonArray = new JSONArray(res.toString());
			for (int i = 0; i < jsonArray.length(); i++) {
			    JSONObject json = jsonArray.getJSONObject(i);
//			    institucionConsumidoraList.add(new InstitucionConsumidora((i + 1), json.getString("ruc"), json.getString("razonSocial"), Integer.parseInt(json.getString("mes")), Integer.parseInt(json.getString("cantidad"))));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void onRowSelectInstitucionConsumidora() {
        System.out.println("===>Seleccionado " + institucionConsumidoraSelected.getRazonSocial());
    }

//Getters & Setters
    public String getCedulaConsulta() {
        return cedulaConsulta;
    }

    public void setCedulaConsulta(String cedulaConsulta) {
        this.cedulaConsulta = cedulaConsulta;
    }

    public InstitucionConsumidora getInstitucionConsumidoraSelected() {
        return institucionConsumidoraSelected;
    }

    public void setInstitucionConsumidoraSelected(InstitucionConsumidora institucionConsumidoraSelected) {
        this.institucionConsumidoraSelected = institucionConsumidoraSelected;
    }

    public List<InstitucionConsumidora> getInstitucionConsumidoraList() {
        return institucionConsumidoraList;
    }

    public void setInstitucionConsumidoraList(List<InstitucionConsumidora> institucionConsumidoraList) {
        this.institucionConsumidoraList = institucionConsumidoraList;
    }

}
