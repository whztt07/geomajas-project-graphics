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
package org.geomajas.graphics.client.util;


/**
 * Interface with getters and setters of stroke attributes.
 * 
 * @author Jan Venstermans
 * 
 */
public interface HasStroke {

	String getStrokeColor();

	void setStrokeColor(String strokeColor);

	int getStrokeWidth();

	void setStrokeWidth(int strokeWidth);

	double getStrokeOpacity();

	void setStrokeOpacity(double strokeOpacity);
}