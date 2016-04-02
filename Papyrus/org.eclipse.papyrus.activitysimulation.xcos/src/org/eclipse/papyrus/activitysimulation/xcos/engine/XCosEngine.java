package org.eclipse.papyrus.activitysimulation.xcos.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.papyrus.activitysimulation.xcos.console.ConsoleDisplayMgr;
import org.scilab.modules.javasci.JavasciException;
import org.scilab.modules.javasci.Scilab;
import org.scilab.modules.types.ScilabType;

public class XCosEngine {

	private Scilab sci;
	private static XCosEngine instance;

	private XCosEngine(){
		try {
			//ConsoleDisplayMgr.getInstance().clear();
			this.sci = new Scilab();
			sci.open();
			//ConsoleDisplayMgr.getInstance().println("Scilab started.", ConsoleDisplayMgr.MSG_INFORMATION);
		} catch (JavasciException e) {
			// TODO Auto-generated catch block
			//ConsoleDisplayMgr.getInstance().println(e.toString(), ConsoleDisplayMgr.MSG_ERROR); 
		}
	}

	public static XCosEngine getInstance() {
		if(instance == null){
			instance = new XCosEngine();
		}
		return instance;
	}

	public ArrayList<String> executeCommand(String command, ArrayList<String> observableParameters){
		
		
		
		ArrayList<String> results = new ArrayList<String>();
		
		//ConsoleDisplayMgr.getInstance().println("Launching command - ", ConsoleDisplayMgr.MSG_INFORMATION);
		sci.exec(command);
		
		for(int i = 0; i < observableParameters.size(); i++){
		
			try {
				results.add(sci.get(observableParameters.get(i)).toString());
			} catch (JavasciException e) {
				e.printStackTrace();
			}
		}
		
		//ConsoleDisplayMgr.getInstance().println("result = " + result, ConsoleDisplayMgr.MSG_INFORMATION);
		//ConsoleDisplayMgr.getInstance().println("End Scilab execution.", ConsoleDisplayMgr.MSG_INFORMATION);
	
		return results;
		//result = sci.getLastErrorMessage();
		
		
		//sci.close();
		
	}
	
	public void openScilabXCos(String diagram_path){
		
		String sci_env = System.getenv("SCI");
		java.lang.Runtime runtime = Runtime.getRuntime();
		String cmd = sci_env + "bin\\WScilex" + " -e xcos(\"" + diagram_path + "\")";
		System.out.println(cmd);
		try {
			final java.lang.Process process = runtime.exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
