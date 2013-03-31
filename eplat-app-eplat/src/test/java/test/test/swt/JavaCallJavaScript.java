/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.test.swt;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.ole.win32.OLE;
import org.eclipse.swt.ole.win32.OleAutomation;
import org.eclipse.swt.ole.win32.OleControlSite;
import org.eclipse.swt.ole.win32.OleEvent;
import org.eclipse.swt.ole.win32.OleFrame;
import org.eclipse.swt.ole.win32.OleListener;
import org.eclipse.swt.ole.win32.Variant;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Running a script within IE. (win32 only)
 * 
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 * 
 * @author obullxl@gmail.com
 * @version $Id: JavaCallJavaScript.java, V1.0.1 2013-3-31 下午8:57:06 $
 */
public class JavaCallJavaScript {
    public static void main(String[] args) {
        final Display display = new Display();
        Shell shell = new Shell(display);
        shell.setLayout(new FillLayout());
        OleControlSite controlSite;
        try {
            OleFrame frame = new OleFrame(shell, SWT.NONE);
            controlSite = new OleControlSite(frame, SWT.NONE, "Shell.Explorer");
            controlSite.doVerb(OLE.OLEIVERB_INPLACEACTIVATE);
        } catch (SWTError e) {
            System.out.println("Unable to open activeX control");
            display.dispose();
            return;
        }

        // IWebBrowser
        final OleAutomation webBrowser = new OleAutomation(controlSite);

        // When the document is loaded, access the document object for the new page
        // and evalute expression using Script.
        int DownloadComplete = 104;
        controlSite.addEventListener(DownloadComplete, new OleListener() {
            public void handleEvent(OleEvent event) {
                int[] htmlDocumentID = webBrowser.getIDsOfNames(new String[] { "Document" });
                if (htmlDocumentID == null)
                    return;
                Variant pVarResult = webBrowser.getProperty(htmlDocumentID[0]);
                if (pVarResult == null || pVarResult.getType() == 0)
                    return;
                //IHTMLDocument2
                OleAutomation htmlDocument = null;
                try {
                    htmlDocument = pVarResult.getAutomation();
                    pVarResult.dispose();

                    int[] scriptID = htmlDocument.getIDsOfNames(new String[] { "Script" });
                    if (scriptID == null)
                        return;
                    pVarResult = htmlDocument.getProperty(scriptID[0]);
                    if (pVarResult == null || pVarResult.getType() == 0)
                        return;
                    OleAutomation htmlWindow = null;
                    try {
                        //IHTMLWindow2
                        htmlWindow = pVarResult.getAutomation();
                        pVarResult.dispose();
                        int[] evaluateID = htmlWindow.getIDsOfNames(new String[] { "evaluate" });
                        if (evaluateID == null)
                            return;
                        String expression = "5+Math.sin(9)";
                        Variant[] rgvarg = new Variant[] { new Variant(expression) };
                        pVarResult = htmlWindow.invoke(evaluateID[0], rgvarg, null);
                        if (pVarResult == null || pVarResult.getType() == 0)
                            return;
                        System.out.println(expression + " = " + pVarResult.getString());
                    } finally {
                        htmlWindow.dispose();
                    }
                } finally {
                    htmlDocument.dispose();
                }
            }
        });

        // Navigate to a web site
        int[] ids = webBrowser.getIDsOfNames(new String[] { "Navigate", "URL" });
        String html = new File(JavaScriptCallJava.class.getResource("/JavaCallJavaScript.html").getFile()).getAbsolutePath();
        Variant[] rgvarg = new Variant[] { new Variant(html) };
        int[] rgdispidNamedArgs = new int[] { ids[1] };
        webBrowser.invoke(ids[0], rgvarg, rgdispidNamedArgs);

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        //Remember to release OleAutomation Object
        webBrowser.dispose();
        display.dispose();
    }
    
}
