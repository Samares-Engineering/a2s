package org.eclipse.papyrus.activitysimulation.mokaconnector.factory;

import org.eclipse.papyrus.moka.fuml.Semantics.Actions.BasicActions.ActionActivation;
import org.eclipse.papyrus.moka.fuml.Semantics.Classes.Kernel.Object_;
import org.eclipse.papyrus.moka.fuml.Semantics.Classes.Kernel.RealValue;
import org.eclipse.papyrus.moka.fuml.Semantics.Classes.Kernel.Value;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.ValueSpecification;

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
			
			String command = opaqueAction.getBodies().get(opaqueAction.getLanguages().indexOf("Scilab"));
			List<OutputPin> resultPins = opaqueAction.getOutputs();
			//System.out.println(this.getTokens(opaqueAction.getInputs().get(0)).get(0).toString());
			
			//FIXME for multiple inputs
			List<Value> inputValues = this.getTokens(opaqueAction.getInputs().get(0));
			System.out.println(inputValues.toString());
			
			//FIXME for multiple inputs
			//replace input variables (in the command) by the real value evaluated by moka.
			command = this.replaceInputValues(command, opaqueAction.getInputs().get(0).getName(), inputValues.get(0));
		
			ArrayList<String> observableParameters = new ArrayList<String>();
			for(int i= 0; i < resultPins.size(); i++){
				observableParameters.add(resultPins.get(i).getName());
			}
			ArrayList<Object> results= XCosEngine.getInstance().executeCommand(command, observableParameters);
			
			/*for(int i = 0; i < results.size(); i++){
				//ConsoleDisplayMgr.getInstance().println(observableParameters.get(i) + " = " + results.get(i), ConsoleDisplayMgr.MSG_INFORMATION);
			}*/
			
			for (int i = 0; i < results.size(); i++) {
				
				Value value = null;
				
				if(results.get(i) instanceof java.lang.Double){
					value = new RealValue();
					((RealValue) value).value = (double) results.get(i); 
					((RealValue) value).type = (PrimitiveType) this.getExecutionLocus().factory.getBuiltInType("Real"); 
				}
				
				this.putToken(opaqueAction.getOutputs().get(i), value);
			}
			
			//Value value =  this.getExecutionLocus().executor.evaluate(XCosEngine.getInstance().execute(opaqueAction.getBodies().get(0))); 
					
			//this.putToken(resultPins.get(0), value);
			System.out.println("###############"  + results.toString());
		}
	}

	private String replaceInputValues(String command, String name, Value value) {
		return command.replaceAll(name, value.toString());
	}

}
