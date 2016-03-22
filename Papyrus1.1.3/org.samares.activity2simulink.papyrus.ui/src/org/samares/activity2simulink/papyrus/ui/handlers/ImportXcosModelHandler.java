/*******************************************************************************
 * Copyright (c) 2016 Samares-Engineering.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Samares-Engineering - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.samares.activity2simulink.papyrus.ui.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.samares.activity2simulink.papyrus.ui.wizard.ImportXcosModelWizard;
import org.samares.activity2simulink.papyrus.ui.wizard.MyWizard;

public class ImportXcosModelHandler  implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		Shell activeShell = HandlerUtil.getActiveShell(event);
		ImportXcosModelWizard wizard = new ImportXcosModelWizard();
		WizardDialog dialog = new WizardDialog(activeShell, wizard);
		dialog.open();
		return null;	
	}

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHandled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub
		
	}

}
