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
import org.geomajas.graphics.client.object.BaseGraphicsObject;
import org.geomajas.graphics.client.object.Draggable;
import org.geomajas.graphics.client.object.Resizable;
import org.geomajas.graphics.client.util.FlipState;
import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.shape.Ellipse;

/**
 * Extension of {@link BaseGraphicsObject} for a ellipse.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public class BaseEllipse extends BaseGraphicsObject implements Resizable, Draggable {

	private Ellipse ellipse;

	public BaseEllipse(Bbox boundingBox) {
		this(BboxService.getCenterPoint(boundingBox), boundingBox.getWidth() / 2, boundingBox.getHeight() / 2);
	}

	public BaseEllipse(Coordinate ellipseCenter, double userRadiusX, double userRadiusY) {
		this(ellipseCenter.getX(), ellipseCenter.getY(), userRadiusX, userRadiusY);
	}

	public BaseEllipse(double ellipseCenterX, double ellipseCenterY, double userRadiusX, double userRadiusY) {
		this(new Ellipse(ellipseCenterX, ellipseCenterY, userRadiusX, userRadiusY));
	}

	public BaseEllipse(Ellipse ellipse) {
		this.ellipse = ellipse;
		addRole(Resizable.TYPE, this);
		addRole(Draggable.TYPE, this);
	}

	@Override
	public void setPosition(Coordinate position) {
		ellipse.setUserX(position.getX());
		ellipse.setUserY(position.getY());
	}

	@Override
	public Coordinate getPosition() {
		return new Coordinate(ellipse.getUserX(), ellipse.getUserY());
	}

	public Object cloneObject() {
		Ellipse mask = new Ellipse(ellipse.getUserX(), ellipse.getUserY(), ellipse.getUserRadiusX(),
				ellipse.getUserRadiusY());
		return new BaseEllipse(mask);
	}

	@Override
	public void flip(FlipState state) {
		// symmetric
	}

	@Override
	public void setUserBounds(Bbox bounds) {
		setPosition(BboxService.getCenterPoint(bounds));
		ellipse.setUserRadiusX(bounds.getWidth() / 2);
		ellipse.setUserRadiusY(bounds.getHeight() / 2);
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
		return new Bbox(ellipse.getUserX() - ellipse.getUserRadiusX(), ellipse.getUserY()
				- ellipse.getUserRadiusY(), 2 * ellipse.getUserRadiusX(), 2 * ellipse.getUserRadiusY());
	}

	@Override
	public Bbox getBounds() {
		return new Bbox(ellipse.getX() - ellipse.getRadiusX(), ellipse.getY() - ellipse.getRadiusY(),
				2 * ellipse.getRadiusX(), 2 * ellipse.getRadiusY());
	}

	@Override
	public VectorObject asObject() {
		return ellipse;
	}


	@Override
	public void setOpacity(double opacity) {
		ellipse.setFillOpacity(opacity);
		ellipse.setStrokeOpacity(opacity);
	}

}
