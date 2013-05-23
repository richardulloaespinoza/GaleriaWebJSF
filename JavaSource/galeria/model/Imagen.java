package galeria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity

public class Imagen {

	public static final String TEXTO = "txt";
	public static final String HTML = "html";
	
	
	@Id
	private int id;
	
	@Column
	private String titulo;
	
	@Column
	private String ruta;
	
	@Transient
	private boolean persisted;
	
	@Transient
	private boolean borrado;
	
	@Transient
	private int indice; //identificador paa manejo en memoria
	
	public Imagen(){
		super();
		this.setPersisted(false);
		this.setBorrado(false);
	}
	
	public Imagen(String titulo, String ruta){
		this();
		this.setTitulo(titulo);
		this.setRuta(ruta);
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public boolean isPersisted() {
		return persisted;
	}
	public void setPersisted(boolean persisted) {
		this.persisted = persisted;
	}
	public boolean isBorrado() {
		return borrado;
	}
	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	
	public String toString(){
		return this.getRuta();
	}
	
}
