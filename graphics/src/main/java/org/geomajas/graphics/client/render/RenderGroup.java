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
package org.geomajas.graphics.client.render;

import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.VectorObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Vaadin gwt implementation of {@link RenderableList}.
 *
 * @author Jan Venstermans
 */
public class RenderGroup implements RenderableList {

	private Group group = new Group();

	private List<Renderable> renderableList = new ArrayList<Renderable>();

	public RenderGroup() {
	}

	@Override
	public void addRenderable(Renderable renderable) {
		renderableList.add(renderable);
		group.add(renderable.asObject());
	}

	@Override
	public void removeRenderable(Renderable renderable) {
		renderableList.remove(renderable);
		group.remove(renderable.asObject());
	}

	@Override
	public List<Renderable> getRenderableList() {
		return renderableList;
	}

	@Override
	public VectorObject asObject() {
		return group;
	}

	@Override
	public void setOpacity(double opacity) {
		group.setOpacity(opacity);
	}
}