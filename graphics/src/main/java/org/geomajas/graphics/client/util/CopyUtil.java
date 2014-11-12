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
package org.geomajas.graphics.client.util;

import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.object.role.Strokable;

/**
 * Utility class for some graphics operations.
 * 
 * @author Jan De Moerloose
 * 
 */
public final class CopyUtil {

	private CopyUtil() {

	}

	public static Bbox clone(Bbox bounds) {
		return new Bbox(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
	}

	/**
	 * Copy all {@link Fillable} properties from original to copy.
	 *
	 * @param fillableOriginal
	 * @param fillableCopy
	 */
	public static void copyFillableProperties(Fillable fillableOriginal, Fillable fillableCopy) {
		fillableCopy.setFillOpacity(fillableOriginal.getFillOpacity());
		fillableCopy.setFillColor(fillableOriginal.getFillColor());
	}

	/**
	 * Copy all {@link Strokable} properties from original to copy.
	 *
	 * @param strokableOriginal
	 * @param strokableCopy
	 */
	public static void copyStrokableProperties(Strokable strokableOriginal, Strokable strokableCopy) {
		strokableCopy.setStrokeOpacity(strokableOriginal.getStrokeOpacity());
		strokableCopy.setStrokeColor(strokableOriginal.getStrokeColor());
		strokableCopy.setStrokeWidth(strokableOriginal.getStrokeWidth());
	}

	/**
	 * Copy values of array of coordinates to new array.
	 *
	 * @param coordinatesOriginal
	 * @return
	 */
	public static Coordinate[] deepCopyCoordinates(Coordinate[] coordinatesOriginal) {
		if (coordinatesOriginal != null) {
			Coordinate[] copy = new Coordinate[coordinatesOriginal.length];
			for (int i = 0; i < coordinatesOriginal.length; i++) {
				copy[i] = deepCopyCoordinate(coordinatesOriginal[i]);
			}
			return copy;
		}
		return null;
	}

	public static Coordinate deepCopyCoordinate(Coordinate coordinateOriginal) {
		if (coordinateOriginal != null) {
			return new Coordinate(coordinateOriginal.getX(), coordinateOriginal.getY());
		}
		return null;
	}

	public static Bbox deepCopyBbox(Bbox bbox) {
		if (bbox != null) {
			return new Bbox(bbox.getX(), bbox.getY(), bbox.getWidth(), bbox.getHeight());
		}
		return null;
	}

	public static void copyResizableProperties(Resizable resizableOriginal, Resizable resizableCopy) {
		resizableCopy.setUserPosition(deepCopyCoordinate(resizableOriginal.getUserPosition()));
		resizableCopy.setUserBounds(deepCopyBbox(resizableOriginal.getUserBounds()));
	}

	public static void copyDraggableProperties(Draggable draggableOriginal, Draggable draggableCopy) {
		draggableCopy.setUserPosition(deepCopyCoordinate(draggableOriginal.getUserPosition()));
		draggableCopy.setUserBounds(deepCopyBbox(draggableOriginal.getUserBounds()));
	}
}
