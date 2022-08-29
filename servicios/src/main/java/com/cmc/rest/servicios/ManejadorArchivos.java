package com.cmc.rest.servicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import com.cmc.rest.commons.ArchivoException;
import com.cmc.rest.commons.DateUtil;
import com.cmc.rest.entidades.Persona;

public class ManejadorArchivos {
	private String rutaArchivo;
	// private Logger logger = LogManager.getLogger(ManejadorArchivos.class);

	public ManejadorArchivos(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	public ArrayList<Persona> leer() throws ArchivoException {
		File file = new File(this.rutaArchivo);
		System.out.println("****************    " + file.getAbsolutePath() + "    ****************");
		FileReader fileReader = null;
		BufferedReader br = null;
		ArrayList<Persona> personas = new ArrayList<Persona>();
		String linea = "";
		String[] separado;
		Persona p = null;
		try {
			fileReader = new FileReader(file);
			br = new BufferedReader(fileReader);
			while ((linea = br.readLine()) != null) {
				separado = linea.split("-");
				p = new Persona(separado[2], separado[0], separado[1], Integer.parseInt(separado[3]));
				p.setFechaCreacion((DateUtil.convertir(new Date())));
				personas.add(p);
			}
			br.close();
		} catch (FileNotFoundException e) {
			// logger.error("No existe el archivo ", e);
			System.out.println("No existe el archivo " + e);
			throw new ArchivoException("No existe el archivo: " + this.rutaArchivo);
		} catch (IOException e) {
			// logger.error("Error al leer el archivo", e);
			System.out.println("Error al leer el archivo" + e);
			throw new ArchivoException("Error al leer el archivo: " + this.rutaArchivo);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Error al convertir fecha" + e);
		} finally {
			try {
				if (br != null) { // Eliminamos la posibilidad de
									// NullPointerExeption
					br.close();
				}
			} catch (IOException e) {
				System.out.println("Error al cerrar el BufferedReader br " + e);
				// logger.error("Error al cerrar el BufferedReader br ", e);
			}
			try {
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (IOException e) {
				System.out.println("Error al cerrar el FileReader fr " + e);
				// logger.error("Error al cerrar el FileReader fr ", e);
			}
		}
		return personas;
	}
}
