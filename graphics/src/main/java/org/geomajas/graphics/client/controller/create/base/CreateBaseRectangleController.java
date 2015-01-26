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
package org.geomajas.graphics.client.controller.create.base;

import org.geomajas.graphics.client.controller.create.CreateObjectByRectangleController;
import org.geomajas.graphics.client.object.base.BaseRectangle;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * Controller that creates a {@link BaseRectangle}.
 *
 * @author Jan Venstermans
 *
 */

public class CreateBaseRectangleController extends CreateObjectByRectangleController<BaseRectangle> {

	public CreateBaseRectangleController(GraphicsService graphicsService) {
		super(graphicsService);
	}

	@Override
	protected BaseRectangle createObjectWithoutBounds() {
		return new BaseRectangle(0, 0, 0, 0);
	}
}