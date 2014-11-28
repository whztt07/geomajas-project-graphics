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
package org.geomajas.graphics.client.service.objectcontainer;

import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.render.Renderable;
import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Container of graphics objects.
 * 
 * @author Jan Venstermans
 * 
 */
public interface RenderObjectContainer extends Renderable {

//	void add(RenderObjectContainer group);
//
//	void remove(RenderObjectContainer container);

	void add(Renderable object);

	void remove(Renderable object);

	void clear();

	void bringToFront(Renderable container);
}
