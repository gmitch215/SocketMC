package xyz.gmitch215.socketmc.util.render.text;

/**
 * Utility class for a list of common translate texts used in Vanilla Minecraft.
 */
public final class CommonTexts {
    
    private CommonTexts() {}

    /**
     * Field for {@link JsonText#empty()}.
     */
    public static final JsonText EMPTY = JsonText.empty();

    /**
     * Represents the text for the "ON" option.
     */
    public static final JsonText OPTION_ON = JsonText.translate("options.on");

    /**
     * Represents the text for the "OFF" option.
     */
    public static final JsonText OPTION_OFF = JsonText.translate("options.off");

    /**
     * "Done" Button Text.
     */
    public static final JsonText GUI_DONE = JsonText.translate("gui.done");

    /**
     * "Cancel" Button Text.
     */
    public static final JsonText GUI_CANCEL = JsonText.translate("gui.cancel");

    /**
     * "Yes" Button Text.
     */
    public static final JsonText GUI_YES = JsonText.translate("gui.yes");

    /**
     * "No" Button Text.
     */
    public static final JsonText GUI_NO = JsonText.translate("gui.no");

    /**
     * "Ok" Button Text.
     */
    public static final JsonText GUI_OK = JsonText.translate("gui.ok");

    /**
     * "Proceed" Button Text.
     */
    public static final JsonText GUI_PROCEED = JsonText.translate("gui.proceed");

    /**
     * "Continue" Button Text.
     */
    public static final JsonText GUI_CONTINUE = JsonText.translate("gui.continue");

    /**
     * "Back" Button Text.
     */
    public static final JsonText GUI_BACK = JsonText.translate("gui.back");

    /**
     * "Back To Title Screen" Button Text.
     */
    public static final JsonText GUI_TO_TITLE = JsonText.translate("gui.toTitle");

    /**
     * "Acknowledge" Button Text.
     */
    public static final JsonText GUI_ACKNOWLEDGE = JsonText.translate("gui.acknowledge");

    /**
     * "Open in Browser" Button Text.
     */
    public static final JsonText GUI_OPEN_IN_BROWSER = JsonText.translate("chat.link.open");

    /**
     * "Copy Link to Clipboard" Button Text.
     */
    public static final JsonText GUI_COPY_LINK_TO_CLIPBOARD = JsonText.translate("gui.copy_link_to_clipboard");

    /**
     * "Disconnect" Button Text.
     */
    public static final JsonText GUI_DISCONNECT = JsonText.translate("menu.disconnect");

    /**
     * "Connection Failed" Message for proxy (Bungeecord) connections.
     */
    public static final JsonText TRANSFER_CONNECT_FAILED = JsonText.translate("connect.failed.transfer");

    /**
     * "Connection Failed" Message.
     */
    public static final JsonText CONNECT_FAILED = JsonText.translate("connect.failed");

    /**
     * Newline Character.
     */
    public static final PlainText NEW_LINE = PlainText.of("\n");

    /**
     * The literal text for a period end and a space, used in the narration function.
     */
    public static final PlainText NARRATION_SEPARATOR = PlainText.of(". ");

    /**
     * Ellipsis Characters (...).
     */
    public static final PlainText ELLIPSIS = PlainText.of("...");

    /**
     * Space Character.
     */
    public static final PlainText SPACE = PlainText.of(" ");
    
}
