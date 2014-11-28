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
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.resource.GraphicsResource;
import org.geomajas.graphics.client.service.objectcontainer.GraphicsObjectContainer;

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
		GraphicsObject clone = (GraphicsObject) object.cloneObject();
		if (clone.hasRole(Resizable.TYPE)) {
			Bbox bounds = clone.getRole(Resizable.TYPE).getUserBounds();
			clone.getRole(Resizable.TYPE).setUserBounds(translateUserBounds(bounds));
		} else if (clone.hasRole(Draggable.TYPE)) {
			Coordinate position = clone.getRole(Draggable.TYPE).getUserPosition();
			clone.getRole(Draggable.TYPE).setUserPosition(translateUserCoordinate(position));
		}
		getService().getObjectContainer().add(clone);
	}

	private Coordinate translateUserCoordinate(Coordinate userPosition) {
		return getService().getObjectContainer().transform(
				new Coordinate(userPosition.getX() + 40, userPosition.getY() + 10),
				GraphicsObjectContainer.Space.SCREEN, GraphicsObjectContainer.Space.USER);
	}

	private Bbox translateUserBounds(Bbox userBounds) {
		Bbox newUserBounds = new Bbox(userBounds.getX(), userBounds.getY(), userBounds.getWidth(),
				userBounds.getHeight());
		Coordinate screenLowLeftPosition = getService().getObjectContainer().transform(
				new Coordinate(userBounds.getX(), userBounds.getY()),
				GraphicsObjectContainer.Space.USER, GraphicsObjectContainer.Space.SCREEN);
		Coordinate newLowLeftPosition = translateUserCoordinate(screenLowLeftPosition);
		newUserBounds.setX(newLowLeftPosition.getX());
		newUserBounds.setY(newLowLeftPosition.getY());
		return newUserBounds;
	}
}
