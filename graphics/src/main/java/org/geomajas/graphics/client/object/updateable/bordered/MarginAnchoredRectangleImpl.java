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
package org.geomajas.graphics.client.object.updateable.bordered;

import org.geomajas.graphics.client.render.shape.AnchoredRectangleImpl;

/**
 * A non-scaling rectangle that is anchored to its world space location on a specific pixel or anchor location (useful
 * for location markers).
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public class MarginAnchoredRectangleImpl extends AnchoredRectangleImpl {

	private int margin;


	/**
	 * Creates an rectangle at the specified world location with a specified size and anchor point. E.g., if
	 * (anchorX,anchorY)=(width/2, height/2), the center of the rectangle will be positioned at the world location.
	 *
	 * @param userX      x-location in world coordinates
	 * @param userY      y-location in world coordinates
	 * @param userWidth  width in pixels
	 * @param userHeight height in pixels
	 * @param anchorX    x-location of the anchor point (rectangle-relative)
	 * @param anchorY    y-location of the anchor point (rectangle-relative)
	 */
	public MarginAnchoredRectangleImpl(double userX, double userY, double userWidth,
									   double userHeight, int anchorX, int anchorY, int margin) {
		super(userX, userY, userWidth, userHeight, anchorX, anchorY);
		this.margin = margin;
		setRoundedCorners(margin);
	}

	@Override
	protected void drawTransformed() {
		if (isFixedSize()) {
			// center on userX, userY
			setX((int) Math.round(getUserX() * getScaleX() + getDeltaX() - getUserWidth() / 2 - margin));
			setY((int) Math.round(getUserY() * getScaleY() + getDeltaY() - getUserHeight() / 2 - margin));
			setWidth((int) getUserWidth() + 2 * margin);
			setHeight((int) getUserHeight() + 2 * margin);
		} else {
			// transform all points and calculate new bounds
			double x1 = getUserX() * getScaleX() + getDeltaX();
			double y1 = getUserY() * getScaleY() + getDeltaY();
			double x2 = (getUserX() + getUserWidth()) * getScaleX() + getDeltaX();
			double y2 = (getUserY() + getUserHeight()) * getScaleY() + getDeltaY();
			setX((int) Math.round(Math.min(x1, x2)) - margin);
			setY((int) Math.round(Math.min(y1, y2)) - margin);
			setWidth((int) Math.abs(x1 - x2) + 2 * margin);
			setHeight((int) Math.abs(y1 - y2) + 2 * margin);
		}
	}
}