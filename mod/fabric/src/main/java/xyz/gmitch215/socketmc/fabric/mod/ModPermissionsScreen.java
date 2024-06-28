package xyz.gmitch215.socketmc.fabric.mod;

import xyz.gmitch215.socketmc.SocketMC;
import xyz.gmitch215.socketmc.config.ModPermission;
import xyz.gmitch215.socketmc.spigot.SocketPlugin;
import net.minecraft.Util;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LayoutSettings;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static xyz.gmitch215.socketmc.fabric.mod.MainScreen.PERMISSIONS;

public final class ModPermissionsScreen extends Screen {

    private final SocketPlugin current;
    private final Screen previousScreen;
    private final HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this, 60, 30);

    public ModPermissionsScreen(Screen previousScreen) {
        this(null, previousScreen);
    }

    public ModPermissionsScreen(SocketPlugin current, Screen previousScreen) {
        super(PERMISSIONS);

        this.current = current;
        this.previousScreen = previousScreen;
    }

    public void init() {
        init(0);
    }

    public void init(int page0) {
        List<SocketPlugin> plugins = SocketMC.plugins();
        Collections.sort(plugins);

        int pageMax = (plugins.size() + 6) / 7;
        int page = Math.max(0, Math.min(page0, pageMax));
        plugins = plugins.subList(page * 7, Math.min(plugins.size(), (page + 1) * 7));

        LinearLayout header = layout.addToHeader(LinearLayout.vertical().spacing(8));
        header.addChild(new StringWidget(PERMISSIONS, this.font), LayoutSettings::alignHorizontallyCenter);

        GridLayout parentGrid = new GridLayout();
        parentGrid.defaultCellSetting().paddingHorizontal(12).paddingBottom(4);

        GridLayout.RowHelper rows = parentGrid.createRowHelper(2);

        LinearLayout left = LinearLayout.vertical().spacing(16);
        left.defaultCellSetting().alignHorizontallyLeft();

        Component def = Component.translatable("options.gamma.default");
        if (current == null) {
            FocusableTextWidget text = new FocusableTextWidget(this.width - 80, def, this.font);
            left.addChild(text);
        } else {
            StringWidget text = new StringWidget(def, this.font) {
                @Override
                public void onClick(double mouseX, double mouseY) { minecraft.setScreen(screen(null)); }
            };
            left.addChild(text);
        }

        for (final SocketPlugin plugin : plugins) {
            boolean same = plugin.equals(current);
            Component name = Component.literal(plugin.getPluginName());
            Tooltip tooltip = Tooltip.create(Component.literal(plugin.getPluginName() + " v" + plugin.getPluginVersion()));

            if (same) {
                FocusableTextWidget text = new FocusableTextWidget(this.width - 80, name, this.font);
                text.setTooltip(tooltip);
                left.addChild(text);
            } else {
                StringWidget text = new StringWidget(name, this.font) {
                    @Override
                    public void onClick(double mouseX, double mouseY) { minecraft.setScreen(screen(plugin)); }
                };

                text.setTooltip(tooltip);
                left.addChild(text);
            }
        }

        rows.addChild(left);

        if (pageMax != 1) {
            LinearLayout buttons = LinearLayout.horizontal().spacing(4);
            buttons.defaultCellSetting().paddingTop(4);

            if (page > 0)
                buttons.addChild(Button.builder(Component.literal("<-"), button -> page(page + 1)).width(40).build());
            if (page < pageMax)
                buttons.addChild(Button.builder(Component.literal("->"), button -> page(page - 1)).width(40).build());

            left.addChild(buttons);
        }

        GridLayout right = new GridLayout();
        right.defaultCellSetting().paddingVertical(2).paddingHorizontal(8);

        GridLayout.RowHelper rightRows = right.createRowHelper(2);
        for (ModPermission permission : ModPermission.values()) {
            boolean state = SocketMC.isPermissionEnabled(current, permission);

            Function<Boolean, Component> message = on -> Component.translatable(permission.getKey())
                    .append(": ")
                    .append(on ? CommonComponents.OPTION_ON : CommonComponents.OPTION_OFF);

            Button permButton = Button.builder(message.apply(state), button -> {
                boolean currentState = SocketMC.isPermissionEnabled(current, permission);
                SocketMC.setPermissionEnabled(current, permission, !currentState);
                button.setMessage(message.apply(!currentState));
            }).width(120).build();

            if (!permission.isChangeable())
                permButton.active = false;

            Component tooltip = Component.translatable(permission.getTooltip());
            permButton.setTooltip(Tooltip.create(tooltip));

            rightRows.addChild(permButton);
        }

        if (current != null && current.getPluginUrl() != null) {
            URI url = URI.create(current.getPluginUrl());

            LinearLayout rightBottom = LinearLayout.horizontal().spacing(4);
            rightBottom.defaultCellSetting().paddingTop(4);
            rightBottom.addChild(Button.builder(Component.translatable("gui.socketmc.website"),
                    button -> Util.getPlatform().openUri(url)).width(240).build());

            rightRows.addChild(rightBottom);
        }
        rows.addChild(right);

        layout.addToContents(parentGrid);
        layout.addToFooter(Button.builder(CommonComponents.GUI_BACK, button -> onClose()).width(200).build());
        layout.visitWidgets(this::addRenderableWidget);
        repositionElements();
    }

    public void page(int newPage) {
        clearWidgets();
        clearFocus();
        init(newPage);
        setInitialFocus();
    }

    @Override
    public void repositionElements() {
        layout.arrangeElements();
    }

    @Override
    public void onClose() {
        minecraft.setScreen(previousScreen);
    }

    private ModPermissionsScreen screen(SocketPlugin plugin) {
        return new ModPermissionsScreen(plugin, previousScreen);
    }

}
