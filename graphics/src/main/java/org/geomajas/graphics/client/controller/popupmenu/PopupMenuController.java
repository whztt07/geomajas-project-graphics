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
package org.geomajas.graphics.client.controller.popupmenu;

import com.google.gwt.user.client.ui.IsWidget;
import org.geomajas.graphics.client.action.Action;
import org.geomajas.graphics.client.controller.VisibleOnActiveGraphicsController;
import org.geomajas.graphics.client.event.GraphicsObjectContainerEvent;
import org.geomajas.graphics.client.event.GraphicsOperationEvent;

/**
 * Controller that shows a popup menu at the upper left corner of the
 * {@link org.geomajas.graphics.client.object.GraphicsObject}. The menu is created only
 * once when the controller is initalized.
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 *
 */
public interface PopupMenuController extends GraphicsObjectContainerEvent.Handler,
	GraphicsOperationEvent.Handler, VisibleOnActiveGraphicsController {

	/**
	 * MVP view part of {@link PopupMenuController}.
	 *
	 * @author Jan Venstermans
	 *
	 */
	public interface View {

		void addHandler(Handler handler);

		void hide();

		void addAction(String label, Action action);

		void show(int clientX, int clientY);
	}

	/**
	 * MVP view handler part of {@link PopupMenuController}.
	 *
	 * @author Jan Venstermans
	 *
	 */
	public interface Handler {

		void onSelectAction(Action action);
	}

	/**
	 * MVP editor view part of {@link PopupMenuController}.
	 *
	 * @author Jan Venstermans
	 *
	 */
	public interface EditorView extends IsWidget {

		void addHandler(EditorHandler editorHandler);

		void center();

		void hide();

	}

	/**
	 * MVP editor view Handler part of {@link PopupMenuController}.
	 *
	 * @author Jan Venstermans
	 *
	 */
	public interface EditorHandler {

		void onOk();

		void onApply();

		void onUndo();

		void onCancel();
	}

}
