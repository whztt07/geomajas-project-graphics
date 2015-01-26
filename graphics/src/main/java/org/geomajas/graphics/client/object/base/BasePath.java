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
package org.geomajas.graphics.client.object.base;

import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.Graphics;
import org.geomajas.graphics.client.object.BaseGraphicsObject;
import org.geomajas.graphics.client.object.role.CoordinateBased;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.render.CoordinatePath;
import org.geomajas.graphics.client.util.CopyUtil;
import org.geomajas.graphics.client.util.FlipState;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Extension of {@link BaseGraphicsObject} for a path.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 *
 */
public class BasePath extends BaseGraphicsObject implements Resizable, Draggable, Fillable,
		Strokable, CoordinateBased {

	protected CoordinatePath path;

	public BasePath(double x, double y, boolean closed) {
		this(new Coordinate[]{new Coordinate(x, y)}, closed);
	}

	public BasePath(Coordinate coordinate, boolean closed) {
		this(new Coordinate[] { coordinate }, closed);
	}

	public BasePath(Coordinate[] coordinates, boolean closed) {
		this(Graphics.getRenderElementFactory().createCoordinatePath(coordinates, closed));
	}

	public BasePath(CoordinatePath coordinatePath) {
		path = coordinatePath;
		addRole(Strokable.TYPE, this);
		addRole(Resizable.TYPE, this);
		addRole(CoordinateBased.TYPE, this);
		addRole(Draggable.TYPE, this);
		if (path.isClosed()) {
			addRole(Fillable.TYPE, this);
		}
	}

	@Override
	public Coordinate getLastCoordinate() {
		return path.getLastCoordinate();
	}

	@Override
	public void setCoordinates(Coordinate[] coordinates) {
		path.setCoordinates(coordinates);
	}

	@Override
	public void addCoordinate(Coordinate coordinate) {
		path.addCoordinate(coordinate);
	}

	@Override
	public void moveCoordinate(Coordinate coordinate, int index) {
		path.moveCoordinate(coordinate, index);
	}

	@Override
	public int getCoordinateCount() {
		return path.getCoordinateCount();
	}

	public Coordinate[] getCoordinates() {
		return path.getCoordinates();
	}

	public boolean isClosed() {
		return path.isClosed();
	}

	@Override
	public void flip(FlipState state) {
		// TODO flip coordinates and - for FLIP_X, FLIP_Y - reverse coordinate order of closed paths
		switch (state) {
			case FLIP_X:
				break;
			case FLIP_XY:
				break;
			case FLIP_Y:
				break;
			case NONE:
				break;
		}
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
	public void setUserBounds(Bbox bounds) {
		path.setUserBounds(bounds);
	}

	@Override
	public Bbox getUserBounds() {
		return path.getUserBounds();
	}

	@Override
	public Bbox getBounds() {
		return path.getBounds();
	}

	@Override
	public void setUserPosition(Coordinate position) {
		path.setUserPosition(position);
	}

	@Override
	public Coordinate getUserPosition() {
		return path.getUserPosition();
	}

	@Override
	public VectorObject asObject() {
		if (path instanceof VectorObject) {
			return (VectorObject) path;
		}
		return null;
	}

	@Override
	public Object cloneObject() {
		BasePath copy = new BasePath(CopyUtil.deepCopyCoordinates(getCoordinates()), path.isClosed());
		CopyUtil.copyResizableProperties(getRole(Resizable.TYPE), copy.getRole(Resizable.TYPE));
		CopyUtil.copyDraggableProperties(getRole(Draggable.TYPE), copy.getRole(Draggable.TYPE));
		CopyUtil.copyStrokableProperties(getRole(Strokable.TYPE), copy.getRole(Strokable.TYPE));
		if (hasRole(Fillable.TYPE) && copy.hasRole(Fillable.TYPE)) {
			CopyUtil.copyFillableProperties(getRole(Fillable.TYPE), copy.getRole(Fillable.TYPE));
		}
		return copy;
	}

	@Override
	public void setFillColor(String fillColor) {
		path.setFillColor(fillColor);
	}

	@Override
	public void setFillOpacity(double fillOpacity) {
		path.setFillOpacity(fillOpacity);
	}

	@Override
	public String getFillColor() {
		return path.getFillColor();
	}

	@Override
	public double getFillOpacity() {
		return path.getFillOpacity();
	}

	@Override
	public String getStrokeColor() {
		return path.getStrokeColor();
	}

	@Override
	public void setStrokeColor(String strokeColor) {
		path.setStrokeColor(strokeColor);
	}

	@Override
	public int getStrokeWidth() {
		return path.getStrokeWidth();
	}

	@Override
	public void setStrokeWidth(int strokeWidth) {
		path.setStrokeWidth(strokeWidth);
	}

	@Override
	public double getStrokeOpacity() {
		return path.getStrokeOpacity();
	}

	@Override
	public void setStrokeOpacity(double strokeOpacity) {
		path.setStrokeOpacity(strokeOpacity);
	}

	@Override
	public void setOpacity(double opacity) {
		setFillOpacity(opacity);
		setStrokeOpacity(opacity);
	}
}