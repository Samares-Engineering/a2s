package org.samares.activity2simulink.papyrus.ui.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class MyWizard extends Wizard implements INewWizard{
	
	protected MyPageOne one;
	protected MyPageTwo two;

	public MyWizard() {
		super();
		setNeedsProgressMonitor(true);
		setWindowTitle("Import Xcos model");
	}

	@Override
	public String getWindowTitle() {
		return "Export My Data";
	}

	@Override
	public void addPages() {
		super.addPages();
		one = new MyPageOne();
		two = new MyPageTwo();
		addPage(one);
		addPage(two);
	}

	@Override
	public boolean performFinish() {
		// Print the result to the console
		System.out.println(one.getText1());
		System.out.println(two.getText1());

		return true;
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
		
	}

}
