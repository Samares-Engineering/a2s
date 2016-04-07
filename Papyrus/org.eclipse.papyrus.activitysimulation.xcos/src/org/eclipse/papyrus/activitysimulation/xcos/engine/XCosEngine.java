package org.eclipse.papyrus.activitysimulation.xcos.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.papyrus.activitysimulation.xcos.console.ConsoleDisplayMgr;
import org.scilab.modules.javasci.JavasciException;
import org.scilab.modules.javasci.Scilab;
import org.scilab.modules.types.ScilabDouble;
import org.scilab.modules.types.ScilabInteger;
import org.scilab.modules.types.ScilabType;
//import org.scilab.modules.xcos.Xcos;
import org.scilab.modules.xcos.graph.XcosDiagram;
public class XCosEngine {

	private Scilab sci;
	//private Xcos xcos;
	//private XcosDiagram xcosDiagram;
	private static XCosEngine instance;

	private XCosEngine(){
		try {
			//ConsoleDisplayMgr.getInstance().clear();
			this.sci = new Scilab();
			sci.open();
			//xcosDiagram = new XcosDiagram();
			//xcosDiagram.installListeners();
			//xcos.op
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


	/**
	 * @param command
	 * @param observableParameters 
	 * continuous/discrete variable or input/output
	 * @return
	 */
	public ArrayList<Object> executeCommand(String command, ArrayList<String> observableParameters){
		
		
		
		ArrayList<Object> results = new ArrayList<Object>();
		System.out.println(command);
		//ConsoleDisplayMgr.getInstance().println("Launching command - ", ConsoleDisplayMgr.MSG_INFORMATION);
		System.out.println(sci.exec(command));
		System.out.println(sci.getLastErrorMessage());
		
		for(int i = 0; i < observableParameters.size(); i++){
		//FIXME see documentation to avoid strings
			try {
				ScilabType scilabType = sci.get(observableParameters.get(i));
				if(scilabType instanceof ScilabInteger){
					results.add(Integer.parseInt(scilabType.toString()));	
				} else if(sci.get(observableParameters.get(i)) instanceof ScilabDouble){
					ScilabDouble sd = (ScilabDouble) sci.get(observableParameters.get(i));
					double[][] realPart = sd.getRealPart();
					double d = realPart[0][0];
					results.add(d);
				}
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
	
	public void getContext(){
		sci.exec("context = scs_m.props.context;");
		sci.exec("context");
	}
	
	public void changeContext(String parameterName, Object value){
		sci.exec("Context." + parameterName + " = " + value.toString()+";");
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
