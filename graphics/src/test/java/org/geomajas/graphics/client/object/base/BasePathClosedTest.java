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
import org.junit.Test;

public class BasePathClosedTest extends GraphicsMockSetup {

	private BasePath basePath;

	@Test
	public void testConstructorUnclosedPathCreatesRoles() throws Exception {
		Coordinate[] coordinates =  new Coordinate[] { new Coordinate(0,0), new Coordinate(2,5), new Coordinate(-1,3)};
		basePath = createBasePath(coordinates, true);

		// strokable
		Assert.assertTrue(basePath.hasRole(Strokable.TYPE));
		Strokable strokable = basePath.getRole(Strokable.TYPE);
		Assert.assertNotNull(strokable);
		Assert.assertEquals(basePath, strokable);
		// fillable
		Assert.assertTrue(basePath.hasRole(Fillable.TYPE));
		Fillable fillable = basePath.getRole(Fillable.TYPE);
		Assert.assertNotNull(fillable);
		Assert.assertEquals(basePath, fillable);
		// resizable
		Assert.assertTrue(basePath.hasRole(Resizable.TYPE));
		Resizable resizable = basePath.getRole(Resizable.TYPE);
		Assert.assertNotNull(resizable);
		Assert.assertEquals(basePath, resizable);
		// draggable
		Assert.assertTrue(basePath.hasRole(Draggable.TYPE));
		Draggable draggable = basePath.getRole(Draggable.TYPE);
		Assert.assertNotNull(draggable);
		Assert.assertEquals(basePath, draggable);
		// coordinateBased
		Assert.assertTrue(basePath.hasRole(CoordinateBased.TYPE));
		CoordinateBased coordinateBased = basePath.getRole(CoordinateBased.TYPE);
		Assert.assertNotNull(coordinateBased);
		Assert.assertEquals(basePath, coordinateBased);
	}

	@Test
	public void testCloneObjectDraggable() throws Exception {
		Coordinate[] coordinates =  new Coordinate[] { new Coordinate(0,0), new Coordinate(2,5), new Coordinate(-1,3)};
		basePath = createBasePath(coordinates, true);
		Draggable expected = basePath.getRole(Draggable.TYPE);
		expected.setUserPosition(new Coordinate(2, 15));
		expected.setUserBounds(new Bbox(15, 20, 5, 5));

		Object clone = basePath.cloneObject();

		Assert.assertNotNull(clone);
		Assert.assertTrue(clone instanceof BasePath);
		BasePath basePathClone = (BasePath) clone;
		Assert.assertTrue(basePathClone.hasRole(Draggable.TYPE));
		assertRoleEqualityDraggable(expected, basePathClone.getRole(Draggable.TYPE));
	}

	@Test
	public void testCloneObjectResizableRole() throws Exception {
		Coordinate[] coordinates =  new Coordinate[] { new Coordinate(0,0), new Coordinate(2,5), new Coordinate(-1,3)};
		basePath = createBasePath(coordinates, true);
		Resizable expected = basePath.getRole(Resizable.TYPE);
		expected.setUserPosition(new Coordinate(2, 15));
		expected.setUserBounds(new Bbox(15, 20, 5, 5));

		Object clone = basePath.cloneObject();

		Assert.assertNotNull(clone);
		Assert.assertTrue(clone instanceof BasePath);
		BasePath basePathClone = (BasePath) clone;
		Assert.assertTrue(basePathClone.hasRole(Resizable.TYPE));
		assertRoleEqualityResizable(expected, basePathClone.getRole(Resizable.TYPE));
	}

	@Test
	public void testCloneObjectStrokable() throws Exception {
		Coordinate[] coordinates =  new Coordinate[] { new Coordinate(0,0), new Coordinate(2,5), new Coordinate(-1,3)};
		basePath = createBasePath(coordinates, true);
		Strokable expected = basePath.getRole(Strokable.TYPE);
		expected.setStrokeColor("strokeColor");
		expected.setStrokeOpacity(0.3);
		expected.setStrokeWidth(8);

		Object clone = basePath.cloneObject();

		Assert.assertNotNull(clone);
		Assert.assertTrue(clone instanceof BasePath);
		BasePath basePathClone = (BasePath) clone;
		Assert.assertTrue(basePathClone.hasRole(Strokable.TYPE));
		assertRoleEqualityStrokable(expected, basePathClone.getRole(Strokable.TYPE));
	}

	@Test
	public void testCloneObjectFillable() throws Exception {
		Coordinate[] coordinates =  new Coordinate[] { new Coordinate(0,0), new Coordinate(2,5), new Coordinate(-1,3)};
		basePath = createBasePath(coordinates, true);
		Fillable expected = basePath.getRole(Fillable.TYPE);
		expected.setFillColor("fillColor");
		expected.setFillOpacity(0.4);

		Object clone = basePath.cloneObject();

		Assert.assertNotNull(clone);
		Assert.assertTrue(clone instanceof BasePath);
		BasePath basePathClone = (BasePath) clone;
		Assert.assertTrue(basePathClone.hasRole(Fillable.TYPE));
		assertRoleEqualityFillable(expected, basePathClone.getRole(Fillable.TYPE));
		Assert.assertTrue(basePathClone.isClosed());
	}

	private BasePath createBasePath(Coordinate[] coordinates, boolean closed) {
		return new BasePath(coordinates, closed);
	}

}
