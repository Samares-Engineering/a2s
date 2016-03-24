package org.samares.activity2simulink.papyrus.xcoscore;

import org.scilab.modules.javasci.JavasciException;
import org.scilab.modules.javasci.JavasciException.InitializationException;
import org.scilab.modules.javasci.Scilab;
import org.scilab.modules.types.ScilabType;

public class XCosCore {

	private Scilab sci;
	
	public XCosCore(){
		try {
			this.sci = new Scilab();
		} catch (InitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void execute(String command){
		try {
			sci.open();
			sci.exec(command);
	        ScilabType result = sci.get("result");
	        System.out.println("result = " + result);
		} catch (JavasciException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
