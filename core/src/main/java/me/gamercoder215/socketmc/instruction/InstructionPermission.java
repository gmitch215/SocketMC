package me.gamercoder215.socketmc.instruction;

import me.gamercoder215.socketmc.config.ModPermission;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used on Instruction IDs to check if the instruction is allowed to be executed.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InstructionPermission {

    /**
     * The permission required to execute the instruction.
     * @return Permission
     */
    @NotNull
    ModPermission value();

}
