package com.mslc;

public class Persona {
	private String nombre;
    private int edad;
    private boolean sano;
    private boolean contacto;
    private boolean sintomas;
    private boolean pasaporte_COVID;
    private boolean es_Profesional;
    
    //Constructores
    public Persona(String nombre, int edad, boolean sano, boolean contacto, boolean sintomas, boolean pasaporte_COVID,
		boolean es_Profesional) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.sano = sano;
		this.contacto = contacto;
		this.sintomas = sintomas;
		this.pasaporte_COVID = pasaporte_COVID;
		this.es_Profesional = es_Profesional;
	}

    //Getters
	public String getNombre() {
		return nombre;
	}

	public int getEdad() {
		return edad;
	}

	public boolean isSano() {
		return sano;
	}

	public boolean isContacto() {
		return contacto;
	}

	public boolean isSintomas() {
		return sintomas;
	}

	public boolean isPasaporte_COVID() {
		return pasaporte_COVID;
	}

	public boolean isProfesional() {
		return es_Profesional;
	}
}
