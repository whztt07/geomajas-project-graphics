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
package org.geomajas.graphics.client.render;

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.object.base.BasePath;
import org.geomajas.graphics.client.render.shape.CoordinatePathShape;
import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Extention of {@link org.geomajas.graphics.client.object.base.BasePath} for an unclosed path SVG object (a line),
 * with a pointer event area with a (stroke)width  value of at least
 * {@link org.geomajas.graphics.client.object.GPath.ResizablePathLine#getPointerEventAreaminimumWidth()} ()}.
 * This value can be customized.
 * Implementation details: a second transparant line object of the minimum width is combined with the
 * visible line object as a vector group.
 * {@link org.geomajas.graphics.client.object.GPath.ResizablePathLine#asObject()} returns this vecor group.
 *
 * @author Jan Venstermans
 */
public class BasePathLine extends BasePath {

	/**
	 * Transparant helper line object, still generating pointer events .
	 */
	private CoordinatePathShape clickArea;

	private Group group;

	/**
	 * minimum mouse event buffer.
	 */
	private static int pointerEventAreaminimumWidth = 10;

	public BasePathLine(double x, double y) {
		this(new Coordinate[]{new Coordinate(x, y)});
	}

	public BasePathLine(Coordinate coordinate) {
		this(new Coordinate[] { coordinate });
	}

	public BasePathLine(Coordinate[] coordinates) {
		super(coordinates, false);
		group = new Group();
		group.add(((VectorObject) path));
		clickArea = new CoordinatePathShape(coordinates, false);
		clickArea.setStrokeWidth(pointerEventAreaminimumWidth);
		clickArea.setStrokeOpacity(0);  // makes it invisible, but mouse events will still be registered
		group.add(clickArea);
	}

	@Override
	public VectorObject asObject() {
		return group;
	}

	@Override
	public void setCoordinates(Coordinate[] coordinates) {
		super.setCoordinates(coordinates);
		clickArea.setCoordinates(coordinates);
	}

	@Override
	public void addCoordinate(Coordinate coordinate) {
		super.addCoordinate(coordinate);
		clickArea.addCoordinate(coordinate);
	}

	@Override
	public void moveCoordinate(Coordinate coordinate, int index) {
		super.moveCoordinate(coordinate, index);
		clickArea.moveCoordinate(coordinate, index);
	}

	@Override
	public void setUserPosition(Coordinate position) {
		super.setUserPosition(position);
		clickArea.setUserPosition(position);
	}

	public static int getPointerEventAreaminimumWidth() {
		return pointerEventAreaminimumWidth;
	}

	public static void setPointerEventAreaminimumWidth(int pointerEventAreaminimumWidth) {
		BasePathLine.pointerEventAreaminimumWidth = pointerEventAreaminimumWidth;
	}
}