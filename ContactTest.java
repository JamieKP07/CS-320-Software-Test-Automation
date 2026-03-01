/*************************
 * Name: Jamie Kutzer
 * Course: CS-320
 * Date: January 25, 2026
 * Description:
 * Unit tests for the Contact class. These tests verify that Contact objects
 * enforce the customer requirements for ID, name, phone, and address fields.
 *************************/

package contactservice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ContactTest {

    /*
     * Helper method to create a valid Contact quickly for tests that only want to
     * change one field at a time.
     */
    private Contact makeValidContact() {
        return new Contact("12345", "Jamie", "Kutzer", "1234567890", "123 Main Street");
    }

    // ---------------- VALID CREATION ----------------

    @Test
    @DisplayName("Create contact with valid fields should succeed")
    void createContact_validFields_shouldCreate() {
        Contact contact = makeValidContact();

        assertEquals("12345", contact.getContactId());
        assertEquals("Jamie", contact.getFirstName());
        assertEquals("Kutzer", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("123 Main Street", contact.getAddress());
    }

    // ---------------- CONTACT ID REQUIREMENTS ----------------

    @Test
    @DisplayName("Contact ID shall not be null")
    void contactId_null_shouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact(null, "Jamie", "Kutzer", "1234567890", "123 Main Street"));
    }

    @Test
    @DisplayName("Contact ID shall not be longer than 10 characters")
    void contactId_tooLong_shouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("12345678901", "Jamie", "Kutzer", "1234567890", "123 Main Street"));
    }

    @Test
    @DisplayName("Contact ID shall not be updatable (no setter exists)")
    void contactId_isImmutable_shouldNotChange() {
        Contact contact = makeValidContact();
        // There is intentionally no setter for contactId, so the ID cannot be updated.
        assertEquals("12345", contact.getContactId());
    }

    // ---------------- FIRST NAME REQUIREMENTS ----------------

    @Test
    @DisplayName("First name shall not be null")
    void firstName_null_shouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", null, "Kutzer", "1234567890", "123 Main Street"));
    }

    @Test
    @DisplayName("First name shall not be longer than 10 characters")
    void firstName_tooLong_shouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "Hubensussiedorff", "Kutzer", "1234567890", "123 Main Street"));
    }

    // ---------------- LAST NAME REQUIREMENTS ----------------

    @Test
    @DisplayName("Last name shall not be null")
    void lastName_null_shouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "Jamie", null, "1234567890", "123 Main Street"));
    }

    @Test
    @DisplayName("Last name shall not be longer than 10 characters")
    void lastName_tooLong_shouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "Jamie", "Hubensussiedorff", "1234567890", "123 Main Street"));
    }

    // ---------------- PHONE REQUIREMENTS ----------------

    @Test
    @DisplayName("Phone shall not be null")
    void phone_null_shouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "Jamie", "Kutzer", null, "123 Main Street"));
    }

    @Test
    @DisplayName("Phone shall be exactly 10 digits")
    void phone_notTenDigits_shouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "Jamie", "Kutzer", "12345", "123 Main Street"));
    }

    @Test
    @DisplayName("Phone shall contain only digits")
    void phone_containsLetters_shouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "Jamie", "Kutzer", "12345abcde", "123 Main Street"));
    }

    // ---------------- ADDRESS REQUIREMENTS ----------------

    @Test
    @DisplayName("Address shall not be null")
    void address_null_shouldThrow() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "Jamie", "Kutzer", "1234567890", null));
    }

    @Test
    @DisplayName("Address shall not be longer than 30 characters")
    void address_tooLong_shouldThrow() {
        String longAddress = "1234567890123456789012345678901"; // 31 chars
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "Jamie", "Kutzer", "1234567890", longAddress));
    }

    // ---------------- UPDATES ----------------

    @Test
    @DisplayName("Valid updates should modify updatable fields")
    void updateFields_valid_shouldUpdate() {
        Contact contact = makeValidContact();

        contact.setFirstName("Amy");
        contact.setLastName("Graham");
        contact.setPhone("0987654321");
        contact.setAddress("456 Oak Avenue");

        assertEquals("12345", contact.getContactId()); // still unchanged
        assertEquals("Amy", contact.getFirstName());
        assertEquals("Graham", contact.getLastName());
        assertEquals("0987654321", contact.getPhone());
        assertEquals("456 Oak Avenue", contact.getAddress());
    }

    @Test
    @DisplayName("Invalid update should throw an exception")
    void updatePhone_invalid_shouldThrow() {
        Contact contact = makeValidContact();
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("111"));
    }
}
