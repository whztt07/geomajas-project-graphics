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
package org.geomajas.graphics.client.controller.popupmenu;

import java.util.ArrayList;
import java.util.List;

import org.geomajas.graphics.client.Graphics;
import org.geomajas.graphics.client.action.Action;
import org.geomajas.graphics.client.controller.GraphicsController;
import org.geomajas.graphics.client.controller.GraphicsControllerFactory;
import org.geomajas.graphics.client.editor.Editor;
import org.geomajas.graphics.client.editor.EditorAction;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.service.GraphicsService;
import org.geomajas.graphics.client.util.Interruptible;

/**
 * Factory for the {@link PopupMenuControllerImpl}.
 * 
 * @author Jan De Moerloose
 * 
 */
public class PopupMenuControllerFactory implements GraphicsControllerFactory {

	private List<Action> actions = new ArrayList<Action>();
	
	// URL for the PopupMenu icon
	protected String iconUrl;
	
	public PopupMenuControllerFactory() {
		this(null);
	}
	
	public PopupMenuControllerFactory(String iconUrl) {
		super();
		this.iconUrl = iconUrl;
	}
	
	@Override
	public boolean supports(GraphicsObject object) {
		return true;
	}

	@Override
	public GraphicsController createController(GraphicsService graphicsService, GraphicsObject object) {
		for (Action action : actions) {
			action.setService(graphicsService);
		}
		PopupMenuController controller = new PopupMenuControllerImpl(actions, object, graphicsService, iconUrl);
		return controller;
	}

	/**
	 * Register an action with the popup menu. Actions/editors appear top-to-bottom in the order in which they have been
	 * registered.
	 * 
	 * @param action
	 */
	public void registerAction(Action action) {
		actions.add(action);
	}

	/**
	 * Register an editor with the popup menu. Actions/editors appear top-to-bottom in the order in which they have been
	 * registered.
	 * 
	 * @param editor
	 */

	public void registerEditor(Editor editor) {
		actions.add(new PopupMenuEditorAction(editor));
	}
	
	public List<Action> getActions() {
		return actions;
	}
	
	/**
	 * Cancels all {@link Interruptible} {@link Action} that are in progress.
	 * 
	 */
	public void cancelActions() {
		for (Interruptible action : getInterruptibleActions()) {
			if (action.isInProgress()) {
				action.cancel();
			}
		}
	}

	/**
	 * Saves and stops all {@link Interruptible} {@link Action} that are in progress.
	 * 
	 */
	public void saveAndStopActions() {
		for (Interruptible action : getInterruptibleActions()) {
			if (action.isInProgress()) {
				action.save();
				action.stop();
			}
		}
	}

	/**
	 * Checks whether it contains at least one {@link Interruptible} {@link Action} that is in progress.
	 * 
	 */
	public boolean isActionInProgress() {
		for (Interruptible action : getInterruptibleActions()) {
			if (action.isInProgress()) {
				return true;
			}
		}
		return false;
	}
	
	private List<Interruptible> getInterruptibleActions() {
		List<Interruptible> interruptibles = new ArrayList<Interruptible>();
		for (Action action : getActions()) {
			if (action instanceof Interruptible) {
				interruptibles.add((Interruptible) action);
			}
		}
		return interruptibles;
	}

	/**
	 * Wraps editor invocation in an {@link Action}.
	 * 
	 * @author Jan De Moerloose
	 * @author Jan Venstermans
	 * 
	 */
	public class PopupMenuEditorAction extends EditorAction implements PopupMenuController.EditorHandler {

		private PopupMenuController.EditorView box;
		
		public PopupMenuEditorAction(Editor editor) {
			super(editor);
			box = Graphics.getViewManager().createPopupMenuEditorView(editor);
			box.addHandler(this);
		}

		@Override
		public void execute(GraphicsObject object) {
			super.execute(object);
			box.center();
		}

		@Override
		public void cancel() {
			box.hide();
			super.cancel();
		}

		@Override
		public void stop() {
			box.hide();
			super.stop();
		}

		@Override
		public void onOk() {
			if (getEditor().validate()) {
				getEditor().onOk();
				box.hide();
			}
		}

		@Override
		public void onApply() {
			if (getEditor().validate()) {
				getEditor().onOk();
			}
		}

		@Override
		public void onUndo() {
			 getEditor().undo();
		}

		@Override
		public void onCancel() {
			 cancel();
		}
	}

	

}
