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
package org.geomajas.graphics.client.widget.createcontrollergroup;

import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.SimpleEventBus;
import org.geomajas.graphics.client.GraphicsMockSetup;
import org.geomajas.graphics.client.controller.create.CreateController;
import org.geomajas.graphics.client.service.GraphicsService;
import org.geomajas.graphics.client.service.GraphicsServiceImpl;
import org.geomajas.graphics.client.service.HasAllMouseAndClickHandlers;
import org.geomajas.graphics.client.service.objectcontainer.GraphicsObjectContainer;
import org.geomajas.graphics.client.service.objectcontainer.GraphicsObjectContainer.Space;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreateControllerGroupPresenterImplTest extends GraphicsMockSetup {
	
	@Mock
	private GraphicsObjectContainer objectContainerMock;

	@Mock
	private HasAllMouseAndClickHandlers backgroundMock;

	private GraphicsService graphicsService;

	private SimpleEventBus eventBus = new SimpleEventBus();

	private CreateControllerGroupPresenterImpl createControllerGroupPresenter;

	@Captor
	private ArgumentCaptor<CreateController<?>> controllerArgumentCaptor;

	@Mock
	private CreateController createController1;

	private String label1 = "label1";

	@Mock
	private CreateController createController2;

	private String label2 = "label2";

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		graphicsService = new GraphicsServiceImpl(eventBus, false);
		graphicsService.setObjectContainer(objectContainerMock);
		createControllerGroupPresenter = new CreateControllerGroupPresenterImpl(graphicsService, viewManagerMock.createControllerGroupView);
		stub(objectContainerMock.getBackGround()).toReturn(backgroundMock);
		stub(backgroundMock.addMouseDownHandler(any(MouseDownHandler.class))).toReturn(mock(HandlerRegistration.class));
		stub(backgroundMock.addMouseMoveHandler(any(MouseMoveHandler.class))).toReturn(mock(HandlerRegistration.class));
		stub(backgroundMock.addMouseUpHandler(any(MouseUpHandler.class))).toReturn(mock(HandlerRegistration.class));
		graphicsService.start();
		reset(viewManagerMock.createControllerGroupView);
	}

	@Test
	public void testViewSetHandler() throws Exception {
		createControllerGroupPresenter = new CreateControllerGroupPresenterImpl(graphicsService, viewManagerMock.createControllerGroupView);
		verify(viewManagerMock.createControllerGroupView).setHandler(createControllerGroupPresenter);
	}

	@Test
	public void testAddControllers() {
		createControllerGroupPresenter.addCreateController(createController1, label1);
		createControllerGroupPresenter.addCreateController(createController2, label2);

		ArgumentCaptor<String> labelsCaptor = ArgumentCaptor.forClass(String.class);
		verify(viewManagerMock.createControllerGroupView, times(2)).
				addCreateController(controllerArgumentCaptor.capture(), labelsCaptor.capture());
		List<CreateController<?>> createControllerResult = controllerArgumentCaptor.getAllValues();
		List<String> labelsResult = labelsCaptor.getAllValues();
		assertEquals(createController1, createControllerResult.get(0));
		assertEquals(label1, labelsResult.get(0));
		assertEquals(createController2, createControllerResult.get(1));
		assertEquals(label2, labelsResult.get(1));
	}

	@Test
	public void testOnActivateTrue() throws Exception {
		// add controller to presenter
		createControllerGroupPresenter.addCreateController(createController1, label1);
		createControllerGroupPresenter.addCreateController(createController2, label2);

        createControllerGroupPresenter.onActivateController(createController1, true);

		verify(createController1).setActive(true);
		verify(createController2).setActive(false);
		verify(createController2, never()).setActive(true);
		assertFalse(graphicsService.getMetaController().isActive());
	}

	@Test
	public void testOnActivateWhileOtherActive() throws Exception {
		// add controller to presenter
		createControllerGroupPresenter.addCreateController(createController1, label1);
		createControllerGroupPresenter.addCreateController(createController2, label2);
		createControllerGroupPresenter.onActivateController(createController1, true);
		reset(createController1);
		reset(createController2);

		createControllerGroupPresenter.onActivateController(createController2, true);

		verify(createController2).setActive(true);
		verify(createController1).setActive(false);
		verify(createController1, never()).setActive(true);
		assertFalse(graphicsService.getMetaController().isActive());
	}

	@Test
	public void testOnDeActivateAfterActive() throws Exception {
		// add controller to presenter
		createControllerGroupPresenter.addCreateController(createController1, label1);
		createControllerGroupPresenter.addCreateController(createController2, label2);
		createControllerGroupPresenter.onActivateController(createController1, true);
		reset(createController1);
		reset(createController2);

		createControllerGroupPresenter.onActivateController(createController1, false);

		verify(createController1).setActive(false);
		verify(createController1, never()).setActive(true);
		verify(createController2).setActive(false);
		verify(createController2, never()).setActive(true);
		assertTrue(graphicsService.getMetaController().isActive());
	}
}
