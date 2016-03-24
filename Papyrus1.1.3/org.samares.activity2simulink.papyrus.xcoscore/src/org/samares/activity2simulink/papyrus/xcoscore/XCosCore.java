package org.samares.activity2simulink.papyrus.xcoscore;

import org.scilab.modules.javasci.JavasciException;
import org.scilab.modules.javasci.Scilab;
import org.scilab.modules.types.ScilabType;

public class XCosCore {

	private Scilab sci;
	private static XCosCore instance;

	private XCosCore(){
		try {
			this.sci = new Scilab();
			this.sci.open();
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

	public void execute(String command){
		ScilabType result = null;
		ConsoleDisplayMgr.getInstance().clear();
		ConsoleDisplayMgr.getInstance().println("Launching Scilab with command " + command, ConsoleDisplayMgr.MSG_INFORMATION);

		sci.exec(command);


		try {
			result = sci.get("result");
		} catch (JavasciException e) {
			ConsoleDisplayMgr.getInstance().println(e.toString(), ConsoleDisplayMgr.MSG_ERROR); 
		}
		
		ConsoleDisplayMgr.getInstance().println("result = " + result, ConsoleDisplayMgr.MSG_INFORMATION);
		ConsoleDisplayMgr.getInstance().println("End Scilab execution.", ConsoleDisplayMgr.MSG_INFORMATION);
	}
}
