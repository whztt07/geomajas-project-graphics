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
package org.geomajas.graphics.client.controller;

import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * {@link GraphicsControllerWithVisibleElement} implementation of {@link UpdateHandlerGraphicsController}.
 *
 * @author Jan De Moerloose
 *
 */
public abstract class UpdateHandlerGraphicsControllerWithVisibleElement extends UpdateHandlerGraphicsController
		implements GraphicsControllerWithVisibleElement {

	public UpdateHandlerGraphicsControllerWithVisibleElement(GraphicsService graphicsService, GraphicsObject object) {
		super(graphicsService, object);
	}

	@Override
	public void setControllerElementsVisible(boolean visible) {
		getHandlerGroup().setOpacity(visible ? 1 : 0);
	}
	
}
