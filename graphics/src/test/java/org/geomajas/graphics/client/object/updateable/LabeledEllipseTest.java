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
import org.geomajas.graphics.client.object.base.BaseEllipse;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.object.role.Textable;
import org.geomajas.graphics.client.object.updateable.labeled.Labeled;
import org.junit.Assert;
import org.junit.Test;

public class LabeledEllipseTest extends GraphicsMockSetup {

	private LabeledEllipse labeledEllipse;

	@Test
	public void testConstructorCreatesRoles() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		String labelString = "labelString";
		labeledEllipse = createLabeledEllipse(bbox, labelString);

		// resizable
		Assert.assertTrue(labeledEllipse.hasRole(Resizable.TYPE));
		Resizable resizable = labeledEllipse.getRole(Resizable.TYPE);
		Assert.assertNotNull(resizable);
		// draggable
		Assert.assertTrue(labeledEllipse.hasRole(Draggable.TYPE));
		Draggable draggable = labeledEllipse.getRole(Draggable.TYPE);
		Assert.assertNotNull(draggable);
		// strokable
		Assert.assertTrue(labeledEllipse.hasRole(Strokable.TYPE));
		Strokable strokable = labeledEllipse.getRole(Strokable.TYPE);
		Assert.assertNotNull(strokable);
		// fillable
		Assert.assertTrue(labeledEllipse.hasRole(Fillable.TYPE));
		Fillable fillable = labeledEllipse.getRole(Fillable.TYPE);
		Assert.assertNotNull(fillable);
		// labeled
		Assert.assertTrue(labeledEllipse.hasRole(Labeled.TYPE));
		Labeled labeled = labeledEllipse.getRole(Labeled.TYPE);
		Assert.assertNotNull(labeled);
	}

	@Test
	public void testCloneObjectDraggable() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		String labelString = "labelString";
		labeledEllipse = createLabeledEllipse(bbox, labelString);
		Draggable expected = labeledEllipse.getRole(Draggable.TYPE);
		expected.setUserPosition(new Coordinate(2, 15));
		expected.setUserBounds(new Bbox(15, 20, 5, 5));

		Object clone = labeledEllipse.cloneObject();

		LabeledEllipse labeledEllipseClone = assertIsCorrectObject(clone);
		Assert.assertTrue(labeledEllipseClone.hasRole(Draggable.TYPE));
		assertRoleEqualityDraggable(labeledEllipse.getRole(Draggable.TYPE), labeledEllipseClone.getRole(Draggable.TYPE));
	}

	@Test
	public void testCloneObjectResizableRole() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		String labelString = "labelString";
		labeledEllipse = createLabeledEllipse(bbox, labelString);
		Resizable expected = labeledEllipse.getRole(Resizable.TYPE);
		expected.setUserPosition(new Coordinate(2, 15));
		expected.setUserBounds(new Bbox(15, 20, 5, 5));

		Object clone = labeledEllipse.cloneObject();

		LabeledEllipse labeledEllipseClone = assertIsCorrectObject(clone);
		Assert.assertTrue(labeledEllipseClone.hasRole(Resizable.TYPE));
		assertRoleEqualityResizable(expected, labeledEllipseClone.getRole(Resizable.TYPE));
	}

	@Test
	public void testCloneObjectStrokable() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		String labelString = "labelString";
		labeledEllipse = createLabeledEllipse(bbox, labelString);
		Strokable expected = labeledEllipse.getRole(Strokable.TYPE);
		expected.setStrokeColor("strokeColor");
		expected.setStrokeOpacity(0.3);
		expected.setStrokeWidth(8);

		Object clone = labeledEllipse.cloneObject();

		LabeledEllipse labeledEllipseClone = assertIsCorrectObject(clone);
		Assert.assertTrue(labeledEllipseClone.hasRole(Strokable.TYPE));
		assertRoleEqualityStrokable(expected, labeledEllipseClone.getRole(Strokable.TYPE));
	}

	@Test
	public void testCloneObjectFillable() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		String labelString = "labelString";
		labeledEllipse = createLabeledEllipse(bbox, labelString);
		Fillable expected = labeledEllipse.getRole(Fillable.TYPE);
		expected.setFillColor("fillColor");
		expected.setFillOpacity(0.4);

		Object clone = labeledEllipse.cloneObject();

		LabeledEllipse labeledEllipseClone = assertIsCorrectObject(clone);
		Assert.assertTrue(labeledEllipseClone.hasRole(Fillable.TYPE));
		assertRoleEqualityFillable(expected, labeledEllipseClone.getRole(Fillable.TYPE));
	}

	@Test
	public void testCloneObjectLabeled() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		String labelString = "labelString";
		labeledEllipse = createLabeledEllipse(bbox, labelString);
		Labeled expected = labeledEllipse.getRole(Labeled.TYPE);
		Textable textableExpected = expected.getTextable();
		textableExpected.setFontColor("fontColorTest");
		textableExpected.setFontFamily("fontFamilyTest");
		textableExpected.setFontSize(52);

		Object clone = labeledEllipse.cloneObject();

		LabeledEllipse labeledEllipseClone = assertIsCorrectObject(clone);
		Assert.assertTrue(labeledEllipseClone.hasRole(Labeled.TYPE));
		Labeled labeledClone = labeledEllipseClone.getRole(Labeled.TYPE);
		assertRoleEqualityTextable(textableExpected, labeledClone.getTextable());
	}


	private LabeledEllipse createLabeledEllipse(Bbox bbox, String label) {
		return new LabeledEllipse(bbox.getX(), bbox.getY(), bbox.getWidth(), bbox.getHeight(), label);
	}

	private LabeledEllipse assertIsCorrectObject(Object clone) {
		Assert.assertNotNull(clone);
		Assert.assertTrue(clone instanceof LabeledEllipse);
		return (LabeledEllipse) clone;
	}

}
