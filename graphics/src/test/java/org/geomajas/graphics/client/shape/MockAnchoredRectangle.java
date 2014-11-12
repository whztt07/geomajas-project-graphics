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

import org.geomajas.graphics.client.render.AnchoredRectangle;

public class MockAnchoredRectangle implements AnchoredRectangle {

	private double userX;
	private double userY;
	private double userWidth;
	private double userHeight;
	private double fillOpacity;
	private String fillColor;
	private String strokeColor;
	private int strokeWidth;
	private double strokeOpacity;
	private int x;
	private int y;
	private int width;
	private int height;

	@Override
	public void setUserX(double userX) {
		this.userX = userX;
	}

	@Override
	public void setUserY(double userY) {
		this.userY = userY;
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
	public double getUserWidth() {
		return userWidth;
	}

	@Override
	public double getUserHeight() {
		return userHeight;
	}

	@Override
	public void setUserWidth(double width) {
		this.userWidth = width;
	}

	@Override
	public void setUserHeight(double height) {
		this.userHeight = height;
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
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	// stroke

	@Override
	public String getStrokeColor() {
		return strokeColor;
	}

	@Override
	public void setStrokeColor(String color) {
		this.strokeColor = color;
	}

	@Override
	public int getStrokeWidth() {
		return strokeWidth;
	}

	@Override
	public void setStrokeWidth(int width) {
		 this.strokeWidth = width;
	}

	@Override
	public double getStrokeOpacity() {
		return strokeOpacity;
	}

	@Override
	public void setStrokeOpacity(double fillOpacity) {
		strokeOpacity = fillOpacity;
	}

	// fill

	@Override
	public double getFillOpacity() {
		return fillOpacity;
	}

	@Override
	public void setFillOpacity(double opacity) {
		fillOpacity = opacity;
	}

	@Override
	public String getFillColor() {
		return fillColor;
	}

	@Override
	public void setFillColor(String color) {
		this.fillColor = color;
	}

	// other

	@Override
	public Object cloneObject() {
		return null;
	}
}
