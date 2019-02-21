package com.oreilly.mvc.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class JulianDateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String strDate) {
		
		System.out.println("Converting to a date from a string");
		Date date = null;
		
		try {
			date = new SimpleDateFormat("yyyyDDD").parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

}
