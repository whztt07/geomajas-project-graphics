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

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.object.RoleInterface;
import org.geomajas.graphics.client.object.RoleType;

/**
 * Implemented by graphical objects that can be represented an array of coordinates.
 * 
 * @author Jan De Moerloose
 * 
 */
public interface CoordinateBased extends RoleInterface {

	RoleType<CoordinateBased> TYPE = new RoleType<CoordinateBased>("CoordinateBased");

	void setCoordinates(Coordinate[] coordinates);

	Coordinate[] getCoordinates();

	void addCoordinate(Coordinate coordinate);

	Coordinate getLastCoordinate();

	int getCoordinateCount();

	void moveCoordinate(Coordinate coordinate, int index);
}
