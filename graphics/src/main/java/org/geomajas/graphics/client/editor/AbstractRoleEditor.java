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

import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.role.RoleInterface;
import org.geomajas.graphics.client.object.role.RoleType;
import org.geomajas.graphics.client.service.GraphicsService;

/**
 * Abstract implementation of {@link org.geomajas.graphics.client.editor.Editor} interface.
 *
 * @param <T> interface, if a {@link org.geomajas.graphics.client.object.GraphicsObject}
 *           has a role of this interface, this editor will be
 *           supported for the object.
 * @author Jan Venstermans
 *
 */
public abstract class AbstractRoleEditor<T extends RoleInterface> implements Editor {

	private GraphicsService service;

	private GraphicsObject object;

	private String iconUrl;

	private T roleObject;

	public AbstractRoleEditor() {

	}

	@Override
	public boolean supports(GraphicsObject object) {
		return object.hasRole(getType());
	}

	protected abstract RoleType<T> getType() ;

	@Override
	public void setObject(GraphicsObject object) {
		this.object = object;
		setRoleObject(object.getRole(getType()));
	}

	protected GraphicsObject getObject() {
		return object;
	}

	@Override
	public void setService(GraphicsService service) {
		this.service = service;
	}

	protected GraphicsService getService() {
		return service;
	}

	@Override
	public void undo() {
		service.undo();
	}

	@Override
	public void setIconUrl(String url) {
		this.iconUrl = url;
	}

	@Override
	public String getIconUrl() {
		return iconUrl;
	}

	protected void setRoleObject(T roleObject) {
		this.roleObject = roleObject;
	}

	protected T getRoleObject() {
		return roleObject;
	}
}
