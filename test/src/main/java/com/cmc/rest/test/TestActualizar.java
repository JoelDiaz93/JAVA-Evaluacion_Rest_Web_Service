package com.cmc.rest.test;

import com.cmc.rest.commons.ValidationException;
import com.cmc.rest.entidades.Persona;
import com.cmc.rest.servicios.ServicioPersonas;

public class TestActualizar {
	public static void main(String[] args) {
		ServicioPersonas servicio = new ServicioPersonas();
		Persona p1 = new Persona("1234567890", "joel", "diaz", 23);
		System.out.println(p1);
		try {
			System.out.println(servicio.actualizar(p1));
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
	}
}
