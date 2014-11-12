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

import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.render.CoordinatePath;
import org.geomajas.graphics.client.render.Ellipse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockEllipse implements Ellipse {

	private double userX;
	private double userY;
	private double userRadiusX;
	private double userRadiusY;
	private int x;
	private int y;
	private int radiusX;
	private int radiusY;
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
	public double getUserRadiusX() {
		return userRadiusX;
	}

	@Override
	public double getUserRadiusY() {
		return userRadiusY;
	}

	@Override
	public void setUserRadiusX(double v) {
	    this.userRadiusX = v;
	}

	@Override
	public void setUserRadiusY(double v) {
		this.userRadiusY = v;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getRadiusX() {
		return radiusX;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getRadiusY() {
		return radiusY;
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

	public void setRadiusX(int radiusX) {
		this.radiusX = radiusX;
	}

	public void setRadiusY(int radiusY) {
		this.radiusY = radiusY;
	}

	public double getFillOpacity() {
		return fillOpacity;
	}

	public double getStrokeOpacity() {
		return strokeOpacity;
	}
}


