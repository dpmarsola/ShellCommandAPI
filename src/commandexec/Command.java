package commandexec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Command {
	
	public String run(String grepFilters) {
		
		StringBuilder sb = new StringBuilder(); 
		
		try {
			
			// Will execute this command
			sb.append("/home/daniel/workspace/temp/grepexec.sh");
			sb.append(grepFilters);

			// Run a shell command
			Process process = Runtime.getRuntime().exec(sb.toString());

			sb.setLength(0);
			
			int exitVal = process.waitFor();
			if (exitVal == 0) {
				ReturnCommand rc = new ReturnCommand();
				String ret = rc.run();
				sb.append(ret);
			} else {
				System.out.println("Abnormal code: " + exitVal);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
		
	}	
		
}
