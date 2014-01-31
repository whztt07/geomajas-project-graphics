/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2013 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the Apache
 * License, Version 2.0. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */
package org.geomajas.graphics.client.action;

import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.anchor.AnchoredTo;
import org.geomajas.graphics.client.object.anchor.ExternalLabelOfResizable;
import org.geomajas.graphics.client.operation.BringToFrontOperation;
import org.geomajas.graphics.client.resource.GraphicsResource;

/**
 * Action to delete a {@link GraphicsObject}.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public class BringToFrontAction extends AbstractAction {

	@Override
	protected String getDefaultLabel() {
		return GraphicsResource.MESSAGES.actionLabelBringToFront();
	}

	@Override
	public boolean supports(GraphicsObject object) {
		if (object.hasRole(AnchoredTo.TYPE) && object.getRole(AnchoredTo.TYPE) instanceof ExternalLabelOfResizable) {
			return false;
		}
		return true;
	}

	@Override
	public void execute(GraphicsObject object) {
		getService().execute(new BringToFrontOperation(object, getService()));
	}
}
