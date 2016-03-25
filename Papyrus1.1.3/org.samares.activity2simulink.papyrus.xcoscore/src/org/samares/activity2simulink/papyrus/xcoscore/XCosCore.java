package org.samares.activity2simulink.papyrus.xcoscore;

import java.io.IOException;

import org.samares.activity2simulink.papyrus.xcoscore.console.ConsoleDisplayMgr;
import org.scilab.modules.javasci.JavasciException;
import org.scilab.modules.javasci.Scilab;
import org.scilab.modules.types.ScilabType;

public class XCosCore {

	private Scilab sci;
	private static XCosCore instance;

	private XCosCore(){
		try {
			this.sci = new Scilab();
			sci.open();
		} catch (JavasciException e) {
			// TODO Auto-generated catch block
			ConsoleDisplayMgr.getInstance().clear();
			ConsoleDisplayMgr.getInstance().println(e.toString(), ConsoleDisplayMgr.MSG_ERROR); 
		}
	}

	public static XCosCore getInstance() {
		if(instance == null){
			instance = new XCosCore();
		}
		return instance;
	}

	public String execute(String command){
		String result = null;
		
		sci.exec(command);
		//result = sci.getLastErrorMessage();
		try {
			result = sci.get("result").toString();
		} catch (JavasciException e) {
			ConsoleDisplayMgr.getInstance().println(e.toString(), ConsoleDisplayMgr.MSG_ERROR); 
		}
		
		//sci.close();
		
		return result;
		
	}
	
	public void openScilabXCos(String diagram_path){
		
		String sci_env = System.getenv("SCI");
		java.lang.Runtime runtime = Runtime.getRuntime();
		String cmd = sci_env + "\\bin\\WScilex" + " -e xcos(\"" + diagram_path + "\")";
		try {
			final java.lang.Process process = runtime.exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
