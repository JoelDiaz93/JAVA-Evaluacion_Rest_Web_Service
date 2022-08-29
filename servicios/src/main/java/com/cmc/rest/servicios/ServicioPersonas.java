package com.cmc.rest.servicios;

import java.util.ArrayList;

import com.cmc.rest.commons.ArchivoException;
import com.cmc.rest.commons.ValidationException;
import com.cmc.rest.entidades.Persona;

public class ServicioPersonas {

	public static Persona actualizar(Persona persona) throws ValidationException {
		if (persona.getNombre().length() <= 3) {
			throw new ValidationException("el nombre es muy corto");
		}
		if (persona.getApellido().length() <= 3) {
			throw new ValidationException("El apellido es muy corto");
		}
		
		return (new Persona(persona.getCedula(), persona.getNombre().toUpperCase(), persona.getApellido().toUpperCase(), persona.getEdad()));
	}
	
	public static Persona buscarPorCedula(String cedula) {
		ManejadorArchivos archivos = new ManejadorArchivos("miarchivo.txt");
		ArrayList<Persona> personas = new ArrayList<Persona>();
		try {
			personas.addAll(archivos.leer());
		} catch (ArchivoException e) {
			e.printStackTrace();
		}
		for (Persona persona : personas) {
			if (persona != null) {
				if (persona.getCedula().equals(cedula)) {
					return persona;
				}
			}
		}
		return null;
	}
}
