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

import junit.framework.Assert;
import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.object.role.CoordinateBased;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.object.role.Textable;
import org.geomajas.graphics.client.object.updateable.anchored.Anchored;
import org.geomajas.graphics.client.render.shape.RenderElementFactory;
import org.junit.Before;

public class GraphicsMockSetup {

	protected GraphicsViewManagerMock viewManagerMock = new GraphicsViewManagerMock();

	protected RenderElementFactory renderElementFactoryMock = new RenderElementFactoryMock();

	@Before
	public void injectMock() {
		Graphics.setViewManager(viewManagerMock);
		Graphics.setRenderElementFactory(renderElementFactoryMock);
	}

	public void assertRoleEqualityTextable(Textable textableExpected, Textable textableNew) {
		Assert.assertNotNull(textableExpected);
		Assert.assertNotNull(textableNew);
		Assert.assertEquals(textableExpected.getLabel(), textableNew.getLabel());
		Assert.assertEquals(textableExpected.getFontColor(), textableNew.getFontColor());
		Assert.assertEquals(textableExpected.getFontFamily(), textableNew.getFontFamily());
		Assert.assertEquals(textableExpected.getFontSize(), textableNew.getFontSize());
	}

	public void assertRoleEqualityDraggable(Draggable draggableExpected, Draggable draggableNew) {
		Assert.assertNotNull(draggableExpected);
		Assert.assertNotNull(draggableNew);

		Assert.assertNotNull(draggableExpected.getUserPosition());
		Assert.assertNotNull(draggableNew.getUserPosition());
		Coordinate coordinateExpected = draggableExpected.getUserPosition();
		Coordinate coordinateNew = draggableNew.getUserPosition();
		Assert.assertEquals(coordinateExpected.getX(), coordinateNew.getX());
		Assert.assertEquals(coordinateExpected.getY(), coordinateNew.getY());

		Assert.assertNotNull(draggableExpected.getUserBounds());
		Assert.assertNotNull(draggableNew.getUserBounds());
		Bbox bboxExpected = draggableExpected.getUserBounds();
		Bbox bboxNew = draggableNew.getUserBounds();
		Assert.assertEquals(bboxExpected.getX(), bboxNew.getX());
		Assert.assertEquals(bboxExpected.getY(), bboxNew.getY());
		Assert.assertEquals(bboxExpected.getMaxX(), bboxNew.getMaxX());
		Assert.assertEquals(bboxExpected.getMaxY(), bboxNew.getMaxY());
		Assert.assertEquals(bboxExpected.getWidth(), bboxNew.getWidth());
		Assert.assertEquals(bboxExpected.getHeight(), bboxNew.getHeight());
	}

	public void assertRoleEqualityResizable(Resizable resizableExpected, Resizable resizableNew) {
		Assert.assertNotNull(resizableExpected);
		Assert.assertNotNull(resizableNew);

		Assert.assertNotNull(resizableExpected.getUserPosition());
		Assert.assertNotNull(resizableNew.getUserPosition());
		Coordinate coordinateExpected = resizableExpected.getUserPosition();
		Coordinate coordinateNew = resizableNew.getUserPosition();
		Assert.assertEquals(coordinateExpected.getX(), coordinateNew.getX());
		Assert.assertEquals(coordinateExpected.getY(), coordinateNew.getY());

		Assert.assertNotNull(resizableExpected.getUserBounds());
		Assert.assertNotNull(resizableNew.getUserBounds());
		Bbox bboxExpected = resizableExpected.getUserBounds();
		Bbox bboxNew = resizableNew.getUserBounds();
		Assert.assertEquals(bboxExpected.getX(), bboxNew.getX());
		Assert.assertEquals(bboxExpected.getY(), bboxNew.getY());
		Assert.assertEquals(bboxExpected.getMaxX(), bboxNew.getMaxX());
		Assert.assertEquals(bboxExpected.getMaxY(), bboxNew.getMaxY());
		Assert.assertEquals(bboxExpected.getWidth(), bboxNew.getWidth());
		Assert.assertEquals(bboxExpected.getHeight(), bboxNew.getHeight());

		Assert.assertEquals(resizableExpected.isAutoHeight(), resizableNew.isAutoHeight());
		Assert.assertEquals(resizableExpected.isPreserveRatio(), resizableNew.isPreserveRatio());
	}

	public void assertRoleEqualityStrokable(Strokable strokableExpected, Strokable strokableNew) {
		Assert.assertNotNull(strokableExpected);
		Assert.assertNotNull(strokableNew);

		Assert.assertEquals(strokableExpected.getStrokeColor(), strokableNew.getStrokeColor());
		Assert.assertEquals(strokableExpected.getStrokeOpacity(), strokableNew.getStrokeOpacity());
		Assert.assertEquals(strokableExpected.getStrokeWidth(), strokableNew.getStrokeWidth());
	}

	public void assertRoleEqualityFillable(Fillable fillableExpected, Fillable fillableNew) {
		Assert.assertNotNull(fillableExpected);
		Assert.assertNotNull(fillableNew);

		Assert.assertEquals(fillableExpected.getFillColor(), fillableNew.getFillColor());
		Assert.assertEquals(fillableExpected.getFillOpacity(), fillableNew.getFillOpacity());
	}

	public void assertRoleEqualityCoordinateBased(CoordinateBased coordinateBasedExpected,
												  CoordinateBased coordinateBasedNew) {
		Assert.assertNotNull(coordinateBasedExpected);
		Assert.assertNotNull(coordinateBasedNew);

		Assert.assertEquals(coordinateBasedExpected.getCoordinateCount(), coordinateBasedNew.getCoordinateCount());
		Assert.assertEquals(coordinateBasedExpected.getLastCoordinate(), coordinateBasedNew.getLastCoordinate());
		Coordinate[] coordinatesExpected = coordinateBasedExpected.getCoordinates();
		Coordinate[] coordinatesNew = coordinateBasedNew.getCoordinates();
		for (int i = 0 ; i < coordinateBasedExpected.getCoordinateCount() ; i++) {
			Assert.assertEquals(coordinatesExpected[i], coordinatesNew[i]);
		}
	}

	public void assertRoleEqualityAnchored(Anchored anchoredExpected, Anchored anchoredNew) {
		assertRoleEqualityStrokable(anchoredExpected.getAnchorLineStrokable(), anchoredNew.getAnchorLineStrokable());
		assertRoleEqualityStrokable(anchoredExpected.getAnchorMarkerShapeStrokable(),
				anchoredNew.getAnchorMarkerShapeStrokable());
		assertRoleEqualityFillable(anchoredExpected.getAnchorMarkerShapeFillable(),
				anchoredNew.getAnchorMarkerShapeFillable());

		Assert.assertEquals(anchoredExpected.getAnchorPosition(), anchoredNew.getAnchorPosition());
	}

}
