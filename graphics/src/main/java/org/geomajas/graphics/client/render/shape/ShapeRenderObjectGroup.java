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
package org.geomajas.graphics.client.render.shape;

import org.geomajas.graphics.client.render.Renderable;
import org.geomajas.graphics.client.service.objectcontainer.RenderObjectContainer;
import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Extension of {@link org.vaadin.gwtgraphics.client.shape.Rectangle} that adds margin,...
 *
 * @author Jan Venstermans
 *
 */
public class ShapeRenderObjectGroup extends Group implements RenderObjectContainer {

	@Override
	public void add(Renderable object) {
		add(object.asObject());
	}

	@Override
	public void remove(Renderable object) {
		remove(object.asObject());
	}

	@Override
	public void bringToFront(Renderable container) {
	   	bringToFront(container.asObject());
	}

	@Override
	public VectorObject asObject() {
		return this;
	}
}
