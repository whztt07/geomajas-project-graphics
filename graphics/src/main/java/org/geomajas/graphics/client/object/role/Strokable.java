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
import org.geomajas.graphics.client.util.HasStroke;

/**
 * Implemented by graphics objects that can be stroked.
 * 
 * @author Jan De Moerloose
 * 
 */
public interface Strokable extends RoleInterface, HasStroke {
	
	RoleType<Strokable> TYPE = new RoleType<Strokable>("Strokable");

//	String setStrokeStyle();
//
//	void setStrokeStyle(String style);

}
