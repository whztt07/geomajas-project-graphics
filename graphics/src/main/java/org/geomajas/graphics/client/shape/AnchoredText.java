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

/**
 * A non-scaling text that is anchored to its world space location on a specific pixel or anchor location (useful for
 * location markers).
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public interface AnchoredText {

	void setUserX(double userX);

	void setUserY(double userY);

	double getUserX();

	double getUserY();

	String getText();

	double getAnchorX();

	double getAnchorY();

	int getStrokeWidth();

	String getFillColor();

	String getFontFamily();

	int getFontSize();

	void setText(String label);

	double getUserWidth();

	double getUserHeight();

	int getX();

	int getY();

	int getTextHeight();

	int getTextWidth();

	void setFillColor(String color);

	void setStrokeColor(String color);

	void setFontSize(int size);

	void update();

	void setFontFamily(String font);

	void setStrokeWidth(int width);

	double getFillOpacity();

	void setStrokeOpacity(double fillOpacity);

	void setFillOpacity(double opacity);
}