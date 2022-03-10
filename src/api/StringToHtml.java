package api;

import jodd.servlet.HtmlEncoder;

public class StringToHtml {
	
	public String convert(String in) {

		final String LINE_BEGINS = "<LINE_BEGINS>";
		final String LINE_ENDS = "<LINE_ENDS>";
		
		String stringConverted = "";
		
		stringConverted += in.replace(LINE_BEGINS, "<div>");
		stringConverted += in.replace(LINE_ENDS, "</div>");		
		
		return stringConverted;
		
	}

}
