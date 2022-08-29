package com.cmc.rest.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static String convertir(Date date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM hh:mm:ss");
		String fechaComoCadena = sdf.format(date);
		return fechaComoCadena;
	}
	
}
