package commandbuilder;

import java.util.HashMap;
import java.util.Map;

public class CommandBuilder {
	
	public String data = "";
	public StringBuilder cmdString = new StringBuilder();
	
	public int validateAndBuild(String grepFilters) {
		
    	DataParse dparse = new DataParse(); 
    	Map<String, String> mappedFilters = new HashMap<String, String>();
   	
    	try {
    		
    		dparse.validate(grepFilters);
    		mappedFilters = dparse.parsedata(grepFilters);

    		// after validate and parse the fields, we will build the full command    	    
    	    data = build(mappedFilters);
    	    		
        	return 0;

    	}catch (IllegalArgumentException iae) {
    		data = iae.getMessage();
	    	return 1;
		}

	}
	
	private String build(Map<String, String> filters) {

		// build the string containing the full command
		
	    for (String key : filters.keySet()) {

	    	cmdString.append(" ");
	    	cmdString.append(key);
	    	cmdString.append(" ");
			cmdString.append(filters.get(key));

	    }
	    
	    return cmdString.toString();
	    
	}

}
