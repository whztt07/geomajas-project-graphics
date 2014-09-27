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

import com.google.gwt.user.client.ui.Widget;
import org.geomajas.graphics.client.controller.create.CreateController;
import org.geomajas.graphics.client.event.GraphicsObjectContainerEvent;
import org.geomajas.graphics.client.event.GraphicsObjectContainerEvent.ActionType;
import org.geomajas.graphics.client.service.GraphicsService;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of {@link CreateControllerGroupPresenter}.
 *
 * @author Jan Venstermans
 *
 */
public class CreateControllerGroupPresenterImpl implements CreateControllerGroupPresenter,
		CreateControllerGroupPresenter.Handler, GraphicsObjectContainerEvent.Handler {

	private boolean active;

	private List<CreateController<?>> createControllerList;

	private View view;

	private GraphicsService service;

	public CreateControllerGroupPresenterImpl(GraphicsService service, View view) {
		this.service = service;
		createControllerList = new ArrayList<CreateController<?>>();
		service.getObjectContainer().addGraphicsObjectContainerHandler(this);
		this.view = view;
		this.view.setHandler(this);
	}

	@Override
	public void addCreateController(CreateController<?> createController, String buttonlabel) {
		createControllerList.add(createController);
		view.addCreateController(createController, buttonlabel);
	}

	@Override
	public Widget getWidget() {
		return view.asWidget();
	}

	@Override
	public void onActivateController(CreateController<?> createController, boolean create) {
		if (create /*&& !active*/) {
			setActive(true);
			createController.setActive(active);
			view.setControllerInactive(createController);
			service.getMetaController().setActive(false);
			return;
		}
		if (!create && active) {
			setActive(false);
			service.getMetaController().setActive(true);
		}
	}

	@Override
	public void onAction(GraphicsObjectContainerEvent event) {
		if (active && event.getActionType().equals(ActionType.ADD)) {
			// object created due to one of registered CreateControllers.
			setActive(false);
		}
	}

	private void setActive(boolean active) {
		deactivateControllersAndView();
		this.active = active;
	}

	private void deactivateControllersAndView() {
		for (CreateController<?> controller : createControllerList) {
			controller.setActive(false);
		}
		view.setAllControllersInactive();
	}
}
