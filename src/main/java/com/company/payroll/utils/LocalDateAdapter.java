package com.company.payroll.utils;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * LocalDateAdapter serializer
 * For converting LocalDate to json string, add it together with GsonBuilder()
 * */
public class LocalDateAdapter implements JsonSerializer<LocalDate> {

	@Override
	public JsonElement serialize(LocalDate date, Type arg1, JsonSerializationContext arg2) {
		return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
	}

}
