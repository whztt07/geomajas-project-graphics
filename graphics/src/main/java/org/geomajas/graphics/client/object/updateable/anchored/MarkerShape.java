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

package org.geomajas.graphics.client.object.updateable.anchored;

import org.geomajas.graphics.client.Graphics;
import org.geomajas.graphics.client.render.shape.AnchoredCircleImpl;
import org.geomajas.graphics.client.render.shape.AnchoredCrossImpl;
import org.geomajas.graphics.client.render.shape.AnchoredRectangleImpl;
import org.vaadin.gwtgraphics.client.Shape;

/**
 * Enumeration of standard Marker Shapes.
 * 
 * @author Jan Venstermans
 * 
 */
public enum MarkerShape {

	/**
	 * a square, side 8 pixels.
	 */
	SQUARE("Square"),
	
	/**
	 * a circle, diameter 8 pixels.
	 */
	CIRCLE("Circle"),
	
	/**
	 * a cross: diagonals of a 8x8 box.
	 */
	CROSS("Cross");
	
	private String title;

	/**
	 * Constructor.
	 * 
	 * @param title
	 */
	private MarkerShape(String title) {
		this.title = title;
	}

//	public AnchorMarker getSimpleShape() {
//		switch(this) {
//			case SQUARE:
//				return new Rectangle(2, 2, 8, 8);
//			case CIRCLE:
//				return new Circle(6, 6, 4);
//			case CROSS:
//				return CROSS.getMarkerShape();
//		}
//		return null;
//	}

	public AnchorMarker getMarkerShape() {
		switch(this) {
			case SQUARE:
				return Graphics.getRenderElementFactory().createMarkerAnchoredRectangle(0, 0, 8, 8, 4, 4);
			case CIRCLE:
				return Graphics.getRenderElementFactory().createMarkerAnchoredCircle(0, 0, 4, 0, 0);
			case CROSS:
				return Graphics.getRenderElementFactory().createMarkerAnchoredCross(6, 6, 8);
		}
		return null;
	}

	/**
	 * Create a marker shape that is centered around a given coordinate posX, posY
	 * and whose shape is is a box with given size.
	 * @param posX
	 * @param posY
	 * @param size
	 * @return
	 */
	public Shape getMarkerShape(double posX, double posY, double size) {
		switch(this) {
			case SQUARE:
				return new AnchoredRectangleImpl(posX, posX,
						size, size, (int) size / 2, (int) size / 2);
			case CIRCLE:
				return new AnchoredCircleImpl(posX, posY, size / 2, 0, 0);
			case CROSS:
				return new AnchoredCrossImpl(posX, posY, (int) size);
		}
		return null;
	}
	
	public String getTitle() {
		return title;
	}

}

