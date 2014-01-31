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

import org.geomajas.graphics.client.object.ExternalLabel;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.role.ExternalizableLabeled;
import org.geomajas.graphics.client.operation.ToggleExternalizableLabelOperation;
import org.geomajas.graphics.client.resource.GraphicsResource;

/**
 * Action to delete a {@link GraphicsObject}.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public class ToggleLabelAction extends AbstractAction {

	@Override
	protected String getDefaultLabel() {
		return GraphicsResource.MESSAGES.actionLabelToggleLabel();
	}

	@Override
	public boolean supports(GraphicsObject object) {
		return (object.hasRole(ExternalizableLabeled.TYPE) 
				&& object.getRole(ExternalizableLabeled.TYPE) instanceof ExternalizableLabeled) ||
				object instanceof ExternalLabel;
	}

	@Override
	public void execute(GraphicsObject object) {
		if (object instanceof ExternalLabel) {
			ExternalLabel exLabel = (ExternalLabel) object;
			object = (GraphicsObject) exLabel.getExternalizableLabeled().getMasterObject();
		}
		getService().execute(new ToggleExternalizableLabelOperation(object));
	}
}
