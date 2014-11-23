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
import org.geomajas.graphics.client.object.base.BaseImage;
import org.geomajas.graphics.client.object.updateable.labeled.Labeled;
import org.geomajas.graphics.client.object.updateable.labeled.LabeledImpl;
import org.geomajas.graphics.client.object.updateable.wrapper.DraggableWrapperForUpdateable;
import org.geomajas.graphics.client.object.updateable.wrapper.ResizableWrapperForUpdateable;
import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Extension of {@link org.geomajas.graphics.client.object.updateable.UpdateableGroupGraphicsObject}
 * that shows a text centered on a {@link BaseImage}.
 *
 * @author Jan Venstermans
 *
 */
public class LabeledImage extends UpdateableGroupGraphicsObject {

	private Group rootGroup = new Group();

	private BaseImage baseImage;

	private LabeledImpl labeled;

	public LabeledImage(int x, int y, int width, int height, String href, String text) {
		// create base graphics objects
		baseImage = new BaseImage(x, y, width, height, href, true);
		labeled = new LabeledImpl(baseImage, text);

		// register updateables
		addUpdateable(labeled);

		// register roles of group object
		addRole(Resizable.TYPE, new ResizableWrapperForUpdateable(baseImage, this));
		addRole(Draggable.TYPE, new DraggableWrapperForUpdateable(baseImage, this));
		addRole(Labeled.TYPE, labeled);

		// register render order
		rootGroup.add(baseImage.asObject());
		rootGroup.add(labeled.asObject());
	}

	@Override
	public Object cloneObject() {
		Bbox userBounds = baseImage.getUserBounds();
		LabeledImage labeledRectangleClone = new LabeledImage((int) userBounds.getX(), (int) userBounds.getY(),
				(int) userBounds.getWidth(), (int) userBounds.getHeight(),
				baseImage.getHref(), labeled.getTextable().getLabel());
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
