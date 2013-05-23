package galeria.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import bsh.This;

import galeria.db.DatabaseSQLHelper;
import galeria.hibernate.HibernateHelper;
import galeria.model.Imagen;

public class Galeria {

	private List<Imagen> imagenes;
	private int indice;
	
	HibernateHelper hibernateHelper = new HibernateHelper();
	
	public Galeria(){
		super();
		this.imagenes = new ArrayList<Imagen>(0);
		this.indice = -1;
		//inicializarDesdeBaseDeDatos
		inicializarDesdeBaseDeDatosHibernate();
	}
	
	private void inicializarDesdeBaseDeDatos() {
		this.imagenes = DatabaseSQLHelper.getListaImagenes();
		
	}
	
	private void inicializarDesdeBaseDeDatosHibernate() {
		this.imagenes = this.hibernateHelper.getListaImagenes();
		
	}

	private void inicializarPorDefecto() {
		//Imagen 1
		Imagen i = new Imagen();
		i.setTitulo("Imagen 1");
		i.setRuta("imagen/Invierno.jpg");
		this.agregarImagen(i);
		
		//Imagen 2
		i = new Imagen();
		i.setTitulo("Imagen 2");
		i.setRuta("imagen/Puesta de sol.jpg");
		this.agregarImagen(i);
		
		//Imagen 3
		i = new Imagen();
		i.setTitulo("Imagen 3");
		i.setRuta("imagen/Puesta de sol.jpg");
		this.agregarImagen(i);
		
	}

	public boolean agregarImagen(Imagen i){
		
		return this.imagenes.add(i);
	}
	
	
	public void siguiente(){
		if (this.indice >= this.imagenes.size()) this.indice = -1;
		this.indice ++;
	}
	
	public Imagen getImagenActual(){
		if (this.indice == -1) this.indice++;
		return this.imagenes.get(this.indice);
	}
	
	public void anterior(){
		if (this.indice <= -1) this.indice = this.imagenes.size();
		this.indice --;
	}
	
	public void aleatorio(){
		this.indice = (int)((Math.random() * this.imagenes.size()) + 1);
	}
	
	public List<Imagen> getImagenes(){
		inicializarDesdeBaseDeDatos();
		return this.imagenes;
	}
	
	public void actualizarImagen(int indice, Imagen imagen){
		this.imagenes.remove(indice);
		this.imagenes.add(indice, imagen);
	}
	
	public void borrarImagen(int indice){
		this.getImagenes().get(indice).setBorrado(true);
	}
	
	public void grabarEnBaseDeDatos() throws SQLException{
		for (Imagen img : this.getImagenes()){
			if (img.isBorrado()){
				DatabaseSQLHelper.deleteImage(img);
			} if (img.isPersisted()){
				DatabaseSQLHelper.updateImage(img);
			} if (img.isBorrado()){
				DatabaseSQLHelper.insertImage(img);
			}
		}
	}
	
	public String editar(){
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> requestParams =  context.getExternalContext().getRequestParameterMap();
		this.indice = Integer.parseInt((String)requestParams.get("indice"));		
		return "editar";
	}
	
	public String nuevo(){
		Imagen img = new Imagen();
		img.setPersisted(false);
		this.indice = this.imagenes.size();
		img.setIndice(this.indice);
		this.imagenes.add(img);
		
		return "editar";
	}
	
	public String guardar() throws SQLException{
		
//		if (getImagenActual().isPersisted())
//			DatabaseSQLHelper.updateImage(this.getImagenActual());
//		else
//			DatabaseSQLHelper.insertImage(this.getImagenActual());
//		return "guardado";
		
		if (getImagenActual().isPersisted())
			hibernateHelper.update(this.getImagenActual());
		else
			hibernateHelper.persist(this.getImagenActual());
		return "guardado";
	}
	
	public String borrar() throws SQLException{
		editar();
		if (getImagenActual().isPersisted())
			DatabaseSQLHelper.deleteImage(this.getImagenActual());
		return "borrado";
	}
	
	public String administracion(){
		return "administracion";
	}
	
	public static void main(String args[]){
		Galeria g = new Galeria();
		System.out.println(g.getImagenActual());

	}
}
