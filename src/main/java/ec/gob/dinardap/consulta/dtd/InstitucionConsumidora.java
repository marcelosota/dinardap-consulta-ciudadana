/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.dinardap.consulta.dtd;

/**
 *
 * @author christian.gaona
 */
public class InstitucionConsumidora {

    private Integer id;
    private String ruc;
    private String razonSocial;
    private Integer mesInt;
    private Integer cantidadConsultas; //Este ya no iria
    private Integer[] consulta = new Integer[12];

    private String mesStr;

    public InstitucionConsumidora() {
    }

    public InstitucionConsumidora(Integer id, String ruc, String razonSocial, Integer mesInt, Integer cantidadConsultas) {
        super();
        this.id = id;
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.mesInt = mesInt;
        //this.cantidadConsultas = cantidadConsultas;
        this.mesStr = asignarMesStr(this.mesInt);
        this.consulta[mesInt - 1] = cantidadConsultas;
    }

    private static String asignarMesStr(Integer mesInt) {
        String mesAux = "";
        switch (mesInt) {
            case 1:
                mesAux = "ENERO";
                break;
            case 2:
                mesAux = "FEBRERO";
                break;
            case 3:
                mesAux = "MARZO";
                break;
            case 4:
                mesAux = "ABRIL";
                break;
            case 5:
                mesAux = "MAYO";
                break;
            case 6:
                mesAux = "JUNIO";
                break;
            case 7:
                mesAux = "JULIO";
                break;
            case 8:
                mesAux = "AGOSTO";
                break;
            case 9:
                mesAux = "SEPTIEMBRE";
                break;
            case 10:
                mesAux = "OCTUBRE";
                break;
            case 11:
                mesAux = "NOVIEMBRE";
                break;
            case 12:
                mesAux = "DICIEMBRE";
                break;
            default:
                mesAux = "Fecha sin Definir";
                break;
        }
        return mesAux;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Integer getMesInt() {
        return mesInt;
    }

    public void setMesInt(Integer mesInt) {
        this.mesInt = mesInt;
    }

    public Integer getCantidadConsultas() {
        return cantidadConsultas;
    }

    public void setCantidadConsultas(Integer cantidadConsultas) {
        this.cantidadConsultas = cantidadConsultas;
    }

    public String getMesStr() {
        return mesStr;
    }

    public void setMesStr(String mesStr) {
        this.mesStr = mesStr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public Integer[] getConsulta() {
		return consulta;
	}

	public void setConsulta(Integer[] consulta) {
		this.consulta = consulta;
	}

}
