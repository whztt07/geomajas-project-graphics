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
package org.geomajas.graphics.client.object.role.cache;


import org.geomajas.graphics.client.object.role.Fillable;

/**
 * Implementation of {@link Fillable} for containing properties.
 *
 * @author Jan Venstermans
 *
 */
public class FillableCache implements Fillable {

	private double fillOpacity;
	private String fillColor;

	@Override
	public double getFillOpacity() {
		return fillOpacity;
	}

	@Override
	public void setFillOpacity(double fillOpacity) {
		this.fillOpacity = fillOpacity;
	}

	@Override
	public String getFillColor() {
		return fillColor;
	}

	@Override
	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}
}
