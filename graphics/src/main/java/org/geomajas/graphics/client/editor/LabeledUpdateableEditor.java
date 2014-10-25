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

import org.geomajas.graphics.client.object.updateable.LabeledUpdateable;
import org.geomajas.graphics.client.object.role.RoleInterface;
import org.geomajas.graphics.client.object.role.RoleType;
import org.geomajas.graphics.client.resource.GraphicsResource;

/**
 * {@link Editor}
 * for the {@link LabeledUpdateable} role.
 * TODO: merge Labeled and LabeledUpdateable roles
 *
 * @author Jan Venstermans
 *
 */
public class LabeledUpdateableEditor extends AbstractDelegateRoleEditor<LabeledUpdateable, TextableEditor> {

	@Override
	public TextableEditor createNewDelegate() {
		return new TextableEditor();
	}

	@Override
	public RoleInterface getRoleObjectForDelegate() {
		return getRoleObject().getTextable();
	}

	@Override
	protected RoleType<LabeledUpdateable> getType() {
		return LabeledUpdateable.TYPE;
	}

	@Override
	public String getLabel() {
		return GraphicsResource.MESSAGES.editorLabelLabeled();
	}


}
