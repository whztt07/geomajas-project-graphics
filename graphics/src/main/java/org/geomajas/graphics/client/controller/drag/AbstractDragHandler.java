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

import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.controller.MetaController;
import org.geomajas.graphics.client.controller.UpdateHandlerGraphicsController;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.operation.GraphicsOperation;
import org.geomajas.graphics.client.render.RenderableList;
import org.geomajas.graphics.client.service.GraphicsService;
import org.geomajas.graphics.client.service.objectcontainer.GraphicsObjectContainer.Space;
import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Base class for a handling drag functions. Two extra objects are
 * created: an invisible {@link VectorObject} that defines the click area; a
 * visible {@link GraphicsObject} that will be shown as the dragging object.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public abstract class AbstractDragHandler implements MouseDownHandler,
		MouseUpHandler, MouseMoveHandler, ClickHandler, DoubleClickHandler {

	/**
	 * Original object, will not be dragged.
	 */
	private GraphicsObject object;

	/**
	 * Object you can see while dragging.
	 */
	private GraphicsObject draggingMask;

	/**
	 * VectorObject that will be set invisible. It contains the area where you
	 * can click for starting to drag.
	 */
	private VectorObject invisibleClickArea;

	private GraphicsService service;

	/**
	 *  begin position in user coordinates (double, double)
	 */
	private Coordinate beginPositionUser;

	private int beginPositionScreenX;

	private int beginPositionScreenY;

	private Coordinate userBegin;

	private String captureCursor;

	/**
	 * Are we dragging ?
	 */
	private boolean dragging;

	private UpdateHandlerGraphicsController graphicsHandler;

	/**
	 *
	 * @param object needs to have {@link org.geomajas.graphics.client.object.role.Draggable} role.
	 * @param service
	 * @param graphicsHandler
	 */
	public AbstractDragHandler(GraphicsObject object, GraphicsService service,
			UpdateHandlerGraphicsController graphicsHandler) {
		this.object = object;
		this.service = service;
		this.graphicsHandler = graphicsHandler;
		render();
	}

	@Override
	public void onDoubleClick(DoubleClickEvent event) {
	}

	@Override
	public void onClick(ClickEvent event) {
	}

	@Override
	public void onMouseDown(MouseDownEvent event) {
		if (!dragging) {
			capture(invisibleClickArea.getElement(), Cursor.MOVE);
			setDragging(true);
			onDragStart(event.getClientX(), event.getClientY());
			if (draggingMask != null) { // may happen in unusual scenario where
				// mouse-up is not called
				graphicsHandler.getHandlerGroup().removeRenderable(draggingMask);
			}
			draggingMask = createDraggingMask();
			graphicsHandler.getHandlerGroup().addRenderable(draggingMask);
		}
	}

	/** {@inheritDoc} */
	@Override
	public void onMouseMove(MouseMoveEvent event) {
		if (dragging) {
			mouseMoveContent(event);
			onDragContinue();
		}
	}

	/** {@inheritDoc} */
	@Override
	public void onMouseUp(MouseUpEvent event) {
		if (dragging) {
			setDragging(false);
			graphicsHandler.getHandlerGroup().removeRenderable(draggingMask);
			draggingMask = null;
			onDragStop(event.getClientX(), event.getClientY());
			release(invisibleClickArea.getElement());
		}
	}

	public void addToGroup(RenderableList group) {
		group.addRenderable(invisibleClickArea);
	}

	//--------------------------------------------
	// getter methods
	//--------------------------------------------

	public VectorObject getInvisibleMask() {
		return invisibleClickArea;
	}

	public Coordinate getBeginPositionUser() {
		return beginPositionUser;
	}

	public GraphicsObject getObject() {
		return object;
	}

	public GraphicsService getService() {
		return service;
	}

	public GraphicsObject getDraggingMask() {
		return draggingMask;
	}

	public boolean isDragging() {
		return dragging;
	}

	//--------------------------------------------
	// abstract methods
	//--------------------------------------------

	protected abstract VectorObject createInvisibleMask();

	protected abstract GraphicsObject createDraggingMask();

	public abstract void update();

	protected abstract Coordinate getObjectPosition();

	protected abstract GraphicsOperation createGraphicsOperation(Coordinate before, Coordinate after);

	protected abstract void mouseMoveContent(MouseMoveEvent event);

	//--------------------------------------------
	// protected methods
	//--------------------------------------------

	protected void render() {
		if (graphicsHandler.getHandlerGroup() != null) {
			invisibleClickArea = createInvisibleMask();
			invisibleClickArea.addDoubleClickHandler(this);
			invisibleClickArea.addClickHandler(this);
			invisibleClickArea.addMouseDownHandler(this);
			invisibleClickArea.addMouseUpHandler(this);
			invisibleClickArea.addMouseMoveHandler(this);
			invisibleClickArea.getElement().getStyle().setCursor(Cursor.MOVE);
		}
	}

	protected void onDragStart(int x, int y) {
		beginPositionScreenX = x;
		beginPositionScreenY = y;
		userBegin = service.getObjectContainer().transform(new Coordinate(x, y), Space.SCREEN, Space.USER);
		beginPositionUser = (Coordinate) getObjectPosition().clone();
	}

	protected void onDragContinue() {
		graphicsHandler.updateHandlers();
	}

	protected void onDragStop(int x, int y) {
		onDragContinue();
		if (x != beginPositionScreenX && y != beginPositionScreenY) {
			performOperation(beginPositionUser, getNewPosition(x, y));
		}
	}

	private void performOperation(Coordinate before, Coordinate after) {
		service.execute(createGraphicsOperation(before, after));
	}

	protected Coordinate getNewPosition(int x, int y) {
		Coordinate userEnd = service.getObjectContainer().transform(new Coordinate(x, y), Space.SCREEN, Space.USER);
		double dx = userEnd.getX() - userBegin.getX();
		double dy = userEnd.getY() - userBegin.getY();
		return new Coordinate(beginPositionUser.getX() + dx, beginPositionUser.getY() + dy);
	}

	protected void setDragging(boolean draggingNewValue) {
		dragging = draggingNewValue;
		if (!service.isShowOriginalObjectWhileDragging()) {
			object.asObject().setVisible(!dragging);
			((MetaController) service.getMetaController()).setControllersOfObjectVisible(object, !dragging);
		}
	}

	protected void capture(Element element, Cursor cursor) {
		DOM.setCapture(element);
		captureCursor = RootPanel.getBodyElement().getStyle().getCursor();
		RootPanel.getBodyElement().getStyle().setCursor(cursor);
	}

	protected void release(Element element) {
		DOM.releaseCapture(element);
		RootPanel.getBodyElement().getStyle().setProperty("cursor", captureCursor);
	}
}
