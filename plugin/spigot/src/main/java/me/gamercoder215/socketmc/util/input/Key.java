package me.gamercoder215.socketmc.util.input;

import org.jetbrains.annotations.Nullable;

/**
 * Represents a key on a keyboard.
 */
public enum Key {

    /**
     * ' ' (space)
     */
    SPACE(32),
    /**
     * '
     */
    APOSTROPHE(39),
    /**
     * ,
     */
    COMMA(44),
    /**
     * -
     */
    MINUS(45),
    /**
     * .
     */
    PERIOD(46),
    /**
     * /
     */
    SLASH(47),
    /**
     * 0
     */
    NUM_0(48),
    /**
     * 1
     */
    NUM_1(49),
    /**
     * 2
     */
    NUM_2(50),
    /**
     * 3
     */
    NUM_3(51),
    /**
     * 4
     */
    NUM_4(52),
    /**
     * 5
     */
    NUM_5(53),
    /**
     * 6
     */
    NUM_6(54),
    /**
     * 7
     */
    NUM_7(55),
    /**
     * 8
     */
    NUM_8(56),
    /**
     * 9
     */
    NUM_9(57),
    /**
     * ;
     */
    SEMICOLON(59),
    /**
     * =
     */
    EQUAL(61),
    /**
     * A
     */
    A(65),
    /**
     * B
     */
    B(66),
    /**
     * C
     */
    C(67),
    /**
     * D
     */
    D(68),
    /**
     * E
     */
    E(69),
    /**
     * F
     */
    F(70),
    /**
     * G
     */
    G(71),
    /**
     * H
     */
    H(72),
    /**
     * I
     */
    I(73),
    /**
     * J
     */
    J(74),
    /**
     * K
     */
    K(75),
    /**
     * L
     */
    L(76),
    /**
     * M
     */
    M(77),
    /**
     * N
     */
    N(78),
    /**
     * O
     */
    O(79),
    /**
     * P
     */
    P(80),
    /**
     * Q
     */
    Q(81),
    /**
     * R
     */
    R(82),
    /**
     * S
     */
    S(83),
    /**
     * T
     */
    T(84),
    /**
     * U
     */
    U(85),
    /**
     * V
     */
    V(86),
    /**
     * W
     */
    W(87),
    /**
     * X
     */
    X(88),
    /**
     * Y
     */
    Y(89),
    /**
     * Z
     */
    Z(90),
    /**
     * [
     */
    LEFT_BRACKET(91),
    /**
     * \
     */
    BACKSLASH(92),
    /**
     * ]
     */
    RIGHT_BRACKET(93),
    /**
     * `
     */
    GRAVE_ACCENT(96),
    /**
     * World 1 (non-US #1)
     */
    WORLD_1(161),
    /**
     * World 2 (non-US #2)
     */
    WORLD_2(162),
    /**
     * Escape Button
     */
    ESCAPE(256),
    /**
     * Enter Button
     */
    ENTER(257),
    /**
     * Tab Button
     */
    TAB(258),
    /**
     * Backspace Button
     */
    BACKSPACE(259),
    /**
     * Insert Button
     */
    INSERT(260),
    /**
     * Delete Button
     */
    DELETE(261),
    /**
     * Right Arrow Button
     */
    RIGHT(262),
    /**
     * Left Arrow Button
     */
    LEFT(263),
    /**
     * Down Arrow Button
     */
    DOWN(264),
    /**
     * Up Arrow Button
     */
    UP(265),
    /**
     * Page Up Button
     */
    PAGE_UP(266),
    /**
     * Page Down Button
     */
    PAGE_DOWN(267),
    /**
     * Home Button
     */
    HOME(268),
    /**
     * End Button
     */
    END(269),
    /**
     * Caps Lock Button
     */
    CAPS_LOCK(280),
    /**
     * Scroll Lock Button
     */
    SCROLL_LOCK(281),
    /**
     * Num Lock Button
     */
    NUM_LOCK(282),
    /**
     * Print Screen Button
     */
    PRINT_SCREEN(283),
    /**
     * Pause Button
     */
    PAUSE(284),
    /**
     * F1
     */
    F1(290),
    /**
     * F2
     */
    F2(291),
    /**
     * F3
     */
    F3(292),
    /**
     * F4
     */
    F4(293),
    /**
     * F5
     */
    F5(294),
    /**
     * F6
     */
    F6(295),
    /**
     * F7
     */
    F7(296),
    /**
     * F8
     */
    F8(297),
    /**
     * F9
     */
    F9(298),
    /**
     * F10
     */
    F10(299),
    /**
     * F11
     */
    F11(300),
    /**
     * F12
     */
    F12(301),
    /**
     * F13
     */
    F13(302),
    /**
     * F14
     */
    F14(303),
    /**
     * F15
     */
    F15(304),
    /**
     * F16
     */
    F16(305),
    /**
     * F17
     */
    F17(306),
    /**
     * F18
     */
    F18(307),
    /**
     * F19
     */
    F19(308),
    /**
     * F20
     */
    F20(309),
    /**
     * F21
     */
    F21(310),
    /**
     * F22
     */
    F22(311),
    /**
     * F23
     */
    F23(312),
    /**
     * F24
     */
    F24(313),
    /**
     * F25
     */
    F25(314),
    /**
     * 0 (Keypad)
     */
    KP_0(320),
    /**
     * 1 (Keypad)
     */
    KP_1(321),
    /**
     * 2 (Keypad)
     */
    KP_2(322),
    /**
     * 3 (Keypad)
     */
    KP_3(323),
    /**
     * 4 (Keypad)
     */
    KP_4(324),
    /**
     * 5 (Keypad)
     */
    KP_5(325),
    /**
     * 6 (Keypad)
     */
    KP_6(326),
    /**
     * 7 (Keypad)
     */
    KP_7(327),
    /**
     * 8 (Keypad)
     */
    KP_8(328),
    /**
     * 9 (Keypad)
     */
    KP_9(329),
    /**
     * . (Keypad)
     */
    KP_DECIMAL(330),
    /**
     * / (Keypad)
     */
    KP_DIVIDE(331),
    /**
     * * (Keypad)
     */
    KP_MULTIPLY(332),
    /**
     * - (Keypad)
     */
    KP_SUBTRACT(333),
    /**
     * + (Keypad)
     */
    KP_ADD(334),
    /**
     * Enter Button (Keypad)
     */
    KP_ENTER(335),
    /**
     * = (Keypad)
     */
    KP_EQUAL(336),
    /**
     * Left Shift Button
     */
    LEFT_SHIFT(340),
    /**
     * Left Control Button
     */
    LEFT_CONTROL(341),
    /**
     * Left Alt Button
     */
    LEFT_ALT(342),
    /**
     * Left Super Button
     */
    LEFT_SUPER(343),
    /**
     * Right Shift Button
     */
    RIGHT_SHIFT(344),
    /**
     * Right Control Button
     */
    RIGHT_CONTROL(345),
    /**
     * Right Alt Button
     */
    RIGHT_ALT(346),
    /**
     * Right Super Button
     */
    RIGHT_SUPER(347),
    /**
     * Menu Button
     */
    MENU(348)
    ;

    private final int code;

    Key(int code) {
        this.code = code;
    }

    /**
     * Gets the keyboard code of this key, as specified by <a href="https://www.glfw.org/docs/latest/group__keys.html#gabf48fcc3afbe69349df432b470c96ef2">GLFW</a>.
     * @return the keyboard code
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets the key from the keyboard code.
     * @param code the keyboard code
     * @return the key or null if not found
     * @see #getCode()
     */
    @Nullable
    public static Key fromCode(int code) {
        for (Key key : values()) {
            if (key.code == code) {
                return key;
            }
        }
        return null;
    }
}
