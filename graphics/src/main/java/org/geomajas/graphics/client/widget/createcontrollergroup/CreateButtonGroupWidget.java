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

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import org.geomajas.graphics.client.Graphics;
import org.geomajas.graphics.client.controller.create.CreateController;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * Default implementation of
 * {@link org.geomajas.graphics.client.widget.createcontrollergroup.CreateControllerGroupPresenter}.
 *
 * @author Jan Venstermans
 *
 */
public class CreateButtonGroupWidget implements IsWidget {

	private CreateControllerGroupPresenter.View view;

	private CreateControllerGroupPresenter presenter;

	public CreateButtonGroupWidget(GraphicsService service) {
		this(service, Graphics.getViewManager().createCreateControllerGroupView());
	}

	public CreateButtonGroupWidget(GraphicsService service, CreateControllerGroupPresenter.View view) {
		this.view = view;
		presenter = new CreateControllerGroupPresenterImpl(service, view);
	}

	@Override
	public Widget asWidget() {
		return view.asWidget();
	}

	public void addCreateController(CreateController createController, String label) {
		presenter.addCreateController(createController, label);
	}
}
