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
import org.geomajas.graphics.client.Graphics;
import org.geomajas.graphics.client.GraphicsMockSetup;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BaseRectangleTest extends GraphicsMockSetup {

	private BaseRectangle baseRectangle;

	@Test
	public void testConstructorCreatesRoles() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		baseRectangle = createRectangleText(bbox.getX(), bbox.getY(), bbox.getWidth(), bbox.getHeight());

		// strokable
		Assert.assertTrue(baseRectangle.hasRole(Strokable.TYPE));
		Strokable strokable = baseRectangle.getRole(Strokable.TYPE);
		Assert.assertNotNull(strokable);
		Assert.assertEquals(baseRectangle, strokable);
		// fillable
		Assert.assertTrue(baseRectangle.hasRole(Fillable.TYPE));
		Fillable fillable = baseRectangle.getRole(Fillable.TYPE);
		Assert.assertNotNull(fillable);
		Assert.assertEquals(baseRectangle, fillable);
		// resizable
		Assert.assertTrue(baseRectangle.hasRole(Resizable.TYPE));
		Resizable resizable = baseRectangle.getRole(Resizable.TYPE);
		Assert.assertNotNull(resizable);
		Assert.assertEquals(baseRectangle, resizable);
		// draggable
		Assert.assertTrue(baseRectangle.hasRole(Draggable.TYPE));
		Draggable draggable = baseRectangle.getRole(Draggable.TYPE);
		Assert.assertNotNull(draggable);
		Assert.assertEquals(baseRectangle, draggable);
	}

	@Test
	public void testCloneObjectDraggable() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		baseRectangle = createRectangleText(bbox.getX(), bbox.getY(), bbox.getWidth(), bbox.getHeight());
		Draggable expected = baseRectangle.getRole(Draggable.TYPE);
		expected.setUserPosition(new Coordinate(2, 15));
		expected.setUserBounds(new Bbox(15, 20, 5, 5));

		Object clone = baseRectangle.cloneObject();

		Assert.assertNotNull(clone);
		Assert.assertTrue(clone instanceof BaseRectangle);
		BaseRectangle baseRectangleClone = (BaseRectangle) clone;
		Assert.assertTrue(baseRectangleClone.hasRole(Draggable.TYPE));
		assertRoleEqualityDraggable(expected, baseRectangleClone.getRole(Draggable.TYPE));
	}

	@Test
	 public void testCloneObjectResizable() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		baseRectangle = createRectangleText(bbox.getX(), bbox.getY(), bbox.getWidth(), bbox.getHeight());
		Resizable expected = baseRectangle.getRole(Resizable.TYPE);
		expected.setUserPosition(new Coordinate(2, 15));
		expected.setUserBounds(new Bbox(15, 20, 5, 5));

		Object clone = baseRectangle.cloneObject();

		Assert.assertNotNull(clone);
		Assert.assertTrue(clone instanceof BaseRectangle);
		BaseRectangle baseRectangleClone = (BaseRectangle) clone;
		Assert.assertTrue(baseRectangleClone.hasRole(Resizable.TYPE));
		assertRoleEqualityResizable(expected, baseRectangleClone.getRole(Resizable.TYPE));
	}

	@Test
	public void testCloneObjectStrokable() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		baseRectangle = createRectangleText(bbox.getX(), bbox.getY(), bbox.getWidth(), bbox.getHeight());
		Strokable expected = baseRectangle.getRole(Strokable.TYPE);
		expected.setStrokeColor("strokeColor");
		expected.setStrokeOpacity(0.3);
		expected.setStrokeWidth(8);

		Object clone = baseRectangle.cloneObject();

		Assert.assertNotNull(clone);
		Assert.assertTrue(clone instanceof BaseRectangle);
		BaseRectangle baseRectangleClone = (BaseRectangle) clone;
		Assert.assertTrue(baseRectangleClone.hasRole(Strokable.TYPE));
		assertRoleEqualityStrokable(expected, baseRectangleClone.getRole(Strokable.TYPE));
	}

	@Test
	public void testCloneObjectFillable() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		baseRectangle = createRectangleText(bbox.getX(), bbox.getY(), bbox.getWidth(), bbox.getHeight());
		Fillable expected = baseRectangle.getRole(Fillable.TYPE);
		expected.setFillColor("fillColor");
		expected.setFillOpacity(0.4);

		Object clone = baseRectangle.cloneObject();

		Assert.assertNotNull(clone);
		Assert.assertTrue(clone instanceof BaseRectangle);
		BaseRectangle baseRectangleClone = (BaseRectangle) clone;
		Assert.assertTrue(baseRectangleClone.hasRole(Fillable.TYPE));
		assertRoleEqualityFillable(expected, baseRectangleClone.getRole(Fillable.TYPE));
	}

	private BaseRectangle createRectangleText(double userX, double userY, double width, double height) {
		return new BaseRectangle(userX, userY, width, height);
	}

}
