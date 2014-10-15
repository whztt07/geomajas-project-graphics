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

public class MockAnchoredText implements AnchoredText {

	private double userX;
	private double userY;
	private double anchorX;
	private double anchorY;
	private double userWidth;
	private double userHeight;
	private double fillOpacity;
	private double strokeOpacity;
	private int strokeWidth;
	private String text;
	private String fillColor;
	private String fillFamily;
	private int fontSize;
	private int textHeight;
	private int textWidth;
	private String fontFamily;
	private String strokeColor;

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
	public String getText() {
		return text;
	}

	@Override
	public double getAnchorX() {
		return anchorX;
	}

	@Override
	public double getAnchorY() {
		return anchorY;
	}

	@Override
	public int getStrokeWidth() {
		return strokeWidth;
	}

	@Override
	public String getFillColor() {
		return fillColor;
	}

	@Override
	public String getFontFamily() {
		return fillFamily;
	}

	@Override
	public int getFontSize() {
		return fontSize;
	}

	@Override
	public void setText(String label) {
	   this.text = label;
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
	public int getX() {
		return 0;
	}

	@Override
	public int getY() {
		return 0;
	}

	@Override
	public int getTextHeight() {
		return textHeight;
	}

	@Override
	public int getTextWidth() {
		return textWidth;
	}

	@Override
	public void setFillColor(String color) {
		this.fillColor = color;
	}

	@Override
	public void setStrokeColor(String color) {
		this.strokeColor = color;
	}

	@Override
	public void setFontSize(int size) {
		 this.fontSize = size;
	}

	@Override
	public void update() {

	}

	@Override
	public void setFontFamily(String font) {
	   this.fontFamily = font;
	}

	@Override
	public void setStrokeWidth(int width) {
		 this.strokeWidth = width;
	}

	@Override
	public double getFillOpacity() {
		return fillOpacity;
	}

	@Override
	public void setStrokeOpacity(double fillOpacity) {
		strokeOpacity = fillOpacity;
	}

	@Override
	public void setFillOpacity(double opacity) {
		fillOpacity = opacity;
	}
}
