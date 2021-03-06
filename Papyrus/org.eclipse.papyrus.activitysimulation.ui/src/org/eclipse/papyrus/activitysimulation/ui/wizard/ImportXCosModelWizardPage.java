/*******************************************************************************
 * Copyright (c) 2016 Samares-Engineering.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Jean-Marie Gauthier - initial API and implementation
 *******************************************************************************/
package org.eclipse.papyrus.activitysimulation.ui.wizard;

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

class ImportXCosModelWizardPage extends WizardPage {

	private Text textXcosUserModel;
	private Text textXcosLibraryModel;
	private Button browseXcosUserModelButton;
	private Button browseXcosLibraryButton;
	
	private Composite container;

	public ImportXCosModelWizardPage() {

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
				FileDialog fileDialog = new FileDialog(ImportXCosModelWizardPage.this.container.getShell(), SWT.OPEN); 
				fileDialog.setText("Import user defined model");
				String [] extensions = {"*.xcos"};
				fileDialog.setFilterExtensions(extensions);
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

