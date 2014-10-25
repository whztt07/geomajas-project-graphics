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

import org.geomajas.graphics.client.controller.create.base.CreateBasePathController;
import org.geomajas.graphics.client.object.base.BasePath;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.updateable.LabeledPath;
import org.geomajas.graphics.client.operation.AddOperation;
import org.geomajas.graphics.client.service.GraphicsService;
import org.geomajas.graphics.client.util.CopyUtil;

/**
 * Controller that creates a {@link org.geomajas.graphics.client.object.GRectangle}.
 *
 * @author Jan Venstermans
 *
 */

public class CreateLabeledPathController extends CreateBasePathController {

	public CreateLabeledPathController(GraphicsService graphicsService, boolean closedPath) {
		super(graphicsService, closedPath);
	}

	@Override
	protected void addObject(BasePath path) {
		LabeledPath labeledPath = new LabeledPath(path.getCoordinates(), path.isClosed(),
				path.isClosed() ? "Polygon" : "Line");
		if (labeledPath.hasRole(Fillable.TYPE) && path.hasRole(Fillable.TYPE)) {
			CopyUtil.copyProperties(path.getRole(Fillable.TYPE), labeledPath.getRole(Fillable.TYPE));
		}
		execute(new AddOperation(labeledPath));
	}
}