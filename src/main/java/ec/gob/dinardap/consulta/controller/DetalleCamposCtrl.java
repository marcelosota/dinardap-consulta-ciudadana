package ec.gob.dinardap.consulta.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.stream.JsonCollectors;

import org.glassfish.json.JsonUtil;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ec.gob.dinardap.consulta.dtd.CamposDtd;
import ec.gob.dinardap.consulta.dtd.InstitucionConsumidora;
import ec.gob.dinardap.consulta.util.ConsumoMicroservicio;

@Named("detalleCamposCtrl")
@ViewScoped
public class DetalleCamposCtrl extends BaseCtrl implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6009898481084937519L;

	private List<CamposDtd> campos;
	private String titulo;
	private String cedulaConsulta;
	private String urlMicroservicio;
	
	@PostConstruct
	protected void init() {
		try {
			setCedulaConsulta("1714284856");
			urlMicroservicio = getAmbiente().equals("Development") ? getBundleMicroservicios("urlDesarrollo", null) : getBundleMicroservicios("urlProduccion", null);
			//consultar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private List<CamposDtd> consultarPorMeses(StringBuilder res) {
		JSONArray jsonArray = new JSONArray(res.toString());
		List<CamposDtd> registros = new ArrayList<>();
		for (int i = 0; i < jsonArray.length(); i++) {
		    JSONObject json = jsonArray.getJSONObject(i);
		    CamposDtd item = new CamposDtd();
		    item.setCampo(json.getString("ruc"));
		    item.setValor(json.getString("razonSocial"));
		    registros.add(item);
		}
		return registros;
	}
	
	public void consultar() {
		try {
			String url = urlMicroservicio + getBundleMicroservicios("metodo1", null) + cedulaConsulta ;
			campos = new ArrayList<>();
			//campos = getData();
			//campos = consultarPorMeses(new StringBuilder(ConsumoMicroservicio .peticionHttpGet(url)));
			convertirJson(new StringBuilder(ConsumoMicroservicio .peticionHttpGet(url)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void convertirJson(StringBuilder sb) {
		//Gson gson = new Gson();
		//List<InstitucionConsumidora> lista = gson.fromJson(sb.toString(), new TypeToken<List<InstitucionConsumidora>>() {}.getType());
		JsonArray contacts = (JsonArray) JsonUtil.toJson(sb.toString());
		
		JsonObject result = contacts.getValuesAs(JsonObject.class).stream()
	            .collect(JsonCollectors.groupingBy(x->((JsonObject)x).getString("ruc")));
		/*JsonObject result = contacts.getValuesAs(JsonObject.class).stream()
                .filter(x->"F".equals(x.getString("gender")))
                .collect(JsonCollectors.toJsonObject(
                        x->x.asJsonObject().getString("name"),
                        x->x.asJsonObject().getJsonObject("phones").get("mobile")))*/
		System.out.println(result.toString());
    
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
	public String getCedulaConsulta() {
		return cedulaConsulta;
	}
	public void setCedulaConsulta(String cedulaConsulta) {
		this.cedulaConsulta = cedulaConsulta;
	}
	public String getUrlMicroservicio() {
		return urlMicroservicio;
	}
	public void setUrlMicroservicio(String urlMicroservicio) {
		this.urlMicroservicio = urlMicroservicio;
	}


}
