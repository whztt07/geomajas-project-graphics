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
package org.geomajas.graphics.client.object.updateable.anchored;

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.Graphics;
import org.geomajas.graphics.client.object.BaseGraphicsObject;
import org.geomajas.graphics.client.object.role.Cloneable;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.render.RenderableList;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Implementation of {@link Anchored} role.
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 *
 */
public class Marker extends BaseGraphicsObject implements Strokable, Fillable {

	private AnchorMarker anchor;

	private AnchorMarker background;

	private MarkerShape markerShape = MarkerShape.SQUARE;

	private RenderableList markerGroup;

	public Marker(Coordinate markerPosition, MarkerShape markerShape) {
		this.markerShape = (markerShape != null) ? markerShape : MarkerShape.SQUARE;
		anchor = createAnchor(this.markerShape.getMarkerShape());
		markerGroup = Graphics.getRenderElementFactory().createRenderableList();
		// add a transparant but clickable background object in case of Cross Markershape
		// The cross itself is not clickable/draggable enough
		if (this.markerShape.equals(MarkerShape.CROSS)) {
			background = MarkerShape.SQUARE.getMarkerShape();
			background.setFillOpacity(0);
			background.setStrokeOpacity(0);
			markerGroup.addRenderable(background);
		}
		markerGroup.addRenderable(anchor);
		setPosition(markerPosition);

		addRole(Strokable.TYPE, this);
		addRole(Fillable.TYPE, this);
	}

	public void setPosition(Coordinate markerPosition) {
		anchor.setUserX(markerPosition.getX());
		anchor.setUserY(markerPosition.getY());
		if (background != null) {
			background.setUserX(markerPosition.getX());
			background.setUserY(markerPosition.getY());
		}
	}

	public Coordinate getPosition() {
		return new Coordinate(anchor.getUserX(), anchor.getUserY());
	}

	public MarkerShape getMarkerShape() {
		return markerShape;
	}

	protected AnchorMarker createAnchor(AnchorMarker shape) {
		if (shape != null && shape instanceof Cloneable) {
			anchor = (AnchorMarker) ((Cloneable) shape).cloneObject();
		} else {
			// standard marker shape: rectangle
			anchor =  Graphics.getRenderElementFactory().createAnchoredRectangle(0, 0, 8, 8, 4, 4);
		}
		anchor.setFixedSize(true);
		anchor.setFillColor("#FF6600");
		anchor.setStrokeColor("#FF6600");
		anchor.setFillOpacity(0.7);
		return anchor;
	}

	@Override
	public Object cloneObject() {
		return null;
	}

	@Override
	public void setOpacity(double opacity) {
		setFillOpacity(opacity);
		setStrokeOpacity(opacity);
	}

	//------------------------
	// fillable methods
	//------------------------

	@Override
	public void setFillColor(String fillColor) {
		anchor.setFillColor(fillColor);
	}

	@Override
	public void setFillOpacity(double fillOpacity) {
		anchor.setFillOpacity(fillOpacity);
	}

	@Override
	public String getFillColor() {
		return anchor.getFillColor();
	}

	@Override
	public double getFillOpacity() {
		return anchor.getFillOpacity();
	}

	//------------------------
	// strokable methods
	//------------------------

	@Override
	public String getStrokeColor() {
		return anchor.getStrokeColor();
	}

	@Override
	public void setStrokeColor(String strokeColor) {
		anchor.setStrokeColor(strokeColor);
	}

	@Override
	public int getStrokeWidth() {
		return anchor.getStrokeWidth();
	}

	@Override
	public void setStrokeWidth(int strokeWidth) {
		anchor.setStrokeWidth(strokeWidth);
	}

	@Override
	public double getStrokeOpacity() {
		return anchor.getStrokeOpacity();
	}

	@Override
	public void setStrokeOpacity(double strokeOpacity) {
		anchor.setStrokeOpacity(strokeOpacity);
	}

	//------------------------
	// render
	//------------------------

	@Override
	public VectorObject asObject() {
		return markerGroup.asObject();
	}

	public void setVisible(boolean visible) {
		anchor.setVisible(visible);
	}
}
