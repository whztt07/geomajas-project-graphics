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
package org.geomajas.graphics.client.object.updateable;

import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.RoleType;
import org.geomajas.graphics.client.object.role.Strokable;

/**
 * Simplified {@link org.geomajas.graphics.client.object.Bordered} role.
 * TODO: merge Bordered and BorderedUpdateable roles.
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public interface BorderedUpdateable extends Updateable {

	RoleType<BorderedUpdateable> TYPE = new RoleType<BorderedUpdateable>("BorderedUpdateable");

	Strokable getStrokable();

	Fillable getFillable();

}
