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
package org.geomajas.graphics.client.shape;

import org.geomajas.graphics.client.render.AnchoredText;

public class MockAnchoredText implements AnchoredText {

	//position
	private double userX;
	private double userY;
	private double anchorX;
	private double anchorY;
	private double userWidth;
	private double userHeight;
	private int x;
	private int y;

	//text
	private String text;
	private int textHeight;
	private int textWidth;
	//font
	private String fontFamily;
	private int fontSize;
	private String fontColor;
	// stroke
	private String strokeColor;
	private double strokeOpacity;
	private int strokeWidth;
	//fill
	private String fillColor;
	private double fillOpacity;

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
	public double getAnchorX() {
		return anchorX;
	}

	@Override
	public double getAnchorY() {
		return anchorY;
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
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	public void setAnchorX(double anchorX) {
		this.anchorX = anchorX;
	}

	public void setAnchorY(double anchorY) {
		this.anchorY = anchorY;
	}

	public void setUserWidth(double userWidth) {
		this.userWidth = userWidth;
	}

	public void setUserHeight(double userHeight) {
		this.userHeight = userHeight;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	//text

	@Override
	public int getTextHeight() {
		return textHeight;
	}

	@Override
	public int getTextWidth() {
		return textWidth;
	}

	@Override
	public String getText() {
		return text;
	}

	public void setTextHeight(int textHeight) {
		this.textHeight = textHeight;
	}

	public void setTextWidth(int textWidth) {
		this.textWidth = textWidth;
	}


	@Override
	public void setText(String label) {
		this.text = label;
	}


	// font

	@Override
	public String getFontFamily() {
		return fontFamily;
	}

	@Override
	public int getFontSize() {
		return fontSize;
	}

	@Override
	public void setFontColor(String color) {
		this.fontColor = color;
	}

	@Override
	public String getFontColor() {
		return fontColor;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
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
}
