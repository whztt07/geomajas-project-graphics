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

import com.google.gwt.user.client.ui.Widget;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.role.RenderPlane;
import org.geomajas.graphics.client.object.role.RoleInterface;
import org.geomajas.graphics.client.object.role.RoleType;
import org.geomajas.graphics.client.resource.GraphicsResource;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * Abstract implementation for an {@link AbstractRoleEditor} that will pass information and requests
 * to a delegate editor. This reflects some of the role structure.
 * E.g. the {@link org.geomajas.graphics.client.object.role.Labeled} role
 * contains a {@link org.geomajas.graphics.client.object.role.Textable} role object.
 * In this case, the
 *
 * @param <T> the {@link org.geomajas.graphics.client.object.role.RoleInterface} that will contain reference
 *           to a delegate role.
 * @param <D> the delegate {@link org.geomajas.graphics.client.editor.AbstractRoleEditor}
 * @author Jan Venstermans
 */
public abstract class AbstractDelegateRoleEditor<T extends RenderPlane, D extends AbstractRoleEditor>
		extends AbstractRoleEditor<T> {

	private D delegateEditor;

	public AbstractDelegateRoleEditor() {
		delegateEditor = createNewDelegate();
	}

	public abstract D createNewDelegate();

	public abstract RoleInterface getRoleObjectForDelegate();

	@Override
	public Widget asWidget() {
		return delegateEditor.asWidget();
	}

	protected abstract RoleType<T> getType();

	@Override
	public boolean supports(GraphicsObject object) {
		return object.hasRole(getType());
	}

	@Override
	public void setObject(GraphicsObject object) {
		super.setObject(object);
		delegateEditor.setObject(getObject());
		delegateEditor.setRoleObject(getRoleObjectForDelegate());
	}

	public void onOk() {
		delegateEditor.onOk();
	}

	@Override
	public String getLabel() {
		return GraphicsResource.MESSAGES.editorLabelLabeled();
	}

	@Override
	public boolean validate() {
		return delegateEditor.validate();
	}

	@Override
	public void setService(GraphicsService service) {
		super.setService(service);
		delegateEditor.setService(service);
	}

	public D getDelegateEditor() {
		return delegateEditor;
	}

}
