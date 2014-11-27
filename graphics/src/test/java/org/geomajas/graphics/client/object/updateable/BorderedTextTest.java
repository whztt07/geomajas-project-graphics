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
package org.geomajas.graphics.client.object.updateable;

import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.GraphicsMockSetup;
import org.geomajas.graphics.client.object.base.BasePath;
import org.geomajas.graphics.client.object.base.BaseText;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.object.role.Textable;
import org.geomajas.graphics.client.object.updateable.bordered.Bordered;
import org.geomajas.graphics.client.object.updateable.bordered.BorderedImpl;
import org.geomajas.graphics.client.object.updateable.bordered.MarginBaseRectangle;
import org.geomajas.graphics.client.object.updateable.wrapper.DraggableWrapperForUpdateable;
import org.geomajas.graphics.client.object.updateable.wrapper.TextableWrapperForUpdateable;
import org.junit.Assert;
import org.junit.Test;

public class BorderedTextTest extends GraphicsMockSetup {

	private BorderedText borderedText;

	@Test
	public void testConstructorCreatesRoles() throws Exception {
		Coordinate coordinate =  new Coordinate(15, 20);
		String startString = "startString";
		borderedText = createBorderedText(coordinate.getX(), coordinate.getY(), startString, 10);

		// textable
		Assert.assertTrue(borderedText.hasRole(Textable.TYPE));
		Textable textable = borderedText.getRole(Textable.TYPE);
		Assert.assertNotNull(textable);
		// draggable
		Assert.assertTrue(borderedText.hasRole(Draggable.TYPE));
		Draggable draggable = borderedText.getRole(Draggable.TYPE);
		Assert.assertNotNull(draggable);
		// strokable
		Assert.assertTrue(borderedText.hasRole(Strokable.TYPE));
		Strokable strokable = borderedText.getRole(Strokable.TYPE);
		Assert.assertNotNull(strokable);
		// fillable
		Assert.assertTrue(borderedText.hasRole(Fillable.TYPE));
		Fillable fillable = borderedText.getRole(Fillable.TYPE);
		Assert.assertNotNull(fillable);
		// bordered
		Assert.assertTrue(borderedText.hasRole(Bordered.TYPE));
		Bordered bordered = borderedText.getRole(Bordered.TYPE);
		Assert.assertNotNull(bordered);
	}

	@Test
	public void testCloneObjectTextable() throws Exception {
		Coordinate coordinate =  new Coordinate(15, 20);
		String startString = "startString";
		borderedText = createBorderedText(coordinate.getX(), coordinate.getY(), startString, 10);
		Textable textable = borderedText.getRole(Textable.TYPE);
		textable.setFontColor("fontColorTest");
		textable.setFontFamily("fontFamilyTest");
		textable.setFontSize(52);

		Object clone = borderedText.cloneObject();

		BorderedText borderedTextClone = assertIsCorrectObject(clone);
		Assert.assertTrue(borderedTextClone.hasRole(Textable.TYPE));
		assertRoleEqualityTextable(borderedText.getRole(Textable.TYPE), borderedTextClone.getRole(Textable.TYPE));
	}

	@Test
	public void testCloneObjectDraggable() throws Exception {
		Coordinate coordinate =  new Coordinate(15, 20);
		String startString = "startString";
		borderedText = createBorderedText(coordinate.getX(), coordinate.getY(), startString, 10);
		Draggable expected = borderedText.getRole(Draggable.TYPE);
		expected.setUserPosition(new Coordinate(2, 15));
		expected.setUserBounds(new Bbox(15, 20, 5, 5));

		Object clone = borderedText.cloneObject();

		BorderedText borderedTextClone = assertIsCorrectObject(clone);
		Assert.assertTrue(borderedTextClone.hasRole(Draggable.TYPE));
		assertRoleEqualityDraggable(borderedText.getRole(Draggable.TYPE), borderedTextClone.getRole(Draggable.TYPE));
	}

	@Test
	public void testCloneObjectStrokable() throws Exception {
		Coordinate coordinate =  new Coordinate(15, 20);
		String startString = "startString";
		borderedText = createBorderedText(coordinate.getX(), coordinate.getY(), startString, 10);
		Strokable expected = borderedText.getRole(Strokable.TYPE);
		expected.setStrokeColor("strokeColor");
		expected.setStrokeOpacity(0.3);
		expected.setStrokeWidth(8);

		Object clone = borderedText.cloneObject();

		BorderedText borderedTextClone = assertIsCorrectObject(clone);
		Assert.assertTrue(borderedTextClone.hasRole(Strokable.TYPE));
		assertRoleEqualityStrokable(expected, borderedTextClone.getRole(Strokable.TYPE));
	}

	@Test
	public void testCloneObjectFillable() throws Exception {
		Coordinate coordinate =  new Coordinate(15, 20);
		String startString = "startString";
		borderedText = createBorderedText(coordinate.getX(), coordinate.getY(), startString, 10);
		Fillable expected = borderedText.getRole(Fillable.TYPE);
		expected.setFillColor("fillColor");
		expected.setFillOpacity(0.4);

		Object clone = borderedText.cloneObject();

		BorderedText borderedTextClone = assertIsCorrectObject(clone);
		Assert.assertTrue(borderedTextClone.hasRole(Fillable.TYPE));
		assertRoleEqualityFillable(expected, borderedTextClone.getRole(Fillable.TYPE));
	}

	@Test
	public void testCloneObjectBordered() throws Exception {
		Coordinate coordinate =  new Coordinate(15, 20);
		String startString = "startString";
		borderedText = createBorderedText(coordinate.getX(), coordinate.getY(), startString, 10);
		Bordered expected = borderedText.getRole(Bordered.TYPE);
		Fillable fillableExpected = expected.getFillable();
		fillableExpected.setFillColor("fillColor");
		fillableExpected.setFillOpacity(0.4);
		Strokable strokableExpected = expected.getStrokable();
		strokableExpected.setStrokeColor("strokeColor");
		strokableExpected.setStrokeOpacity(0.3);
		strokableExpected.setStrokeWidth(8);

		Object clone = borderedText.cloneObject();

		BorderedText borderedTextClone = assertIsCorrectObject(clone);
		Assert.assertTrue(borderedTextClone.hasRole(Bordered.TYPE));
		Bordered borderedClone = borderedTextClone.getRole(Bordered.TYPE);
		assertRoleEqualityFillable(fillableExpected, borderedClone.getFillable());
		assertRoleEqualityStrokable(strokableExpected, borderedClone.getStrokable());
	}


	private BorderedText createBorderedText(double userX, double userY, String text, int margin) {
		return new BorderedText(userX, userY, text, margin);
	}

	private BorderedText assertIsCorrectObject(Object clone) {
		Assert.assertNotNull(clone);
		Assert.assertTrue(clone instanceof BorderedText);
		return (BorderedText) clone;
	}

}
