package work2.second.server.server;


import work2.second.entity.AddressBook;
import work2.second.server.dao.AddressBookDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookService {

    private AddressBookDao addressBookDao = new AddressBookDao();

    public void addContact(String name, String address, String phone){
        AddressBook addressBook = new AddressBook();
        addressBook.setName(name);
        addressBook.setAddress(address);
        addressBook.setPhone(phone);
        addressBookDao.addContact(addressBook);
    }

    public List<String> getAllContacts() {
        return addressBookDao.getAllContacts();
    }

    public void updateContact(int id1, String name, String address, String phone) {

        AddressBook addressBook = new AddressBook();
        addressBook.setName(name);
        addressBook.setAddress(address);
        addressBook.setPhone(phone);
        addressBook.setId(id1);
        addressBookDao.updateContact(addressBook);
    }

    public void deleteContact(int id1) {
        addressBookDao.deleteContact(id1);
    }
}
