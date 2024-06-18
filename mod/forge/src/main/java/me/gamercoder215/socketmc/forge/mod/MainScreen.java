package me.gamercoder215.socketmc.forge.mod;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LayoutSettings;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

import java.util.function.Function;

public final class MainScreen extends Screen {

    public static final Component TITLE = Component.translatable("gui.socketmc.title");

    public static final Component PERMISSIONS = Component.translatable("gui.socketmc.permissions");
    public static final Component AUDIT_LOG = Component.translatable("gui.socketmc.audit_log");

    private Screen previousScreen;
    private final HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this, 61, 33);

    public MainScreen(Screen previousScreen) {
        super(TITLE);
        this.previousScreen = previousScreen;
    }

    @Override
    public void onClose() {
        minecraft.setScreen(previousScreen);
    }

    @Override
    public void init() {
        LinearLayout top = layout.addToHeader(LinearLayout.vertical().spacing(8));
        top.addChild(new StringWidget(TITLE, this.font), LayoutSettings::alignHorizontallyCenter);

        GridLayout grid = new GridLayout();
        grid.defaultCellSetting().paddingHorizontal(4).paddingBottom(4).alignHorizontallyCenter();

        GridLayout.RowHelper rows = grid.createRowHelper(2);
        rows.addChild(screen(PERMISSIONS, ModPermissionsScreen::new));
        rows.addChild(screen(AUDIT_LOG, AuditLogScreen::new));

        layout.addToContents(grid);
        layout.addToFooter(Button.builder(CommonComponents.GUI_DONE, button -> this.onClose()).width(200).build());
        layout.visitWidgets(this::addRenderableWidget);
        repositionElements();
    }

    @Override
    public void repositionElements() {
        this.layout.arrangeElements();
    }

    private Button screen(Component text, Function<Screen, Screen> screenSupplier) {
        return Button.builder(text, button -> minecraft.setScreen(screenSupplier.apply(previousScreen))).build();
    }

}
