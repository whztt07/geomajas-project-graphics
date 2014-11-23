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
import org.geomajas.graphics.client.object.role.Resizable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BaseCircleTest extends GraphicsMockSetup {

	private BaseCircle baseCircle;

	@Before
	public void construct() throws Exception {
		baseCircle = new BaseCircle(15, 20, 5);
	}

	@Test
	public void testConstructorCreatesRoles() throws Exception {
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
	}

	@Test
	public void testCloneObjectDraggable() throws Exception {
		Draggable expected = baseCircle.getRole(Draggable.TYPE);
		expected.setUserPosition(new Coordinate(2, 15));
		expected.setUserBounds(new Bbox(15, 20, 5, 5));

		Object clone = baseCircle.cloneObject();

		Assert.assertNotNull(clone);
		Assert.assertTrue(clone instanceof BaseCircle);
		BaseCircle baseCircleClone = (BaseCircle) clone;
		Assert.assertTrue(baseCircleClone.hasRole(Draggable.TYPE));
		assertRoleEqualityDraggable(expected, baseCircleClone.getRole(Draggable.TYPE));
	}

	@Test
	public void testCloneObjectResizableRole() throws Exception {
		Resizable expected = baseCircle.getRole(Resizable.TYPE);
		expected.setUserPosition(new Coordinate(2, 15));
		expected.setUserBounds(new Bbox(15, 20, 5, 5));

		Object clone = baseCircle.cloneObject();

		Assert.assertNotNull(clone);
		Assert.assertTrue(clone instanceof BaseCircle);
		BaseCircle baseCircleClone = (BaseCircle) clone;
		Assert.assertTrue(baseCircleClone.hasRole(Resizable.TYPE));
		assertRoleEqualityResizable(expected, baseCircleClone.getRole(Resizable.TYPE));
	}
}
