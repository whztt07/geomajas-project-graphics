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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BaseIconTest extends GraphicsMockSetup {

	private BaseIcon baseIcon;

	private String url = "testUrl";

	@Before
	public void construct() throws Exception {
		baseIcon = new BaseIcon(15, 20, 5, 8, url);
	}

	@Test
	public void testConstructorCreatesRoles() throws Exception {
		// draggable
		Assert.assertTrue(baseIcon.hasRole(Draggable.TYPE));
		Draggable draggable = baseIcon.getRole(Draggable.TYPE);
		Assert.assertNotNull(draggable);
		Assert.assertEquals(baseIcon, draggable);
	}

	@Test
	public void testCloneObjectDraggable() throws Exception {
		Draggable expected = baseIcon.getRole(Draggable.TYPE);
		expected.setUserPosition(new Coordinate(2, 15));
		expected.setUserBounds(new Bbox(15, 20, 5, 5));

		Object clone = baseIcon.cloneObject();

		Assert.assertNotNull(clone);
		Assert.assertTrue(clone instanceof BaseIcon);
		BaseIcon baseIconClone = (BaseIcon) clone;
		Assert.assertTrue(baseIconClone.hasRole(Draggable.TYPE));
		assertRoleEqualityDraggable(expected, baseIconClone.getRole(Draggable.TYPE));
	}

}
