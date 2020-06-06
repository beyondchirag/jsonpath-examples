package jsonpath.examples.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class Utils {

	public static void outputSeparator() {
		System.out.println("-----------------------------------\n\n");
	}


	public static String readJson() {
		try {
			return FileUtils.readFileToString(new File("test.json"), "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
