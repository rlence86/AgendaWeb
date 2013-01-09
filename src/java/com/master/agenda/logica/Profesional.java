package com.master.agenda.logica;

public class Profesional extends Contacto {
	private String direccion;
	private String empresa;

	public Profesional() {
	}
	public Profesional(String nombre, String email, Long telefono, String direccion, String empresa) {
		super(nombre, email, telefono);
		this.direccion = direccion;
		this.empresa = empresa;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesional other = (Profesional) obj;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return super.toString()+" Profesional [direccion=" + direccion + ", empresa=" + empresa
				+ "]";
	}
	
		
	
}
