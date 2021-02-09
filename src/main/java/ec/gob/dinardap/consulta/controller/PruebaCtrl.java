package ec.gob.dinardap.consulta.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.commons.io.FileUtils;
import org.apache.poi.util.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import ec.gob.dinardap.sftp.util.CredencialesSFTP;
import ec.gob.dinardap.sftp.util.GestionSSHJ;


@Named("pruebaCtrl")
@ViewScoped
public class PruebaCtrl extends BaseCtrl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private byte[] contenido;
	private UploadedFile file;

	public void subirArchivo(FileUploadEvent event) {
		try {
			Calendar cal = Calendar.getInstance();
			
			contenido = null;
			file = event.getFile();
			contenido = IOUtils.toByteArray(file.getInputstream());
			CredencialesSFTP credenciales = new CredencialesSFTP();
			credenciales.setHost("192.168.132.24");
			credenciales.setPuerto(22);
			credenciales.setUsuario("beneficiosocial");
			credenciales.setContrasena("1q2w3e4r5t");
			credenciales.setDirDestino("/beneficiosocial/especial/2021/02/01/"+cal.getTimeInMillis()+"/"+file.getFileName());
			GestionSSHJ.subirArchivo(contenido, credenciales);
		} catch (IOException ex) {
			Logger.getLogger(PruebaCtrl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void descargarArchivo() {
		CredencialesSFTP credenciales = new CredencialesSFTP();
		credenciales.setHost("192.168.132.24");
		credenciales.setPuerto(22);
		credenciales.setUsuario("beneficiosocial");
		credenciales.setContrasena("1q2w3e4r5t");
		credenciales.setDirOrigen("/beneficiosocial/especial/2021/02/01/1612211582858/Libro_Somerville_9.pdf");
		credenciales.setDirDestino("/tmp/");
		if(GestionSSHJ.descargarArchivo(credenciales)) {
			try {
				contenido = null;
				String ruta = credenciales.getDirDestino()+"Libro_Somerville_9.pdf";
				File archivo = new File(ruta);
				contenido = FileUtils.readFileToByteArray(archivo);
				//TipoArchivo tipoArchivo = new TipoArchivo();
				URLConnection connection = archivo.toURI().toURL().openConnection();
				
				descargarArchivo(contenido, connection.getContentType(), ruta.substring(ruta.lastIndexOf("/") + 1));
				//archivo.delete();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public byte[] getContenido() {
		return contenido;
	}

	public void setContenido(byte[] contenido) {
		this.contenido = contenido;
	}

}
