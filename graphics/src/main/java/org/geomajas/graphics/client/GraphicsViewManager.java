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

/**
 * Interface for specific view elements.
 *
 * @author Jan Venstermans
 *
 */
public interface GraphicsViewManager {

	PopupMenuController.View createPopupMenuView();

	PopupMenuController.EditorView createPopupMenuEditorView(Editor object);
}
