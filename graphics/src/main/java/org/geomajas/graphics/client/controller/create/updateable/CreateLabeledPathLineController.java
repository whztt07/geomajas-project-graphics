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
package org.geomajas.graphics.client.controller.create.updateable;

import org.geomajas.graphics.client.controller.create.base.CreateBasePathLineController;
import org.geomajas.graphics.client.object.base.BasePathLine;
import org.geomajas.graphics.client.object.updateable.LabeledPath;
import org.geomajas.graphics.client.operation.AddOperation;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * Controller that creates a {@link org.geomajas.graphics.client.object.GRectangle}.
 *
 * @author Jan Venstermans
 *
 */

public class CreateLabeledPathLineController extends CreateBasePathLineController {

	public CreateLabeledPathLineController(GraphicsService graphicsService) {
		super(graphicsService);
	}

	@Override
	protected void addObject(BasePathLine path) {
		LabeledPath labeledPath = new LabeledPath(path.getCoordinates(), false, "Line");
		execute(new AddOperation(labeledPath));
	}
}