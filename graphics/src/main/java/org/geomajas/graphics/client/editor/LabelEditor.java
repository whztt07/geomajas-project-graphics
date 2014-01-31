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
package org.geomajas.graphics.client.editor;

import org.geomajas.graphics.client.object.role.Labeled;
import org.geomajas.graphics.client.object.role.RoleInterface;
import org.geomajas.graphics.client.object.role.RoleType;
import org.geomajas.graphics.client.resource.GraphicsResource;

/**
 * {@link org.geomajas.graphics.client.editor.Editor}
 * for the {@link org.geomajas.graphics.client.object.role.Labeled} role.
 *
 * @author Jan Venstermans
 *
 */
public class LabelEditor extends AbstractDelegateRoleEditor<Labeled, TextableEditor> {

	@Override
	public TextableEditor createNewDelegate() {
		return new TextableEditor();
	}

	@Override
	public RoleInterface getRoleObjectForDelegate() {
		return getRoleObject().getTextable();
	}

	@Override
	protected RoleType<Labeled> getType() {
		return Labeled.TYPE;
	}

	@Override
	public String getLabel() {
		return GraphicsResource.MESSAGES.editorLabelLabeled();
	}


}
