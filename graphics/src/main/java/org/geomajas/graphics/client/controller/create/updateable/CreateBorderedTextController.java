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

import org.geomajas.graphics.client.controller.create.base.CreateBaseTextController;
import org.geomajas.graphics.client.object.base.BaseText;
import org.geomajas.graphics.client.object.updateable.BorderedText;
import org.geomajas.graphics.client.operation.AddOperation;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * Controller that creates a {@link org.geomajas.graphics.client.object.base.BaseText}.
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 *
 */
public class CreateBorderedTextController extends CreateBaseTextController {

	public CreateBorderedTextController(GraphicsService graphicsService) {
		super(graphicsService);
	}

	@Override
	protected void addObject(BaseText result) {
		if (result == null) {
			execute(null);
			return;
		}
		BorderedText borderedText = new BorderedText(result.getUserX(), result.getUserY(), result.getLabel(), 10);
		execute(new AddOperation(borderedText));
	}
}
