package work2.second.server.dao;

import work2.second.entity.AddressBook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDao {

    private Connection connect() {
        String url = "jdbc:mysql://localhost:3306/addressbook";
        String user = "root";
        String password = "18634702005Lin@";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addContact(AddressBook addressBook) {
        String sql = "INSERT INTO contacts (name, address, phone) VALUES (?, ?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, addressBook.getName());
            pstmt.setString(2, addressBook.getAddress());
            pstmt.setString(3, addressBook.getPhone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<String> getAllContacts() {
        List<String> contacts = new ArrayList<>();
        String sql = "SELECT id, name, address, phone FROM contacts";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // 通过字段检索
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");

                // 将联系人信息格式化为字符串并添加到列表中
                contacts.add("ID: " + id + ", Name: " + name + ", Address: " + address + ", Phone: " + phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public void updateContact(AddressBook addressBook) {
        String sql = "UPDATE contacts SET name = ?, address = ?, phone = ? WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, addressBook.getName());
            pstmt.setString(2, addressBook.getAddress());
            pstmt.setString(3, addressBook.getPhone());
            pstmt.setInt(4, addressBook.getId());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("联系人信息更新成功");
            } else {
                System.out.println("未找到联系人信息");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteContact(int id1) {
        String sql = "DELETE FROM contacts WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id1);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("联系人删除成功");
            } else {
                System.out.println("未找到联系人信息");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
