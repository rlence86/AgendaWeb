package com.master.agenda.logica;

import java.util.Calendar;

public class Amigo extends Contacto {
	private Calendar fechaCumple;

	public Amigo() {
	}

	public Amigo(String nombre, String email, Long telefono, Calendar fechaCumple) {
		super(nombre, email, telefono);
		this.fechaCumple = fechaCumple;
	}

	public String getFechaCumple() {
		return fechaCumple.get(Calendar.DAY_OF_MONTH) +"/"+ (fechaCumple.get(Calendar.MONTH) + 1);
	}
	
	public Calendar getFechaCumpleCalendar(){
		return fechaCumple;
	}

	public void setFechaCumple(Calendar fechaCumple) {
		this.fechaCumple = fechaCumple;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((fechaCumple == null) ? 0 : fechaCumple.hashCode());
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
		Amigo other = (Amigo) obj;
		if (fechaCumple == null) {
			if (other.fechaCumple != null)
				return false;
		} else if (!fechaCumple.equals(other.fechaCumple))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString()+" Amigo [fechaCumple=" + getFechaCumple()+"]";
	}
	
	
	
}
