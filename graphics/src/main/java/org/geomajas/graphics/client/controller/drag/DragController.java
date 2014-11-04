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

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import org.geomajas.geometry.service.BboxService;
import org.geomajas.graphics.client.controller.UpdateHandlerGraphicsController;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.service.GraphicsService;
import org.vaadin.gwtgraphics.client.Group;

/**
 * {@link org.geomajas.graphics.client.controller.UpdateHandlerGraphicsController}
 * that handles dragging.
 * 
 * @author Jan De Moerloose
 * 
 */
public class DragController extends UpdateHandlerGraphicsController implements MouseDownHandler {

	/**
	 * Object under control.
	 */
	private Draggable object;

	/**
	 * Handler to drag our object.
	 */
	private GraphicsObjectDragHandler dragHandler;

	public DragController(GraphicsObject object, GraphicsService service) {
		super(service, object);
		this.object = object.getRole(Draggable.TYPE);
	}

	/**
	 * This {@link MouseDownHandler} handler method is called from the
	 * {@link org.geomajas.graphics.client.controller.MetaController}.
	 *
	 * @param event
	 */
	@Override
	public void onMouseDown(MouseDownEvent event) {
		if (isActive()) {
			if (BboxService.contains(object.getUserBounds(), getUserCoordinate(event))) {
				dragHandler.onMouseDown(event);
				event.stopPropagation();
			}
		}
	}

	@Override
	protected void init() {
		setHandlerGroup(new Group());
		// create the drag handler and attach it
		dragHandler = new GraphicsObjectDragHandler(getObject(), getService(), this);
		getHandlerGroup().add(dragHandler.getInvisbleMaskGraphicsObject().asObject());
		// update positions
		updateHandlers();
		// add the group
		getContainer().add(getHandlerGroup());
	}

	@Override
	public void updateHandlers() {
		if (dragHandler != null) {
			dragHandler.update();
		}
	}
}
