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
package org.geomajas.graphics.client.render.shape;

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.object.updateable.bordered.MarginAnchoredRectangleImpl;
import org.geomajas.graphics.client.render.AnchoredRectangle;
import org.geomajas.graphics.client.render.AnchoredText;
import org.geomajas.graphics.client.render.CoordinatePath;
import org.geomajas.graphics.client.render.AnchoredEllipse;

/**
 * Default implementation of {@link RenderElementFactory} for {@link org.vaadin.gwtgraphics.client.Shape} objects.
 *
 * @author Jan Venstermans
 *
 */
public class ShapeRenderElementFactoryImpl implements RenderElementFactory {

	@Override
	public AnchoredText createAnchoredText(double userX, double userY, String text, double anchorX, double anchorY) {
		return new AnchoredTextImpl(userX, userY, text, anchorX, anchorY);
	}

	@Override
	public AnchoredRectangle createAnchoredRectangle(double userX, double userY, double userWidth, double userHeight) {
		return new AnchoredRectangleImpl(userX, userY, userWidth, userHeight, 0, 0);
	}

	@Override
	public AnchoredRectangle createMarginAnchoredRectangle(double userX, double userY,
														   double width, double height, int margin) {
		return new MarginAnchoredRectangleImpl(userX, userY, width, height, 0, 0, margin);
	}

	@Override
	public CoordinatePath createCoordinatePath(Coordinate[] coordinates, boolean closed) {
		return new CoordinatePathShape(coordinates, closed);
	}

	@Override
	public AnchoredEllipse createEllipse(double ellipseCenterX,
										 double ellipseCenterY, double userRadiusX, double userRadiusY) {
		return new AnchoredEllipseImpl(ellipseCenterX, ellipseCenterY, userRadiusX, userRadiusY);
	}
}