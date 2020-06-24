package dev.oscarreyes.javajson;

import adapter.IntegerTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.oscarreyes.javajson.entity.Fixture;
import dev.oscarreyes.javajson.io.FileHelper;

public class Main {
	private static final String JSON_FILE = "server-response.json";
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private static Gson gson;

	public static void main(String[] args) {
		final String jsonString = FileHelper.readResourceFile(JSON_FILE);
		final Fixture fixture = fromJson(jsonString, Fixture.class);

		System.out.println(fixture);
	}

	private static <T> T fromJson(String data, Class<T> type) {
		createGsonInstance();

		return gson.fromJson(data, type);
	}

	private static void createGsonInstance() {
		if (gson == null) {
			final GsonBuilder builder = new GsonBuilder();

			builder.setDateFormat(DATE_FORMAT);
			builder.serializeNulls();
			builder.registerTypeAdapter(Integer.class, new IntegerTypeAdapter());

			gson = builder.create();
		}
	}
}
