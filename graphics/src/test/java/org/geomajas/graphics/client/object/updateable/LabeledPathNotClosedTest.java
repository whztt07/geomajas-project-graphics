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

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.GraphicsMockSetup;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.object.role.Textable;
import org.geomajas.graphics.client.object.updateable.labeled.Labeled;
import org.junit.Assert;
import org.junit.Test;

public class LabeledPathNotClosedTest extends GraphicsMockSetup {

	private LabeledPath labeledPath;

	@Test
	public void testConstructorCreatesRoles() throws Exception {
		Coordinate[] coordinates =  new Coordinate[] { new Coordinate(0,0), new Coordinate(2,5), new Coordinate(-1,3)};
		String labelString = "labelString";
		labeledPath = createLabeledPath(coordinates, labelString);

		// strokable
		Assert.assertTrue(labeledPath.hasRole(Strokable.TYPE));
		Strokable strokable = labeledPath.getRole(Strokable.TYPE);
		Assert.assertNotNull(strokable);
		// fillable
		Assert.assertFalse(labeledPath.hasRole(Fillable.TYPE));
		// resizable
		Assert.assertTrue(labeledPath.hasRole(Resizable.TYPE));
		Resizable resizable = labeledPath.getRole(Resizable.TYPE);
		Assert.assertNotNull(resizable);
		// draggable
		Assert.assertTrue(labeledPath.hasRole(Draggable.TYPE));
		Draggable draggable = labeledPath.getRole(Draggable.TYPE);
		Assert.assertNotNull(draggable);
		// labeled
		Assert.assertTrue(labeledPath.hasRole(Labeled.TYPE));
		Labeled labeled = labeledPath.getRole(Labeled.TYPE);
		Assert.assertNotNull(labeled);
	}

	@Test
	public void testCloneObjectStrokable() throws Exception {
		Coordinate[] coordinates =  new Coordinate[] { new Coordinate(0,0), new Coordinate(2,5), new Coordinate(-1,3)};
		String labelString = "labelString";
		labeledPath = createLabeledPath(coordinates, labelString);
		Strokable expected = labeledPath.getRole(Strokable.TYPE);
		expected.setStrokeColor("strokeColor");
		expected.setStrokeOpacity(0.3);
		expected.setStrokeWidth(8);

		Object clone = labeledPath.cloneObject();

		LabeledPath labeledPathClone = assertIsCorrectObject(clone);
		Assert.assertTrue(labeledPathClone.hasRole(Strokable.TYPE));
		assertRoleEqualityStrokable(expected, labeledPathClone.getRole(Strokable.TYPE));
	}

	@Test
	public void testCloneObjectDraggable() throws Exception {
		Coordinate[] coordinates =  new Coordinate[] { new Coordinate(0,0), new Coordinate(2,5), new Coordinate(-1,3)};
		String labelString = "labelString";
		labeledPath = createLabeledPath(coordinates, labelString);
		Draggable expected = labeledPath.getRole(Draggable.TYPE);
		Assert.assertNotNull(expected.getUserBounds());
		Assert.assertNotNull(expected.getUserPosition());
//		expected.setUserPosition(new Coordinate(2, 15));
//		expected.setUserBounds(new Bbox(15, 20, 5, 5));

		Object clone = labeledPath.cloneObject();

		LabeledPath labeledPathClone = assertIsCorrectObject(clone);
		Assert.assertTrue(labeledPathClone.hasRole(Draggable.TYPE));
		assertRoleEqualityDraggable(labeledPath.getRole(Draggable.TYPE), labeledPathClone.getRole(Draggable.TYPE));
	}

	@Test
	public void testCloneObjectResizableRole() throws Exception {
		Coordinate[] coordinates =  new Coordinate[] { new Coordinate(0,0), new Coordinate(2,5), new Coordinate(-1,3)};
		String labelString = "labelString";
		labeledPath = createLabeledPath(coordinates, labelString);
		Resizable expected = labeledPath.getRole(Resizable.TYPE);
		Assert.assertNotNull(expected.getUserBounds());
		Assert.assertNotNull(expected.getUserPosition());
//		expected.setUserPosition(new Coordinate(2, 15));
//		expected.setUserBounds(new Bbox(15, 20, 5, 5));

		Object clone = labeledPath.cloneObject();

		LabeledPath labeledPathClone = assertIsCorrectObject(clone);
		Assert.assertTrue(labeledPathClone.hasRole(Resizable.TYPE));
		assertRoleEqualityResizable(expected, labeledPathClone.getRole(Resizable.TYPE));
	}

	@Test
	public void testCloneObjectLabeled() throws Exception {
		Coordinate[] coordinates =  new Coordinate[] { new Coordinate(0,0), new Coordinate(2,5), new Coordinate(-1,3)};
		String labelString = "labelString";
		labeledPath = createLabeledPath(coordinates, labelString);
		Labeled expected = labeledPath.getRole(Labeled.TYPE);
		Textable textableExpected = expected.getTextable();
		textableExpected.setFontColor("fontColorTest");
		textableExpected.setFontFamily("fontFamilyTest");
		textableExpected.setFontSize(52);

		Object clone = labeledPath.cloneObject();

		LabeledPath labeledPathClone = assertIsCorrectObject(clone);
		Assert.assertTrue(labeledPathClone.hasRole(Labeled.TYPE));
		Labeled labeledClone = labeledPathClone.getRole(Labeled.TYPE);
		assertRoleEqualityTextable(textableExpected, labeledClone.getTextable());
	}


	private LabeledPath createLabeledPath(Coordinate[] coordinates, String label) {
		return new LabeledPath(coordinates, false, label);
	}

	private LabeledPath assertIsCorrectObject(Object clone) {
		Assert.assertNotNull(clone);
		Assert.assertTrue(clone instanceof LabeledPath);
		return (LabeledPath) clone;
	}

}
