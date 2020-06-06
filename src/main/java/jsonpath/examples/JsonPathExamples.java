package jsonpath.examples;

import static jsonpath.examples.JsonPathHelper.evalJsonPath;
import static jsonpath.examples.JsonPathHelper.evalJsonPathWithPredicates;
import static jsonpath.examples.utils.Utils.outputSeparator;
import static jsonpath.examples.utils.Utils.readJson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.Predicate;

public class JsonPathExamples {
	public static void main(String[] args) {
		String jsonData = readJson();

//		Single item
		System.out.println(evalJsonPath(jsonData, "$.label", String.class));
		outputSeparator();

//		List of items	
		evalJsonPath(jsonData, "$.movies", List.class).stream().forEach(System.out::println);
		outputSeparator();

//		Filtered list - filter part of expression
		evalJsonPath(jsonData, "$.movies[?(@.type==Action && @.price>101)].title", List.class).stream().forEach(System.out::println);
		outputSeparator();

//		Filtered list with Predicates
		Predicate pricePredicate = context -> Float.valueOf(context.item(Map.class).get("price").toString()) > 101;
		Predicate typePredicate = context -> context.item(Map.class).get("type").toString().equals("Action");

		List<Predicate> predicates = new ArrayList<Predicate>(Arrays.asList(typePredicate, pricePredicate));

		evalJsonPathWithPredicates(jsonData, "$.movies[?].title", List.class, Filter.filter(predicates)).stream().forEach(System.out::println);
		outputSeparator();
		
	}
}
