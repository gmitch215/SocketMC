package me.gamercoder215.socketmc.instruction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used in internal machines to identify the instruction.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface InstructionId {

    /**
     * The instruction ID.
     * @return The instruction ID.
     */
    String value();

}
