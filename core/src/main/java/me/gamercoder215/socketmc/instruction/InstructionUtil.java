package me.gamercoder215.socketmc.instruction;

import org.jetbrains.annotations.Nullable;

import java.util.regex.Pattern;

/**
 * Utilities used in the validaton and execution of instructions.
 */
public final class InstructionUtil {

    private InstructionUtil() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * <p>A regular expression pattern that matches a valid email address.</p>
     * <p>This expression is based on the <a href="https://www.rfc-editor.org/info/rfc5322">RFC 5322</a> standard for
     * email addresses.</p>
     */
    public static final Pattern VALID_EMAIL_ADDRESS = Pattern.compile(
            "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",
            Pattern.CASE_INSENSITIVE
    );

    /**
     * Checks if the given string is a valid email address.
     * @param email The email address to check.
     * @return {@code true} if the email address is valid, {@code false} otherwise.
     * @see #VALID_EMAIL_ADDRESS
     */
    public static boolean isEmailAddress(@Nullable String email) {
        if (email == null) return false;

        return VALID_EMAIL_ADDRESS.matcher(email).matches();
    }

}
