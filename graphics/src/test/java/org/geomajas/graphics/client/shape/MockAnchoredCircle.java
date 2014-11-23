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

import org.geomajas.graphics.client.render.AnchoredCircle;

public class MockAnchoredCircle implements AnchoredCircle {

	private double userX;
	private double userY;
	private double userRadius;
	private int x;
	private int y;
	private int radius;
	private double fillOpacity;
	private double strokeOpacity;

	@Override
	public void setUserX(double x) {
		this.userX = x;
	}

	@Override
	public void setUserY(double y) {
		this.userY = y;
	}

	@Override
	public double getUserX() {
		return userX;
	}

	@Override
	public double getUserY() {
		return userY;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setFillOpacity(double opacity) {
		this.fillOpacity = opacity;
	}

	@Override
	public void setStrokeOpacity(double opacity) {
	   this.strokeOpacity = opacity;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getFillOpacity() {
		return fillOpacity;
	}

	public double getStrokeOpacity() {
		return strokeOpacity;
	}

	public double getUserRadius() {
		return userRadius;
	}

	public void setUserRadius(double userRadius) {
		this.userRadius = userRadius;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}


