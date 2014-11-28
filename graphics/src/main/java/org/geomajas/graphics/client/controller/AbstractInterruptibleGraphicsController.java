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

import com.google.gwt.event.dom.client.MouseEvent;
import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.operation.GraphicsOperation;
import org.geomajas.graphics.client.service.GraphicsService;
import org.geomajas.graphics.client.service.GraphicsServiceImpl;
import org.geomajas.graphics.client.service.objectcontainer.GraphicsObjectContainer;
import org.geomajas.graphics.client.service.objectcontainer.GraphicsObjectContainer.Space;
import org.geomajas.graphics.client.service.objectcontainer.RenderObjectContainer;
import org.geomajas.graphics.client.util.BboxPosition;
import org.geomajas.graphics.client.util.Interruptible;
import org.vaadin.gwtgraphics.client.VectorObjectContainer;

/**
 * Contains default implementations of {@link GraphicsController} and {@link Interruptible}.
 * It also contains a {@link GraphicsService} and some delegate methods to this {@link GraphicsService}.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public abstract class AbstractInterruptibleGraphicsController implements GraphicsController, Interruptible {

	private GraphicsService graphicsService;

	private GraphicsObject object;

	private boolean active;

	public AbstractInterruptibleGraphicsController(GraphicsService graphicsService) {
		this(graphicsService, null);
	}

	public AbstractInterruptibleGraphicsController(GraphicsService graphicsService, GraphicsObject object) {
		this.graphicsService = graphicsService;
		this.object = object;
	}

	//-----------------------------------------------------------------------
	// default method implementation of GraphicsController
	//-----------------------------------------------------------------------

	@Override
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public boolean isActive() {
		return active;
	}

	@Override
	public void destroy() {
	}

	//-----------------------------------------------------------------------
	// default method implementation of Interruptible : hooks
	//-----------------------------------------------------------------------

	@Override
	public void cancel() {
	}

	@Override
	public void stop() {
	}

	@Override
	public void save() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public boolean isInterrupted() {
		return false;
	}

	@Override
	public boolean isInProgress() {
		return false;
	}

	@Override
	public void start() {
	}

	//-----------------------------------------------------------------------
	// protected methods
	//-----------------------------------------------------------------------

	protected GraphicsObject getObject() {
		return object;
	}

	//-----------------------------------------------------------------------
	// protected methods passing to GraphicsService
	//-----------------------------------------------------------------------

	protected GraphicsService getService() {
		return graphicsService;
	}

	protected void execute(GraphicsOperation operation) {
		graphicsService.execute(operation);
	}

	//-----------------------------------------------------------------------
	// protected methods passing to GraphicsObjectContainer
	//-----------------------------------------------------------------------

	protected GraphicsObjectContainer getObjectContainer() {
		return getService().getObjectContainer();
	}

	protected Coordinate getScreenCoordinate(MouseEvent<?> event) {
		return getObjectContainer().getScreenCoordinate(event);
	}

	protected Coordinate getUserCoordinate(MouseEvent<?> event) {
		return transform(getObjectContainer().getScreenCoordinate(event), Space.SCREEN, Space.USER);
	}

	protected Coordinate transform(Coordinate coordinate, Space from, Space to) {
		return getObjectContainer().transform(coordinate, from, to);
	}

	protected Bbox transform(Bbox bbox, Space from, Space to) {
		return getObjectContainer().transform(bbox, from, to);
	}

	protected BboxPosition transform(BboxPosition position, Space from, Space to) {
		return getObjectContainer().transform(position, from, to);
	}

	//-----------------------------------------------------------------------
	// protected methods passing to GraphicsServiceImpl //TODO review
	//-----------------------------------------------------------------------

	private GraphicsServiceImpl getGraphicsServiceImpl() {
		return (GraphicsServiceImpl) graphicsService;
	}

	protected RenderObjectContainer createContainer() {
		return getGraphicsServiceImpl().createContainer();
	}

	protected void removeContainer(RenderObjectContainer container) {
		getGraphicsServiceImpl().removeContainer(container);
	}

	protected void bringContainerToFront(RenderObjectContainer container) {
		getGraphicsServiceImpl().bringContainerToFront(container);
	}
}
