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
package org.geomajas.graphics.client.object.updateable.bordered;

import org.geomajas.graphics.client.object.RoleInterface;
import org.geomajas.graphics.client.object.RoleType;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Strokable;

/**
 * Interface for a border.
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public interface Bordered extends RoleInterface {

	RoleType<Bordered> TYPE = new RoleType<Bordered>("Bordered");

	Strokable getStrokable();

	Fillable getFillable();

}
