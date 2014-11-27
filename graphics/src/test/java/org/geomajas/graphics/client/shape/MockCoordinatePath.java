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

import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.render.CoordinatePath;
import org.vaadin.gwtgraphics.client.VectorObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockCoordinatePath implements CoordinatePath {

	boolean closed;
	private Coordinate userPosition;
	private Bbox userBounds;
	private Bbox bounds;
	private double fillOpacity;
	private String fillColor;
	private String strokeColor;
	private int strokeWidth;
	private double strokeOpacity;

	private List<Coordinate> coordinateList = new ArrayList<Coordinate>();

	@Override
	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	// coordinates

	@Override
	public Coordinate getLastCoordinate() {
		if (getCoordinateCount() > 0) {
			return coordinateList.get(coordinateList.size() - 1);
		}
		return null;
	}

	@Override
	public void setCoordinates(Coordinate[] coordinates) {
		coordinateList = Arrays.asList(coordinates);
	}

	@Override
	public void addCoordinate(Coordinate coordinate) {
	   coordinateList.add(coordinate);
	}

	@Override
	public void moveCoordinate(Coordinate coordinate, int index) {
		coordinateList.remove(coordinate);
	    coordinateList.add(index, coordinate);
	}

	@Override
	public int getCoordinateCount() {
		return coordinateList.size();
	}

	@Override
	public Coordinate[] getCoordinates() {
		return coordinateList.toArray(new Coordinate[getCoordinateCount()]);
	}

	// position and bounds

	@Override
	public void setUserPosition(Coordinate userPosition) {
		this.userPosition = userPosition;
	}

	@Override
	public Coordinate getUserPosition() {
		return userPosition;
	}

	@Override
	public void setDashArray(String dashArray) {

	}

	@Override
	public void setUserBounds(Bbox userBounds) {

		this.userBounds = userBounds;
	}

	@Override
	public Bbox getUserBounds() {
		return userBounds;
	}

	@Override
	public Bbox getBounds() {
		return bounds;
	}

	public void setBounds(Bbox bounds) {
		this.bounds = bounds;
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

	@Override
	public VectorObject asObject() {
		return null;
	}

	@Override
	public void setOpacity(double opacity) {

	}
}

