package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessosController {

	public ProcessosController() {
		super();
	}
	
	public String os() {
			String os = System.getProperty("os.name");
			String arch = System.getProperty("os.arch");
			String version = System.getProperty("os.version");
			
			return os + " - v. " + version + " - arch. " + arch;
	}
	
	public void callProcess(String process) {
		try {
			Runtime.getRuntime().exec(process);
		} catch (IOException error) {
			String msgErro = error.getMessage();
			
			if (msgErro.contains("740")) {
				StringBuffer buffer = new StringBuffer();
				
				buffer.append("cmd /c ");
				buffer.append(process);
				
				try {
					Runtime.getRuntime().exec(buffer.toString());
				} catch (IOException error2) {
					error2.printStackTrace();
				}
				
			} else {
				error.printStackTrace();
			}
		}
	}
	
	public void readProcess(String process) {
		try {
			Process p = Runtime.getRuntime().exec(process);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			
			while (linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
			
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (IOException error) {
			error.printStackTrace();
		}
	}
	
	public void killProcess(String param) {
		String cmdPid = "TASKKILL /PID";
		String cmdNome = "TASKKILL /IM";
		int pid = 0;
		StringBuffer buffer = new StringBuffer();
		
		try {
			pid = Integer.parseInt(param);
			buffer.append(cmdPid + " " + pid);
		} catch (NumberFormatException error) {
			buffer.append(cmdNome + " " + param);
		}
		
		
		callProcess(buffer.toString());
	}

}
