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
import org.geomajas.graphics.client.object.updateable.anchored.AnchorMarker;
import org.geomajas.graphics.client.render.AnchoredCircle;
import org.geomajas.graphics.client.render.AnchoredEllipse;
import org.geomajas.graphics.client.render.AnchoredImage;
import org.geomajas.graphics.client.render.AnchoredRectangle;
import org.geomajas.graphics.client.render.AnchoredText;
import org.geomajas.graphics.client.render.CoordinatePath;
import org.geomajas.graphics.client.render.RenderableList;
import org.geomajas.graphics.client.service.objectcontainer.RenderObjectContainer;
import org.geomajas.graphics.client.service.objectcontainer.RenderObjectContainerWithHasAllMouseAndClickHandlers;
import org.vaadin.gwtgraphics.client.Group;

/**
 * Interface for specific view elements.
 *
 * @author Jan Venstermans
 *
 */
public interface RenderElementFactory {

	AnchoredText createAnchoredText(double userX, double userY, String text, double anchorX, double anchorY);

	AnchoredRectangle createAnchoredRectangle(double userX, double userY, double userWidth, double userHeight,
											  int anchorX, int anchorY);

	AnchoredRectangle createMarginAnchoredRectangle(double userX, double userY,
													double width, double height, int margin);

	CoordinatePath createCoordinatePath(Coordinate[] coordinates, boolean closed);

	AnchoredEllipse createEllipse(double ellipseCenterX, double ellipseCenterY, double userRadiusX, double userRadiusY);

	AnchoredCircle createCircle(double circleCenterX, double circleCenterY, double radius);

	AnchoredCircle createAnchoredCircle(double circleCenterX, double circleCenterY, double radius,
										int anchorX, int anchorY);

	AnchoredImage createAnchoredImage(double userX, double userY, int width, int height, String href,
									  boolean preserveRatio, double anchorX, double anchorY);

	RenderableList createRenderableList();

	AnchorMarker createMarkerAnchoredRectangle(double userX, double userY, double userWidth, double userHeight,
											   int anchorX, int anchorY);

	AnchorMarker createMarkerAnchoredCircle(double circleCenterX, double circleCenterY, double radius,
											int anchorX, int anchorY);

	AnchorMarker createMarkerAnchoredCross(double userX, double userY, int crossHeightPixels);

	RenderObjectContainer createRenderObjectContainer();

	RenderObjectContainerWithHasAllMouseAndClickHandlers createRenderObjectContainerWithHasAllMouseAndClickHandlers();
}
