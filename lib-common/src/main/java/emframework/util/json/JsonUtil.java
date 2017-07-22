package emframework.util.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	public static JSONObject convertToJsonObject(InputStream inStream) throws IOException, JSONException {
		String string = readStreamToString(inStream);
		return new JSONObject(string );
	}

	public static String readStreamToString(InputStream inStream)
			throws UnsupportedEncodingException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inStream, "UTF-8"), 8 * 1024);
		String line = null;
		StringBuffer entityStringBuilder = new StringBuffer();
		while ((line = bufferedReader.readLine()) != null) {
			entityStringBuilder.append(line + "/n");
		}
		return entityStringBuilder.toString();
	}

	@SuppressWarnings("unchecked")
	public static <T> T parseJson(InputStream inStream, Class<T> valueType)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objMapper = new ObjectMapper();
		String json = readStreamToString(inStream);
		return (T) objMapper.readValue(json , valueType);
	}
	@SuppressWarnings("unchecked")
	public static <T> T parseJson(String json, Class<T> valueType)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objMapper = new ObjectMapper();
		return (T) objMapper.readValue(json, valueType);
	}
	
	public static String toJsonString(Object o){
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			return "{ \"error\":\"convert to Json failed\"}" ;
		}
	}
}
