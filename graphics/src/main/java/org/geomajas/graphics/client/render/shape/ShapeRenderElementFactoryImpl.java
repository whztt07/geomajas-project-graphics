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

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.object.updateable.anchored.AnchorMarker;
import org.geomajas.graphics.client.object.updateable.bordered.MarginAnchoredRectangleImpl;
import org.geomajas.graphics.client.render.AnchoredCircle;
import org.geomajas.graphics.client.render.AnchoredEllipse;
import org.geomajas.graphics.client.render.AnchoredImage;
import org.geomajas.graphics.client.render.AnchoredRectangle;
import org.geomajas.graphics.client.render.AnchoredText;
import org.geomajas.graphics.client.render.CoordinatePath;
import org.geomajas.graphics.client.render.RenderGroup;
import org.geomajas.graphics.client.render.RenderableList;

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
	public AnchoredRectangle createAnchoredRectangle(double userX, double userY, double userWidth, double userHeight,
													 int anchorX, int anchorY) {
		return new AnchoredRectangleImpl(userX, userY, userWidth, userHeight, anchorX, anchorY);
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

	@Override
	public AnchoredCircle createCircle(double circleCenterX, double circleCenterY, double radius) {
		return createAnchoredCircle(circleCenterX, circleCenterY, radius, 0, 0);
	}

	@Override
	public AnchoredCircle createAnchoredCircle(double circleCenterX, double circleCenterY, double radius,
											   int anchorX, int anchorY) {
		return new AnchoredCircleImpl(circleCenterX, circleCenterY, radius, anchorX, anchorY);
	}

	@Override
	public AnchoredImage createAnchoredImage(double userX, double userY, int width, int height,
											 String href, boolean preserveRatio,
											 double anchorX, double anchorY) {
		AnchoredImageImpl anchoredImage = new AnchoredImageImpl(userX, userY, width, height, href, anchorX, anchorY);
		anchoredImage.setPreserveAspectRatio(preserveRatio);
		return anchoredImage;
	}

	@Override
	public RenderableList createRenderableList() {
		return new RenderGroup();
	}

	@Override
	public AnchorMarker createMarkerAnchoredRectangle(double userX, double userY, double userWidth,
													  double userHeight, int anchorX, int anchorY) {
		return new AnchoredRectangleImpl(userX, userY, userWidth, userHeight, anchorX, anchorY);
	}

	@Override
	public AnchorMarker createMarkerAnchoredCircle(double circleCenterX, double circleCenterY,
												   double radius, int anchorX, int anchorY) {
		return new AnchoredCircleImpl(circleCenterX, circleCenterY, radius, anchorX, anchorY);
	}

	@Override
	public AnchorMarker createMarkerAnchoredCross(double userX, double userY, int crossHeightPixels) {
		return new AnchoredCrossImpl(6, 6, 8);
	}
}
