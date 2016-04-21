/*******************************************************************************
 * Copyright (c) 2016 Samares-Engineering.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Jean-Marie Gauthier - initial API and implementation
 *******************************************************************************/
package org.eclipse.papyrus.activitysimulation.xcos.engine;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.papyrus.activitysimulation.xcos.console.ConsoleDisplayMgr;
import org.scilab.modules.javasci.JavasciException;
import org.scilab.modules.javasci.Scilab;
import org.scilab.modules.types.ScilabDouble;
import org.scilab.modules.types.ScilabInteger;
import org.scilab.modules.types.ScilabType;
//import org.scilab.modules.xcos.Xcos;
//import org.scilab.modules.xcos.graph.XcosDiagram;

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
		
		sci.exec(command);
		
		//ConsoleDisplayMgr.getInstance().println("Launching command - ", ConsoleDisplayMgr.MSG_INFORMATION);
		for(int i = 0; i < observableParameters.size(); i++){
			try {
				ScilabType scilabType = sci.get(observableParameters.get(i));
				if(scilabType instanceof ScilabInteger){
					results.add(Integer.parseInt(scilabType.toString()));	
				} else if(scilabType instanceof ScilabDouble){
					ScilabDouble sd = (ScilabDouble) scilabType;
					double[][] realPart = sd.getRealPart();
					double d = realPart[0][0];
					results.add(d);
					///System.out.println("Results : " + d);
				}
			} catch (JavasciException e) {
				e.printStackTrace();
			}
		}
		
		//ConsoleDisplayMgr.getInstance().println("result = " + result, ConsoleDisplayMgr.MSG_INFORMATION);
		//ConsoleDisplayMgr.getInstance().println("End Scilab execution.", ConsoleDisplayMgr.MSG_INFORMATION);
	
		return results;
	}

	public void sciClose(){
		this.sci.close();
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

	public double executeCommand(String command) {
		sci.exec(command);
		try {
			ScilabType scilabType = sci.get("result");
			if(scilabType instanceof ScilabDouble){
				ScilabDouble sd = (ScilabDouble) scilabType;
				double[][] realPart = sd.getRealPart();
				return(realPart[0][0]);
			}
		} catch (JavasciException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public void execute(String command){
		sci.exec(command);
		System.out.println(sci.getLastErrorMessage());
		this.getContext();
	}
}
