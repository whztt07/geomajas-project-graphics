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
package org.geomajas.graphics.client.shape;

import org.geomajas.graphics.client.object.role.Cloneable;

/**
 * A non-scaling rectangle that is anchored to its world space location on a specific pixel or anchor location (useful
 * for location markers).
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public interface AnchoredRectangle extends Cloneable {

	void setUserX(double userX);

	void setUserY(double userY);

	double getUserX();

	double getUserY();

	double getUserWidth();

	double getUserHeight();

	void setUserWidth(double width);

	void setUserHeight(double height);

	int getX();

	int getY();

	int getWidth();

	int getHeight();

	String getFillColor();

	void setFillColor(String color);

	double getFillOpacity();

	void setFillOpacity(double opacity);

	String getStrokeColor();

	void setStrokeColor(String color);

	int getStrokeWidth();

	void setStrokeWidth(int width);

	double getStrokeOpacity();

	void setStrokeOpacity(double opacity);
}