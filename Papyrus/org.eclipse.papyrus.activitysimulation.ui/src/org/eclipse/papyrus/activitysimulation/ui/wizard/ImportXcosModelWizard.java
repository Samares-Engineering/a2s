package org.eclipse.papyrus.activitysimulation.ui.wizard;

import org.eclipse.jface.wizard.Wizard;

public class ImportXcosModelWizard extends Wizard {

	protected ImportXCosModelWizardPage one;
	
	public ImportXcosModelWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	@Override
	public String getWindowTitle() {
		return "Import Scilab/XCos function";
	}

	@Override
	public void addPages() {
		one = new ImportXCosModelWizardPage();
		addPage(one);
	}

	@Override
	public boolean performFinish() {
		// Print the result to the console
		System.out.println(one.getTextXcosUserModel());

		return true;
	}

	public Object getResult() {
	      return null;  // should deliver back the in the Wizard generated data
	}
}