/*******************************************************************************
 * Copyright (c) 2007 Exadel, Inc. and Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Exadel, Inc. and Red Hat, Inc. - initial API and implementation
 ******************************************************************************/ 
package org.jboss.tools.vpe.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import junit.framework.TestCase;
import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.runtime.ContributorFactoryOSGi;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.RegistryFactory;
import org.jboss.tools.common.reporting.IProblemReporter;
import org.jboss.tools.common.reporting.ProblemReporterFactory;
import org.jboss.tools.vpe.editor.template.VpeTemplateManager;
import org.osgi.framework.Bundle;

/**
 * This class created for testing templates expression.
 * 
 * @author Max Areshkau
 */
public class TemplatesExpressionParsingTest extends TestCase {

	private static final String PLUGIN_FAILURE_NAME = "testFailure-plugin.xml"; //$NON-NLS-1$

	private static final String PLUGIN_OK_NAME = "testOk-plugin.xml"; //$NON-NLS-1$

	private static final String EXTENSION_POINT_ID = "org.jboss.tools.vpe.templates"; //$NON-NLS-1$

	private static final String EXTENSION_ERROR_EXTENSION_ID_1 = "org.jboss.tools.vpe.tests.failureExtensions"; //$NON-NLS-1$

	private static final String EXTENSION_ERROR_EXTENSION_ID_2 = "org.jboss.tools.vpe.tests.okExtensions"; //$NON-NLS-1$

	private IStatus iStatus = null;

	private int errorNumber = 0;

	private VpeTemplateManager vpeTemplateManager;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		// sets problem reporter
		ProblemReporterFactory reporter = ProblemReporterFactory.getInstance();
		reporter.setReporter(new IProblemReporter() {
			public void reportProblem(IStatus status) {
				errorNumber++;
				iStatus = status;
			}
		});
	}

	@Override
	protected void tearDown() throws Exception {
		removeExtension(EXTENSION_POINT_ID, EXTENSION_ERROR_EXTENSION_ID_1);
		removeExtension(EXTENSION_POINT_ID, EXTENSION_ERROR_EXTENSION_ID_2);
		iStatus=null;
		errorNumber = 0;
		super.tearDown();
	}

	/**
	 * Tests existing templates
	 * 
	 * @throws Exception
	 */
	public void testExistingTemplates() throws Exception {

		vpeTemplateManager = VpeTemplateManager.getInstance();
		vpeTemplateManager.reload();
		assertNull("Can not parse template from ReDHat DevStudio" + iStatus, iStatus); //$NON-NLS-1$
		assertEquals("There exist some errors", 0, errorNumber); //$NON-NLS-1$
		assertNotNull(vpeTemplateManager);
	}

	/**
	 * Creates test template and testing possible(without errors) variants
	 * 
	 * @throws Exception
	 */
	public void testCorrectTemplates() throws Exception {
		createTemplatesForTesting(PLUGIN_OK_NAME);
		vpeTemplateManager = VpeTemplateManager.getInstance();
		vpeTemplateManager.reload();
		assertNull("Can not parse template from ReDHat DevStudio" + iStatus, iStatus); //$NON-NLS-1$
		assertEquals("There exist some errors", 0, errorNumber); //$NON-NLS-1$
		return;
	}
	
	/**
	 * Creates test template and testing that extensions with errors have errors
	 * variants
	 * 
	 * @throws Exception
	 */
	public void testIncorrectTemplates() throws Exception {
		createTemplatesForTesting(PLUGIN_FAILURE_NAME);
		vpeTemplateManager = VpeTemplateManager.getInstance();
		errorNumber = 0;
		vpeTemplateManager.reload();
		assertEquals("Number founds error is Incorrect ", 5, errorNumber); //$NON-NLS-1$
		assertNotNull("Can not parse template from ReDHat DevStudio" + iStatus, //$NON-NLS-1$
				iStatus);
		return;
	}


	/**
	 * Tests passible template
	 * 
	 * @throws Exception
	 */
	private void createTemplatesForTesting(String pluginXmlFileName)
			throws Exception {
		IPath iPath = getFullpathForConfigurationElement(pluginXmlFileName,	null);
		File file = iPath.toFile();
		FileInputStream is = new FileInputStream(file);
		IExtensionRegistry registry = RegistryFactory.getRegistry();
		Object key = ((ExtensionRegistry) registry).getTemporaryUserToken();
		Bundle bundle = VpeTestPlugin.getDefault().getBundle();
		IContributor contributor = ContributorFactoryOSGi
				.createContributor(bundle);
		registry.addContribution(is, contributor, false, null, null, key);
	}

	/**
	 * Get a full path for IConfigurationElement
	 * 
	 * @param fileName
	 *            a String contain relevant fileName
	 * @param confElement
	 *            a IConfigurationElement
	 * @return full path for IConfigurationElement
	 * @throws IOException
	 *             if an error occurs during the conversion
	 */
	private static IPath getFullpathForConfigurationElement(String name,
			IConfigurationElement confElement) throws IOException {
		// 1. get a shared instance
		VpeTestPlugin plugin = VpeTestPlugin.getDefault();

		Bundle bundle = null;

		if (confElement == null) {
			bundle = plugin.getBundle();
		} else {
			bundle = Platform.getBundle(confElement.getNamespaceIdentifier());
		}

		URL url = bundle.getEntry("/"); //$NON-NLS-1$

		IPath path = new Path(FileLocator.toFileURL(url).getFile());
		path = path.append(name);
		return path;
	}

	/**
	 * Removing extensions from eclipse
	 * 
	 * @param extensionPointId
	 * @param extensionId
	 */
	private void removeExtension(String extensionPointId, String extensionId) {
		// use Eclipse Dynamic Extension API
		IExtensionRegistry reg = RegistryFactory.getRegistry();
		Object token = ((ExtensionRegistry) reg).getTemporaryUserToken();
		IExtension extension = reg.getExtension(extensionPointId, extensionId);
		reg.removeExtension(extension, token);
	}
}
