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
package org.geomajas.graphics.client.controller.drag;

import com.google.web.bindery.event.shared.SimpleEventBus;
import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.controller.drag.GraphicsObjectDragHandler;
import org.geomajas.graphics.client.controller.resize.ResizeController;
import org.geomajas.graphics.client.object.MockDraggable;
import org.geomajas.graphics.client.service.objectcontainer.GraphicsObjectContainer;
import org.geomajas.graphics.client.service.objectcontainer.GraphicsObjectContainer.Space;
import org.geomajas.graphics.client.service.GraphicsService;
import org.geomajas.graphics.client.service.GraphicsServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class DragControllerTest {
	
	@Mock
	private GraphicsObjectContainer objectContainer;

	private SimpleEventBus eventBus = new SimpleEventBus();

	private GraphicsService service;

	private static final double DOUBLE_DELTA = 0.0001;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		when(objectContainer.transform(new Coordinate(105, 110), Space.SCREEN, Space.USER)).thenReturn(new Coordinate(105, 110));
		when(objectContainer.transform(new Coordinate(100, 100), Space.SCREEN, Space.USER)).thenReturn(new Coordinate(100, 100));
		service = new GraphicsServiceImpl(eventBus, false);
		service.setObjectContainer(objectContainer);
	}

	@Test
	public void testDrag() {
		MockDraggable m = new MockDraggable(new Coordinate(5, 6), new Bbox(0, 0, 50, 50));
		ResizeController r = new ResizeController(m, service, true);
		GraphicsObjectDragHandler h = new GraphicsObjectDragHandler(m, service, r);

		h.onDragStart(100, 100);
		h.onDragStop(105, 110);

		Assert.assertTrue(m.getPosition().equalsDelta(new Coordinate(10, 16), DOUBLE_DELTA));
		service.undo();
		Assert.assertTrue(m.getPosition().equalsDelta(new Coordinate(5, 6), DOUBLE_DELTA));
	}

}
