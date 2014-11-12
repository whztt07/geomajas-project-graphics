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
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.object.base.BaseRectangle;
import org.geomajas.graphics.client.object.updateable.labeled.Labeled;
import org.geomajas.graphics.client.object.updateable.labeled.LabeledImpl;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.object.updateable.wrapper.DraggableWrapperForUpdateable;
import org.geomajas.graphics.client.object.updateable.wrapper.ResizableWrapperForUpdateable;
import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Extension of {@link UpdateableGroupGraphicsObject}
 * that shows a text centered on a {@link org.geomajas.graphics.client.object.base.BaseRectangle}.
 *
 * @author Jan Venstermans
 *
 */
public class LabeledRectangle extends UpdateableGroupGraphicsObject {

	private Group rootGroup = new Group();

	private BaseRectangle baseRectangle;

	private LabeledImpl labeled;

	public LabeledRectangle(double userX, double userY, double width, double height, String text) {
		// create base graphics objects
		baseRectangle = new BaseRectangle(userX, userY, width, height);
		labeled = new LabeledImpl(baseRectangle, text);

		// register updateables
		addUpdateable(labeled);

		// register roles of group object
		addRole(Strokable.TYPE, baseRectangle);
		addRole(Fillable.TYPE, baseRectangle);
		addRole(Resizable.TYPE, new ResizableWrapperForUpdateable(baseRectangle, this));
		addRole(Draggable.TYPE, new DraggableWrapperForUpdateable(baseRectangle, this));
		addRole(Labeled.TYPE, labeled);

		// register render order
		rootGroup.add(baseRectangle.asObject());
		rootGroup.add(labeled.asObject());
	}

	@Override
	public Object cloneObject() {
		Bbox userBounds = baseRectangle.getUserBounds();
		LabeledRectangle labeledRectangleClone = new LabeledRectangle(userBounds.getX(),
				userBounds.getY(), userBounds.getWidth(), userBounds.getHeight(), labeled.getTextable().getLabel());
		return labeledRectangleClone;
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
