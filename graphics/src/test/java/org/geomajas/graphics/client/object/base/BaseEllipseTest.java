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
package org.geomajas.graphics.client.object.base;

import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.GraphicsMockSetup;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BaseEllipseTest extends GraphicsMockSetup {

	private BaseEllipse baseEllipse;

	@Test
	public void testConstructorCreatesRoles() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		baseEllipse = createBaseEllipse(bbox);

		// resizable
		Assert.assertTrue(baseEllipse.hasRole(Resizable.TYPE));
		Resizable resizable = baseEllipse.getRole(Resizable.TYPE);
		Assert.assertNotNull(resizable);
		Assert.assertEquals(baseEllipse, resizable);
		// draggable
		Assert.assertTrue(baseEllipse.hasRole(Draggable.TYPE));
		Draggable draggable = baseEllipse.getRole(Draggable.TYPE);
		Assert.assertNotNull(draggable);
		Assert.assertEquals(baseEllipse, draggable);
		// strokable
		Assert.assertTrue(baseEllipse.hasRole(Strokable.TYPE));
		Strokable strokable = baseEllipse.getRole(Strokable.TYPE);
		Assert.assertNotNull(strokable);
		Assert.assertEquals(baseEllipse, strokable);
		// fillable
		Assert.assertTrue(baseEllipse.hasRole(Fillable.TYPE));
		Fillable fillable = baseEllipse.getRole(Fillable.TYPE);
		Assert.assertNotNull(fillable);
		Assert.assertEquals(baseEllipse, fillable);
	}

	@Test
	public void testCloneObjectDraggable() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		baseEllipse = createBaseEllipse(bbox);
		Draggable expected = baseEllipse.getRole(Draggable.TYPE);
		expected.setUserPosition(new Coordinate(2, 15));
		expected.setUserBounds(new Bbox(15, 20, 5, 5));

		Object clone = baseEllipse.cloneObject();

		BaseEllipse baseEllipseClone = assertIsCorrectObject(clone);
		Assert.assertTrue(baseEllipseClone.hasRole(Draggable.TYPE));
		assertRoleEqualityDraggable(expected, baseEllipseClone.getRole(Draggable.TYPE));
	}

	@Test
	public void testCloneObjectResizableRole() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		baseEllipse = createBaseEllipse(bbox);
		Resizable expected = baseEllipse.getRole(Resizable.TYPE);
		expected.setUserPosition(new Coordinate(2, 15));
		expected.setUserBounds(new Bbox(15, 20, 5, 5));

		Object clone = baseEllipse.cloneObject();

		BaseEllipse baseEllipseClone = assertIsCorrectObject(clone);
		Assert.assertTrue(baseEllipseClone.hasRole(Resizable.TYPE));
		assertRoleEqualityResizable(expected, baseEllipseClone.getRole(Resizable.TYPE));
	}

	@Test
	public void testCloneObjectStrokable() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		baseEllipse = createBaseEllipse(bbox);
		Strokable expected = baseEllipse.getRole(Strokable.TYPE);
		expected.setStrokeColor("strokeColor");
		expected.setStrokeOpacity(0.3);
		expected.setStrokeWidth(8);

		Object clone = baseEllipse.cloneObject();

		BaseEllipse baseEllipseClone = assertIsCorrectObject(clone);
		Assert.assertTrue(baseEllipseClone.hasRole(Strokable.TYPE));
		assertRoleEqualityStrokable(expected, baseEllipseClone.getRole(Strokable.TYPE));
	}

	@Test
	public void testCloneObjectFillable() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		baseEllipse = createBaseEllipse(bbox);
		Fillable expected = baseEllipse.getRole(Fillable.TYPE);
		expected.setFillColor("fillColor");
		expected.setFillOpacity(0.4);

		Object clone = baseEllipse.cloneObject();

		BaseEllipse baseEllipseClone = assertIsCorrectObject(clone);
		Assert.assertTrue(baseEllipseClone.hasRole(Fillable.TYPE));
		assertRoleEqualityFillable(expected, baseEllipseClone.getRole(Fillable.TYPE));
	}

	private BaseEllipse createBaseEllipse(Bbox bbox) {
		return new BaseEllipse(bbox);
	}

	private BaseEllipse assertIsCorrectObject(Object clone) {
		Assert.assertNotNull(clone);
		Assert.assertTrue(clone instanceof BaseEllipse);
		return (BaseEllipse) clone;
	}
}
