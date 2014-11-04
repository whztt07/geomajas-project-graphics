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
package org.geomajas.graphics.client.object;

import java.util.HashMap;
import java.util.Map;

/**
 * Base class for {@link GraphicsObject} implementations. Keeps track of the object's roles.
 * 
 * @author Jan De Moerloose
 * 
 */
public abstract class BaseGraphicsObject implements GraphicsObject {

	private Map<RoleType<?>, Object> roles = new HashMap<RoleType<?>, Object>();

	@Override
	public boolean hasRole(RoleType<?> type) {
		return roles.containsKey(type);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends RoleInterface> T getRole(RoleType<T> type) {
		return (T) roles.get(type);
	}

	public <T extends RoleInterface> void addRole(RoleType<T> type, T role) {
		roles.put(type, role);
	}

	public void removeRole(RoleType<?> type) {
		roles.remove(type);
	}

	protected Map<RoleType<?>, Object> getRoles() {
		return roles;
	}

}
