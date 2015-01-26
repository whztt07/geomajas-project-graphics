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
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.object.role.Textable;
import org.geomajas.graphics.client.object.updateable.anchored.Anchored;
import org.geomajas.graphics.client.object.updateable.anchored.MarkerShape;
import org.geomajas.graphics.client.object.updateable.bordered.Bordered;
import org.junit.Assert;
import org.junit.Test;

public class AnchoredBorderedTextTest extends GraphicsMockSetup {

	private AnchoredBorderedText anchoredBorderedText;

	@Test
	public void testConstructorCreatesRoles() throws Exception {
		Coordinate coordinate =  new Coordinate(15, 20);
		String startString = "startString";
		anchoredBorderedText = createBorderedText(coordinate.getX(), coordinate.getY(), startString, 10, new Coordinate(-1, -2), MarkerShape.CIRCLE);

		// textable
		Assert.assertTrue(anchoredBorderedText.hasRole(Textable.TYPE));
		Textable textable = anchoredBorderedText.getRole(Textable.TYPE);
		Assert.assertNotNull(textable);
		// draggable
		Assert.assertTrue(anchoredBorderedText.hasRole(Draggable.TYPE));
		Draggable draggable = anchoredBorderedText.getRole(Draggable.TYPE);
		Assert.assertNotNull(draggable);
		// strokable
		Assert.assertTrue(anchoredBorderedText.hasRole(Strokable.TYPE));
		Strokable strokable = anchoredBorderedText.getRole(Strokable.TYPE);
		Assert.assertNotNull(strokable);
		// fillable
		Assert.assertTrue(anchoredBorderedText.hasRole(Fillable.TYPE));
		Fillable fillable = anchoredBorderedText.getRole(Fillable.TYPE);
		Assert.assertNotNull(fillable);
		// bordered
		Assert.assertTrue(anchoredBorderedText.hasRole(Bordered.TYPE));
		Bordered bordered = anchoredBorderedText.getRole(Bordered.TYPE);
		Assert.assertNotNull(bordered);
	}

	@Test
	public void testCloneObjectTextable() throws Exception {
		Coordinate coordinate =  new Coordinate(15, 20);
		String startString = "startString";
		anchoredBorderedText = createBorderedText(coordinate.getX(), coordinate.getY(), startString, 10, new Coordinate(-1, -2), MarkerShape.CIRCLE);
		Textable textable = anchoredBorderedText.getRole(Textable.TYPE);
		textable.setFontColor("fontColorTest");
		textable.setFontFamily("fontFamilyTest");
		textable.setFontSize(52);

		Object clone = anchoredBorderedText.cloneObject();

		AnchoredBorderedText anchoredBorderedTextClone = assertIsCorrectObject(clone);
		Assert.assertTrue(anchoredBorderedTextClone.hasRole(Textable.TYPE));
		assertRoleEqualityTextable(anchoredBorderedText.getRole(Textable.TYPE), anchoredBorderedTextClone.getRole(Textable.TYPE));
	}

	@Test
	public void testCloneObjectDraggable() throws Exception {
		Coordinate coordinate =  new Coordinate(15, 20);
		String startString = "startString";
		anchoredBorderedText = createBorderedText(coordinate.getX(), coordinate.getY(), startString, 10, new Coordinate(-1, -2), MarkerShape.CIRCLE);
		Draggable expected = anchoredBorderedText.getRole(Draggable.TYPE);
		expected.setUserPosition(new Coordinate(2, 15));
		expected.setUserBounds(new Bbox(15, 20, 5, 5));

		Object clone = anchoredBorderedText.cloneObject();

		AnchoredBorderedText anchoredBorderedTextClone = assertIsCorrectObject(clone);
		Assert.assertTrue(anchoredBorderedTextClone.hasRole(Draggable.TYPE));
		assertRoleEqualityDraggable(anchoredBorderedText.getRole(Draggable.TYPE), anchoredBorderedTextClone.getRole(Draggable.TYPE));
	}

	@Test
	public void testCloneObjectStrokable() throws Exception {
		Coordinate coordinate =  new Coordinate(15, 20);
		String startString = "startString";
		anchoredBorderedText = createBorderedText(coordinate.getX(), coordinate.getY(), startString, 10, new Coordinate(-1, -2), MarkerShape.CIRCLE);
		Strokable expected = anchoredBorderedText.getRole(Strokable.TYPE);
		expected.setStrokeColor("strokeColor");
		expected.setStrokeOpacity(0.3);
		expected.setStrokeWidth(8);

		Object clone = anchoredBorderedText.cloneObject();

		AnchoredBorderedText anchoredBorderedTextClone = assertIsCorrectObject(clone);
		Assert.assertTrue(anchoredBorderedTextClone.hasRole(Strokable.TYPE));
		assertRoleEqualityStrokable(expected, anchoredBorderedTextClone.getRole(Strokable.TYPE));
	}

	@Test
	public void testCloneObjectFillable() throws Exception {
		Coordinate coordinate =  new Coordinate(15, 20);
		String startString = "startString";
		anchoredBorderedText = createBorderedText(coordinate.getX(), coordinate.getY(), startString, 10, new Coordinate(-1, -2), MarkerShape.CIRCLE);
		Fillable expected = anchoredBorderedText.getRole(Fillable.TYPE);
		expected.setFillColor("fillColor");
		expected.setFillOpacity(0.4);

		Object clone = anchoredBorderedText.cloneObject();

		AnchoredBorderedText anchoredBorderedTextClone = assertIsCorrectObject(clone);
		Assert.assertTrue(anchoredBorderedTextClone.hasRole(Fillable.TYPE));
		assertRoleEqualityFillable(expected, anchoredBorderedTextClone.getRole(Fillable.TYPE));
	}

	@Test
	public void testCloneObjectBordered() throws Exception {
		Coordinate coordinate =  new Coordinate(15, 20);
		String startString = "startString";
		anchoredBorderedText = createBorderedText(coordinate.getX(), coordinate.getY(), startString, 10, new Coordinate(-1, -2), MarkerShape.CIRCLE);
		Bordered expected = anchoredBorderedText.getRole(Bordered.TYPE);
		Fillable fillableExpected = expected.getFillable();
		fillableExpected.setFillColor("fillColor");
		fillableExpected.setFillOpacity(0.4);
		Strokable strokableExpected = expected.getStrokable();
		strokableExpected.setStrokeColor("strokeColor");
		strokableExpected.setStrokeOpacity(0.3);
		strokableExpected.setStrokeWidth(8);

		Object clone = anchoredBorderedText.cloneObject();

		AnchoredBorderedText anchoredBorderedTextClone = assertIsCorrectObject(clone);
		Assert.assertTrue(anchoredBorderedTextClone.hasRole(Bordered.TYPE));
		Bordered borderedClone = anchoredBorderedTextClone.getRole(Bordered.TYPE);
		assertRoleEqualityFillable(fillableExpected, borderedClone.getFillable());
		assertRoleEqualityStrokable(strokableExpected, borderedClone.getStrokable());
	}

	@Test
	public void testCloneObjectAnchored() throws Exception {
		Coordinate coordinate =  new Coordinate(15, 20);
		String startString = "startString";
		anchoredBorderedText = createBorderedText(coordinate.getX(), coordinate.getY(), startString, 10, new Coordinate(-1, -2), MarkerShape.CIRCLE);
		Anchored expected = anchoredBorderedText.getRole(Anchored.TYPE);
		Strokable anchorLineStrokable = expected.getAnchorLineStrokable();
		anchorLineStrokable.setStrokeColor("strokeColor");
		anchorLineStrokable.setStrokeOpacity(0.3);
		anchorLineStrokable.setStrokeWidth(8);
		Strokable anchorMarkerStrokable = expected.getAnchorMarkerShapeStrokable();
		anchorMarkerStrokable.setStrokeColor("strokeColor2");
		anchorMarkerStrokable.setStrokeOpacity(0.7);
		anchorMarkerStrokable.setStrokeWidth(2);
		Fillable anchorMarkerFillable = expected.getAnchorMarkerShapeFillable();
		anchorMarkerFillable.setFillColor("fillColor");
		anchorMarkerFillable.setFillOpacity(0.4);
		expected.setAnchorPosition(new Coordinate(20, -18));

		Object clone = anchoredBorderedText.cloneObject();

		AnchoredBorderedText anchoredBorderedTextClone = assertIsCorrectObject(clone);
		Assert.assertTrue(anchoredBorderedTextClone.hasRole(Anchored.TYPE));
		Anchored anchoredClone = anchoredBorderedTextClone.getRole(Anchored.TYPE);
		assertRoleEqualityAnchored(expected, anchoredClone);
	}

	private AnchoredBorderedText createBorderedText(double userX, double userY, String text, int margin, Coordinate anchorCoordinate,
													MarkerShape markerShape) {
		return new AnchoredBorderedText(new Coordinate(userX, userY), text, margin, anchorCoordinate, markerShape);
	}

	private AnchoredBorderedText assertIsCorrectObject(Object clone) {
		Assert.assertNotNull(clone);
		Assert.assertTrue(clone instanceof AnchoredBorderedText);
		return (AnchoredBorderedText) clone;
	}

}
