package org.samares.activity2simulink.papyrus.xcoscore.test;

//A simple Java example
//javasci v2
//Filename: BasicExample.java

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.lang.reflect.*;

import org.scilab.modules.javasci.Scilab;
import org.scilab.modules.types.ScilabType;



class BasicExample {

	public static void main(String[] args) {
		try {
			//System.setProperty( "java.library.path", "/Applications/scilab-5.5.2.app/Contents/MacOS/lib/scilab/:/Applications/scilab-5.5.2.app/Contents/MacOS/lib/thirdparty/" );
			//System.setProperty( "sun.boot.library.path", "/Applications/scilab-5.5.2.app/Contents/MacOS/lib/scilab:/Applications/scilab-5.5.2.app/Contents/MacOS/lib/thirdparty:/Library/Java/JavaVirtualMachines/jdk1.7.0_79.jdk/Contents/Home/jre/lib" );
			//System.setProperty( "sun.boot.library.path", "/Applications/scilab-5.5.2.app/Contents/MacOS/lib/scilab:/Applications/scilab-5.5.2.app/Contents/MacOS/lib/thirdparty:/Library/Java/JavaVirtualMachines/jdk1.6.0.jdk/Contents/Home/jre/lib" );

			//System.setProperty( "java.library.path", "/Applications/scilab-5.5.2.app/Contents/MacOS/lib/scilab:/Applications/scilab-5.5.2.app/Contents/MacOS/lib/thirdparty:/Users/faudouraphael/Library/Java/Extensions:/Library/Java/Extensions:/Network/Library/Java/Extensions:/System/Library/Java/Extensions:/usr/lib/java:.");

			/*Field fieldSysPath;
			try {
				fieldSysPath = ClassLoader.class.getDeclaredField( "sys_paths" );
				fieldSysPath.setAccessible( true );
				fieldSysPath.set( null, null );
			} catch (NoSuchFieldException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/



			System.out.println("===== ENV VARIABLES =====");
			System.out.println(System.getenv("SCI"));
			//dumpVars(System.getenv());
			System.out.println("===== SYSTEM PROPERTIES =====");
			//dumpVars(new HashMap(System.getProperties()));
			System.out.println(System.getProperty("java.library.path"));

			Scilab sci = new Scilab();
			sci.open();
			sci.exec("a=cos(%pi)*sin(%pi^2);");
			ScilabType a = sci.get("a");
			System.out.println("a = " + a);
		} catch (org.scilab.modules.javasci.JavasciException e) {
			System.err.println("Could not find variable type: " + e.getLocalizedMessage());
		}


		/*String sci_env = System.getenv("SCI");
		String diagram_path = "";
		java.lang.Runtime runtime = Runtime.getRuntime();
		String cmd = sci_env + "bin\\WScilex" + " -e xcos(\"" + diagram_path + "\")";
		System.out.println(cmd);
		try {
			final java.lang.Process process = runtime.exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}


	private static void dumpVars(Map<String, ?> m) {
		List<String> keys = new ArrayList<String>(m.keySet());
		Collections.sort(keys);
		for (String k : keys) {
			System.out.println(k + " : " + m.get(k));
		}
	}


}