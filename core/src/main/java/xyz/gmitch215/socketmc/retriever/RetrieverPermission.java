package xyz.gmitch215.socketmc.retriever;

import org.jetbrains.annotations.NotNull;
import xyz.gmitch215.socketmc.config.ModPermission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used on Retriever Types to check if the retriever is allowed to be requested.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RetrieverPermission {

    /**
     * The permission required to use the retreiver.
     * @return Permission
     */
    @NotNull
    ModPermission value();

}
