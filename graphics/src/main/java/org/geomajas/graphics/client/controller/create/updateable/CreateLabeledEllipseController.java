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

import org.geomajas.geometry.Bbox;
import org.geomajas.graphics.client.controller.create.CreateObjectByRectangleController;
import org.geomajas.graphics.client.object.updateable.LabeledEllipse;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * Controller that creates a {@link org.geomajas.graphics.client.object.GEllipse}.
 *
 * @author Jan Venstermans
 *
 */
public class CreateLabeledEllipseController extends CreateObjectByRectangleController<LabeledEllipse> {

	public CreateLabeledEllipseController(GraphicsService graphicsService) {
		super(graphicsService);
	}

	@Override
	protected LabeledEllipse createObjectWithoutBounds() {
		LabeledEllipse labeledEllipse = new LabeledEllipse(new Bbox(), "Ellipse");
		return labeledEllipse;
	}
}