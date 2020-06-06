package jsonpath.examples;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Predicate;

public class JsonPathHelper {

	public static <T> T evalJsonPath(String jsonData, String jsonPath, Class<T> type) {
		DocumentContext jsonContext = JsonPath.parse(jsonData);
		return jsonContext.read(jsonPath, type);
	}
	
	public static <T> T evalJsonPathWithPredicates(String jsonData, String jsonPath, Class<T> type,  Predicate ... filters) {
		DocumentContext jsonContext = JsonPath.parse(jsonData);
		return jsonContext.read(jsonPath, type, filters);
	}
	
}
