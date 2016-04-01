package org.eclipse.papyrus.activitysimulation.ui.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.papyrus.activitysimulation.xcos.console.ConsoleDisplayMgr;
import org.eclipse.papyrus.activitysimulation.xcos.engine.XCosEngine;
import org.eclipse.papyrus.emf.facet.custom.metamodel.v0_2_0.internal.treeproxy.impl.EObjectTreeElementImpl;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.internal.impl.ActivityImpl;

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
		//get selected activity
		//get model associated to the activity
		//get parameters
		//create the command to load the model into scilab
		//create the command to launch the simulation of the model with the parameters
		//the current selection in the entire page
				
		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();

		ISelection selection = activePage.getSelection();
				//the current selection in the navigator view
		selection = activePage.getSelection();
		System.out.println("Selection: " + selection.getClass().toString());
		
		if(selection instanceof TreeSelection){
			if (((TreeSelection)selection).getFirstElement() instanceof EObjectTreeElementImpl){
				System.out.println("Execute " + ((EObjectTreeElementImpl)((TreeSelection)selection).getFirstElement()).basicGetEObject().toString());
				if(((EObjectTreeElementImpl)((TreeSelection)selection).getFirstElement()).basicGetEObject() instanceof ActivityImpl){
					System.out.println("Activity yes");
				}
			}
		}
		String command = "result = 3 + rand();";

		ConsoleDisplayMgr.getInstance().clear();
		ConsoleDisplayMgr.getInstance().println("Launching Scilab with command " + command, ConsoleDisplayMgr.MSG_INFORMATION);

		String result = XCosEngine.getInstance().execute(command);

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
