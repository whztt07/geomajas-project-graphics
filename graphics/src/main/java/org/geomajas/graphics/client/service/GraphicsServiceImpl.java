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
package org.geomajas.graphics.client.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.geomajas.graphics.client.controller.DefaultMetaController;
import org.geomajas.graphics.client.controller.GraphicsController;
import org.geomajas.graphics.client.controller.GraphicsControllerFactory;
import org.geomajas.graphics.client.controller.MetaController;
import org.geomajas.graphics.client.event.GraphicsOperationEvent;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.operation.GraphicsOperation;
import org.geomajas.graphics.client.service.objectcontainer.GraphicsObjectContainer;
import org.vaadin.gwtgraphics.client.VectorObjectContainer;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * Default implementation of {@link GraphicsService}.
 * 
 * @author Jan De Moerloose
 * 
 */
public class GraphicsServiceImpl implements GraphicsService, GraphicsOperationEvent.Handler {

	private Stack<GraphicsOperation> undoStack = new Stack<GraphicsOperation>();

	private Stack<GraphicsOperation> redoStack = new Stack<GraphicsOperation>();

	private GraphicsObjectContainer objectContainer;

	private List<GraphicsControllerFactory> controllerFactoryList = new ArrayList<GraphicsControllerFactory>();

	private MetaControllerFactory metaControllerFactory;

	private MetaController metaController;
	
	private EventBus eventBus;

	private HandlerRegistration standardGraphicsOperationEventRegistration;
	
	private boolean showOriginalObjectWhileDragging;
	
	private boolean externalizableLabeledOriginallyExternal;

	private boolean undoKeys;
	private HandlerRegistration undoKeysHandlerRegistration;

	public GraphicsServiceImpl(final EventBus eventBus) {
		this.eventBus = eventBus;
		standardGraphicsOperationEventRegistration = eventBus.addHandler(GraphicsOperationEvent.getType(), this);
		setMetaControllerFactory(null); //create default metaControllerFactory
	}
	
	@Override
	public void start() {
		if (metaController == null) {
			metaController = metaControllerFactory.createController(this);
		}
		metaController.setActive(true);
	}

	@Override
	public void stop() {
		if (metaController != null) {
			metaController.setActive(false);
		}
	}

	@Override
	public void execute(GraphicsOperation operation) {
			undoStack.push(operation);
			redoStack.clear();
			operation.execute();
			eventBus.fireEvent(new GraphicsOperationEvent(operation));
	}

	@Override
	public void undo() {
		if (!undoStack.isEmpty()) {
			GraphicsOperation operation = undoStack.pop();
			operation.undo();
			redoStack.add(operation);
			eventBus.fireEvent(new GraphicsOperationEvent(operation));
		}
	}

	@Override
	public void redo() {
		if (!redoStack.isEmpty()) {
			GraphicsOperation operation = redoStack.pop();
			operation.execute();
			undoStack.push(operation);
			eventBus.fireEvent(new GraphicsOperationEvent(operation));
		}
	}

	@Override
	public HandlerRegistration addGraphicsOperationHandler(GraphicsOperationEvent.Handler handler) {
		standardGraphicsOperationEventRegistration.removeHandler();
		return eventBus.addHandler(GraphicsOperationEvent.getType(), handler);
	}

	@Override
	public void setMetaControllerFactory(MetaControllerFactory metaControllerFactory) {
		if (metaControllerFactory != null) {
			this.metaControllerFactory = metaControllerFactory;
		} else {
			// set default metacontrollerFactory
			this.metaControllerFactory = new MetaControllerFactory() {

				@Override
				public MetaController createController(GraphicsService graphicsService) {
					return new DefaultMetaController(graphicsService);
				}
			};
		}
	}

	@Override
	public GraphicsController getMetaController() {
		return metaController;
	}

	@Override
	public void registerControllerFactory(GraphicsControllerFactory controllerFactory) {
		controllerFactoryList.add(controllerFactory);
	}

	@Override
	public void setObjectContainer(GraphicsObjectContainer objectContainer) {
		this.objectContainer = objectContainer;
	}

	public VectorObjectContainer createContainer() {
		return objectContainer.createContainer();
	}

	public void bringContainerToFront(VectorObjectContainer container) {
		objectContainer.bringContainerToFront(container);
	}

	public void removeContainer(VectorObjectContainer container) {
		objectContainer.removeContainer(container);
	}

	@Override
	public GraphicsObjectContainer getObjectContainer() {
		return objectContainer;
	}

	@Override
	public List<GraphicsControllerFactory> getControllerFactoryList() {
		return controllerFactoryList;
	}

	@Override
	public void update(GraphicsObject object) {
		objectContainer.update(object);
	}
	
	@Override
	public void onOperation(GraphicsOperationEvent event) {
		switch (event.getOperation().getType()) {
			case ADD:
				getObjectContainer().add(event.getOperation().getObject());
				break;
			case REMOVE:
				getObjectContainer().remove(event.getOperation().getObject());
				break;
			case UPDATE:
				getObjectContainer().update(event.getOperation().getObject());
				break;
			default:
				break;
		}
	}

	// BOOLEAN PROPERTIES

	@Override
	public boolean isUndoKeys() {
		return undoKeys;
	}

	@Override
	public void setUndoKeys(boolean undoKeys) {
		this.undoKeys = undoKeys;
		updateUndoKeysHandler();
	}

	@Override
	public boolean isShowOriginalObjectWhileDragging() {
		return showOriginalObjectWhileDragging;
	}

	@Override
	public void setShowOriginalObjectWhileDragging(boolean showOriginalObjectWhileDragging) {
		this.showOriginalObjectWhileDragging = showOriginalObjectWhileDragging;
	}

	@Override
	public boolean isExternalizableLabeledOriginallyExternal() {
		return externalizableLabeledOriginallyExternal;
	}

	@Override
	public void setExternalizableLabeledOriginallyExternal(
			boolean externalizableLabeledOriginallyExternal) {
		this.externalizableLabeledOriginallyExternal = externalizableLabeledOriginallyExternal;
	}

	private void updateUndoKeysHandler() {
		if (isUndoKeys()) {
			if (undoKeysHandlerRegistration == null) {
				undoKeysHandlerRegistration = Event.addNativePreviewHandler(new NativePreviewHandler() {

					@Override
					public void onPreviewNativeEvent(NativePreviewEvent event) {
						if (event.getTypeInt() == Event.ONKEYDOWN) {
							NativeEvent ne = event.getNativeEvent();
							// if CTRL key or META key is down (META for MAC)
							if (ne.getCtrlKey() || ne.getMetaKey()) {
								switch (ne.getKeyCode()) {
									case 'Z':
										event.cancel();
										undo();
										break;
									case 'Y':
										event.cancel();
										redo();
										break;
								}
							}
						}
					}
				});
			}
		} else {
			if (undoKeysHandlerRegistration != null) {
				undoKeysHandlerRegistration.removeHandler();
				undoKeysHandlerRegistration = null;
			}
		}
	}

}
