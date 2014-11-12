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
package org.geomajas.graphics.client.object.updateable.labeled;

import org.geomajas.geometry.Coordinate;
import org.geomajas.geometry.service.BboxService;
import org.geomajas.graphics.client.object.BaseGraphicsObject;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.object.base.BaseText;
import org.geomajas.graphics.client.object.role.Textable;
import org.geomajas.graphics.client.object.updateable.Updateable;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Implementation of {@link Labeled} role with the {@link Updateable} interface.
 * The label is a {@link BaseText} that is positioned based on a {@link Resizable}.
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 *
 */
public class LabeledImpl extends BaseGraphicsObject implements Labeled, Updateable {

	private Resizable resizable; // subject

	private BaseText baseText;

	public LabeledImpl(Resizable resizableSubject, String label) {
		this(resizableSubject, new BaseText(0, 0, label));
	}

	public LabeledImpl(Resizable resizableSubject, BaseText baseText) {
		this.resizable = resizableSubject;
		this.baseText = baseText;

		addRole(Labeled.TYPE, this);

		onUpdate();
	}

	@Override
	public VectorObject asObject() {
		return baseText.asObject();
	}
	
	@Override
	public void onUpdate() {
		centerText();
	}

	@Override
	public Object cloneObject() {
		LabeledImpl labeledCopy = new LabeledImpl(resizable, baseText.getLabel());
		return labeledCopy;
	}

	@Override
	public void setOpacity(double opacity) {
		baseText.setOpacity(opacity);
	}

	private void centerText() {
		Coordinate center = BboxService.getCenterPoint(resizable.getUserBounds());
		baseText.setUserPosition(center);
	}

	@Override
	public Textable getTextable() {
		return baseText.getRole(Textable.TYPE);
	}
}
