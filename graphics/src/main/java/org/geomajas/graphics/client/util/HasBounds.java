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


import org.geomajas.geometry.Bbox;

/**
 * Interface with getters and setters of bounds attributes.
 * 
 * @author Jan Venstermans
 * 
 */
public interface HasBounds {

	void setUserBounds(Bbox bounds);

	/**
	 * Get the bounds in screen space.
	 *
	 * @return the bounds in screen space
	 */
	Bbox getUserBounds();

	/**
	 * Get the bounds in worls space.
	 *
	 * @return the bounds in world space
	 */
	Bbox getBounds();
}