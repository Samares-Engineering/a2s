package org.samares.activity2simulink.papyrus.ui.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.samares.activity2simulink.papyrus.xcoscore.XCosCore;
import org.samares.activity2simulink.papyrus.xcoscore.console.ConsoleDisplayMgr;

public class ExecuteXcosModelHandler implements IHandler {


	
	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		//get model associated to the activity
		//get parameters
		//create the command to load the model into scilab
		//create the command to launch the simulation of the model
		String command = "result = 3 + rand();";
		
		ConsoleDisplayMgr.getInstance().clear();
		ConsoleDisplayMgr.getInstance().println("Launching Scilab with command " + command, ConsoleDisplayMgr.MSG_INFORMATION);
		
		String result = XCosCore.getInstance().execute(command);
		
		ConsoleDisplayMgr.getInstance().println("result = " + result, ConsoleDisplayMgr.MSG_INFORMATION);
		ConsoleDisplayMgr.getInstance().println("End Scilab execution.", ConsoleDisplayMgr.MSG_INFORMATION);
		
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isHandled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub
	}
}
