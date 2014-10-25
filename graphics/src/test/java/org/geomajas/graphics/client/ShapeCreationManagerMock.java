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

import org.geomajas.graphics.client.controller.popupmenu.PopupMenuController;
import org.geomajas.graphics.client.editor.Editor;
import org.geomajas.graphics.client.shape.AnchoredRectangle;
import org.geomajas.graphics.client.shape.AnchoredText;
import org.geomajas.graphics.client.shape.MockAnchoredText;
import org.geomajas.graphics.client.shape.ShapeCreationManager;
import org.geomajas.graphics.client.widget.createcontrollergroup.CreateControllerGroupPresenter;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Starting class for the Graphics Project.
 *
 * @author Jan Venstermans
 *
 */
public class ShapeCreationManagerMock implements ShapeCreationManager {

	@Mock
	public AnchoredText anchoredTextMock;

	@Mock
	public AnchoredRectangle anchoredRectangleMock;

	@Mock
	public AnchoredRectangle marginAnchoredRectangleMock;

	public ShapeCreationManagerMock() {
		MockitoAnnotations.initMocks(this);
	}

	@Override
	public AnchoredText createAnchoredText(double userX, double userY, String text, double anchorX, double anchorY) {
		return new MockAnchoredText();
	}

	@Override
	public AnchoredRectangle createAnchoredRectangle(double userX, double userY, double userWidth, double userHeight) {
		return anchoredRectangleMock;
	}

	@Override
	public AnchoredRectangle createMarinAnchoredRectangle(double userX, double userY, double width, double height, int margin) {
		return marginAnchoredRectangleMock;
	}
}
