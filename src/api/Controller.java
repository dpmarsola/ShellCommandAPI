package api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import commandbuilder.CommandBuilder;
import commandbuilder.DataParse;
import commandexec.Command;
import jodd.servlet.HtmlEncoder;

@RestController
public class Controller {

	//* Query string accepted format: 
	//* field1=value1,field2=value2 

    @RequestMapping(value = "/command", method = RequestMethod.GET)
    public String Get(@RequestParam String grepFilters) {
    	
    	// builds the grep command to be executed 
    	CommandBuilder cbuilder = new CommandBuilder();
    	int ret = cbuilder.validateAndBuild(grepFilters);
    	
    	if ( ret == 0 ) {
        	
    		// executes the command built in the previous step 
    		Command cmd = new Command();

    		// converts the return of the grep command to an HTML format
    		StringToHtml strToHtml = new StringToHtml();
    		
    		// returns the result to the controller
    		String response = strToHtml.convert(cmd.run(cbuilder.data));
    		
    		return response;

    	}else{
    		
    		return cbuilder.data;
    	
    	}
    	   	
    }


}