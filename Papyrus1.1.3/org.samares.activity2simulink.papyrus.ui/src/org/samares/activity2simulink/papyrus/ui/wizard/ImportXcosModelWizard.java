package org.samares.activity2simulink.papyrus.ui.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

public class ImportXcosModelWizard extends Wizard implements INewWizard {
	
	//protected MyPageOne page;
	private WizardNewProjectCreationPage page;
	
	@Override
	public void addPages() {
	    super.addPages();
	    page = new WizardNewProjectCreationPage("Import XCos model");
	    page.setTitle("Select model");
	    page.setDescription("XCos model selection from library or source file.");
	    addPage(page);
	} 
	public ImportXcosModelWizard() {
		setWindowTitle("Import XCos model");
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return true;
	}

}
