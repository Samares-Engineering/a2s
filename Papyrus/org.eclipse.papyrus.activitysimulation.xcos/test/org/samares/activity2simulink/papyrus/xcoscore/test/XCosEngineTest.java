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
package org.samares.activity2simulink.papyrus.xcoscore.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.papyrus.activitysimulation.xcos.engine.XCosEngine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class XCosEngineTest {

	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/*@Test
	public void testExecute_Sum_Command() {
		
		String command = "result = 3 + 1;";	
		ArrayList<String> outVars = new ArrayList<String>();
		outVars.add("result");
		assertEquals("[[4.0]]", XCosEngine.getInstance().executeCommand(command, outVars).toString());
	}*/

	@Test
	public void testExecute_Model_Command() {
		String diagram_path = "test/org/samares/activity2simulink/papyrus/xcoscore/test/ressources/pid_feedback.zcos";
		String command = "loadXcosLibs();"; 	
		//ArrayList<Object> resultXCos = executeCommand(command);
		XCosEngine.getInstance().execute(command);
		
		command = "status = importXcosDiagram(\"" + diagram_path + "\");"; 	
		//ArrayList<Object> resultXCos = executeCommand(command);
		XCosEngine.getInstance().execute(command);
		
		command = "xcos_simulate(scs_m, 4);"; 	
		//ArrayList<Object> resultXCos = executeCommand(command);
		XCosEngine.getInstance().execute(command);
	}
	
	/*@Test
	public void openScilabXCosTest(){
		String diagram_path = "test/org/samares/activity2simulink/papyrus/xcoscore/test/ressources/pid_feedback.zcos";
		File xcosFile = new File(diagram_path);
		System.out.println(xcosFile.getAbsolutePath());
		XCosEngine.getInstance().openScilabXCos(xcosFile.getAbsolutePath());
	}*/
}
