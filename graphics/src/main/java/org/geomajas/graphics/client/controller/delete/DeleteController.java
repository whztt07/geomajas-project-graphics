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
package org.geomajas.graphics.client.controller.delete;

import org.geomajas.graphics.client.controller.AbstractInterruptibleGraphicsController;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.operation.RemoveOperation;
import org.geomajas.graphics.client.service.GraphicsService;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;

/**
 * Controller to delete objects by DELETE key.
 * 
 * @author Jan De Moerloose
 * 
 */
public class DeleteController extends AbstractInterruptibleGraphicsController implements NativePreviewHandler {

	public DeleteController(GraphicsObject object, GraphicsService graphicsService) {
		super(graphicsService, object);
		Event.addNativePreviewHandler(this);
	}

	@Override
	public void onPreviewNativeEvent(NativePreviewEvent event) {
		if (event.getTypeInt() == Event.ONKEYDOWN) {
			NativeEvent ne = event.getNativeEvent();
			if (ne.getKeyCode() == KeyCodes.KEY_DELETE || (ne.getCtrlKey() && ne.getKeyCode() == 'X')) {
				if (isActive()) {
					execute(new RemoveOperation(getObject()));
				}
			}
		}
	}
}
