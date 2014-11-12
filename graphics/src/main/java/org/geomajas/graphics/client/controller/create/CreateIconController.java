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

import org.geomajas.graphics.client.render.shape.MarkerShape;

/**
 * Controller that creates a {@link org.geomajas.graphics.client.object.GIcon}.
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 *
 */
public interface CreateIconController {


	void createIconInContainer(String iconUrl, MarkerShape selectedMarkerShape);
}
