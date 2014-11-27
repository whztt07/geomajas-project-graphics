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
package org.geomajas.graphics.client.object.updateable.anchored;

import org.geomajas.graphics.client.render.Renderable;
import org.geomajas.graphics.client.util.HasFill;
import org.geomajas.graphics.client.util.HasStroke;

/**
 * Interface for marker's shape.
 * 
 * @author Jan Venstermans
 * 
 */
public interface AnchorMarker extends HasFill, HasStroke, Renderable {

	void setFixedSize(boolean fixedSize);

	void setVisible(boolean visible);

	double getUserX();

	double getUserY();

	void setUserX(double userX);

	void setUserY(double userY);
}
