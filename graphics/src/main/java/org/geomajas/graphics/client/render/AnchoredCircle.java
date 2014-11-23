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
package org.geomajas.graphics.client.render;

/**
 * A non-scaling text that is anchored to its world space location on a specific pixel or anchor location (useful for
 * location markers).
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public interface AnchoredCircle {

	void setUserX(double x);

	void setUserY(double y);

	double getUserX();

	double getUserY();

	double getUserRadius();

	void setUserRadius(double v);

	int getX();

	int getRadius();

	int getY();

	void setFillOpacity(double opacity);

	void setStrokeOpacity(double opacity);
}