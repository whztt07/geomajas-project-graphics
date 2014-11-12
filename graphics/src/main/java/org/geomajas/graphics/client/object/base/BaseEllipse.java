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
import org.geomajas.geometry.service.BboxService;
import org.geomajas.graphics.client.Graphics;
import org.geomajas.graphics.client.object.BaseGraphicsObject;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.render.AnchoredEllipse;
import org.geomajas.graphics.client.util.FlipState;
import org.vaadin.gwtgraphics.client.VectorObject;


/**
 * Extension of {@link BaseGraphicsObject} for a ellipse.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public class BaseEllipse extends BaseGraphicsObject implements Resizable, Draggable {

	private AnchoredEllipse anchoredEllipse;

	public BaseEllipse(Bbox boundingBox) {
		this(BboxService.getCenterPoint(boundingBox), boundingBox.getWidth() / 2, boundingBox.getHeight() / 2);
	}

	public BaseEllipse(Coordinate ellipseCenter, double userRadiusX, double userRadiusY) {
		this(ellipseCenter.getX(), ellipseCenter.getY(), userRadiusX, userRadiusY);
	}

	public BaseEllipse(double ellipseCenterX, double ellipseCenterY, double userRadiusX, double userRadiusY) {
		this(Graphics.getRenderElementFactory().
				createEllipse(ellipseCenterX, ellipseCenterY, userRadiusX, userRadiusY));
	}

	public BaseEllipse(AnchoredEllipse anchoredEllipse) {
		this.anchoredEllipse = anchoredEllipse;
		addRole(Resizable.TYPE, this);
		addRole(Draggable.TYPE, this);
	}

	@Override
	public void setUserPosition(Coordinate position) {
		anchoredEllipse.setUserX(position.getX());
		anchoredEllipse.setUserY(position.getY());
	}

	@Override
	public Coordinate getUserPosition() {
		return new Coordinate(anchoredEllipse.getUserX(), anchoredEllipse.getUserY());
	}

	public Object cloneObject() {
		BaseEllipse mask = new BaseEllipse(anchoredEllipse.getUserX(),
				anchoredEllipse.getUserY(), anchoredEllipse.getUserRadiusX(), anchoredEllipse.getUserRadiusY());
		return mask;
	}

	@Override
	public void flip(FlipState state) {
		// symmetric
	}

	@Override
	public void setUserBounds(Bbox bounds) {
		setUserPosition(BboxService.getCenterPoint(bounds));
		anchoredEllipse.setUserRadiusX(bounds.getWidth() / 2);
		anchoredEllipse.setUserRadiusY(bounds.getHeight() / 2);
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
	public Bbox getUserBounds() {
		return new Bbox(anchoredEllipse.getUserX() - anchoredEllipse.getUserRadiusX(), anchoredEllipse.getUserY()
				- anchoredEllipse.getUserRadiusY(), 2 * anchoredEllipse.getUserRadiusX(),
				2 * anchoredEllipse.getUserRadiusY());
	}

	@Override
	public Bbox getBounds() {
		return new Bbox(anchoredEllipse.getX() - anchoredEllipse.getRadiusX(),
				anchoredEllipse.getY() - anchoredEllipse.getRadiusY(),
				2 * anchoredEllipse.getRadiusX(), 2 * anchoredEllipse.getRadiusY());
	}

	@Override
	public VectorObject asObject() {
		if (anchoredEllipse instanceof VectorObject) {
			return (VectorObject) anchoredEllipse;
		}
		return null;
	}


	@Override
	public void setOpacity(double opacity) {
		anchoredEllipse.setFillOpacity(opacity);
		anchoredEllipse.setStrokeOpacity(opacity);
	}

}
