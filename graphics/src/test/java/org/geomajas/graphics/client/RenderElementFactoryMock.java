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

import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.geometry.service.BboxService;
import org.geomajas.graphics.client.object.updateable.anchored.AnchorMarker;
import org.geomajas.graphics.client.render.AnchoredCircle;
import org.geomajas.graphics.client.render.AnchoredEllipse;
import org.geomajas.graphics.client.render.AnchoredImage;
import org.geomajas.graphics.client.render.AnchoredRectangle;
import org.geomajas.graphics.client.render.AnchoredText;
import org.geomajas.graphics.client.render.CoordinatePath;
import org.geomajas.graphics.client.render.MockRenderableList;
import org.geomajas.graphics.client.render.RenderableList;
import org.geomajas.graphics.client.render.shape.RenderElementFactory;
import org.geomajas.graphics.client.shape.MockAnchoredCircle;
import org.geomajas.graphics.client.shape.MockAnchoredEllipse;
import org.geomajas.graphics.client.shape.MockAnchoredImage;
import org.geomajas.graphics.client.shape.MockAnchoredRectangle;
import org.geomajas.graphics.client.shape.MockAnchoredText;
import org.geomajas.graphics.client.shape.MockCoordinatePath;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Starting class for the Graphics Project.
 *
 * @author Jan Venstermans
 *
 */
public class RenderElementFactoryMock implements RenderElementFactory {

	@Mock
	public AnchoredRectangle anchoredRectangleMock;

	@Mock
	public AnchoredRectangle marginAnchoredRectangleMock;

	@Mock
	public CoordinatePath coordinatePathMock;

	public RenderElementFactoryMock() {
		MockitoAnnotations.initMocks(this);
	}

	@Override
	public AnchoredText createAnchoredText(double userX, double userY, String text, double anchorX, double anchorY) {
		MockAnchoredText mockAnchoredText = new MockAnchoredText();
		mockAnchoredText.setUserX(userX);
		mockAnchoredText.setUserY(userY);
		mockAnchoredText.setText(text);
		return mockAnchoredText;
	}

	@Override
	public AnchoredRectangle createAnchoredRectangle(double userX, double userY, double userWidth, double userHeight, int anchorX, int anchorY) {
		MockAnchoredRectangle mockAnchoredRectangle = new MockAnchoredRectangle();
		mockAnchoredRectangle.setUserX(userX);
		mockAnchoredRectangle.setUserY(userY);
		mockAnchoredRectangle.setUserWidth(userWidth);
		mockAnchoredRectangle.setUserHeight(userHeight);
		return mockAnchoredRectangle;
	}

	@Override
	public AnchoredRectangle createMarginAnchoredRectangle(double userX, double userY, double userWidth,
														   double userHeight,
														   int margin) {
		MockAnchoredRectangle mockAnchoredRectangle = new MockAnchoredRectangle();
		mockAnchoredRectangle.setUserX(userX);
		mockAnchoredRectangle.setUserY(userY);
		mockAnchoredRectangle.setUserWidth(userWidth);
		mockAnchoredRectangle.setUserHeight(userHeight);
		//TODO: margin?
		return mockAnchoredRectangle;
	}

	@Override
	public CoordinatePath createCoordinatePath(Coordinate[] coordinates, boolean closed) {
		MockCoordinatePath mockCoordinatePath = new MockCoordinatePath();
		mockCoordinatePath.setClosed(closed);
		mockCoordinatePath.setCoordinates(coordinates);
		Bbox bbox = createBboxOfCoordinates(coordinates);
		mockCoordinatePath.setUserBounds(bbox);
		mockCoordinatePath.setUserPosition(BboxService.getCenterPoint(bbox));
		return mockCoordinatePath;
	}

	private Bbox createBboxOfCoordinates(Coordinate[] coordinates) {
		if (coordinates.length > 0) {
			Coordinate coordinateLow = new Coordinate(coordinates[0]); //lower left
			Coordinate coordinateHigh = new Coordinate(coordinates[0]); //upper right
			for (int i = 1; i < coordinates.length; i++) {
				Coordinate coordinate = coordinates[i];
				if (coordinate.getX() < coordinateLow.getX()) {
					coordinateLow.setX(coordinate.getX());
				}
				if (coordinate.getX() > coordinateHigh.getX()) {
					coordinateHigh.setX(coordinate.getX());
				}
				if (coordinate.getY() < coordinateLow.getY()) {
					coordinateLow.setY(coordinate.getY());
				}
				if (coordinate.getY() > coordinateHigh.getY()) {
					coordinateHigh.setY(coordinate.getY());
				}
			}
			return new Bbox(coordinateLow.getX(), coordinateLow.getY(), coordinateHigh.getX() - coordinateLow.getX(),
					coordinateHigh.getY() - coordinateLow.getY());
		}
		return new Bbox();
	}

	@Override
	public AnchoredEllipse createEllipse(double ellipseCenterX, double ellipseCenterY, double userRadiusX, double userRadiusY) {
		MockAnchoredEllipse mockEllipse = new MockAnchoredEllipse();
		mockEllipse.setUserRadiusX(userRadiusX);
		mockEllipse.setUserRadiusY(userRadiusY);
		mockEllipse.setUserX(ellipseCenterX);
		mockEllipse.setUserY(ellipseCenterY);
		return mockEllipse;
	}

	@Override
	public AnchoredCircle createCircle(double circleCenterX, double circleCenterY, double radius) {
		MockAnchoredCircle mockAnchoredCircle = new MockAnchoredCircle();
		mockAnchoredCircle.setUserRadius(radius);
		mockAnchoredCircle.setUserX(circleCenterX);
		mockAnchoredCircle.setUserY(circleCenterY);
		return mockAnchoredCircle;
	}

	@Override
	public AnchoredCircle createAnchoredCircle(double circleCenterX, double circleCenterY, double radius, int anchorX, int anchorY) {
		MockAnchoredCircle mockAnchoredCircle = new MockAnchoredCircle();
		mockAnchoredCircle.setUserRadius(radius);
		mockAnchoredCircle.setUserX(circleCenterX);
		mockAnchoredCircle.setUserY(circleCenterY);
		return mockAnchoredCircle;
	}

	@Override
	public AnchoredImage createAnchoredImage(double userX, double userY, int width, int height,
											 String href, boolean preserveRatio, double anchorX, double anchorY) {
		MockAnchoredImage mockAnchoredImage = new MockAnchoredImage();
		mockAnchoredImage.setUserX(userX);
		mockAnchoredImage.setUserY(userY);
		mockAnchoredImage.setWidth(width);
		mockAnchoredImage.setUserWidth(width);
		mockAnchoredImage.setHeight(height);
		mockAnchoredImage.setUserHeight(height);
		mockAnchoredImage.setHref(href);
		mockAnchoredImage.setPreserveAspectRatio(preserveRatio);
		mockAnchoredImage.setAnchorX(anchorX);
		mockAnchoredImage.setAnchorY(anchorY);
		return mockAnchoredImage;
	}

	@Override
	public RenderableList createRenderableList() {
		return new MockRenderableList();
	}

	@Override
	public AnchorMarker createMarkerAnchoredRectangle(double userX, double userY, double userWidth, double userHeight, int anchorX, int anchorY) {
		MockAnchoredRectangle mockAnchoredRectangle = new MockAnchoredRectangle();
		mockAnchoredRectangle.setUserX(userX);
		mockAnchoredRectangle.setUserY(userY);
		mockAnchoredRectangle.setUserWidth(userWidth);
		mockAnchoredRectangle.setUserHeight(userHeight);
		return mockAnchoredRectangle;
	}

	@Override
	public AnchorMarker createMarkerAnchoredCircle(double circleCenterX, double circleCenterY, double radius, int anchorX, int anchorY) {
		MockAnchoredCircle mockAnchoredCircle = new MockAnchoredCircle();
		mockAnchoredCircle.setUserRadius(radius);
		mockAnchoredCircle.setUserX(circleCenterX);
		mockAnchoredCircle.setUserY(circleCenterY);
		return mockAnchoredCircle;
	}

	@Override
	public AnchorMarker createMarkerAnchoredCross(double userX, double userY, int crossHeightPixels) {
		throw new UnsupportedOperationException();
	}

}
