/*************************
 * Name: Jamie Kutzer
 * Course: CS-320
 * Date: January 25, 2026
 * Description:
 * Unit tests for the ContactService class. These tests verify that the service
 * can add, update, and delete contacts by ID, and that it enforces unique IDs
 * and validation rules through exceptions.
 *************************/

package contactservice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {

    /*
     * Helper method to quickly add one known-valid contact to a new service.
     * Using explicit IDs avoids any dependency on test order.
     */
    private ContactService createServiceWithOneContact() {
        ContactService service = new ContactService();
        service.addContact("A1", "First", "Last", "1234567890", "100 Example Rd");
        return service;
    }

    // ---------------- ADD CONTACT TESTS ----------------

    @Test
    @DisplayName("Add contact with unique ID should succeed")
    void addContact_uniqueId_shouldAdd() {
        ContactService service = new ContactService();

        service.addContact("A1", "First", "Last", "1234567890", "100 Example Rd");

        Contact added = service.getContact("A1");
        assertNotNull(added);
        assertEquals("A1", added.getContactId());
        assertEquals("First", added.getFirstName());
    }

    @Test
    @DisplayName("Add contact with duplicate ID should throw exception")
    void addContact_duplicateId_shouldThrow() {
        ContactService service = new ContactService();

        service.addContact("A1", "First", "Last", "1234567890", "100 Example Rd");

        assertThrows(IllegalArgumentException.class, () ->
            service.addContact("A1", "Other", "Person", "0987654321", "200 Example Rd"));
    }

    // ---------------- DELETE CONTACT TESTS ----------------

    @Test
    @DisplayName("Delete contact by existing ID should remove contact")
    void deleteContact_existingId_shouldDelete() {
        ContactService service = createServiceWithOneContact();

        service.deleteContact("A1");

        assertNull(service.getContact("A1"));
    }

    @Test
    @DisplayName("Delete contact with missing ID should throw exception")
    void deleteContact_missingId_shouldThrow() {
        ContactService service = new ContactService();

        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("NOPE"));
    }

    // ---------------- UPDATE CONTACT TESTS ----------------

    @Test
    @DisplayName("Update first name by ID should change first name")
    void updateFirstName_shouldUpdate() {
        ContactService service = createServiceWithOneContact();

        service.updateFirstName("A1", "Updated");

        assertEquals("Updated", service.getContact("A1").getFirstName());
    }

    @Test
    @DisplayName("Update last name by ID should change last name")
    void updateLastName_shouldUpdate() {
        ContactService service = createServiceWithOneContact();

        service.updateLastName("A1", "Updated");

        assertEquals("Updated", service.getContact("A1").getLastName());
    }

    @Test
    @DisplayName("Update phone by ID should change phone")
    void updatePhone_shouldUpdate() {
        ContactService service = createServiceWithOneContact();

        service.updatePhone("A1", "1112223333");

        assertEquals("1112223333", service.getContact("A1").getPhone());
    }

    @Test
    @DisplayName("Update address by ID should change address")
    void updateAddress_shouldUpdate() {
        ContactService service = createServiceWithOneContact();

        service.updateAddress("A1", "200 Updated Ave");

        assertEquals("200 Updated Ave", service.getContact("A1").getAddress());
    }

    @Test
    @DisplayName("Update using missing ID should throw exception")
    void update_missingId_shouldThrow() {
        ContactService service = new ContactService();

        assertThrows(IllegalArgumentException.class, () ->
            service.updateFirstName("MISSING", "Updated"));
    }

    @Test
    @DisplayName("Update phone with invalid value should throw exception")
    void updatePhone_invalid_shouldThrow() {
        ContactService service = createServiceWithOneContact();

        assertThrows(IllegalArgumentException.class, () ->
            service.updatePhone("A1", "123")); // not 10 digits
    }
}
