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

import org.geomajas.graphics.client.action.Action;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.service.GraphicsService;
import org.geomajas.graphics.client.util.Interruptible;

/**
 * Wraps {@link Editor} invocation in an {@link org.geomajas.graphics.client.action.Action}.
 *
 * @author Jan De Moerloose
 *
 */
public class EditorAction implements Action, Interruptible {

	private Editor editor;

	private String iconUrl;

	public EditorAction(Editor editor) {
		this.editor = editor;
	}

	@Override
	public boolean supports(GraphicsObject object) {
		return editor.supports(object);
	}

	@Override
	public void setService(GraphicsService service) {
		editor.setService(service);
	}

	@Override
	public void execute(GraphicsObject object) {
		editor.setObject(object);
		if (editor instanceof Interruptible) {
			((Interruptible) editor).start();
		}
	}

	@Override
	public String getLabel() {
		return editor.getLabel();
	}

	@Override
	public void setIconUrl(String url) {
		this.iconUrl = url;
	}

	@Override
	public String getIconUrl() {
		return iconUrl;
	}

	@Override
	public void cancel() {
		if (editor instanceof Interruptible) {
			((Interruptible) editor).cancel();
		}
	}

	@Override
	public void stop() {
		if (editor instanceof Interruptible) {
			((Interruptible) editor).stop();
		}
	}

	@Override
	public void save() {
		if (editor instanceof Interruptible) {
			((Interruptible) editor).save();
		}
	}

	@Override
	public void pause() {
		if (editor instanceof Interruptible) {
			((Interruptible) editor).pause();
		}
	}

	@Override
	public void resume() {
		if (editor instanceof Interruptible) {
			((Interruptible) editor).resume();
		}
	}

	@Override
	public boolean isInterrupted() {
		if (editor instanceof Interruptible) {
			return ((Interruptible) editor).isInterrupted();
		}
		return false;
	}

	@Override
	public boolean isInProgress() {
		if (editor instanceof Interruptible) {
			return ((Interruptible) editor).isInProgress();
		}
		return false;
	}

	@Override
	public void start() {
		// do nothing
	}

	public Editor getEditor() {
		return editor;
	}
}