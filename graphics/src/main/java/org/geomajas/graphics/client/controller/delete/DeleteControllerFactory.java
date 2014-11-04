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
package org.geomajas.graphics.client.controller.delete;

import org.geomajas.graphics.client.controller.GraphicsControllerFactory;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * Factory for the {@link DeleteController}.
 * 
 * @author Jan De Moerloose
 * 
 */
public class DeleteControllerFactory implements GraphicsControllerFactory<DeleteController> {

	@Override
	public boolean supports(GraphicsObject object) {
		return true;
	}

	@Override
	public DeleteController createController(GraphicsService graphicsService, GraphicsObject object) {
		return new DeleteController(object, graphicsService);
	}

}
