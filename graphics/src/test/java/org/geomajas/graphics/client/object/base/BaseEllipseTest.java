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
import org.geomajas.graphics.client.object.role.CoordinateBased;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BaseEllipseTest extends GraphicsMockSetup {

	private BaseEllipse baseEllipse;

	@Before
	public void construct() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		baseEllipse = new BaseEllipse(bbox);
	}

	@Test
	public void testConstructorUnclosedPathCreatesRoles() throws Exception {
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
	}

	@Test
	public void testCloneObjectDraggable() throws Exception {
		Draggable expected = baseEllipse.getRole(Draggable.TYPE);
		expected.setUserPosition(new Coordinate(2, 15));
		expected.setUserBounds(new Bbox(15, 20, 5, 5));

		Object clone = baseEllipse.cloneObject();

		Assert.assertNotNull(clone);
		Assert.assertTrue(clone instanceof BaseEllipse);
		BaseEllipse basePathClone = (BaseEllipse) clone;
		Assert.assertTrue(basePathClone.hasRole(Draggable.TYPE));
		assertRoleEqualityDraggable(expected, basePathClone.getRole(Draggable.TYPE));
	}

	@Test
	public void testCloneObjectResizableRole() throws Exception {
		Resizable expected = baseEllipse.getRole(Resizable.TYPE);
		expected.setUserPosition(new Coordinate(2, 15));
		expected.setUserBounds(new Bbox(15, 20, 5, 5));

		Object clone = baseEllipse.cloneObject();

		Assert.assertNotNull(clone);
		Assert.assertTrue(clone instanceof BaseEllipse);
		BaseEllipse basePathClone = (BaseEllipse) clone;
		Assert.assertTrue(basePathClone.hasRole(Resizable.TYPE));
		assertRoleEqualityResizable(expected, basePathClone.getRole(Resizable.TYPE));
	}
}
