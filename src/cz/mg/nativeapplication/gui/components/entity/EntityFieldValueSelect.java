//package cz.mg.nativeapplication.gui.components.entity;
//
//import cz.mg.annotations.classes.Utility;
//import cz.mg.annotations.requirement.Mandatory;
//import cz.mg.annotations.requirement.Optional;
//import cz.mg.annotations.storage.Link;
//import cz.mg.annotations.storage.Shared;
//import cz.mg.nativeapplication.gui.MainWindow;
//import cz.mg.nativeapplication.gui.components.RefreshableView;
//import cz.mg.nativeapplication.gui.components.controls.UiButton;
//import cz.mg.nativeapplication.gui.components.controls.UiLabel;
//import cz.mg.nativeapplication.gui.components.controls.UiPanel;
//import cz.mg.nativeapplication.gui.icons.IconGallery;
//import cz.mg.nativeapplication.history.SetEntityFieldAction;
//import cz.mg.nativeapplication.sevices.EntityField;
//import cz.mg.nativeapplication.sevices.gui.ObjectNameProvider;
//
//import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Alignment.LEFT;
//import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Alignment.MIDDLE;
//import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Fill.BOTH;
//
//
//public @Utility class EntityFieldValueSelect implements RefreshableView {
//    private static final int PADDING = 2;
//
//    private final @Mandatory @Link MainWindow mainWindow;
//    private final @Mandatory @Link Object entity;
//    private final @Mandatory @Link EntityField entityField;
//
//    private final @Mandatory @Shared UiLabel label;
//    private final @Mandatory @Shared UiPanel buttons;
//    private final @Mandatory @Shared UiButton clearButton;
//
//    public EntityFieldValueSelect(
//        @Mandatory MainWindow mainWindow,
//        @Mandatory Object entity,
//        @Mandatory EntityField entityField
//    ) {
//        this.mainWindow = mainWindow;
//        this.entity = entity;
//        this.entityField = entityField;
//        this.label = new UiLabel(entityField.getName());
//        this.clearButton = new UiButton(mainWindow, IconGallery.CLEAR, null, "Clear", this::onClearButtonClicked);
//        this.buttons = new UiPanel(0, PADDING, LEFT);
//        this.buttons.add(clearButton, 0, 0, 0, 0, MIDDLE, BOTH);
//        this.buttons.rebuild();
//    }
//
//    public @Mandatory UiLabel getLabel() {
//        return label;
//    }
//
//    public @Mandatory UiPanel getButtons() {
//        return buttons;
//    }
//
//    @Override
//    public void refresh() {
//        Object object = entityField.get(entity);
//        textField.setText(new ObjectNameProvider().getDisplayName(object));
//        textField.setNull(object == null);
//    }
//
//    private void setValue(@Optional Object value) {
//        mainWindow.getApplicationState().getHistory().run(new SetEntityFieldAction(
//            entityField, entity, value, entityField.get(entity)
//        ));
//        refresh();
//    }
//
//    private void onClearButtonClicked() {
//        setValue(null);
//    }
//}
