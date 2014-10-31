/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2014 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the Apache
 * License, Version 2.0. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */
package org.geomajas.graphics.client.action;

import org.geomajas.geometry.Bbox;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.resource.GraphicsResource;

/**
 * Action to duplicate a {@link GraphicsObject}.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public class DuplicateAction extends AbstractAction {

	@Override
	protected String getDefaultLabel() {
		return GraphicsResource.MESSAGES.actionLabelDuplicate();
	}

	@Override
	public boolean supports(GraphicsObject object) {
		return true;
	}

	@Override
	public void execute(GraphicsObject object) {
		GraphicsObject object2 = (GraphicsObject) object.cloneObject();
		if (object2.hasRole(Resizable.TYPE)) {
			Bbox bounds = object2.getRole(Resizable.TYPE).getUserBounds();
			bounds.setX(bounds.getX() + 10.0);
			bounds.setY(bounds.getY() + 10.0);
			object2.getRole(Resizable.TYPE).setUserBounds(bounds);
		} else {
			object2.asObject().setTranslation(10.0, 10.0);
		}
		getService().getObjectContainer().add(object2);
	}
}
