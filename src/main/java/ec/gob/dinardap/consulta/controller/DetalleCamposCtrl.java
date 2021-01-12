package ec.gob.dinardap.consulta.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import ec.gob.dinardap.consulta.dtd.CamposDtd;

@Named("detalleCamposCtrl")
@ViewScoped
public class DetalleCamposCtrl implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6009898481084937519L;

	private List<CamposDtd> campos;
	private String titulo;
	
	@PostConstruct
	protected void init() {
		try {
			campos = new ArrayList<>();
			campos = getData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<CamposDtd> getData() throws Exception {
        String data = "  [{\n" + 
                "    \"nm\": \"Harold II\",\n" + 
                "    \"cty\": \"Ecuador\",\n" + 
                "    \"hse\": \"House of Wessex\",\n" + 
                "    \"yrs\": \"1066\"\n" + 
                "  },\n" + 
                "  {\n" + 
                "    \"nm\": \"William I\",\n" + 
                "    \"cty\": \"United Kingdom\",\n" + 
                "    \"hse\": \"House of Normandy\",\n" + 
                "    \"yrs\": \"1066-1087\"\n" + 
                "  },\n" + 
                "  {\n" + 
                "    \"nm\": \"William II\",\n" + 
                "    \"cty\": \"Finlandia\",\n" + 
                "    \"hse\": \"House of Normandy\",\n" + 
                "    \"yrs\": \"1087-1100\"\n" + 
                "  },\n" + 
                "  {\n" + 
                "    \"nm\": \"Henry I\",\n" + 
                "    \"cty\": \"Alemania\",\n" + 
                "    \"hse\": \"House of Normandy\",\n" + 
                "    \"yrs\": \"1100-1135\"\n" + 
                "  },\n" + 
                "  {\n" + 
                "    \"nm\": \"Stephen\",\n" + 
                "    \"cty\": \"Uruguay\",\n" + 
                "    \"hse\": \"House of Blois\",\n" + 
                "    \"yrs\": \"1135-1154\"\n" + 
                "  }]";

        List<CamposDtd> monarchList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);  
            monarchList.add(new CamposDtd(json.getString("nm"), json.getString("cty")));
        }

        return monarchList;
    }
	public List<CamposDtd> getCampos() {
		return campos;
	}
	public void setCampos(List<CamposDtd> campos) {
		this.campos = campos;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


}
