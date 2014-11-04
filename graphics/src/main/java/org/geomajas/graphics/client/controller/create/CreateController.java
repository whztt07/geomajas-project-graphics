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
package org.geomajas.graphics.client.controller.create;

import com.google.web.bindery.event.shared.HandlerRegistration;
import org.geomajas.graphics.client.controller.AbstractInterruptibleGraphicsController;
import org.geomajas.graphics.client.event.GraphicsObjectContainerEvent;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.operation.AddOperation;
import org.geomajas.graphics.client.operation.GraphicsOperation;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * Controller that creates a {@link GraphicsObject}.
 * 
 * @param <T> The type of GraphicsObject created by the controller.
 * 
 * @author Jan Venstermans
 * 
 */
public abstract class CreateController<T extends GraphicsObject> extends AbstractInterruptibleGraphicsController {

	private HandlerRegistration registration;

	public CreateController(GraphicsService graphicsService) {
		super(graphicsService);
	}

	protected void addObject(final T result) {
		execute(new AddOperation(result));
	}

	@Override
	protected void execute(GraphicsOperation operation) {
		// if object is added to the object container, deactivate the controller and activate metacontroller
		if (operation instanceof AddOperation) {
			final AddOperation addOperation = (AddOperation) operation;
			registration = getService().getObjectContainer()
					.addGraphicsObjectContainerHandler(new GraphicsObjectContainerEvent.Handler() {
						@Override
						public void onAction(GraphicsObjectContainerEvent event) {
							if (event.getActionType().equals(GraphicsObjectContainerEvent.ActionType.ADD)
									&& event.getObject().equals(addOperation.getObject())) {
								setActive(false);
								getService().getMetaController().setActive(true);
								if (registration != null) {
									registration.removeHandler();
								}
							}
						}
					});
		}
		super.execute(operation);
	}
}
