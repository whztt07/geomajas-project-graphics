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
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.object.role.Textable;
import org.geomajas.graphics.client.object.updateable.labeled.Labeled;
import org.junit.Assert;
import org.junit.Test;

public class LabeledImageTest extends GraphicsMockSetup {

	private LabeledImage labeledImage;

	@Test
	public void testConstructorCreatesRoles() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		String href = "hrefTest";
		String labelString = "labelString";
		labeledImage = createLabeledImage(bbox, href, labelString);

		// resizable
		Assert.assertTrue(labeledImage.hasRole(Resizable.TYPE));
		Resizable resizable = labeledImage.getRole(Resizable.TYPE);
		Assert.assertNotNull(resizable);
		// draggable
		Assert.assertTrue(labeledImage.hasRole(Draggable.TYPE));
		Draggable draggable = labeledImage.getRole(Draggable.TYPE);
		Assert.assertNotNull(draggable);
		// labeled
		Assert.assertTrue(labeledImage.hasRole(Labeled.TYPE));
		Labeled labeled = labeledImage.getRole(Labeled.TYPE);
		Assert.assertNotNull(labeled);
	}

	@Test
	public void testCloneObjectDraggable() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		String href = "hrefTest";
		String labelString = "labelString";
		labeledImage = createLabeledImage(bbox, href, labelString);
		Draggable expected = labeledImage.getRole(Draggable.TYPE);
		expected.setUserPosition(new Coordinate(2, 15));
		expected.setUserBounds(new Bbox(15, 20, 5, 5));

		Object clone = labeledImage.cloneObject();

		LabeledImage labeledImageClone = assertIsCorrectObject(clone);
		Assert.assertTrue(labeledImageClone.hasRole(Draggable.TYPE));
		assertRoleEqualityDraggable(labeledImage.getRole(Draggable.TYPE), labeledImageClone.getRole(Draggable.TYPE));
	}

	@Test
	public void testCloneObjectResizableRole() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		String href = "hrefTest";
		String labelString = "labelString";
		labeledImage = createLabeledImage(bbox, href, labelString);
		Resizable expected = labeledImage.getRole(Resizable.TYPE);
		expected.setUserPosition(new Coordinate(2, 15));
		expected.setUserBounds(new Bbox(15, 20, 5, 5));

		Object clone = labeledImage.cloneObject();

		LabeledImage labeledImageClone = assertIsCorrectObject(clone);
		Assert.assertTrue(labeledImageClone.hasRole(Resizable.TYPE));
		assertRoleEqualityResizable(expected, labeledImageClone.getRole(Resizable.TYPE));
	}

	@Test
	public void testCloneObjectLabeled() throws Exception {
		Bbox bbox =  new Bbox(15, 20, 5, 5);
		String href = "hrefTest";
		String labelString = "labelString";
		labeledImage = createLabeledImage(bbox, href, labelString);
		Labeled expected = labeledImage.getRole(Labeled.TYPE);
		Textable textableExpected = expected.getTextable();
		textableExpected.setFontColor("fontColorTest");
		textableExpected.setFontFamily("fontFamilyTest");
		textableExpected.setFontSize(52);

		Object clone = labeledImage.cloneObject();

		LabeledImage labeledImageClone = assertIsCorrectObject(clone);
		Assert.assertTrue(labeledImageClone.hasRole(Labeled.TYPE));
		Labeled labeledClone = labeledImageClone.getRole(Labeled.TYPE);
		assertRoleEqualityTextable(textableExpected, labeledClone.getTextable());
	}


	private LabeledImage createLabeledImage(Bbox bbox, String href, String label) {
		return new LabeledImage((int) bbox.getX(), (int) bbox.getY(), (int) bbox.getWidth(),
				(int) bbox.getHeight(), href, label);
	}

	private LabeledImage assertIsCorrectObject(Object clone) {
		Assert.assertNotNull(clone);
		Assert.assertTrue(clone instanceof LabeledImage);
		return (LabeledImage) clone;
	}

}
