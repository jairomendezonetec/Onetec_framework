package utils;

public class UtilObject {

	public static String[] replaceData(String[] arrayElement, String newValue) {
		arrayElement[1] = arrayElement[1].replace("%%", newValue);
		return arrayElement;
	}
}
