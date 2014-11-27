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
package org.geomajas.graphics.client.object.updateable.anchored;

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.Graphics;
import org.geomajas.graphics.client.object.BaseGraphicsObject;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.render.CoordinatePath;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Implementation of {@link BaseGraphicsObject} for a line with two points.
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 *
 */
public class TwoPointsLine extends BaseGraphicsObject implements Strokable {

	/**
	 * Different dashing possibilities of Anchor lines.
	 * This is based on the dashArray string attribute of Strokeable Shape objects.
	 * for use and result of a dashed shapes:
	 * https://developer.mozilla.org/en-US/docs/Web/SVG/Attribute/stroke-dasharray
	 *
	 * @author Jan Venstermans
	 *
	 */
	public enum LineDashStyle {
		STRAIGHT(""), // straight line
		DASH_EQUAL_5("5 5"); // dashed line, equidistant line and space

		private String dashArray;

		/**
		 * Constructor.
		 *
		 * @param dashArray
		 *            code to apply
		 */
		private LineDashStyle(String dashArray) {
			this.dashArray = dashArray;
		}

		public String toString() {
			return dashArray;
		}
	}

	private CoordinatePath coordinatePath;

	public TwoPointsLine(Coordinate coordinate1, Coordinate coordinate2) {
		coordinatePath = Graphics.getRenderElementFactory().createCoordinatePath(new Coordinate[0], false);
		addRole(Strokable.TYPE, this);
		setCoordinates((coordinate1 != null) ? coordinate1 : new Coordinate(),
				(coordinate2 != null) ? coordinate2 : new Coordinate());
	}

	public void setCoordinates(Coordinate coordinate1, Coordinate coordinate2) {
		coordinatePath.setCoordinates(new Coordinate[]{coordinate1, coordinate2});
	}

	public void setLineDashStyle(LineDashStyle dashStyle) {
		coordinatePath.setDashArray(dashStyle.toString());
	}


	@Override
	public Object cloneObject() {
		return new TwoPointsLine(coordinatePath.getCoordinates()[0], coordinatePath.getCoordinates()[1]);
	}

	@Override
	public void setOpacity(double opacity) {
		setStrokeOpacity(opacity);
	}

	@Override
	public String getStrokeColor() {
		return coordinatePath.getStrokeColor();
	}

	@Override
	public void setStrokeColor(String strokeColor) {
		coordinatePath.setStrokeColor(strokeColor);
	}

	@Override
	public int getStrokeWidth() {
		return coordinatePath.getStrokeWidth();
	}

	@Override
	public void setStrokeWidth(int strokeWidth) {
		coordinatePath.setStrokeWidth(strokeWidth);
	}

	@Override
	public double getStrokeOpacity() {
		return coordinatePath.getStrokeOpacity();
	}

	@Override
	public void setStrokeOpacity(double strokeOpacity) {
		coordinatePath.setStrokeOpacity(strokeOpacity);
	}

	@Override
	public VectorObject asObject() {
		return coordinatePath.asObject();
	}

	private Coordinate getCoordinate1() {
		return coordinatePath.getCoordinates()[0];
	}
}
