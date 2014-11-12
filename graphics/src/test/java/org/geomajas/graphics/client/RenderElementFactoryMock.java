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
package org.geomajas.graphics.client;

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.render.AnchoredRectangle;
import org.geomajas.graphics.client.render.AnchoredText;
import org.geomajas.graphics.client.render.CoordinatePath;
import org.geomajas.graphics.client.render.AnchoredEllipse;
import org.geomajas.graphics.client.shape.MockAnchoredEllipse;
import org.geomajas.graphics.client.shape.MockAnchoredRectangle;
import org.geomajas.graphics.client.shape.MockAnchoredText;
import org.geomajas.graphics.client.render.shape.RenderElementFactory;
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
	public AnchoredRectangle createAnchoredRectangle(double userX, double userY, double userWidth, double userHeight) {
		MockAnchoredRectangle mockAnchoredRectangle = new MockAnchoredRectangle();
		mockAnchoredRectangle.setUserX(userX);
		mockAnchoredRectangle.setUserY(userY);
		mockAnchoredRectangle.setUserWidth(userWidth);
		mockAnchoredRectangle.setUserHeight(userHeight);
		return mockAnchoredRectangle;
	}

	@Override
	public AnchoredRectangle createMarginAnchoredRectangle(double userX, double userY, double width, double height, int margin) {
		return marginAnchoredRectangleMock;
	}

	@Override
	public CoordinatePath createCoordinatePath(Coordinate[] coordinates, boolean closed) {
		MockCoordinatePath mockCoordinatePath = new MockCoordinatePath();
		mockCoordinatePath.setClosed(closed);
		mockCoordinatePath.setCoordinates(coordinates);
		return mockCoordinatePath;
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
}
