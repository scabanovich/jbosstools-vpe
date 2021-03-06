/******************************************************************************* 
 * Copyright (c) 2007 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.jboss.tools.vpe.editor.template;

import org.mozilla.interfaces.nsIDOMNode;
import org.mozilla.interfaces.nsISelection;

/**
 * 
 * interface for template selection
 * 
 * @author Sergey Dzmitrovich
 */
public interface ISelectionManager {

	/**
	 * set selection
	 * 
	 * @param selection
	 */
	public void setSelection(nsIDOMNode visualNode, int focusOffset, int anchorOffset);

	/**
	 * to bring in correspondence visual selection and source selection
	 */
	public void refreshVisualSelection();
	
	/**
	 * Check the flag
	 */
	public boolean isUpdateSelectionEventPerformed();
	/**
	 * Set the flag indicating that source and visual selection is updating
	 */
	public void setUpdateSelectionEventFlag(boolean event);

}
