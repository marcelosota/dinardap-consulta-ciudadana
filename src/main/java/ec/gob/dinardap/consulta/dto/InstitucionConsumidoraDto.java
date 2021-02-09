/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.dinardap.consulta.dto;

import java.util.List;

/**
 *
 * @author christian.gaona
 */
public class InstitucionConsumidoraDto {

    private String ruc;
    private String razonSocial;
    private Integer año;
    private List<Integer> cantidadMes;


    public InstitucionConsumidoraDto() {
    }

    public InstitucionConsumidoraDto(String ruc, String razonSocial, Integer año, List<Integer> cantidadMes) {
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.año = año;
        this.cantidadMes = cantidadMes;
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

    public Integer getAño() {
        return año;
    }

    public void setAño(Integer año) {
        this.año = año;
    }

    public List<Integer> getCantidadMes() {
        return cantidadMes;
    }

    public void setCantidadMes(List<Integer> cantidadMes) {
        this.cantidadMes = cantidadMes;
    }
}
