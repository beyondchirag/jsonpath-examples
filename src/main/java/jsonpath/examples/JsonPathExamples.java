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



interface TypePredicate {
	Predicate typeCriteria(String type);
}

interface PricePredicate{
	Predicate priceCriteria(Float price);
}


public class JsonPathExamples implements TypePredicate, PricePredicate {
	

	
	public static void main(String[] args) {
		String jsonData = readJson();

//		Single item
		System.out.println(evalJsonPath(jsonData, "$.label", String.class));
		outputSeparator();

//		List of items	
		evalJsonPath(jsonData, "$.movies", List.class).stream().forEach(System.out::println);
		outputSeparator();

//		Filtered list - filter part of expression - AND
		evalJsonPath(jsonData, "$.movies[?(@.type==Action && @.price>101)].title", List.class).stream().forEach(System.out::println);
		outputSeparator();
		
//		Filtered list - filter part of expression - OR
		evalJsonPath(jsonData, "$.movies[?(@.type==Drama || @.price<101)].title", List.class).stream().forEach(System.out::println);
		outputSeparator();
	
//		Filtered list with Predicates
		Predicate pricePredicate = new JsonPathExamples().priceCriteria(101f);
		Predicate typePredicate = new JsonPathExamples().typeCriteria("Action");

		List<Predicate> predicates = new ArrayList<Predicate>(Arrays.asList(typePredicate, pricePredicate));

		evalJsonPathWithPredicates(jsonData, "$.movies[?].title", List.class, Filter.filter(predicates)).stream().forEach(System.out::println);
		outputSeparator();
		
	}

	@Override
	public Predicate priceCriteria(Float price) {
		return context -> Float.valueOf(context.item(Map.class).get("price").toString()) > price;
	}

	@Override
	public Predicate typeCriteria(String type) {
		return context -> context.item(Map.class).get("type").toString().equals(type);
	}

}
