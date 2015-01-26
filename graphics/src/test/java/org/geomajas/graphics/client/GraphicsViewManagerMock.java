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
package org.geomajas.graphics.client;

import org.geomajas.graphics.client.controller.popupmenu.PopupMenuController;
import org.geomajas.graphics.client.editor.Editor;
import org.geomajas.graphics.client.widget.createcontrollergroup.CreateControllerGroupPresenter;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Starting class for the Graphics Project.
 *
 * @author Jan Venstermans
 *
 */
public class GraphicsViewManagerMock implements GraphicsViewManager {

	@Mock
	public PopupMenuController.View popupMenuView;

	@Mock
	public PopupMenuController.EditorView popupMenuEditorView;

	@Mock
	public CreateControllerGroupPresenter.View createControllerGroupView;

	public GraphicsViewManagerMock() {
		MockitoAnnotations.initMocks(this);
	}

	@Override
	public PopupMenuController.View createPopupMenuView() {
		return popupMenuView;
	}

	@Override
	public PopupMenuController.EditorView createPopupMenuEditorView(Editor object) {
		return popupMenuEditorView;
	}

	@Override
	public CreateControllerGroupPresenter.View createCreateControllerGroupView() {
		return createControllerGroupView;
	}
}
