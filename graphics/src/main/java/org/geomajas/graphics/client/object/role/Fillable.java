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
import org.geomajas.graphics.client.util.HasFill;

/**
 * Implemented by graphics objects that can be filled.
 * 
 * @author Jan De Moerloose
 * 
 */
public interface Fillable extends RoleInterface, HasFill {
	
	RoleType<Fillable> TYPE = new RoleType<Fillable>("Fillable");
	
}
