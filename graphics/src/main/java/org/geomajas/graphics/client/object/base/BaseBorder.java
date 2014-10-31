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

import org.geomajas.graphics.client.object.BaseGraphicsObject;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.shape.StyledRectangle;
import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.shape.Rectangle;

/**
 * Implementation of {@link org.geomajas.graphics.client.object.Bordered}
 * role for {@link org.geomajas.graphics.client.object.role.Resizable} objects.
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 */
public class BaseBorder extends BaseGraphicsObject implements Fillable, Strokable {

	private Rectangle borderRect;

	public BaseBorder() {
		borderRect = new StyledRectangle(0, 0, 0, 0);
		borderRect.setFillColor("#CCFF99");
		borderRect.setStrokeColor("#CCCC99");
		addRole(Fillable.TYPE, this);
		addRole(Strokable.TYPE, this);
	}

	public void setFixedSize(boolean fixedSize) {
		borderRect.setFixedSize(fixedSize);
	}

	public boolean isFixedSize() {
		return borderRect.isFixedSize();
	}

	@Override
	public VectorObject asObject() {
		return borderRect;
	}

	@Override
	public String getStrokeColor() {
		return borderRect.getStrokeColor();
	}

	@Override
	public void setStrokeColor(String strokeColor) {
		borderRect.setStrokeColor(strokeColor);
	}

	@Override
	public int getStrokeWidth() {
		return borderRect.getStrokeWidth();
	}

	@Override
	public void setStrokeWidth(int strokeWidth) {
		borderRect.setStrokeWidth(strokeWidth);
	}

	@Override
	public double getStrokeOpacity() {
		return borderRect.getStrokeOpacity();
	}

	@Override
	public void setStrokeOpacity(double strokeOpacity) {
		borderRect.setStrokeOpacity(strokeOpacity);
	}

	@Override
	public void setFillColor(String fillColor) {
		borderRect.setFillColor(fillColor);
	}

	@Override
	public void setFillOpacity(double fillOpacity) {
		borderRect.setFillOpacity(fillOpacity);
	}

	@Override
	public String getFillColor() {
		return borderRect.getFillColor();
	}

	@Override
	public double getFillOpacity() {
		return borderRect.getFillOpacity();
	}

	@Override
	public Object cloneObject() {
		BaseBorder baseBorder = new BaseBorder();
		return baseBorder;
	}

	@Override
	public void setOpacity(double opacity) {
	   setFillOpacity(opacity);
	   setStrokeOpacity(opacity);
	}
}
