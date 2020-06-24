package adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class IntegerTypeAdapter implements JsonDeserializer<Integer> {
	private static final int DEFAULT_VALUE = 0;

	@Override
	public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		final String valueString = json.getAsString();

		Integer value;

		try {
			value = Integer.valueOf(valueString);
		} catch (NumberFormatException e) {
			// Unable to parse String to Integer, use default value instead
			value = Integer.valueOf(DEFAULT_VALUE);
		}

		return value;
	}
}
