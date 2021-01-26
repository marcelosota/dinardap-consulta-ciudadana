package ec.gob.dinardap.consulta.dtd;

public class CamposDtd {
	private String campo;
	private String valor;
	
	
	public CamposDtd() {
		super();
	}
	public CamposDtd(String campo, String valor) {
		super();
		this.campo = campo;
		this.valor = valor;
	}
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
}
