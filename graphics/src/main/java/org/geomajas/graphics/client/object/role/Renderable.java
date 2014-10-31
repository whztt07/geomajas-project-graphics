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
package org.geomajas.graphics.client.object.role;

import org.geomajas.graphics.client.object.RoleInterface;
import org.geomajas.graphics.client.object.RoleType;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Implemented by all graphical objects. Rendering happens through {@link #asObject()} method, which returns the
 * {@link VectorObject} that corresponds to this graphical object (usually a group for complex objects).
 * TODO: remove RoleInterface extension
 * 
 * @author Jan De Moerloose
 * 
 */
public interface Renderable extends RoleInterface {

	RoleType<Renderable> TYPE = new RoleType<Renderable>("Renderable");

	VectorObject asObject();

}
