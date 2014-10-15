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

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.GraphicsMockSetup;
import org.geomajas.graphics.client.object.Draggable;
import org.geomajas.graphics.client.object.role.Textable;
import org.junit.Assert;
import org.junit.Test;

public class BaseTextTest extends GraphicsMockSetup {

	private BaseText baseText;

	@Test
	public void testConstructorCreatesRoles() throws Exception {
		Coordinate coordinate =  new Coordinate(15, 20);
		String startString = "startString";
		baseText = createBaseText(coordinate.getX(), coordinate.getY(), startString);

		// textable
		Assert.assertTrue(baseText.hasRole(Textable.TYPE));
		Textable textable = baseText.getRole(Textable.TYPE);
		Assert.assertNotNull(textable);
		Assert.assertEquals(baseText, textable);
		// draggable
		Assert.assertTrue(baseText.hasRole(Draggable.TYPE));
		Draggable draggable = baseText.getRole(Draggable.TYPE);
		Assert.assertNotNull(draggable);
		Assert.assertEquals(baseText, draggable);
	}

	@Test
	public void testCloneObjectTextable() throws Exception {
		Coordinate coordinate =  new Coordinate(15, 20);
		String startString = "startString";
		baseText = createBaseText(coordinate.getX(), coordinate.getY(), startString);
		Textable textable = baseText.getRole(Textable.TYPE);
		textable.setFontColor("fontColorTest");
		textable.setFontFamily("fontFamilyTest");
		textable.setFontSize(52);

		Object clone = baseText.cloneObject();

		Assert.assertNotNull(clone);
		Assert.assertTrue(clone instanceof BaseText);
		BaseText baseTextClone = (BaseText) clone;
		Assert.assertTrue(baseTextClone.hasRole(Textable.TYPE));
		assertRoleEqualityTextable(baseText.getRole(Textable.TYPE), baseTextClone.getRole(Textable.TYPE));
	}

	@Test
	public void testCloneObjectDraggable() throws Exception {
		Coordinate coordinate =  new Coordinate(15, 20);
		String startString = "startString";
		baseText = createBaseText(coordinate.getX(), coordinate.getY(), startString);

		Object clone = baseText.cloneObject();

		Assert.assertNotNull(clone);
		Assert.assertTrue(clone instanceof BaseText);
		BaseText baseTextClone = (BaseText) clone;
		Assert.assertTrue(baseTextClone.hasRole(Draggable.TYPE));
		assertRoleEqualityDraggable(baseText.getRole(Draggable.TYPE), baseTextClone.getRole(Draggable.TYPE));
	}

	private BaseText createBaseText(double userX, double userY, String text) {
		return new BaseText(userX, userY, text);
	}

}
