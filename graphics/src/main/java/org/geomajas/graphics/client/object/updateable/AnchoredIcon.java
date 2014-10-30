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
package org.geomajas.graphics.client.object.updateable;

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.object.Draggable;
import org.geomajas.graphics.client.object.base.BaseIcon;
import org.geomajas.graphics.client.object.updateable.anchored.AnchoredImpl;
import org.geomajas.graphics.client.object.updateable.wrapper.DraggableWrapperForUpdateable;
import org.geomajas.graphics.client.shape.MarkerShape;
import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Extension of {@link UpdateableGroupGraphicsObject}
 * that shows an anchor linked to a {@link org.geomajas.graphics.client.object.base.BaseIcon}.
 *
 * @author Jan Venstermans
 *
 */
public class AnchoredIcon extends UpdateableGroupGraphicsObject {

	private Group rootGroup = new Group();

	private BaseIcon baseIcon;

	private AnchoredImpl anchored;

	public AnchoredIcon(Coordinate iconCoordinate, int iconWidth, int iconHeight,
						String iconHref, Coordinate anchorCoordinate, MarkerShape markerShape) {
		// create base graphics objects
		baseIcon = new BaseIcon(iconCoordinate.getX(), iconCoordinate.getY(), iconWidth, iconHeight, iconHref);
		anchored = new AnchoredImpl(baseIcon, anchorCoordinate, markerShape);

		// register updateables
		addUpdateable(anchored);

		// register roles of group object
		addRole(Draggable.TYPE, new DraggableWrapperForUpdateable(baseIcon, this));
		addRole(AnchoredUpdateable.TYPE, anchored);

		// register render order
		rootGroup.add(anchored.asObject());
		rootGroup.add(baseIcon.asObject());
	}

	@Override
	public Object cloneObject() {
		AnchoredIcon clone = new AnchoredIcon(baseIcon.getPosition(), (int) baseIcon.getBounds().getWidth(),
				(int) baseIcon.getBounds().getHeight(), baseIcon.getHref(), anchored.getAnchorPosition(),
				anchored.getMarkerShape());
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
}
