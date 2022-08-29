package com.cmc.rest.evaluacionrs;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cmc.rest.commons.ArchivoException;
import com.cmc.rest.commons.ValidationException;
import com.cmc.rest.entidades.Persona;
import com.cmc.rest.servicios.ManejadorArchivos;
import com.cmc.rest.servicios.ServicioPersonas;

@Path("/personas")
public class PersonaRest {

	@Path("/m1")
	@GET
	public String metodo1() {
		return "Saludando desde el rest web service!!";
	}

	@Path("/cambio")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static Response cambiar(Persona persona) {

		System.out.println(persona);
		Persona p1 = null;
		try {
			p1 = ServicioPersonas.actualizar(persona);
		} catch (ValidationException e) {
			return Response.status(200).entity(e.getMessage()).build();
		}
		return Response.status(200).entity(p1).build();
	}

	@Path("/cambiar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON) // application/json
	@Produces(MediaType.APPLICATION_JSON) // application/json
	public Persona modificar(Persona persona) {
		System.out.println(persona);
		ServicioPersonas sa = new ServicioPersonas();
		Persona p1 = null;
		try {
			 p1 = sa.actualizar(persona);
		} catch (ValidationException e) {
			e.printStackTrace();
		}
		return p1;
	}
	
	@Path("/consultar")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarPersonas() {
		ManejadorArchivos manejadorArchivos = new ManejadorArchivos("miarchivo.txt"); // CAMBIAR RUTA
		ArrayList<Persona> personas = new ArrayList<Persona>();
		try {
			personas.addAll(manejadorArchivos.leer());
		} catch (ArchivoException e) {
			e.printStackTrace();
			return Response.status(200).entity(e.getMessage()).build();
		}
		return Response.status(200).entity(personas).build();
	}
	
	@Path("/buscarCedula")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Persona buscarPorCedula(Persona cedula) {
		Persona encontrado = null;
		if (cedula != null) {
			encontrado = ServicioPersonas.buscarPorCedula(cedula.getCedula());
		}
		return encontrado;
	}

}
