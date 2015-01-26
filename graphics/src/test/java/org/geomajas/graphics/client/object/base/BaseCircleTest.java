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

public class BaseCircleTest extends GraphicsMockSetup {

	private BaseCircle baseCircle;

	@Test
	public void testConstructorCreatesRoles() throws Exception {
		baseCircle = createBaseCircle(15, 20, 5);

		// resizable
		Assert.assertTrue(baseCircle.hasRole(Resizable.TYPE));
		Resizable resizable = baseCircle.getRole(Resizable.TYPE);
		Assert.assertNotNull(resizable);
		Assert.assertEquals(baseCircle, resizable);
		// draggable
		Assert.assertTrue(baseCircle.hasRole(Draggable.TYPE));
		Draggable draggable = baseCircle.getRole(Draggable.TYPE);
		Assert.assertNotNull(draggable);
		Assert.assertEquals(baseCircle, draggable);
		// strokable
		Assert.assertTrue(baseCircle.hasRole(Strokable.TYPE));
		Strokable strokable = baseCircle.getRole(Strokable.TYPE);
		Assert.assertNotNull(strokable);
		Assert.assertEquals(baseCircle, strokable);
		// fillable
		Assert.assertTrue(baseCircle.hasRole(Fillable.TYPE));
		Fillable fillable = baseCircle.getRole(Fillable.TYPE);
		Assert.assertNotNull(fillable);
		Assert.assertEquals(baseCircle, fillable);
	}

	@Test
	public void testCloneObjectDraggable() throws Exception {
		baseCircle = createBaseCircle(15, 20, 5);
		Draggable expected = baseCircle.getRole(Draggable.TYPE);
		expected.setUserPosition(new Coordinate(2, 15));
		expected.setUserBounds(new Bbox(15, 20, 5, 5));

		Object clone = baseCircle.cloneObject();

		BaseCircle baseCircleClone = assertIsCorrectObject(clone);
		Assert.assertTrue(baseCircleClone.hasRole(Draggable.TYPE));
		assertRoleEqualityDraggable(expected, baseCircleClone.getRole(Draggable.TYPE));
	}

	@Test
	public void testCloneObjectResizableRole() throws Exception {
		baseCircle = createBaseCircle(15, 20, 5);
		Resizable expected = baseCircle.getRole(Resizable.TYPE);
		expected.setUserPosition(new Coordinate(2, 15));
		expected.setUserBounds(new Bbox(15, 20, 5, 5));

		Object clone = baseCircle.cloneObject();

		BaseCircle baseCircleClone = assertIsCorrectObject(clone);
		Assert.assertTrue(baseCircleClone.hasRole(Resizable.TYPE));
		assertRoleEqualityResizable(expected, baseCircleClone.getRole(Resizable.TYPE));
	}

	@Test
	public void testCloneObjectStrokable() throws Exception {
		baseCircle = createBaseCircle(15, 20, 5);
		Strokable expected = baseCircle.getRole(Strokable.TYPE);
		expected.setStrokeColor("strokeColor");
		expected.setStrokeOpacity(0.3);
		expected.setStrokeWidth(8);

		Object clone = baseCircle.cloneObject();

		BaseCircle baseCircleClone = assertIsCorrectObject(clone);
		Assert.assertTrue(baseCircleClone.hasRole(Strokable.TYPE));
		assertRoleEqualityStrokable(expected, baseCircleClone.getRole(Strokable.TYPE));
	}

	@Test
	public void testCloneObjectFillable() throws Exception {
		baseCircle = createBaseCircle(15, 20, 5);
		Fillable expected = baseCircle.getRole(Fillable.TYPE);
		expected.setFillColor("fillColor");
		expected.setFillOpacity(0.4);

		Object clone = baseCircle.cloneObject();

		BaseCircle baseCircleClone = assertIsCorrectObject(clone);
		Assert.assertTrue(baseCircleClone.hasRole(Fillable.TYPE));
		assertRoleEqualityFillable(expected, baseCircleClone.getRole(Fillable.TYPE));
	}

	private BaseCircle createBaseCircle(double x, double y, double radius) {
		return new BaseCircle(x, y, radius);
	}

	private BaseCircle assertIsCorrectObject(Object clone) {
		Assert.assertNotNull(clone);
		Assert.assertTrue(clone instanceof BaseCircle);
		return (BaseCircle) clone;
	}
}
