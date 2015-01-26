/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2015 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the Apache
 * License, Version 2.0. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */

package org.geomajas.graphics.client.util.textbox;

import org.geomajas.graphics.client.resource.GraphicsResource;

/**
 * Extention of the {@link StringTextBoxValidator} for textBoxes where the String content must be an integer.
 * 
 * @author Jan Venstermans
 */
public class IntegerTextBoxValidator extends StringTextBoxValidator {
	
	@Override
	public boolean isValid() {
		validate();
		evaluateErrorMessage(GraphicsResource.MESSAGES.exceptionValidationIntegerIllegalArgument());
		return super.isValid();
	}
	
	@Override
	protected void validate() {
		setValid(validateInteger(getLabel()));
	}

	private boolean validateInteger(String label) {
		try {
			Integer.parseInt(label);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public int getInteger() {
		validate();
		if (super.isValid()) {
			return Integer.parseInt(getLabel());
		} else {
			return 0;
		}
	}
}