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
package org.geomajas.graphics.client.render.shape;

import java.util.ArrayList;
import java.util.List;

import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.geometry.service.BboxService;
import org.geomajas.graphics.client.render.CoordinatePath;
import org.vaadin.gwtgraphics.client.Shape;
import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.shape.Path;
import org.vaadin.gwtgraphics.client.shape.path.ClosePath;
import org.vaadin.gwtgraphics.client.shape.path.LineTo;
import org.vaadin.gwtgraphics.client.shape.path.MoveTo;
import org.vaadin.gwtgraphics.client.shape.path.PathStep;
import org.vaadin.gwtgraphics.client.shape.path.ScaleHelper;

/**
 * Path based on coordinate array.
 * 
 * @author Jan De Moerloose
 * 
 */
public class CoordinatePathShape extends Shape implements CoordinatePath {

	private List<PathStep> steps = new ArrayList<PathStep>();

	private boolean closed;

	private Coordinate[] coordinates;

	private Bbox bounds;

	public CoordinatePathShape(boolean closed) {
		this(new Coordinate[] { new Coordinate(), new Coordinate() }, closed);
	}

	public CoordinatePathShape(Coordinate[] coordinates, boolean closed) {
		this.closed = closed;
		if (!closed) {
			// for lines: clicking the fill area (or bounding box) does not trigger pointer events
			setFillColor("none");
		}
		setCoordinates(coordinates);
	}

	@Override
	protected Class<? extends VectorObject> getType() {
		return Path.class;
	}

	@Override
	public void setCoordinates(Coordinate[] coordinates) {
		this.coordinates = coordinates;
		updateCoordinates();
	}

	@Override
	public Coordinate[] getCoordinates() {
		return coordinates;
	}

	@Override
	public Coordinate getLastCoordinate() {
		return (Coordinate) coordinates[coordinates.length - 1].clone();
	}

	@Override
	public void addCoordinate(Coordinate coordinate) {
		Coordinate[] newCoords = new Coordinate[coordinates.length + 1];
		System.arraycopy(coordinates, 0, newCoords, 0, coordinates.length);
		newCoords[coordinates.length] = coordinate;
		setCoordinates(newCoords);
	}

	@Override
	public void moveCoordinate(Coordinate coordinate, int index) {
		if (index < coordinates.length) {
			coordinates[index] = (Coordinate) coordinate.clone();
		}
		setCoordinates(coordinates);
	}

	@Override
	public int getCoordinateCount() {
		return coordinates.length;
	}

	@Override
	public void setUserPosition(Coordinate position) {
		double x = coordinates[0].getX();
		double y = coordinates[0].getY();
		double dX = position.getX() - x;
		double dY = position.getY() - y;
		for (int i = 0; i < coordinates.length; i++) {
			coordinates[i].setX(coordinates[i].getX() + dX);
			coordinates[i].setY(coordinates[i].getY() + dY);
		}
		setCoordinates(coordinates);
	}

	@Override
	public Coordinate getUserPosition() {
		return (Coordinate) coordinates[0].clone();
	}

	@Override
	public void setUserBounds(Bbox bounds) {
		double width = this.bounds.getWidth();
		double height = this.bounds.getHeight();

		double newWidth = bounds.getWidth();
		double newHeight = bounds.getHeight();

		double scaleX = width == 0 ? 1 : newWidth / width;
		double scaleY = height == 0 ? 1 : newHeight / height;

		double x = this.bounds.getX();
		double y = this.bounds.getY();

		double newX = bounds.getX();
		double newY = bounds.getY();

		for (int i = 0; i < coordinates.length; i++) {
			coordinates[i].setX(newX + scaleX * (coordinates[i].getX() - x));
			coordinates[i].setY(newY + scaleY * (coordinates[i].getY() - y));
		}
		updateCoordinates();
	}

	@Override
	public Bbox getUserBounds() {
		return this.bounds;
	}

	@Override
	public Bbox getBounds() {
		// transform all points and calculate new bounds
		Bbox userBounds = getUserBounds();
		double x1 = userBounds.getX() * getScaleX() + getDeltaX();
		double y1 = userBounds.getY() * getScaleY() + getDeltaY();
		double x2 = (getUserX() + userBounds.getWidth()) * getScaleX() + getDeltaX();
		double y2 = (getUserY() + userBounds.getHeight()) * getScaleY() + getDeltaY();
		return new Bbox(Math.round(Math.min(x1, x2)), Math.round(Math.min(y1, y2)), Math.abs(x1 - x2),
				Math.abs(y1 - y2));
	}

	@Override
	public boolean isClosed() {
		return closed;
	}

	protected List<PathStep> calcSteps() {
		List<PathStep> result = new ArrayList<PathStep>();
		Coordinate prev = coordinates[0];
		result.add(new MoveTo(false, prev.getX(), prev.getY()));
		for (int i = 1; i < coordinates.length; i++) {
			Coordinate next = coordinates[i];
			result.add(new LineTo(true, next.getX() - prev.getX(), next.getY() - prev.getY()));
			prev = next;
		}
		if (closed) {
			result.add(new ClosePath());
		}
		return result;
	}

	protected Bbox calcBounds() {
		Coordinate c = coordinates[0];
		Bbox result = new Bbox(c.getX(), c.getY(), 0, 0);
		for (int i = 1; i < coordinates.length; i++) {
			c = coordinates[i];
			result = BboxService.union(result, new Bbox(c.getX(), c.getY(), 0, 0));
		}
		return result;
	}

	protected void drawTransformed() {
		// apply translation
		MoveTo moveTo = (MoveTo) steps.get(0);
		steps.set(0, new MoveTo(moveTo.isRelativeCoords(), moveTo.getUserX() + getDeltaX(), moveTo.getUserY()
				+ getDeltaY()));
		// apply scale
		ScaleHelper scaleHelper = new ScaleHelper(getScaleX(), getScaleY());
		for (PathStep step : steps) {
			step.scale(scaleHelper);
		}
		getImpl().drawPath(getElement(), steps);
	}

	private void updateCoordinates() {
		this.bounds = calcBounds();
		this.steps = calcSteps();
		setUserX(coordinates[0].getX());
		setUserY(coordinates[0].getX());
		drawTransformed();
	}
}
