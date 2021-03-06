/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2015 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the Apache
 * License, Version 2.0. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */
package org.geomajas.graphics.client.object;

/**
 * Enum-like class that uniquely represents a role type. Add an instance of this class to every new role interface.
 * 
 * @author Jan De Moerloose
 * 
 * @param <T> extends a {@link RoleInterface}
 */
public class RoleType<T extends RoleInterface> {

	private String name;

	public RoleType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
