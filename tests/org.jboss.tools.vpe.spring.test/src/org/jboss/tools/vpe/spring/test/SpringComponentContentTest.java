/*******************************************************************************
  * Copyright (c) 2007-2010 Red Hat, Inc.
  * Distributed under license by Red Hat, Inc. All rights reserved.
  * This program is made available under the terms of the
  * Eclipse Public License v1.0 which accompanies this distribution,
  * and is available at http://www.eclipse.org/legal/epl-v10.html
  *
  * Contributor:
  *     Red Hat, Inc. - initial API and implementation
  ******************************************************************************/
package org.jboss.tools.vpe.spring.test;

import org.jboss.tools.vpe.ui.test.ComponentContentTest;

/**
 * Tests for the context that was generated by Spring templates
 * 
 * @author dmaliarevich
 */
public class SpringComponentContentTest extends ComponentContentTest {

	/**
	 * The Constructor
	 * 
	 * @param name a test case name
	 */
	public SpringComponentContentTest(String name) {
		super(name);
		setCheckWarning(false);
	}

	public void testCheckbox() throws Throwable {
		performContentTestByFullPath("src/main/webapp/WEB-INF/jsp/checkbox.jsp"); //$NON-NLS-1$
	}
	
	public void testCheckboxes() throws Throwable {
		performContentTestByFullPath("src/main/webapp/WEB-INF/jsp/checkboxes.jsp"); //$NON-NLS-1$
	}
	
	public void testRadiobutton() throws Throwable {
		performContentTestByFullPath("src/main/webapp/WEB-INF/jsp/radiobutton.jsp"); //$NON-NLS-1$
	}
	
	public void testRadiobuttons() throws Throwable {
		performContentTestByFullPath("src/main/webapp/WEB-INF/jsp/radiobuttons.jsp"); //$NON-NLS-1$
	}
	
	public void testOption() throws Throwable {
		performContentTestByFullPath("src/main/webapp/WEB-INF/jsp/option.jsp"); //$NON-NLS-1$
	}
	
	public void testOptions() throws Throwable {
		performContentTestByFullPath("src/main/webapp/WEB-INF/jsp/options.jsp"); //$NON-NLS-1$
	}

	public void testErrors() throws Throwable {
		performContentTestByFullPath("src/main/webapp/WEB-INF/jsp/errors.jsp"); //$NON-NLS-1$
	}

	public void testHasBindErrors() throws Throwable {
		performContentTestByFullPath("src/main/webapp/WEB-INF/jsp/hasBindErrors.jsp"); //$NON-NLS-1$
	}

	public void testBind() throws Throwable {
		performContentTestByFullPath("src/main/webapp/WEB-INF/jsp/bind.jsp"); //$NON-NLS-1$
	}

	public void testNestedPath() throws Throwable {
		performContentTestByFullPath("src/main/webapp/WEB-INF/jsp/nestedPath.jsp"); //$NON-NLS-1$
	}
	
	public void testSelect() throws Throwable {
		performContentTestByFullPath("src/main/webapp/WEB-INF/jsp/select.jsp"); //$NON-NLS-1$
	}
	
	public void testHidden() throws Throwable {
		performInvisibleTagTestByFullPath("src/main/webapp/WEB-INF/jsp/hidden.jsp", "hidden"); //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	public void testInput() throws Throwable {
		performContentTestByFullPath("src/main/webapp/WEB-INF/jsp/input.jsp"); //$NON-NLS-1$
	}
	
	public void testPassword() throws Throwable {
		performContentTestByFullPath("src/main/webapp/WEB-INF/jsp/password.jsp"); //$NON-NLS-1$
	}
	
	public void testLabel() throws Throwable {
		performContentTestByFullPath("src/main/webapp/WEB-INF/jsp/label.jsp"); //$NON-NLS-1$
	}
	
	public void testTextarea() throws Throwable {
		performContentTestByFullPath("src/main/webapp/WEB-INF/jsp/textarea.jsp"); //$NON-NLS-1$
	}
	
	public void testMessage() throws Throwable {
		performContentTestByFullPath("src/main/webapp/WEB-INF/jsp/message.jsp"); //$NON-NLS-1$
	}
	
	public void testTheme() throws Throwable {
		performContentTestByFullPath("src/main/webapp/WEB-INF/jsp/theme.jsp"); //$NON-NLS-1$
	}
	
	public void testEscapeBody() throws Throwable {
		performContentTestByFullPath("src/main/webapp/WEB-INF/jsp/escapeBody.jsp"); //$NON-NLS-1$
	}
	
	public void testEval() throws Throwable {
		performContentTestByFullPath("src/main/webapp/WEB-INF/jsp/eval.jsp"); //$NON-NLS-1$
	}
	
	@Override
	protected String getTestProjectName() {
		return SpringAllTests.IMPORT_PROJECT_NAME;
	}

}
