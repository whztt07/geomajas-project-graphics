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
package org.geomajas.graphics.client.controller.create.base;

import org.geomajas.graphics.client.controller.create.CreateBoundedObjectController;
import org.geomajas.graphics.client.object.base.BaseEllipse;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * Controller that creates a {@link BaseEllipse}.
 *
 * @author Jan Venstermans
 *
 */
public class CreateBaseEllipseController extends CreateBoundedObjectController<BaseEllipse> {

	public CreateBaseEllipseController(GraphicsService graphicsService) {
		super(graphicsService);
	}

	@Override
	protected BaseEllipse createObjectWithoutBounds() {
		BaseEllipse ellipse = new BaseEllipse(0, 0, 0, 0);
		return ellipse;
	}
}