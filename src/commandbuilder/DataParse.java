package commandbuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import jdk.jshell.spi.ExecutionControl.UserException;

public class DataParse {

	public String msg;
	private List<String> accepatableFilters = new ArrayList<String>();

	
	public DataParse() {
		
		accepatableFilters.add("data");
		accepatableFilters.add("hora");
		accepatableFilters.add("parceiro");
	
	}

	public int validate(String str) throws IllegalArgumentException {
		
		int res = 0;
		
		if(!str.matches("^[a-zA-Z0-9,;:-]+")) {
			res = 1;
			msg = "Invalid Command Filters. Please use pattern \"commandFilters=field1,value;field2,value2\"";
			throw new IllegalArgumentException(msg);
		}else if (!str.contains(",")) {
			res = 2;
			msg = "Invalid Command Filters. Separate key and value with \",\" (comma).";
			throw new IllegalArgumentException(msg);
		}else if (!str.contains(";")) {
			res = 3;
			msg = "Invalid Command Filters. Separate key-value pairs with \";\" (semicolon).";			
			throw new IllegalArgumentException(msg);
		}
		
		return res;
		
	}
	
	public Map<String, String> parsedata(String str) throws IllegalArgumentException {
		
		Map<String, String> stringToMap = new HashMap<String, String>();
		String inacceptableFilter = "";
		
		String[] keysAndValues; //* All the keys and values splitted by semicolon (;)
		String[] keyValuePair; //* Each single key value pair splitted by comma (,)
		
		keysAndValues=str.split(";");
		
		for (String string : keysAndValues) {
			
			keyValuePair=string.split(","); 
			
			for (String acceptableFilter : accepatableFilters) {
			
				if (keyValuePair[0].equalsIgnoreCase(acceptableFilter)) {
					inacceptableFilter = ""; // clean the string only when the key equals one of the acceptable filters
					break;
				}else {
					inacceptableFilter = keyValuePair[0]; 
				}
				
			}
			
			if (inacceptableFilter.isEmpty()) {
				stringToMap.put(keyValuePair[0],keyValuePair[1]);
			}else {
				msg = "Invalid Command Filters. Filter \"" + inacceptableFilter + "\" is not acceptable";
				throw new IllegalArgumentException(msg);
			}
			
			
		}
		
		return stringToMap;
			
	        
		
	}

	
}
