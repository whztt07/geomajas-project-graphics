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
package org.geomajas.graphics.client.widget.createcontrollergroup;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import org.geomajas.graphics.client.controller.create.CreateController;

/**
 * MVP interface(s) for a widget that contains a number of {@link CreateController}s.
 * 
 * @author Jan Venstermans
 * 
 */
public interface CreateControllerGroupPresenter {

	void addCreateController(CreateController<?> createTextController, String text);

	Widget getWidget();

	/**
	 * Toolbar view.
	 */
	public interface View extends IsWidget {

		void setHandler(Handler handler);

		void addCreateController(CreateController<?> createController, String buttonlabel);

		void setAllControllersInactive();

		void setControllerInactive(CreateController<?> createController);
	}

	/**
	 * Toolbar Handler.
	 */
	public interface Handler {

		/**
		 * The {@link CreateController} is accompanied by action create/undo.
		 * @param controller
		 * @param create
		 */
		void onActivateController(CreateController<?> controller, boolean create);
	}
	
}
