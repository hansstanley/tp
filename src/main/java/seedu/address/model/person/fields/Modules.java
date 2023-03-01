package seedu.address.model.person.fields;

import java.util.Set;

/**
 * Represents a Person's modules taken in the address book.
 */
public class Modules {

    public static final String MESSAGE_CONSTRAINTS = "Modules should be a part of NUS' NUSMods list";
    public final Set<NusMod> mods;

    public Modules(Set<NusMod> mods) {
        this.mods = mods;
    }

    //todo: Update modules to only be able to include mods that are a part of NUSMods.
    public static boolean isValidModules(String trimmedModules) {
        return true;
    }
}
