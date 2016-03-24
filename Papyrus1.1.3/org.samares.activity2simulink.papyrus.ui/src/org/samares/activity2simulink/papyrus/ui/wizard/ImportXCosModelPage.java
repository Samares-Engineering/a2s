package org.samares.activity2simulink.papyrus.ui.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;

public class ImportXCosModelPage extends WizardPage {

	private Text textXcosUserModel;
	private Text textXcosLibraryModel;
	private Button browseXcosUserModelButton;
	private Button browseXcosLibraryButton;
	
	private Composite container;

	public ImportXCosModelPage() {

		super("Import Scilab/XCos function");
		setTitle("Import Scilab/XCos function");
		setDescription("Import existing Scilab/XCos model as an activity.");
	}

	@Override
	public void createControl(Composite parent) {

		this.container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		this.container.setLayout(layout);
		layout.numColumns = 2;

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		
		this.browseXcosUserModelButton = new Button(this.container, SWT.PUSH);
		this.browseXcosUserModelButton.setText("Select user model");
		
		this.textXcosUserModel = new Text(container, SWT.BORDER | SWT.SINGLE);
		this.textXcosUserModel.setLayoutData(gd);
		this.textXcosUserModel.setEditable(false);
		
		this.browseXcosUserModelButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileDialog = new FileDialog(ImportXCosModelPage.this.container.getShell(), SWT.OPEN);         
				fileDialog.setText("Import user defined model");
				String path = fileDialog.open();
				if (path != null) 
					textXcosUserModel.setText(path);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		
		this.browseXcosLibraryButton = new Button(this.container, SWT.PUSH);
		this.browseXcosLibraryButton.setText("Import from libraries");
		
		this.textXcosLibraryModel = new Text(container, SWT.BORDER | SWT.SINGLE);
		this.textXcosLibraryModel.setLayoutData(gd);
		this.textXcosLibraryModel.setEditable(false);
		
		this.browseXcosLibraryButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		
		this.textXcosUserModel.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if (!textXcosUserModel.getText().isEmpty()) {
					setPageComplete(true);
					browseXcosLibraryButton.setEnabled(false);
				}
			}
		});
		
		this.textXcosLibraryModel.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if (!textXcosUserModel.getText().isEmpty()) {
					setPageComplete(true);
					browseXcosUserModelButton.setEnabled(false);
				}
			}
		});
		
		// required to avoid an error in the system
		setControl(container);
		setPageComplete(false);
	}



	public String getTextXcosUserModel() {
		return textXcosUserModel.getText();
	}

}

