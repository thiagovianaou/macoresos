package uk.ac.open.util;

public class TextParser {

	public static String processTextCodes(String text){
		String ret =  "";
		String[] s = text.split(" ");
		for (int i = 0; i < s.length; i++) {
			if(!s[i].startsWith("\\")){
				ret = ret + s[i]+" ";
			}
		}
		ret = ret.replace("}", "");
		return ret;
	}
	
}
