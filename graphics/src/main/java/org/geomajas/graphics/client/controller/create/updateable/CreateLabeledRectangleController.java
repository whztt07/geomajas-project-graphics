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
package org.geomajas.graphics.client.controller.create.updateable;

import org.geomajas.graphics.client.controller.create.CreateObjectByRectangleController;
import org.geomajas.graphics.client.object.updateable.LabeledRectangle;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * Controller that creates a {@link org.geomajas.graphics.client.object.GRectangle}.
 *
 * @author Jan Venstermans
 *
 */

public class CreateLabeledRectangleController extends CreateObjectByRectangleController<LabeledRectangle> {

	public CreateLabeledRectangleController(GraphicsService graphicsService) {
		super(graphicsService);
	}

	@Override
	protected LabeledRectangle createObjectWithoutBounds() {
		return new LabeledRectangle(0, 0, 0, 0, "Rectangle");
	}
}