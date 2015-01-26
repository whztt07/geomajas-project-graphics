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
package org.geomajas.graphics.client.object.role;

import org.geomajas.graphics.client.object.RoleInterface;
import org.geomajas.graphics.client.object.RoleType;
import org.geomajas.graphics.client.util.FlipState;
import org.geomajas.graphics.client.util.HasBounds;
import org.geomajas.graphics.client.util.HasPosition;

/**
 * Implemented by resizable graphics objects. Resizing can include flipping if the resizing handle is "folded".
 * 
 * @author Jan De Moerloose
 * 
 */
public interface Resizable extends RoleInterface, HasPosition, HasBounds {

	RoleType<Resizable> TYPE = new RoleType<Resizable>("Resizable");

	/**
	 * Flip the object according to the specified state.
	 * 
	 * @param state
	 */
	void flip(FlipState state);

	/**
	 * Should the width/height ratio be preserved when resizing this object ?
	 * 
	 * @return true if preserved, false otherwise
	 */
	boolean isPreserveRatio();

	/**
	 * Should the object calculate its own height ?
	 * 
	 * @return true if height is automatic.
	 */
	boolean isAutoHeight();

}
