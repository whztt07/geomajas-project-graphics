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
package org.geomajas.graphics.client.controller.resize;

import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.role.Resizable;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class ResizeControllerFactoryTest {
	
	private ResizeControllerFactory resizeControllerFactory;

	@Test
	public void testResizeControllerFactoryDoesNotSupportNotResizable() {
		resizeControllerFactory = new ResizeControllerFactory();
		GraphicsObject graphicsObjectMock = Mockito.mock(GraphicsObject.class);
		when(graphicsObjectMock.hasRole(Resizable.TYPE)).thenReturn(false);

		boolean supports = resizeControllerFactory.supports(graphicsObjectMock);

		Assert.assertFalse(supports);
	}

	@Test
	public void testResizeControllerFactorySupportResizable() {
		resizeControllerFactory = new ResizeControllerFactory();
		GraphicsObject graphicsObjectMock = Mockito.mock(GraphicsObject.class);
		when(graphicsObjectMock.hasRole(Resizable.TYPE)).thenReturn(true);

		boolean supports = resizeControllerFactory.supports(graphicsObjectMock);

		Assert.assertTrue(supports);
	}
}
