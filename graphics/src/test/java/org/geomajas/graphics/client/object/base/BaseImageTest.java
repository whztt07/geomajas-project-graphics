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

public class BaseImageTest extends GraphicsMockSetup {

	private BaseImage baseImage;

	private String url = "testUrl";

	@Before
	public void construct() throws Exception {
		baseImage = new BaseImage(15, 20, 5, 8, url, true);
	}

	@Test
	public void testConstructorCreatesRoles() throws Exception {
		// resizable
		Assert.assertTrue(baseImage.hasRole(Resizable.TYPE));
		Resizable resizable = baseImage.getRole(Resizable.TYPE);
		Assert.assertNotNull(resizable);
		Assert.assertEquals(baseImage, resizable);
		// draggable
		Assert.assertTrue(baseImage.hasRole(Draggable.TYPE));
		Draggable draggable = baseImage.getRole(Draggable.TYPE);
		Assert.assertNotNull(draggable);
		Assert.assertEquals(baseImage, draggable);
	}

	@Test
	public void testCloneObjectDraggable() throws Exception {
		Draggable expected = baseImage.getRole(Draggable.TYPE);
		expected.setUserPosition(new Coordinate(2, 15));

		Object clone = baseImage.cloneObject();

		Assert.assertNotNull(clone);
		Assert.assertTrue(clone instanceof BaseImage);
		BaseImage baseImageClone = (BaseImage) clone;
		Assert.assertTrue(baseImageClone.hasRole(Draggable.TYPE));
		assertRoleEqualityDraggable(expected, baseImageClone.getRole(Draggable.TYPE));
	}

	@Test
	public void testCloneObjectResizableRole() throws Exception {
		Resizable expected = baseImage.getRole(Resizable.TYPE);
		expected.setUserPosition(new Coordinate(2, 15));

		Object clone = baseImage.cloneObject();

		Assert.assertNotNull(clone);
		Assert.assertTrue(clone instanceof BaseImage);
		BaseImage baseImageClone = (BaseImage) clone;
		Assert.assertTrue(baseImageClone.hasRole(Resizable.TYPE));
		assertRoleEqualityResizable(expected, baseImageClone.getRole(Resizable.TYPE));
	}
}
