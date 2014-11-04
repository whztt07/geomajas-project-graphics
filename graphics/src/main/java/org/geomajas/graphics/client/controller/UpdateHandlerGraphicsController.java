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
package org.geomajas.graphics.client.controller;

import org.geomajas.graphics.client.event.GraphicsObjectContainerEvent;
import org.geomajas.graphics.client.event.GraphicsObjectContainerEvent.ActionType;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.service.GraphicsService;
import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.VectorObjectContainer;

/**
 * Extension of {@link AbstractInterruptibleGraphicsController} containing a {@link Group} of handlers
 * and s {@link VectorObjectContainer}.
 * This class also listens to {@link org.geomajas.graphics.client.event.GraphicsObjectContainerEvent}; upon Update,
 * the {@link VectorObjectContainer} and {@link Group} of handlers will be updated.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public abstract class UpdateHandlerGraphicsController extends AbstractInterruptibleGraphicsController
		implements GraphicsObjectContainerEvent.Handler {
	
	/**
	 * Group with all handler objects.
	 */
	private Group handlerGroup;
	
	/**
	 * Our own container.
	 */
	private VectorObjectContainer container;

	public UpdateHandlerGraphicsController(GraphicsService graphicsService, GraphicsObject object) {
		super(graphicsService, object);
		// create container
		setContainer(createContainer());
		// listen to changes to our object
		getObjectContainer().addGraphicsObjectContainerHandler(this);
	}

	@Override
	public void setActive(boolean active) {
		if (active != isActive()) {
			super.setActive(active);
			if (isActive()) {
				if (getHandlerGroup() == null || getHandlerGroup().getVectorObjectCount() < 1) {
					// create and (implicitly) activate the handler group
					init();
				} else {
					// the group may be detached, update and reattach !
					updateHandlers();
					getContainer().add(getHandlerGroup());
				}
				bringContainerToFront(getContainer());
			} else {
				// just remove the handler group
				if (getHandlerGroup() != null) {
					getContainer().remove(getHandlerGroup());
				}
			}
		}
	}

	@Override
	public void destroy() {
		getContainer().clear();
		removeContainer(getContainer());
	}

	@Override
	public void onAction(GraphicsObjectContainerEvent event) {
		if (event.getObject() == getObject()) {
			switch (event.getActionType()) {
				case UPDATE:
					// must re-initialize as this object has changed (mask)
					getContainer().clear();
					setHandlerGroup(null);
					if (isActive()) {
						init();
					}
					break;
				default:
					// handled by meta controller
					break;
			}
		}
	}

	//--------------------------------
	// abstract methods
	//--------------------------------

	public abstract void updateHandlers();

	protected abstract void init();

	//--------------------------------------------------
	// getters and setters of HandlerGroup and container
	//--------------------------------------------------

	public Group getHandlerGroup() {
		return handlerGroup;
	}

	public void setHandlerGroup(Group handlerGroup) {
		this.handlerGroup = handlerGroup;
	}

	public VectorObjectContainer getContainer() {
		return container;
	}

	public void setContainer(VectorObjectContainer container) {
		this.container = container;
	}
}
