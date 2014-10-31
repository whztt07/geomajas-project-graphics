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
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.util.FlipState;
import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.shape.Circle;

/**
 * Extension of {@link BaseGraphicsObject} for a circle.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 */
public class BaseCircle extends BaseGraphicsObject implements Resizable, Draggable {

	private Circle circle;

	public BaseCircle(double x, double y, double radius) {
		this(new Circle(x, y, radius));
	}

	public BaseCircle(Circle circle) {
		this.circle = circle;
		addRole(Resizable.TYPE, this);
		addRole(Draggable.TYPE, this);
	}

	@Override
	public void setPosition(Coordinate position) {
		circle.setUserX(position.getX());
		circle.setUserY(position.getY());
	}

	@Override
	public Coordinate getPosition() {
		return new Coordinate(circle.getUserX(), circle.getUserY());
	}

	public Object cloneObject() {
		Circle mask = new Circle(circle.getUserX(), circle.getUserY(), circle.getUserRadius());
		return new BaseCircle(mask);
	}

	@Override
	public void flip(FlipState state) {
		// symmetric
	}

	@Override
	public void setUserBounds(Bbox bounds) {
		Coordinate center = BboxService.getCenterPoint(bounds);
		circle.setUserX(center.getX());
		circle.setUserY(center.getY());
		circle.setUserRadius(bounds.getWidth() / 2);
	}

	@Override
	public boolean isPreserveRatio() {
		return true;
	}

	@Override
	public boolean isAutoHeight() {
		return false;
	}

	@Override
	public Bbox getUserBounds() {
		return new Bbox(circle.getUserX() - circle.getUserRadius(), circle.getUserY() - circle.getUserRadius(),
				2 * circle.getUserRadius(), 2 * circle.getUserRadius());
	}

	@Override
	public Bbox getBounds() {
		return new Bbox(circle.getX() - circle.getRadius(), circle.getY() - circle.getRadius(),
				2 * circle.getRadius(), 2 * circle.getRadius());
	}

	@Override
	public VectorObject asObject() {
		return circle;
	}


	@Override
	public void setOpacity(double opacity) {
		circle.setFillOpacity(opacity);
		circle.setStrokeOpacity(opacity);
	}
}
