/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2013 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the Apache
 * License, Version 2.0. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */
package org.geomajas.graphics.client.editor;

import com.google.gwt.user.client.ui.Widget;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.role.Labeled;
import org.geomajas.graphics.client.object.role.RoleType;
import org.geomajas.graphics.client.resource.GraphicsResource;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * {@link org.geomajas.graphics.client.editor.Editor} for the {@link org.geomajas.graphics.client.object.role.Labeled} role.
 *
 * @author Jan Venstermans
 *
 */
public class LabelEditor2 extends AbstractRoleEditor<Labeled> {

	private TextableEditor textableEditor;

	public LabelEditor2() {
		textableEditor = new TextableEditor();
	}

	@Override
	public Widget asWidget() {
		return textableEditor.asWidget();
	}

	@Override
	protected RoleType<Labeled> getType() {
		return Labeled.TYPE;
	}

	@Override
	public void setObject(GraphicsObject object) {
		super.setObject(object);
		textableEditor.setObject(getObject());
		textableEditor.setRoleObject(getRoleObject().getTextable());
	}

	public void onOk() {
		textableEditor.onOk();
	}

	@Override
	public String getLabel() {
		return GraphicsResource.MESSAGES.editorLabelLabeled();
	}

	@Override
	public boolean validate() {
		return textableEditor.validate();
	}

	@Override
	public void setService(GraphicsService service) {
		super.setService(service);
		textableEditor.setService(service);
	}

}
