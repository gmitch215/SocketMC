package xyz.gmitch215.socketmc.forge.mod;

import xyz.gmitch215.socketmc.ModAuditLog;
import net.minecraft.Util;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.FocusableTextWidget;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LayoutSettings;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static xyz.gmitch215.socketmc.forge.mod.MainScreen.AUDIT_LOG;

public final class AuditLogScreen extends Screen {

    private final Screen previousScreen;
    private final HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this, 61, 33);

    public static final Component OPEN_AUDIT_LOG = Component.translatable("gui.socketmc.open_audit_log");

    public AuditLogScreen(Screen previousScreen) {
        super(AUDIT_LOG);

        this.previousScreen = previousScreen;
    }

    @Override
    public void init() {
        LinearLayout top = layout.addToHeader(LinearLayout.vertical().spacing(8));
        top.addChild(new StringWidget(AUDIT_LOG, this.font), LayoutSettings::alignHorizontallyCenter);

        GridLayout grid = new GridLayout();
        grid.defaultCellSetting().paddingHorizontal(8).paddingBottom(4);

        GridLayout.RowHelper rows = grid.createRowHelper(1);
        List<String> lines = new ArrayList<>(ModAuditLog.INSTANCE.readLog(10));

        if (lines.isEmpty())
            rows.addChild(new StringWidget(Component.translatable("gui.socketmc.no_logs"), this.font));
        else for (String line : lines)
            rows.addChild(new FocusableTextWidget(this.width - 80, Component.literal(line), this.font));

        LinearLayout bottom = layout.addToFooter(LinearLayout.horizontal().spacing(8));
        bottom.addChild(Button.builder(CommonComponents.GUI_BACK, button -> onClose()).build());

        URI logFolder = ModAuditLog.INSTANCE.getFolder().toURI();
        bottom.addChild(Button.builder(OPEN_AUDIT_LOG, button -> Util.getPlatform().openUri(logFolder)).build());

        layout.addToContents(grid);
        layout.visitWidgets(this::addRenderableWidget);
        repositionElements();
    }

    @Override
    public void repositionElements() {
        this.layout.arrangeElements();
    }

    @Override
    public void onClose() {
        minecraft.setScreen(previousScreen);
    }

}
