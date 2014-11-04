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
package org.geomajas.graphics.client.object.base;

import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.Graphics;
import org.geomajas.graphics.client.object.BaseGraphicsObject;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.object.role.Textable;
import org.geomajas.graphics.client.shape.AnchoredText;
import org.geomajas.graphics.client.util.FlipState;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Extension of {@link BaseGraphicsObject} for a text.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 */
public class BaseText extends BaseGraphicsObject implements Draggable, Textable, Resizable {

	// centered around userX, userY
	private AnchoredText text;

	//-------------------------------
	// Constructors
	//-------------------------------

	public AnchoredText getAnchor() {
		return text;
	}

	public BaseText(double userX, double userY, String text) {
		this(Graphics.getShapeCreationManager().createAnchoredText(userX, userY, text, 0.5, 0.5));
	}

	public BaseText(AnchoredText text) {
		this(text, true);
	}

	public BaseText(AnchoredText text, boolean setDefaultFontStyle) {
		this.text = text;
		if (setDefaultFontStyle) {
			text.setStrokeWidth(0);
			text.setStrokeColor(text.getFillColor());
			text.setStrokeOpacity(text.getFillOpacity());
			text.setFillColor("black");
			text.setFillOpacity(1.0);
		}
		addRole(Textable.TYPE, this);
		addRole(Draggable.TYPE, this);
	}

	@Override
	public VectorObject asObject() {
		if (text instanceof VectorObject) {
			return (VectorObject) text;
		}
		return null;
	}

	@Override
	public void setPosition(Coordinate position) {
		text.setUserX(position.getX());
		text.setUserY(position.getY());
	}

	@Override
	public Coordinate getPosition() {
		double userX = text.getUserX();
		double userY = text.getUserY();
		return new Coordinate(userX, userY);
	}

	@Override
	public Object cloneObject() {
		AnchoredText clone = Graphics.getShapeCreationManager().createAnchoredText(
				text.getUserX(), text.getUserY(), text.getText(), text.getAnchorX(), text.getAnchorY());
		clone.setStrokeWidth(text.getStrokeWidth());
		clone.setFillColor(text.getFillColor()); // this is font color
		clone.setFontFamily(text.getFontFamily());
		clone.setFontSize(text.getFontSize());
		return new BaseText(clone, false);
	}

	@Override
	public void setLabel(String label) {
		text.setText(label);
	}

	@Override
	public String getLabel() {
		return text.getText();
	}

	public double getUserX() {
		return text.getUserX();
	}

	public double getUserY() {
		return text.getUserY();
	}

	@Override
	public void flip(FlipState state) {
		// no-op
	}

	@Override
	public void setUserBounds(Bbox bounds) {
		// no-op
	}

	@Override
	public Bbox getUserBounds() {
		double userX = text.getUserX() - text.getAnchorX() * text.getUserWidth();
		double userY = text.getUserY() - text.getAnchorY() * text.getUserHeight();
		double userWidth = text.getUserWidth();
		double userHeight = text.getUserHeight();
		Bbox box = new Bbox(userX, userY, userWidth, userHeight);
		return box;
	}

	@Override
	public Bbox getBounds() {
		// y is lower-left !!!
		return new Bbox(text.getX(), text.getY() - text.getTextHeight(), text.getTextWidth(), text.getTextHeight());
	}

	@Override
	public boolean isPreserveRatio() {
		return false;
	}

	@Override
	public boolean isAutoHeight() {
		return false;
	}

	@Override
	public void setFontColor(String color) {
		text.setFillColor(color);
		text.setStrokeColor(color);
	}

	@Override
	public String getFontColor() {
		return text.getFillColor();
	}

	@Override
	public void setFontSize(int size) {
		text.setFontSize(size);
		text.update();
	}

	@Override
	public int getFontSize() {
		return text.getFontSize();
	}

	@Override
	public void setFontFamily(String font) {
		text.setFontFamily(font);
		text.update();
	}

	@Override
	public String getFontFamily() {
		return text.getFontFamily();
	}

	@Override
	public void setOpacity(double opacity) {

	}
}
