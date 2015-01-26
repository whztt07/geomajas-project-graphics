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
package org.geomajas.graphics.client.object.updateable;

import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.GraphicsMockSetup;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.object.role.Textable;
import org.geomajas.graphics.client.object.updateable.bordered.Bordered;
import org.geomajas.graphics.client.object.updateable.labeled.Labeled;
import org.junit.Assert;
import org.junit.Test;

public class LabeledRectangleTest extends GraphicsMockSetup {

	private LabeledRectangle labeledRectangle;

	@Test
	public void testConstructorCreatesRoles() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		String labelString = "labelString";
		labeledRectangle = createLabeledRectangle(bbox, labelString);

		// strokable
		Assert.assertTrue(labeledRectangle.hasRole(Strokable.TYPE));
		Strokable strokable = labeledRectangle.getRole(Strokable.TYPE);
		Assert.assertNotNull(strokable);
		// fillable
		Assert.assertTrue(labeledRectangle.hasRole(Fillable.TYPE));
		Fillable fillable = labeledRectangle.getRole(Fillable.TYPE);
		Assert.assertNotNull(fillable);
		// resizable
		Assert.assertTrue(labeledRectangle.hasRole(Resizable.TYPE));
		Resizable resizable = labeledRectangle.getRole(Resizable.TYPE);
		Assert.assertNotNull(resizable);
		// draggable
		Assert.assertTrue(labeledRectangle.hasRole(Draggable.TYPE));
		Draggable draggable = labeledRectangle.getRole(Draggable.TYPE);
		Assert.assertNotNull(draggable);
		// labeled
		Assert.assertTrue(labeledRectangle.hasRole(Labeled.TYPE));
		Labeled labeled = labeledRectangle.getRole(Labeled.TYPE);
		Assert.assertNotNull(labeled);
	}

	@Test
	public void testCloneObjectStrokable() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		String labelString = "labelString";
		labeledRectangle = createLabeledRectangle(bbox, labelString);
		Strokable expected = labeledRectangle.getRole(Strokable.TYPE);
		expected.setStrokeColor("strokeColor");
		expected.setStrokeOpacity(0.3);
		expected.setStrokeWidth(8);

		Object clone = labeledRectangle.cloneObject();

		LabeledRectangle labeledRectangleClone = assertIsCorrectObject(clone);
		Assert.assertTrue(labeledRectangleClone.hasRole(Strokable.TYPE));
		assertRoleEqualityStrokable(expected, labeledRectangleClone.getRole(Strokable.TYPE));
	}

	@Test
	public void testCloneObjectFillable() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		String labelString = "labelString";
		labeledRectangle = createLabeledRectangle(bbox, labelString);
		Fillable expected = labeledRectangle.getRole(Fillable.TYPE);
		expected.setFillColor("fillColor");
		expected.setFillOpacity(0.4);

		Object clone = labeledRectangle.cloneObject();

		LabeledRectangle labeledRectangleClone = assertIsCorrectObject(clone);
		Assert.assertTrue(labeledRectangleClone.hasRole(Fillable.TYPE));
		assertRoleEqualityFillable(expected, labeledRectangleClone.getRole(Fillable.TYPE));
	}

	@Test
	public void testCloneObjectDraggable() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		String labelString = "labelString";
		labeledRectangle = createLabeledRectangle(bbox, labelString);
		Draggable expected = labeledRectangle.getRole(Draggable.TYPE);
		expected.setUserPosition(new Coordinate(2, 15));
		expected.setUserBounds(new Bbox(15, 20, 5, 5));

		Object clone = labeledRectangle.cloneObject();

		LabeledRectangle labeledRectangleClone = assertIsCorrectObject(clone);
		Assert.assertTrue(labeledRectangleClone.hasRole(Draggable.TYPE));
		assertRoleEqualityDraggable(labeledRectangle.getRole(Draggable.TYPE), labeledRectangleClone.getRole(Draggable.TYPE));
	}

	@Test
	public void testCloneObjectResizableRole() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		String labelString = "labelString";
		labeledRectangle = createLabeledRectangle(bbox, labelString);
		Resizable expected = labeledRectangle.getRole(Resizable.TYPE);
		expected.setUserPosition(new Coordinate(2, 15));
		expected.setUserBounds(new Bbox(15, 20, 5, 5));

		Object clone = labeledRectangle.cloneObject();

		LabeledRectangle labeledRectangleClone = assertIsCorrectObject(clone);
		Assert.assertTrue(labeledRectangleClone.hasRole(Resizable.TYPE));
		assertRoleEqualityResizable(expected, labeledRectangleClone.getRole(Resizable.TYPE));
	}

	@Test
	public void testCloneObjectLabeled() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		String labelString = "labelString";
		labeledRectangle = createLabeledRectangle(bbox, labelString);
		Labeled expected = labeledRectangle.getRole(Labeled.TYPE);
		Textable textableExpected = expected.getTextable();
		textableExpected.setFontColor("fontColorTest");
		textableExpected.setFontFamily("fontFamilyTest");
		textableExpected.setFontSize(52);

		Object clone = labeledRectangle.cloneObject();

		LabeledRectangle labeledRectangleClone = assertIsCorrectObject(clone);
		Assert.assertTrue(labeledRectangleClone.hasRole(Labeled.TYPE));
		Labeled labeledClone = labeledRectangleClone.getRole(Labeled.TYPE);
		assertRoleEqualityTextable(textableExpected, labeledClone.getTextable());
	}


	private LabeledRectangle createLabeledRectangle(Bbox bbox, String label) {
		return new LabeledRectangle(bbox.getX(), bbox.getY(), bbox.getWidth(), bbox.getHeight(), label);
	}

	private LabeledRectangle assertIsCorrectObject(Object clone) {
		Assert.assertNotNull(clone);
		Assert.assertTrue(clone instanceof LabeledRectangle);
		return (LabeledRectangle) clone;
	}

}
