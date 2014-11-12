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
package org.geomajas.graphics.client.util;


/**
 * Interface with getters and setters of fill attributes.
 * 
 * @author Jan Venstermans
 * 
 */
public interface HasFill {

	void setFillColor(String fillColor);

	void setFillOpacity(double fillOpacity);

	String getFillColor();

	double getFillOpacity();
}