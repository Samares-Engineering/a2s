package org.eclipse.papyrus.activitysimulation.ui.handlers;

import java.util.ArrayList;

import javax.swing.JViewport;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.papyrus.activitysimulation.xcos.console.ConsoleDisplayMgr;
import org.eclipse.papyrus.activitysimulation.xcos.engine.XCosEngine;
import org.eclipse.papyrus.emf.facet.custom.metamodel.v0_2_0.internal.treeproxy.impl.EObjectTreeElementImpl;
import org.eclipse.papyrus.uml.diagram.activity.edit.part.CustomOpaqueActionEditPart;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.internal.impl.ActivityImpl;
import org.eclipse.uml2.uml.internal.impl.OpaqueActionImpl;

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
		//get model element associated to the activity
		//get parameters
		//verify parameters/nb of parameters
		//create the command to load the model into scilab
		//create the command to launch the simulation of the model with the parameters
		//the current selection in the entire page

		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
		ISelection selection = activePage.getSelection();
		selection = activePage.getSelection();

		OpaqueAction opaqueAction = this.getOpaqueActionFromMouseSelection(selection);

		if(opaqueAction != null){
			String command = opaqueAction.getBodies().get(0).toString();
			ConsoleDisplayMgr.getInstance().clear();
			ConsoleDisplayMgr.getInstance().println("Launching Scilab with command " + command, ConsoleDisplayMgr.MSG_INFORMATION);

			//To replace by Papyrus node
			ArrayList<String> observableParameters = new ArrayList<String>();

			observableParameters.add("result");
			ArrayList<Object> results = XCosEngine.getInstance().executeCommand(command, observableParameters);

			for(int i = 0; i < results.size(); i++){
				ConsoleDisplayMgr.getInstance().println(observableParameters.get(i) + " = " + results.get(i).toString(), ConsoleDisplayMgr.MSG_INFORMATION);
			}

			ConsoleDisplayMgr.getInstance().println("End Scilab execution.", ConsoleDisplayMgr.MSG_INFORMATION);
		}
		return null;
	}

	private OpaqueAction getOpaqueActionFromMouseSelection(ISelection selection) {
		if(selection instanceof TreeSelection){
			if (((TreeSelection)selection).getFirstElement() instanceof EObjectTreeElementImpl){
				EObjectTreeElementImpl elementImpl = (EObjectTreeElementImpl) ((TreeSelection)selection).getFirstElement();
				System.out.println("Execute " + elementImpl.basicGetEObject().toString());
				if(elementImpl.basicGetEObject() instanceof OpaqueAction){
					return (OpaqueAction) elementImpl.basicGetEObject();

				}
			}
		} else if (selection instanceof StructuredSelection){
			if (((StructuredSelection)selection).getFirstElement() instanceof CustomOpaqueActionEditPart){
				CustomOpaqueActionEditPart customOpaqueActionEditPart = (CustomOpaqueActionEditPart) ((StructuredSelection)selection).getFirstElement();
				if(customOpaqueActionEditPart.getNamedElement() instanceof OpaqueAction){
					return (OpaqueAction) customOpaqueActionEditPart.getNamedElement();
				}
			}
		}
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
