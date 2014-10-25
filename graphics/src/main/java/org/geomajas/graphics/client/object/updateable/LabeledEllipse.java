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

import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.service.BboxService;
import org.geomajas.graphics.client.object.Draggable;
import org.geomajas.graphics.client.object.Resizable;
import org.geomajas.graphics.client.object.base.BaseEllipse;
import org.geomajas.graphics.client.object.labeler.LabeledImpl;
import org.geomajas.graphics.client.object.updateable.wrapper.DraggableWrapperForUpdateable;
import org.geomajas.graphics.client.object.updateable.wrapper.ResizableWrapperForUpdateable;
import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Extension of {@link UpdateableGroupGraphicsObject} that shows a text centered on a {@link BaseEllipse}.
 *
 * @author Jan Venstermans
 *
 */
public class LabeledEllipse extends UpdateableGroupGraphicsObject {

	private Group rootGroup = new Group();

	private BaseEllipse baseEllipse;

	private LabeledImpl labeled;

	public LabeledEllipse(Bbox ellipseBounds, String text) {
		this(BboxService.getCenterPoint(ellipseBounds).getX(), BboxService.getCenterPoint(ellipseBounds).getY(),
				ellipseBounds.getWidth() / 2, ellipseBounds.getHeight() / 2, text);
	}

	public LabeledEllipse(double ellipseCenterX, double ellipseCenterY,
						  double userRadiusX, double userRadiusY, String text) {
		// create base graphics objects
		baseEllipse = new BaseEllipse(ellipseCenterX, ellipseCenterY, userRadiusX, userRadiusY);
		labeled = new LabeledImpl(baseEllipse, text);

		// register updateables
		addUpdateable(labeled);

		// register roles of group object
		addRole(Resizable.TYPE, new ResizableWrapperForUpdateable(baseEllipse, this));
		addRole(Draggable.TYPE, new DraggableWrapperForUpdateable(baseEllipse, this));
		addRole(LabeledUpdateable.TYPE, labeled);

		// register render order
		rootGroup.add(baseEllipse.asObject());
		rootGroup.add(labeled.asObject());
	}

	@Override
	public Object cloneObject() {
		return new LabeledEllipse(baseEllipse.getUserBounds(), labeled.getTextable().getLabel());
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
