package com.snack.news.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

public class SlackAuthHttpClient {

	public static String postRequest(String urlStr, Map<String, String> parms) {

		try {
			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			setHeader(connection);

			String body = generateBody(parms);
			sendRequest(connection, body);

			return receiveResponse(connection);

		} catch (IOException e) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private static void setHeader(HttpURLConnection http) throws ProtocolException {
		http.setDefaultUseCaches(false);
		http.setDoInput(true);
		http.setDoOutput(true);
		http.setRequestMethod("POST");

		http.setRequestProperty("content-type", "application/x-www-form-urlencoded");
	}

	private static String generateBody(Map<String, String> map) {
		StringBuilder buffer = new StringBuilder();

		if (map != null) {

			Set<String> key = map.keySet();

			for (String o : key) {
				String valueName = map.get(o);
				buffer.append(o).append("=").append(valueName).append("&");
			}
		}

		return buffer.toString();
	}

	private static String receiveResponse(HttpURLConnection connection) throws IOException {
		InputStreamReader tmp = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
		BufferedReader reader = new BufferedReader(tmp);
		StringBuilder builder = new StringBuilder();

		String str;
		while ((str = reader.readLine()) != null) {
			builder.append(str).append("\n");
		}

		return builder.toString();
	}

	private static void sendRequest(HttpURLConnection connection, String body) throws IOException {
		OutputStreamWriter outStream = new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8);
		PrintWriter writer = new PrintWriter(outStream);
		writer.write(body);
		writer.flush();
	}
}