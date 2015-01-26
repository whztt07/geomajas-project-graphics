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
package org.geomajas.graphics.client.controller.drag;

import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.role.Draggable;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class DragControllerFactoryTest {
	
	private DragControllerFactory dragControllerFactory;

	@Test
	public void testResizeControllerFactoryDoesNotSupportNotDraggable() {
		dragControllerFactory = new DragControllerFactory();
		GraphicsObject graphicsObjectMock = Mockito.mock(GraphicsObject.class);
		when(graphicsObjectMock.hasRole(Draggable.TYPE)).thenReturn(false);

		boolean supports = dragControllerFactory.supports(graphicsObjectMock);

		Assert.assertFalse(supports);
	}

	@Test
	public void testResizeControllerFactorySupportDraggable() {
		dragControllerFactory = new DragControllerFactory();
		GraphicsObject graphicsObjectMock = Mockito.mock(GraphicsObject.class);
		when(graphicsObjectMock.hasRole(Draggable.TYPE)).thenReturn(true);

		boolean supports = dragControllerFactory.supports(graphicsObjectMock);

		Assert.assertTrue(supports);
	}
}
