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

import ec.gob.dinardap.consulta.dto.InstitucionConsumidoraDto;
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
    private String anioConsulta;
    private Integer mesSeleccionado;
    private InstitucionConsumidoraDto institucionConsumidoraSelected;

    //Listas
    private List<InstitucionConsumidoraDto> institucionConsumidoraList;

    @PostConstruct
    protected void init() {
        urlMicroservicio = getAmbiente().equals("Development") ? getBundleMicroservicios("urlDesarrollo", null) : getBundleMicroservicios("urlProduccion", null);
        //institucionConsumidoraList = new ArrayList<InstitucionConsumidora>();
        cedulaConsulta="1714284856";
        anioConsulta="2020";
        try {
			buscarCedula();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void buscarCedula(){
        try {
        	Object[] param = new Object[3];
        	param[0] = urlMicroservicio;
        	param[1] = cedulaConsulta;
        	param[2] = anioConsulta;
			String url = getBundleMicroservicios("metodo1", param);
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
	            List<Integer> cantidades= new ArrayList<Integer>(); 
	            cantidades.add(Integer.parseInt(json.getString("mes01")));
	            cantidades.add(Integer.parseInt(json.getString("mes02")));
	            cantidades.add(Integer.parseInt(json.getString("mes03")));
	            cantidades.add(Integer.parseInt(json.getString("mes04")));
	            cantidades.add(Integer.parseInt(json.getString("mes05")));
	            cantidades.add(Integer.parseInt(json.getString("mes06")));
	            cantidades.add(Integer.parseInt(json.getString("mes07")));
	            cantidades.add(Integer.parseInt(json.getString("mes08")));
	            cantidades.add(Integer.parseInt(json.getString("mes09")));
	            cantidades.add(Integer.parseInt(json.getString("mes10")));
	            cantidades.add(Integer.parseInt(json.getString("mes11")));
	            cantidades.add(Integer.parseInt(json.getString("mes12")));
	            institucionConsumidoraList.add(new InstitucionConsumidoraDto(json.getString("ruc"),json.getString("razonSocial"),json.getInt("anio"),cantidades));
//	            institucionConsumidoraList.add(new InstitucionConsumidora((i + 1), json.getString("title"), json.getString("title"), json.getInt("id"), json.getInt("userId")));
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
    
    public void celdaSeleccionada() {
    	
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

    public InstitucionConsumidoraDto getInstitucionConsumidoraSelected() {
        return institucionConsumidoraSelected;
    }

    public void setInstitucionConsumidoraSelected(InstitucionConsumidoraDto institucionConsumidoraSelected) {
        this.institucionConsumidoraSelected = institucionConsumidoraSelected;
    }

    public List<InstitucionConsumidoraDto> getInstitucionConsumidoraList() {
        return institucionConsumidoraList;
    }

    public void setInstitucionConsumidoraList(List<InstitucionConsumidoraDto> institucionConsumidoraList) {
        this.institucionConsumidoraList = institucionConsumidoraList;
    }

	public String getAnioConsulta() {
		return anioConsulta;
	}

	public void setAnioConsulta(String anioConsulta) {
		this.anioConsulta = anioConsulta;
	}

	public Integer getMesSeleccionado() {
		return mesSeleccionado;
	}

	public void setMesSeleccionado(Integer mesSeleccionado) {
		this.mesSeleccionado = mesSeleccionado;
	}

}
