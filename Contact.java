/*************************
 * Name: Jamie Kutzer
 * Course: CS-320
 * Date: January 25, 2026
 * Description:
 * This class represents a Contact object for the mobile application.
 * Each contact contains identifying and personal information that must
 * meet strict validation rules defined by the customer requirements.
 *************************/

package contactservice;

public class Contact {

    // Unique contact ID (immutable after creation)
    private final String contactId;

    // Contact information fields
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    /*
     * Constructor
     * Initializes a Contact object while enforcing all field requirements.
     * The contactId is required, must be unique, and cannot be updated.
     * All other fields are validated before assignment.
     */
    public Contact(String contactId, String firstName, String lastName,
                   String phone, String address) {

        validateContactId(contactId);
        validateName(firstName, "First name");
        validateName(lastName, "Last name");
        validatePhone(phone);
        validateAddress(address);

        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    // ---------------- GETTERS ----------------

    /*
     * Returns the unique contact ID.
     * There is no setter for this field to ensure immutability.
     */
    public String getContactId() {
        return contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    // ---------------- SETTERS ----------------

    /*
     * Updates the first name after validating constraints.
     */
    public void setFirstName(String firstName) {
        validateName(firstName, "First name");
        this.firstName = firstName;
    }

    /*
     * Updates the last name after validating constraints.
     */
    public void setLastName(String lastName) {
        validateName(lastName, "Last name");
        this.lastName = lastName;
    }

    /*
     * Updates the phone number after validating constraints.
     * Phone numbers must be exactly 10 digits.
     */
    public void setPhone(String phone) {
        validatePhone(phone);
        this.phone = phone;
    }

    /*
     * Updates the address after validating constraints.
     */
    public void setAddress(String address) {
        validateAddress(address);
        this.address = address;
    }

    // ---------------- VALIDATION METHODS ----------------

    /*
     * Validates the contact ID.
     * Must not be null and must be no longer than 10 characters.
     */
    private static void validateContactId(String contactId) {
        if (contactId == null) {
            throw new IllegalArgumentException("Contact ID must not be null.");
        }
        if (contactId.length() > 10) {
            throw new IllegalArgumentException("Contact ID must be 10 characters or fewer.");
        }
    }

    /*
     * Validates first and last names.
     * Names must not be null and must be no longer than 10 characters.
     */
    private static void validateName(String name, String fieldName) {
        if (name == null) {
            throw new IllegalArgumentException(fieldName + " must not be null.");
        }
        if (name.length() > 10) {
            throw new IllegalArgumentException(fieldName + " must be 10 characters or fewer.");
        }
    }

    /*
     * Validates the phone number.
     * Phone numbers must not be null and must contain exactly 10 digits.
     */
    private static void validatePhone(String phone) {
        if (phone == null) {
            throw new IllegalArgumentException("Phone must not be null.");
        }
        if (!phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone must be exactly 10 digits.");
        }
    }

    /*
     * Validates the address.
     * Address must not be null and must be no longer than 30 characters.
     */
    private static void validateAddress(String address) {
        if (address == null) {
            throw new IllegalArgumentException("Address must not be null.");
        }
        if (address.length() > 30) {
            throw new IllegalArgumentException("Address must be 30 characters or fewer.");
        }
    }
}