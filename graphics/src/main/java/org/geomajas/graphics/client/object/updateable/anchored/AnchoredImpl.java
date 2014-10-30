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
import org.geomajas.geometry.service.BboxService;
import org.geomajas.graphics.client.object.BaseGraphicsObject;
import org.geomajas.graphics.client.object.Resizable;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.object.updateable.AnchoredUpdateable;
import org.geomajas.graphics.client.shape.MarkerShape;
import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Extension of {@link org.geomajas.graphics.client.object.updateable.UpdateableGroupGraphicsObject}
 * that shows an anchor linked to a {@link org.geomajas.graphics.client.object.base.BaseIcon}.
 *
 * @author Jan Venstermans
 *
 */
public class AnchoredImpl extends BaseGraphicsObject implements AnchoredUpdateable {

	private Group rootGroup = new Group();

	private Marker marker;

	private TwoPointsLine anchorLine;

	private Resizable resizable; // subject

	public AnchoredImpl(Resizable resizable, Coordinate anchorPosition, MarkerShape markerShape) {
		this.resizable = resizable;

		// create base graphics objects
		marker = new Marker(anchorPosition, markerShape);
		anchorLine = new TwoPointsLine(BboxService.getCenterPoint(resizable.getUserBounds()), anchorPosition);

		// register roles of group object
		addRole(AnchoredUpdateable.TYPE, this);

		// register render order
		rootGroup.add(anchorLine.asObject());
		rootGroup.add(marker.asObject());
	}

	@Override
	public Object cloneObject() {
		AnchoredImpl clone = new AnchoredImpl(resizable, marker.getPosition(), marker.getMarkerShape());
		return clone;
	}

	//---------------------------------
	// render section
	//---------------------------------

	@Override
	public VectorObject asObject() {
		return rootGroup;
	}

	@Override
	public void setOpacity(double opacity) {
		rootGroup.setOpacity(opacity);
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
		Coordinate anchoredPosition = BboxService.getCenterPoint(resizable.getUserBounds());
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
