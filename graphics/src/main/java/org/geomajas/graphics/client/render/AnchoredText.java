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
package org.geomajas.graphics.client.render;

import org.geomajas.graphics.client.util.HasFill;
import org.geomajas.graphics.client.util.HasFont;
import org.geomajas.graphics.client.util.HasStroke;

/**
 * A non-scaling text that is anchored to its world space location on a specific pixel or anchor location (useful for
 * location markers).
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public interface AnchoredText extends HasStroke, HasFill, HasFont {

	void setUserX(double userX);

	void setUserY(double userY);

	double getUserX();

	double getUserY();

	String getText();

	double getAnchorX();

	double getAnchorY();

	void setText(String label);

	double getUserWidth();

	double getUserHeight();

	int getX();

	int getY();

	int getTextHeight();

	int getTextWidth();

}