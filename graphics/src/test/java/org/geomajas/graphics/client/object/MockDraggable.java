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
package org.geomajas.graphics.client.object;

import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.util.GraphicsUtil;
import org.vaadin.gwtgraphics.client.VectorObject;

public class MockDraggable extends BaseGraphicsObject implements Draggable {

	private Coordinate position;

	private Bbox bounds;

	public MockDraggable(Coordinate position, Bbox bounds) {
		setPosition(position);
		setUserBounds(bounds);
		addRole(Draggable.TYPE, this);
	}

	@Override
	public void setPosition(Coordinate position) {
		this.position = (Coordinate) position.clone();
	}

	@Override
	public Coordinate getPosition() {
		return new Coordinate(position);
	}

	@Override
	public void setOpacity(double opacity) {
	}

	@Override
	public GraphicsObject cloneObject() {
		return null;
	}

	@Override
	public VectorObject asObject() {
		return null;
	}

	public void setUserBounds(Bbox bounds) {
		this.bounds = GraphicsUtil.clone(bounds);
	}

	@Override
	public Bbox getUserBounds() {
		return GraphicsUtil.clone(bounds);
	}

	@Override
	public Bbox getBounds() {
		return null;
	}
}
