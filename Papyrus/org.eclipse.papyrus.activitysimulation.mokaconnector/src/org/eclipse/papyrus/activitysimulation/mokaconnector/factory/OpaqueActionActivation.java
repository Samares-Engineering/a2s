package org.eclipse.papyrus.activitysimulation.mokaconnector.factory;

import org.eclipse.papyrus.moka.fuml.Semantics.Actions.BasicActions.ActionActivation;
import org.eclipse.papyrus.moka.fuml.Semantics.Classes.Kernel.Object_;
import org.eclipse.papyrus.moka.fuml.Semantics.Classes.Kernel.Value;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OutputPin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.papyrus.activitysimulation.xcos.console.ConsoleDisplayMgr;
import org.eclipse.papyrus.activitysimulation.xcos.engine.XCosEngine;

public class OpaqueActionActivation extends ActionActivation {

	@Override
	public void doAction() {
		OpaqueAction opaqueAction = (OpaqueAction) (this.node);
		if(opaqueAction.getLanguages().contains("Scilab")){
			
			
			List<InputPin> inputPins = opaqueAction.getInputs(); 
			List<OutputPin> resultPins = opaqueAction.getOutputs();
			
			//Value target = this.takeTokens(action.getTarget()).get(0);
			
			/*Iterator itInputPin = inputPins.iterator();
			while(itInputPin.hasNext()){
				InputPin inputPin = (InputPin) itInputPin.next();
			}*/
			ArrayList<String> observableParameters = new ArrayList<String>();
			for(int i= 0; i < resultPins.size(); i++){
				observableParameters.add(resultPins.get(i).getName());
			}
			ArrayList<String> results = XCosEngine.getInstance().executeCommand(opaqueAction.getBodies().get(0), observableParameters);
			
			for(int i = 0; i < results.size(); i++){
				//ConsoleDisplayMgr.getInstance().println(observableParameters.get(i) + " = " + results.get(i), ConsoleDisplayMgr.MSG_INFORMATION);
			}
			
			//Value value =  this.getExecutionLocus().executor.evaluate(XCosEngine.getInstance().execute(opaqueAction.getBodies().get(0))); 
					
			//this.putToken(resultPins.get(0), value);
			System.out.println("###############"  + results.toString());
		}
	}

}
