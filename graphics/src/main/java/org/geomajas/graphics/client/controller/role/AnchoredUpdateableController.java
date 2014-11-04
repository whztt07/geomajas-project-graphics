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
package org.geomajas.graphics.client.controller.role;

import com.google.gwt.event.dom.client.MouseMoveEvent;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.controller.UpdateHandlerGraphicsController;
import org.geomajas.graphics.client.controller.UpdateHandlerGraphicsControllerWithVisibleElement;
import org.geomajas.graphics.client.controller.drag.AbstractDragHandler;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.updateable.anchored.AnchoredUpdateable;
import org.geomajas.graphics.client.operation.AnchoredUpdateablePositionOperation;
import org.geomajas.graphics.client.operation.GraphicsOperation;
import org.geomajas.graphics.client.service.GraphicsService;
import org.geomajas.graphics.client.shape.MarkerShape;
import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.Shape;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Controller to change object anchor.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public class AnchoredUpdateableController extends UpdateHandlerGraphicsControllerWithVisibleElement {

	/**
	 * Object under control.
	 */
	private AnchoredUpdateable anchorPointObject;

	/**
	 * Handler to drag our anchor.
	 */
	private AnchorDragHandler dragHandler;

	public AnchoredUpdateableController(GraphicsObject object, GraphicsService service) {
		super(service, object);
		this.anchorPointObject = object.getRole(AnchoredUpdateable.TYPE);
	}

	@Override
	protected void init() {
		setHandlerGroup(new Group());
		// create the drag handler and attach it
		dragHandler = new AnchorDragHandler(getObject(), getService(), this);
		dragHandler.addToGroup(getHandlerGroup());
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

	@Override
	public void setControllerElementsVisible(boolean visible) {
		getObject().getRole(AnchoredUpdateable.TYPE).setAnchorVisible(visible);
	}

	/**
	 * Implementation of {@link org.geomajas.graphics.client.controller.drag.AbstractDragHandler}
	 * for {@link AnchoredUpdateableController}.
	 * 
	 * @author Jan De Moerloose
	 * @author Jan Venstermans
	 * 
	 */
	class AnchorDragHandler extends AbstractDragHandler {
		
		private Shape invisibleSquareAnchor;

		public AnchorDragHandler(GraphicsObject object, GraphicsService service,
				UpdateHandlerGraphicsController graphicsHandler) {
			super(object, service, graphicsHandler);
		}

		@Override
		public void update() {
			invisibleSquareAnchor.setUserX(anchorPointObject.getAnchorPosition().getX());
			invisibleSquareAnchor.setUserY(anchorPointObject.getAnchorPosition().getY());
		}

		public void addToGroup(Group group) {			
			group.add(invisibleSquareAnchor);
		}
		
		@Override
		protected VectorObject createInvisibleMask() {
			invisibleSquareAnchor = MarkerShape.SQUARE.getMarkerShape();
			invisibleSquareAnchor.setFixedSize(true);
			invisibleSquareAnchor.setFillOpacity(0);
			invisibleSquareAnchor.setStrokeOpacity(0);
			return invisibleSquareAnchor;
		}

		@Override
		protected GraphicsObject createDraggingMask() {
			GraphicsObject maskObject = (GraphicsObject) getObject().cloneObject();
			maskObject.setOpacity(0.5);
			maskObject.getRole(AnchoredUpdateable.TYPE).setAnchorPosition(getBeginPositionUser());
			return maskObject;
		}

		@Override
		protected Coordinate getObjectPosition() {
			return anchorPointObject.getAnchorPosition();
		}

		@Override
		protected GraphicsOperation createGraphicsOperation(Coordinate before, Coordinate after) {
			return new AnchoredUpdateablePositionOperation(getObject(), before, after);
		}

		@Override
		protected void mouseMoveContent(MouseMoveEvent event) {
			Coordinate newAnchorPosition = getNewPosition(event.getClientX(), event.getClientY());
			getDraggingMask().getRole(AnchoredUpdateable.TYPE).setAnchorPosition(newAnchorPosition);
		}

	}
}
