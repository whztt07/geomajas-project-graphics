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
package org.geomajas.graphics.client;

/**
 * Constants for Graphics.
 *
 * @author Jan De Moerloose
 *
 */
public final class GraphicsConstants {

	// anchor X offset for the popupMenu icon, relative to the left top border of the Bbox of the resizable
	private static double offsetX = 2;

	// anchor Y offset for the popupMenu icon, relative to the left top border of the Bbox of the resizable
	private static double offsetY = 2;

	public double getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(double offsetX) {
		this.offsetX = offsetX;
	}

	public double getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(double offsetY) {
		this.offsetY = offsetY;
	}
}
