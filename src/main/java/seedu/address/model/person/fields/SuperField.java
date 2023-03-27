package seedu.address.model.person.fields;

import java.util.Collections;
import java.util.Set;

/**
 * Represents a field that hold multiple of a mono-field.
 * @param <T> The type of the mono-field
 */
public abstract class SuperField<T extends Field> {
    public final Set<T> values;

    public SuperField(Set<T> values) {
        this.values = values;
    }

    public Set<T> getValues() {
        return Collections.unmodifiableSet(this.values);
    }

    /**
     * Returns true if at least one of the strings contained in test
     * is contained within the value of the field.
     * For the more mathematically inclined,
     * {∃v ∈ values s.t. v.contains(test) == true}.
     */
    public boolean contains(Set<String> test) {
        for (T value: values) {
            if (value.contains(test)) {
                return true;
            }
        }
        return false;
    }
}