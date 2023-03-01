package seedu.address.model.person.fields.enums;

/**
 * Contains list of general genders to be specified in the addressbook.
 */
public enum Genders {

    MALE("male"),
    FEMALE("female"),
    DNS("dns");

    private final String gender;

    Genders(String gender) {
        this.gender = gender;
    }

}
