package commandexec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ReturnCommand {

	public String run() {
		
		StringBuilder sb = new StringBuilder();
		
		try {
			
			// Will run a cat command to read the result of previous command in file cmd.result
			sb.append("/home/daniel/workspace/temp/buscaresult.sh");

			// Run a shell command
			Process process = Runtime.getRuntime().exec("/home/daniel/workspace/temp/buscaresult.sh");

			sb.setLength(0);
			
			//Catch the process return that would normally go to the stdout
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append("<LINE_BEGINS>" + line + "<LINE_ENDS>");
			}
			
			int exitVal = process.waitFor();
			if (exitVal != 0) {
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
