package org.samares.activity2simulink.papyrus.xcoscore.test;

import static org.junit.Assert.*;

import java.io.IOException;

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

	@Test
	public void testExecute_Sum_Command() {
		
		String command = "result = 3 + 1;";	
		assertEquals("[4.0]", XCosEngine.getInstance().execute(command));
	}

	/*@Test
	public void testExecute_Model_Command() {
		String diagram_path = "test/org/samares/activity2simulink/papyrus/xcoscore/test/ressources/bounce.zcos";
		String command = "importXcosDiagram(\"" + diagram_path + "\")";	
		System.out.println(XCosEngine.getInstance().execute(command));
	}*/
	
	/*@Test
	public void openScilabXCosTest(){
		String diagram_path = "test/org/samares/activity2simulink/papyrus/xcoscore/test/ressources/bounce.zcos";
		XCosEngine.getInstance().openScilabXCos(diagram_path);
	}*/
}
