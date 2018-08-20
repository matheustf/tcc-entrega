package com.puc.tcc.entrega.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;

import com.puc.tcc.entrega.consts.Constants;
import com.puc.tcc.entrega.exceptions.GenericException;

public class Util {

	public static String gerarCodigo(int qtdCaracteres) {
		UUID uuid = UUID.randomUUID();
		String myRandom = uuid.toString();
		return myRandom.substring(0, qtdCaracteres);
	}

	public static String gerarCodigo(String inicio, int qtdCaracteres) {
		UUID uuid = UUID.randomUUID();
		String myRandom = uuid.toString();
		return inicio + "-" + myRandom.substring(0, qtdCaracteres);
	}

	public static String retirarPrefixo(String codigo) {
		String[] partes = codigo.split("-");

		return partes[1];
	}

	public static String dataNow() {
		LocalDateTime agora = LocalDateTime.now();
		DateTimeFormatter formatador = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
				.withLocale(new Locale("pt", "br"));
		return agora.format(formatador); // 08/04/14 10:02

	}

	public static String getIdCadastroToken(String token) throws GenericException {
		try {
			System.out.println(token);
			String[] pieces = token.split("\\.");

			String header = new String(DatatypeConverter.parseBase64Binary(pieces[1]), "UTF-8");
			JSONObject json = new JSONObject(header);

			return (String) json.get("idCadastro");

		} catch (Exception e) {
			throw new GenericException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.SERVER_ERROR);
		}
	}

}
