/*************************
 * Name: Jamie Kutzer
 * Course: CS-320
 * Date: January 25, 2026
 * Description:
 * ContactService stores Contact objects in memory (no database).
 * It supports adding contacts with unique IDs, deleting by ID,
 * and updating specific fields by ID as required by the customer.
 *************************/

package contactservice;

import java.util.HashMap;
import java.util.Map;

public class ContactService {

    /*
     * In-memory storage for contacts.
     * Key = contactId, Value = Contact object
     * A map is ideal here because it makes it easy to find, update, and delete
     * a contact directly by ID.
     */
    private final Map<String, Contact> contacts = new HashMap<>();

    /*
     * Adds a new contact to the service.
     * Requirement: IDs must be unique. If the ID already exists, throw an exception.
     */
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact must not be null.");
        }

        String id = contact.getContactId();

        if (contacts.containsKey(id)) {
            throw new IllegalArgumentException("Contact ID already exists: " + id);
        }

        contacts.put(id, contact);
    }

    /*
     * Convenience method to create and add a contact.
     * This makes it easier to add contacts without building the Contact object first.
     */
    public void addContact(String contactId, String firstName, String lastName, String phone, String address) {
        addContact(new Contact(contactId, firstName, lastName, phone, address));
    }

    /*
     * Deletes a contact by contact ID.
     * If the contact ID does not exist, throw an exception to make behavior testable.
     */
    public void deleteContact(String contactId) {
        if (contactId == null || contactId.isBlank()) {
            throw new IllegalArgumentException("Contact ID must not be null/blank.");
        }
        if (!contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("No contact found with ID: " + contactId);
        }

        contacts.remove(contactId);
    }

    /*
     * Updates the contact's first name by contact ID.
     * Only the allowed fields should be updated (ID is not updatable).
     */
    public void updateFirstName(String contactId, String firstName) {
        getExisting(contactId).setFirstName(firstName);
    }

    public void updateLastName(String contactId, String lastName) {
        getExisting(contactId).setLastName(lastName);
    }

    public void updatePhone(String contactId, String phone) {
        getExisting(contactId).setPhone(phone);
    }

    public void updateAddress(String contactId, String address) {
        getExisting(contactId).setAddress(address);
    }

    /*
     * Helper method used by the service to locate a contact by ID.
     * If missing, throw an exception so update/delete behavior is consistent and testable.
     */
    private Contact getExisting(String contactId) {
        if (contactId == null || contactId.isBlank()) {
            throw new IllegalArgumentException("Contact ID must not be null/blank.");
        }

        Contact contact = contacts.get(contactId);
        if (contact == null) {
            throw new IllegalArgumentException("No contact found with ID: " + contactId);
        }

        return contact;
    }

    /*
     * Optional helper method for unit testing or verification.
     * Returns the contact for the ID, or null if it doesn't exist.
     */
    public Contact getContact(String contactId) {
        return contacts.get(contactId);
    }
}