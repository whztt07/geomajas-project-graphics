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
package org.geomajas.graphics.client.render;

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.util.HasBounds;
import org.geomajas.graphics.client.util.HasFill;
import org.geomajas.graphics.client.util.HasStroke;

/**
 * Path based on coordinate array.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public interface CoordinatePath extends HasStroke, HasFill, HasBounds {

	boolean isClosed();

	Coordinate getLastCoordinate();

	void setCoordinates(Coordinate[] coordinates);

	void addCoordinate(Coordinate coordinate);

	void moveCoordinate(Coordinate coordinate, int index);

	int getCoordinateCount();

	Coordinate[] getCoordinates();

	void setUserPosition(Coordinate position);

	Coordinate getUserPosition();

}
