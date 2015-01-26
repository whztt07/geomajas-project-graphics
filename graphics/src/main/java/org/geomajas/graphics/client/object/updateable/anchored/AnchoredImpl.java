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
import org.geomajas.geometry.service.BboxService;
import org.geomajas.graphics.client.Graphics;
import org.geomajas.graphics.client.object.BaseGraphicsObject;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.object.updateable.Updateable;
import org.geomajas.graphics.client.render.RenderableList;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Implementation of {@link Anchored} with the {@link Updateable} interface.
 * that shows an anchor linked to a {@link org.geomajas.graphics.client.object.base.BaseIcon}.
 *
 * @author Jan Venstermans
 *
 */
public class AnchoredImpl extends BaseGraphicsObject implements Anchored, Updateable {

	private RenderableList renderableList;

	private Marker marker;

	private TwoPointsLine anchorLine;

	private Draggable draggable; // subject

	public AnchoredImpl(Draggable draggable, Coordinate anchorPosition, MarkerShape markerShape) {
		this.draggable = draggable;

		// create base graphics objects
		marker = new Marker(anchorPosition, markerShape);
		anchorLine = new TwoPointsLine(BboxService.getCenterPoint(draggable.getUserBounds()), anchorPosition);

		// register roles of group object
		addRole(Anchored.TYPE, this);

		// register render order
		renderableList = Graphics.getRenderElementFactory().createRenderableList();
		renderableList.addRenderable(anchorLine);
		renderableList.addRenderable(marker);
	}

	@Override
	public Object cloneObject() {
		AnchoredImpl clone = new AnchoredImpl(draggable, marker.getPosition(), marker.getMarkerShape());
		return clone;
	}

	//---------------------------------
	// render section
	//---------------------------------

	@Override
	public VectorObject asObject() {
		return renderableList.asObject();
	}

	@Override
	public void setOpacity(double opacity) {
		renderableList.setOpacity(opacity);
	}

	@Override
	public Strokable getAnchorLineStrokable() {
		return anchorLine.getRole(Strokable.TYPE);
	}

	@Override
	public void setAnchorLineDashStyle(TwoPointsLine.LineDashStyle dashStyle) {
		anchorLine.setLineDashStyle(dashStyle);
	}

	@Override
	public Strokable getAnchorMarkerShapeStrokable() {
		return marker.getRole(Strokable.TYPE);
	}

	@Override
	public Fillable getAnchorMarkerShapeFillable() {
		return marker.getRole(Fillable.TYPE);
	}

	@Override
	public void setAnchorPosition(Coordinate position) {
		marker.setPosition(position);
		onUpdate();
	}

	@Override
	public void onUpdate() {
		Coordinate anchoredPosition = BboxService.getCenterPoint(draggable.getUserBounds());
		anchorLine.setCoordinates(anchoredPosition, marker.getPosition());
	}

	@Override
	public Coordinate getAnchorPosition() {
		return marker.getPosition();
	}

	@Override
	public void setAnchorVisible(boolean visible) {
		marker.setVisible(visible);
	}

	@Override
	public MarkerShape getMarkerShape() {
		return marker.getMarkerShape();
	}
}
