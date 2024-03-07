import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MobilePhoneTest {
    private val mobilePhone = MobilePhone("1234567890")
    private val contact = Contact("Tetka Maggie", "203303212")

    @Test
    fun addNewContact() {
        assertTrue(mobilePhone.addNewContact(contact))
        assertFalse(mobilePhone.addNewContact(contact))
    }

    @Test
    fun updateContact() {
        val oldContact = Contact("Tetka Maggie", "203303212")
        val newContact = Contact("Jane Doe", "5555555555")

        assertFalse(mobilePhone.updateContact(oldContact, newContact))

        mobilePhone.addNewContact(oldContact)
        assertTrue(mobilePhone.updateContact(oldContact, newContact))
        assertEquals(newContact, mobilePhone.queryContact("Jane Doe"))
    }

    @Test
    fun removeContact() {
        assertFalse(mobilePhone.removeContact(contact))

        mobilePhone.addNewContact(contact)
        assertTrue(mobilePhone.removeContact(contact))
        assertNull(mobilePhone.queryContact("Tetka Maggie"))
    }

    @Test
    fun queryContact() {
        assertNull(mobilePhone.queryContact("Tetka Maggie"))

        mobilePhone.addNewContact(contact)
        assertEquals(contact, mobilePhone.queryContact("Tetka Maggie"))
    }

    @Test
    fun printContacts() {

    }
}