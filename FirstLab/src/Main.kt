//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
data class Contact(val name: String, val phoneNumber: String)

class MobilePhone(val myNumber: String) {
    private val myContacts = mutableListOf<Contact>()

    fun addNewContact(contact: Contact): Boolean {
        if (findContact(contact) == -1) {
            myContacts.add(contact)
            return true
        }
        return false
    }

    fun updateContact(oldContact: Contact, newContact: Contact): Boolean {
        val position = findContact(oldContact)
        if (position >= 0) {
            myContacts[position] = newContact
            return true
        }
        return false
    }

    fun removeContact(contact: Contact): Boolean {
        val position = findContact(contact)
        if (position >= 0) {
            myContacts.removeAt(position)
            return true
        }
        return false
    }

    private fun findContact(contact: Contact): Int {
        return myContacts.indexOf(contact)
    }

    fun queryContact(name: String): Contact? {
        val position = myContacts.indexOfFirst { it.name == name }
        return if (position != -1) myContacts[position] else null
    }

    fun printContacts() {
        for ((index, contact) in myContacts.withIndex()) {
            println("${index + 1}. ${contact.name}: ${contact.phoneNumber}")
        }
    }
}

fun main() {
    val mobilePhone = MobilePhone("1234567890")

    val contact1 = Contact("John Doe", "9876543210")
    val contact2 = Contact("Jane Smith", "8765432109")

    mobilePhone.addNewContact(contact1)
    mobilePhone.addNewContact(contact2)

    println("Contacts:")
    mobilePhone.printContacts()

    val newContact = Contact("John Doe", "1111111111")
    mobilePhone.updateContact(contact1, newContact)

    println("\nUpdated Contacts:")
    mobilePhone.printContacts()

    val removedContact = Contact("John Doe", "1111111111")
    mobilePhone.removeContact(removedContact)

    println("\nContacts after removal:")
    mobilePhone.printContacts()

    val foundContact = mobilePhone.queryContact("Jane Smith")
    println("\nFound Contact: $foundContact")
}