package com.puc.tcc.entrega.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;

public class Util {
	
	public static String gerarCodigo(int qtdCaracteres) {
		UUID uuid = UUID.randomUUID();
		String myRandom = uuid.toString();
		return myRandom.substring(0,qtdCaracteres);
	}
	
	public static String gerarCodigo(String inicio, int qtdCaracteres) {
		UUID uuid = UUID.randomUUID();
		String myRandom = uuid.toString();
		return inicio + "-" + myRandom.substring(0,qtdCaracteres);
	}
	
	public static String retirarPrefixo(String codigo) {
		String[] partes = codigo.split("-");
		
		return partes[1];
	}
	
	public static String dataNow() {
		LocalDateTime agora = LocalDateTime.now();
		DateTimeFormatter formatador = DateTimeFormatter
		  .ofLocalizedDateTime(FormatStyle.MEDIUM)
		  .withLocale(new Locale("pt", "br"));
		return agora.format(formatador); //08/04/14 10:02
		
	}
}
