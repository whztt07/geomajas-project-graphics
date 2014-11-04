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
package org.geomajas.graphics.client.controller.drag;

import org.geomajas.graphics.client.controller.GraphicsController;
import org.geomajas.graphics.client.controller.RoleGraphicsControllerFactory;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * Factory for the {@link DragController}.
 * 
 * @author Jan De Moerloose
 * 
 */
public class DragControllerFactory extends RoleGraphicsControllerFactory<Draggable, DragController> {
	
	private boolean dragOnActivate;
	
	public DragControllerFactory() {
		this(true);
	}

	public DragControllerFactory(boolean dragOnActivate) {
		super(Draggable.TYPE);
		this.dragOnActivate = dragOnActivate;
	}

	@Override
	public DragController createController(GraphicsService graphicsService, GraphicsObject object) {
		return new DragController(object, graphicsService, dragOnActivate);
	}

}
