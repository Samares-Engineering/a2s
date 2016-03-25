package org.samares.activity2simulink.papyrus.xcoscore.console;

import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class ConsoleDisplayMgr {

	private static ConsoleDisplayMgr instance = null;
	private MessageConsole fMessageConsole = null;
	private String fTitle = "Scilab Console";

	public static final int MSG_INFORMATION = 1;
	public static final int MSG_ERROR = 2;
	public static final int MSG_WARNING = 3;

	private ConsoleDisplayMgr()
	{		
	}

	public static ConsoleDisplayMgr getInstance() {
		if(instance == null){
			instance = new ConsoleDisplayMgr();
		}
		return instance;
	}	

	public void println(String msg, int msgKind)
	{		
		String msgKindString = null;
		
		if( msg == null ) return;

		displayConsoleView();
		switch (msgKind)
		{
		case MSG_INFORMATION:
			msgKindString = "Info: ";				
			break;
		case MSG_ERROR:
			msgKindString = "Error: ";			
			break;
		case MSG_WARNING:
			msgKindString = "Warning: ";			
			break;
		default:				
		}	
		getNewMessageConsoleStream(msgKind).println(msgKindString + msg);				
	}

	public void clear()
	{		
		IDocument document = getMessageConsole().getDocument();
		if (document != null) {
			document.set("");
		}			
	}	

	private void displayConsoleView()
	{
		try
		{
			IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			if( activeWorkbenchWindow != null )
			{
				IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
				if( activePage != null )
					activePage.showView(IConsoleConstants.ID_CONSOLE_VIEW, null, IWorkbenchPage.VIEW_VISIBLE);
			}

		} catch (PartInitException e) {			
			e.printStackTrace();
		}
	}

	private MessageConsoleStream getNewMessageConsoleStream(int msgKind)
	{		
		int swtColorId = SWT.COLOR_DARK_GREEN;

		switch (msgKind)
		{
		case MSG_INFORMATION:
			swtColorId = SWT.COLOR_DARK_GREEN;				
			break;
		case MSG_ERROR:
			swtColorId = SWT.COLOR_DARK_MAGENTA;
			break;
		case MSG_WARNING:
			swtColorId = SWT.COLOR_DARK_BLUE;
			break;
		default:				
		}	

		MessageConsoleStream msgConsoleStream = getMessageConsole().newMessageStream();		
		msgConsoleStream.setColor(Display.getCurrent().getSystemColor(swtColorId));
		return msgConsoleStream;
	}

	private MessageConsole getMessageConsole()
	{
		if( fMessageConsole == null )
			createMessageConsoleStream(fTitle);	

		return fMessageConsole;
	}

	private void createMessageConsoleStream(String title)
	{
		fMessageConsole = new MessageConsole(title, null); 
		ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[]{ fMessageConsole });
	}
}
