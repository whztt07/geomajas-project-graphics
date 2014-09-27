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

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Widget;
import org.geomajas.graphics.client.controller.create.CreateController;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ToggleButton;

/**
 * This toolbar contains a number of toggle buttons.
 * When a button is clicked, a controller is activated.
 * Only one controller can be active at the time.
 * 
 * @author Jan Venstermans
 */
public class CreateControllerGroupView implements CreateControllerGroupPresenter.View {
	
	private CreateControllerGroupPresenter.Handler handler;
	
	private List<CreateControllerButton> buttons;

	private FlowPanel flowPanel;
	
	public CreateControllerGroupView() {
		buttons = new ArrayList<CreateControllerButton>();
		flowPanel = new FlowPanel();
	}

	@Override
	public void setHandler(CreateControllerGroupPresenter.Handler handler) {
		this.handler = handler;
	}

	@Override
	public void addCreateController(CreateController<?> controller, String buttonLabel) {
		CreateControllerButton button = new CreateControllerButton(handler, controller, buttonLabel);
		buttons.add(button);
		flowPanel.add(button);
	}

	@Override
	public void setAllControllersInactive() {
		for (CreateControllerButton button : buttons) {
			button.setDown(false);
		}
	}

	@Override
	public void setControllerInactive(CreateController<?> createController) {
		for (CreateControllerButton button : buttons) {
			if (button.getController() == createController) {
				button.setDown(true);
			}
		}
	}
	
	@Override
	public Widget asWidget() {
		return flowPanel;
	}

	/**
	 * {@ ToggleButton} that triggers a {@ CreateController}.
	 */
	public class CreateControllerButton extends ToggleButton {

		private CreateController<?> controller;
		
		private CreateControllerGroupPresenter.Handler handler;
		
		public CreateControllerButton(CreateControllerGroupPresenter.Handler handler, final
				CreateController<?> controller, String buttonLabel) {
			super(buttonLabel);
			this.handler = handler;
			this.controller = controller;
			addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					boolean create = isDown();
					setDown(false);
					CreateControllerButton.this.handler.onActivateController(
							getController(), create);
				}
			});
		}
		
		public CreateController<?> getController() {
			return controller;
		}

	}
	
}
