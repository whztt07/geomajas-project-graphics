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
package org.geomajas.graphics.client.render.shape;

import org.geomajas.graphics.client.render.AnchoredRectangle;
import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.shape.Rectangle;

/**
 * A non-scaling rectangle that is anchored to its world space location on a specific pixel or anchor location (useful
 * for location markers).
 * 
 * @author Jan De Moerloose
 * 
 */
public class AnchoredRectangleImpl extends Rectangle implements AnchoredRectangle {

	private int anchorX;

	private int anchorY;

	/**
	 * Creates an rectangle at the specified world location with a specified size and anchor point. E.g., if
	 * (anchorX,anchorY)=(width/2, height/2), the center of the rectangle will be positioned at the world location.
	 * 
	 * @param userX x-location in world coordinates
	 * @param userY y-location in world coordinates
	 * @param userWidth width in pixels
	 * @param userHeight height in pixels
	 * @param anchorX x-location of the anchor point (rectangle-relative)
	 * @param anchorY y-location of the anchor point (rectangle-relative)
	 */
	public AnchoredRectangleImpl(double userX, double userY, double userWidth, double userHeight,
								 int anchorX, int anchorY) {
		super(userX, userY, userWidth, userHeight);
		this.anchorX = anchorX;
		this.anchorY = anchorY;
	}

	@Override
	protected void drawTransformed() {
		getImpl().setX(getElement(), (int) Math.round(getUserX() * getScaleX() + getDeltaX()) - anchorX, isAttached());
		getImpl().setY(getElement(), (int) Math.round(getUserY() * getScaleY() + getDeltaY()) - anchorY, isAttached());
		// don't scale, but have to set width/height here !
		setWidth((int) getUserWidth());
		setHeight((int) getUserHeight());
	}

	@Override
	public Object cloneObject() {
		return new AnchoredRectangleImpl(getUserX(), getUserY(), getUserWidth(), getUserHeight(), anchorX, anchorY);
	}
	
	public int getAnchorX() {
		return anchorX;
	}

	public int getAnchorY() {
		return anchorX;
	}

	@Override
	public VectorObject asObject() {
		return this;
	}

	@Override
	public void setOpacity(double opacity) {
		setFillOpacity(opacity);
		setStrokeOpacity(opacity);
	}
}