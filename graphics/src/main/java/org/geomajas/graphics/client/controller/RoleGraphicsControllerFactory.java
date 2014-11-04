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
package org.geomajas.graphics.client.controller;

import org.geomajas.graphics.client.controller.drag.DragController;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.RoleInterface;
import org.geomajas.graphics.client.object.RoleType;

/**
 * Implementation of {@link GraphicsControllerFactory} for a {@link GraphicsObject} with a specific
 * {@link org.geomajas.graphics.client.object.RoleInterface}.
 *
 * @author Jan Venstermans
 *
 */
public abstract class RoleGraphicsControllerFactory<R extends RoleInterface, C extends GraphicsController>
		implements GraphicsControllerFactory<C> {

	private RoleType<R> roleType;

	protected RoleGraphicsControllerFactory(RoleType<R> roleType) {
		this.roleType = roleType;
	}

	@Override
	public boolean supports(GraphicsObject object) {
		return object.hasRole(roleType);
	}
}
