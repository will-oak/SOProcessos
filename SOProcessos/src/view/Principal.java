package view;

import controller.ProcessosController;

public class Principal {

	public static void main(String[] args) {
		
		ProcessosController processos = new ProcessosController();
		//System.out.println(processos.os());
		
		//String process = "C:\\Windows\\regedit.exe";
		//processos.callProcess(process);
		
		//String process = "PING -t10 www.google.com";
		//processos.readProcess(process);
		
		String param = "cmd.exe";
		processos.killProcess(param);

	}

}
