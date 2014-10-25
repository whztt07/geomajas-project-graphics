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
package org.geomajas.graphics.client.controller.create;

import org.geomajas.graphics.client.object.GEllipse;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * Controller that creates a {@link GEllipse}.
 * 
 * @author Jan De Moerloose
 * 
 */
public class CreateEllipseController extends CreateBoundedObjectController<GEllipse> {

	public CreateEllipseController(GraphicsService graphicsService) {
		super(graphicsService);
	}

	@Override
	protected GEllipse createObjectWithoutBounds() {
		GEllipse ellipse = new GEllipse(0, 0, 0, 0);
		return ellipse;
	}
}