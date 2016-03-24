package org.samares.activity2simulink.papyrus.ui.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.samares.activity2simulink.papyrus.xcoscore.XCosCore;

public class ExecuteXcosModelHandler implements IHandler {

	private XCosCore xcosCore;
	
	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		this.xcosCore = new XCosCore();
		String command = "result = 3 + 2;";
		this.xcosCore.execute(command);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
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
