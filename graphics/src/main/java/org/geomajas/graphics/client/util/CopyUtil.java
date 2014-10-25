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
import org.geomajas.graphics.client.object.role.Fillable;
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
	 * TODO: move to Fillable role?
	 *
	 * @param fillableOriginal
	 * @param fillable
	 */
	public static void copyProperties(Fillable fillableOriginal, Fillable fillable) {
		fillable.setFillOpacity(fillableOriginal.getFillOpacity());
		fillable.setFillColor(fillableOriginal.getFillColor());
	}

	/**
	 * TODO: move to Strokable role?
	 *
	 * @param strokableOriginal
	 * @param strokable
	 */
	public static void copyProperties(Strokable strokableOriginal, Strokable strokable) {
		strokable.setStrokeOpacity(strokable.getStrokeOpacity());
		strokable.setStrokeColor(strokable.getStrokeColor());
		strokable.setStrokeWidth(strokable.getStrokeWidth());
	}


}
