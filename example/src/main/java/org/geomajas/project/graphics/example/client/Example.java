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
package org.geomajas.project.graphics.example.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.SimpleEventBus;
import org.geomajas.graphics.client.action.BringToFrontAction;
import org.geomajas.graphics.client.action.DeleteAction;
import org.geomajas.graphics.client.action.DuplicateAction;
import org.geomajas.graphics.client.controller.AnchorControllerFactory;
import org.geomajas.graphics.client.controller.CreateAnchoredIconController;
import org.geomajas.graphics.client.controller.CreateAnchoredTextController;
import org.geomajas.graphics.client.controller.CreateEllipseController;
import org.geomajas.graphics.client.controller.CreateIconController;
import org.geomajas.graphics.client.controller.CreateImageController;
import org.geomajas.graphics.client.controller.CreateMetaController;
import org.geomajas.graphics.client.controller.CreatePathController;
import org.geomajas.graphics.client.controller.CreateRectangleController;
import org.geomajas.graphics.client.controller.CreateTextAreaHtmlController;
import org.geomajas.graphics.client.controller.CreateTextController;
import org.geomajas.graphics.client.controller.DeleteControllerFactory;
import org.geomajas.graphics.client.controller.DragControllerFactory;
import org.geomajas.graphics.client.controller.LabelControllerFactory;
import org.geomajas.graphics.client.controller.PopupMenuControllerFactory;
import org.geomajas.graphics.client.controller.PopupMenuFactory;
import org.geomajas.graphics.client.controller.ResizeControllerFactory;
import org.geomajas.graphics.client.editor.AnchorStyleEditor;
import org.geomajas.graphics.client.editor.LabelEditor;
import org.geomajas.graphics.client.editor.StrokeFillEditor;
import org.geomajas.graphics.client.editor.TextableEditor;
import org.geomajas.graphics.client.event.GraphicsObjectContainerEvent;
import org.geomajas.graphics.client.event.GraphicsObjectContainerEvent.ActionType;
import org.geomajas.graphics.client.event.GraphicsObjectContainerEvent.Handler;
import org.geomajas.graphics.client.service.GraphicsController;
import org.geomajas.graphics.client.service.GraphicsService;
import org.geomajas.graphics.client.service.GraphicsServiceImpl;
import org.geomajas.graphics.client.widget.CreateButtonToolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Example application.
 * 
 * @author Jan De Moerloose
 * 
 */
public class Example implements EntryPoint, Handler {

	private FlowPanel buttonPanel;
	
	private GraphicsService service;
	
	private VerticalPanel iconChoicePanel;
	
	private CheckBox checkShowDrag;
	
	private CheckBox checkExternalLabel;
	
	private List<String> urls = new ArrayList<String>(Arrays.asList(GWT.getModuleBaseURL() + "image/slider.gif",
			GWT.getModuleBaseURL() + "image/cloud.png",
			GWT.getModuleBaseURL() + "image/sun.jpg"));
	
	private List<String> url = new ArrayList<String>(Arrays.asList(urls.get(0)));
	
	private PopupMenuControllerFactory popupFactory;

	private TextBox textBoxCogXOffset;
	private TextBox textBoxCogYOffset;

	private TestContainer rc;

	@Override
	public void onModuleLoad() {
		SimpleEventBus bus = new SimpleEventBus();
		rc = new TestContainer(bus);
		service = new GraphicsServiceImpl(bus, true, true);
		service.setObjectContainer(rc);
		service.getObjectContainer().addGraphicsObjectContainerHandler(this);
		
		//functionalities
		popupFactory = new PopupMenuControllerFactory(new PopupMenuFactory());
		registerControllerFactories();
		registerPopupFactoryActionsAndEditiors();		
		
		//layout
		DockLayoutPanel dock = new DockLayoutPanel(Unit.PX);
		buttonPanel = new FlowPanel();
		buttonPanel.addStyleName("graphicsExample-leftPanel-total");
		createButtonPanel();
		buttonPanel.getElement().getStyle().setBackgroundColor("grey");
		dock.addWest(buttonPanel, 150);
		dock.add(rc);
		RootLayoutPanel.get().add(dock);
		
		service.start();
	}
	
	private void registerControllerFactories() {
		service.registerControllerFactory(new ResizeControllerFactory());
		service.registerControllerFactory(new DragControllerFactory());
		service.registerControllerFactory(new DeleteControllerFactory());
		service.registerControllerFactory(new LabelControllerFactory());
//		service.registerControllerFactory(new ExternalizableLabeledControllerFactory());
		service.registerControllerFactory(new AnchorControllerFactory());
		service.registerControllerFactory(popupFactory);
	}
	
	private void registerPopupFactoryActionsAndEditiors() {
		popupFactory.registerAction(new DeleteAction());
		popupFactory.registerEditor(new TextableEditor());
		popupFactory.registerEditor(new LabelEditor());
//		popupFactory.registerEditor(new ExternalLabelEditor());
		popupFactory.registerEditor(new StrokeFillEditor());
		popupFactory.registerAction(new DuplicateAction());
		popupFactory.registerAction(new BringToFrontAction());
		popupFactory.registerEditor(new AnchorStyleEditor());
//		popupFactory.registerAction(new AddTextAsAnchorAction());
//		popupFactory.registerAction(new ToggleLabelAction());
//		popupFactory.registerEditor(new TemplateLabelEditor());
	}
	
	private void createButtonPanel() {
		// create extra elements
		createCheckShowDrag();
		createCheckExternalLabel();
		CreateIconController createIconController = new CreateIconController(service, 16, 16, url);
		CreateAnchoredIconController createAnchoredIconController 
			= new CreateAnchoredIconController(service, 16,	16, null);
		createAnchoredIconController.setChoiceListImageSize(32);
		createIconChoicePanel(createIconController, createAnchoredIconController);

		addCaptionPanelToButtonPanel(createCreateButtonsPanel(createIconController, createAnchoredIconController),
				"Create objects");
		addCaptionPanelToButtonPanel(createCogPositionPanel(), "Cog position offset");
		addCaptionPanelToButtonPanel(createGeneralOptionsPanel(), "General Options");

		buttonPanel.add(iconChoicePanel);
	}

	private void addCaptionPanelToButtonPanel(Widget content, String captionText) {
		CaptionPanel captionPanel = new CaptionPanel();
		captionPanel.setCaptionText(captionText);
		captionPanel.setContentWidget(content);
		buttonPanel.add(captionPanel);
	}

	//checkbox for showing original object when dragging
	private void createCheckShowDrag() {
		checkShowDrag = new CheckBox();
		checkShowDrag.setValue(service.isShowOriginalObjectWhileDragging());
		checkShowDrag.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				service.setShowOriginalObjectWhileDragging(checkShowDrag.getValue());
			}
			
		});
		checkShowDrag.setText("show original on drag");
	}
	
	//checkbox for showing original object when dragging
	private void createCheckExternalLabel() {
		checkExternalLabel = new CheckBox();
		checkExternalLabel.setValue(service.isExternalizableLabeledOriginallyExternal());
		checkExternalLabel.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				service.setExternalizableLabeledOriginallyExternal(checkExternalLabel.getValue());
			}
			
		});
		checkExternalLabel.setText("label external on creation");
	}
	
	private void createIconChoicePanel(final CreateIconController createIconController, 
			final CreateAnchoredIconController createAnchoredIconController) {
		iconChoicePanel = new VerticalPanel();
		RadioButton rb0 = new RadioButton("myRadioGroup", "No icon: sets default");
		rb0.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				createIconController.setHrefs(null);
				createAnchoredIconController.setHrefs(null);
			}
		});
		RadioButton rb1 = new RadioButton("myRadioGroup", "1 icon");
		rb1.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				createIconController.setHrefs(url);
				createAnchoredIconController.setHrefs(url);
			}
		});
		RadioButton rb2 = new RadioButton("myRadioGroup", "multiple icons");
		rb2.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				createIconController.setHrefs(urls);
				createAnchoredIconController.setHrefs(urls);
			}
		});
		iconChoicePanel.add(new Label("Change nr of icons in icon choice list:"));
		iconChoicePanel.add(rb0);
		iconChoicePanel.add(rb1);
		iconChoicePanel.add(rb2);
		iconChoicePanel.setVisible(false);
		rb1.setValue(true);
	}

	private Widget createCreateButtonsPanel(CreateIconController createIconController,
											CreateAnchoredIconController createAnchoredIconController) {
		CreateMetaController createMetaController = new CreateMetaController(service);
		createMetaController.addControllerAndCreateButton(new CreateTextController(service), "Text");
		createMetaController.addControllerAndCreateButton(new CreateAnchoredTextController(service), "Text+Anchor");
		createMetaController.addControllerAndCreateButton(new CreateTextAreaHtmlController(service), "Textarea");
		createMetaController.addControllerAndCreateButton(new CreateRectangleController(service), "Rectangle");
		createMetaController.addControllerAndCreateButton(new CreateEllipseController(service), "Ellipse");
		createMetaController.addControllerAndCreateButton(new CreateImageController(service, 200, 235,
				"http://tuxpaint.org/stamps/stamps/animals/birds/cartoon/tux.png"), "Image");
		createMetaController.addControllerAndCreateButton(new CreatePathController(service, false), "Line");
		createMetaController.addControllerAndCreateButton(new CreatePathController(service, true), "Polygon");
		/*createMetaController.addControllerAndCreateButton(new CreateLineWithTemplateLabeledController(service),
				"Line With Templ Labeled"); */
		createMetaController.addControllerAndCreateButton(createIconController, "Icon");
		createMetaController.addControllerAndCreateButton(createAnchoredIconController, "Anchored Icon");
		CreateButtonToolbar createButtonToolbar = createMetaController.getToolbar();
		createButtonToolbar.setStyleName("graphicsExample-leftPanel-createButtonsPanel");
		return createButtonToolbar;
	}

	private Widget createCogPositionPanel() {
		String tooltipText = "x and y offset from left top position. 0-0 position inside object. " +
				"Positive offset values means: more outside the object. Offset step is size of the cog.";

		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setTitle(tooltipText);
		HorizontalPanel xPanel = new HorizontalPanel();
		xPanel.add(new Label("x:"));
		textBoxCogXOffset = new TextBox();
		textBoxCogXOffset.setStyleName("graphicsExample-leftPanel-cogPosition-textBox");
		textBoxCogXOffset.setText(popupFactory.getOffsetX() + "");
		textBoxCogXOffset.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				try {
					popupFactory.setOffsetX(Double.parseDouble(textBoxCogXOffset.getText()));
				} catch (Exception ex) {
					//don't do anything
				}
			}
		});
		textBoxCogXOffset.setTitle(tooltipText);
		xPanel.add(textBoxCogXOffset);
		verticalPanel.add(xPanel);

		HorizontalPanel yPanel = new HorizontalPanel();
		yPanel.add(new Label("y:"));
		textBoxCogYOffset = new TextBox();
		textBoxCogYOffset.setStyleName("graphicsExample-leftPanel-cogPosition-textBox");
		textBoxCogYOffset.setText(popupFactory.getOffsetY() + "");
		textBoxCogYOffset.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				try {
					popupFactory.setOffsetY(Double.parseDouble(textBoxCogYOffset.getText()));
				} catch (Exception ex) {
					//don't do anything
				}
			}
		});
		textBoxCogYOffset.setTitle(tooltipText);
		yPanel.add(textBoxCogYOffset);
		verticalPanel.add(yPanel);

		return verticalPanel;
	}

	private Widget createGeneralOptionsPanel() {
		VerticalPanel verticalPanel = new VerticalPanel();
		ControllerButton controllerButton = new
				ControllerButton(new NavigationController(service, rc.getRootContainer()), "Navigation");
		controllerButton.addStyleName("graphicsExample-leftPanel-generalPanel-navigationButton");
		verticalPanel.add(controllerButton);
		//buttons for creation of objects
		verticalPanel.add(new SimplePanel(checkShowDrag));
		//verticalPanel.add(new SimplePanel(checkExternalLabel));
		return verticalPanel;
	}

	@Override
	public void onAction(GraphicsObjectContainerEvent event) {
		if (event.getActionType() != ActionType.UPDATE) {
			for (int i = 0; i < buttonPanel.getWidgetCount(); i++) {
				if (buttonPanel.getWidget(i) instanceof ControllerButton) {
					((ControllerButton) buttonPanel.getWidget(i)).setControllerActive(false);
					service.getMetaController().setActive(true);
				}
			}
		}

	}

	/**
	 * 
	 */
	public class ControllerButton extends ToggleButton {

		private GraphicsController controller;

		public ControllerButton(final GraphicsController controller, String upText) {
			super(upText);
			this.controller = controller;
			addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					iconChoicePanel.setVisible(false);
					setControllerActive(!controller.isActive());
				}
			});
		}

		public void setControllerActive(boolean active) {
			setDown(active);
			controller.setActive(active);
			if (active) {
				service.getMetaController().setActive(false);
				if (controller instanceof CreateAnchoredIconController || controller instanceof CreateIconController) {
					iconChoicePanel.setVisible(true);
				}
			}
		}

	}
}
