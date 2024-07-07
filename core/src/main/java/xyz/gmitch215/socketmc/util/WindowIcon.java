package xyz.gmitch215.socketmc.util;

import xyz.gmitch215.socketmc.instruction.Instruction;

/**
 * Represents an icon for a system window.
 */
public enum WindowIcon {

    /**
     * The "ok" icon.
     */
    OK,

    /**
     * The "warning" icon.
     */
    WARNING,

    /**
     * The "error" icon.
     */
    ERROR,

    /**
     * The "question" icon. Unsupported for {@link Instruction#EXTERNAL_WINDOW_POPUP}.
     */
    QUESTION,

}
