package com.mx;

import java.util.Set;

public class Persona{
	public String nombre;
	public String apellidoPaterno;
	public String apellidoMaterno;	
	Set<Persona> relacion;

	
	public String getNombre(){
		return nombre;
	};

	public void setNombre(String nombre){
		this.nombre = nombre;
	};

	public String getApellidoPaterno(){
		return apellidoPaterno;
	};

	public void setApellidoPaterno(String apellidoPaterno){
		this.apellidoPaterno = apellidoPaterno;
	};

	public String getApellidoMaterno(){
		return apellidoMaterno;
	};

	public void setApellidoMaterno(String apellidoMaterno){
		this.apellidoMaterno = apellidoMaterno;
	};

	public Set<Persona> getRelacion(){
		return relacion;
	};

	public void setRelacion(Set<Persona> relacion){
		this.relacion = relacion;
	};

	public Persona(String nombre, String apellidoPaterno, 
				String apellidoMaterno, Set<Persona> relacion) {
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.relacion = relacion;
	}

}
