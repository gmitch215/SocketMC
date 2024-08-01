package xyz.gmitch215.socketmc.util.render;

import xyz.gmitch215.socketmc.instruction.Instruction;

/**
 * Represents the context for an itemstack to be displayed.
 */
public enum ItemDisplayType {

    /**
     * The item is not displayed.
     */
    NONE,

    /**
     * The rendering context when the player is holding an item in third person in their left hand.
     */
    THIRD_PERSON_LEFT_HAND,

    /**
     * The rendering context when the player is holding an item in third person in their right hand.
     */
    THIRD_PERSON_RIGHT_HAND,

    /**
     * The rendering context when the player is holding an item in first person in their left hand.
     */
    FIRST_PERSON_LEFT_HAND,

    /**
     * The rendering context when the player is holding an item in first person in their right hand.
     */
    FIRST_PERSON_RIGHT_HAND,

    /**
     * The rendering context when the item is on the player's head.
     */
    HEAD,

    /**
     * The rendering context when the item is on the player's screen, such as in the hotbar. Used for {@link Instruction#DRAW_ITEMSTACK} and is the default.
     */
    GUI,

    /**
     * The rendering context when the item is on the ground.
     */
    GROUND,

    /**
     * The rendering context when the item is in an item frame.
     */
    FIXED

    ;

    /**
     * Returns whether the type is displayed.
     * @return true if the type is not equal to {@link #NONE}, false otherwise
     */
    public boolean isDisplayed() {
        return this != NONE;
    }

    /**
     * Returns whether the type is in first person.
     * @return true if the type is in first person, false otherwise
     */
    public boolean isFirstPerson() {
        return this == FIRST_PERSON_LEFT_HAND || this == FIRST_PERSON_RIGHT_HAND;
    }

    /**
     * Returns whether the type is in third person.
     * @return true if the type is in third person, false otherwise
     */
    public boolean isThirdPerson() {
        return this == THIRD_PERSON_LEFT_HAND || this == THIRD_PERSON_RIGHT_HAND;
    }

    /**
     * Returns whether the type is equal to {@link #GUI}.
     * @return true if the type is equal to {@link #GUI}, false otherwise
     */
    public boolean isGui() {
        return this == GUI;
    }
}
