package work2.first;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class AddressBookClient extends JFrame {
    private JTextField nameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField idField;
    private JButton selectButton;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextArea textArea;

    public AddressBookClient() {
        setTitle("个人通讯录");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setPreferredSize(new Dimension(300, 200));
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        nameField = new JTextField();
        addressField = new JTextField();
        phoneField = new JTextField();
        idField = new JTextField();
        selectButton = new JButton("查看联系人");
        addButton = new JButton("添加联系人");
        updateButton = new JButton("修改联系人");
        deleteButton = new JButton("删除联系人");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String address = addressField.getText();
                String phone = phoneField.getText();
                if (name.length() == 0 || address.length() == 0 || phone.length() == 0) {
                    JOptionPane.showMessageDialog(null, "联系人信息不全！");
                    throw new RuntimeException("联系人信息不全");

                }
                new AddressBookServer().addContact(name, address, phone);
                JOptionPane.showMessageDialog(null, "联系人添加成功！");
            }
        });
        selectButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                List<String> allContacts = new AddressBookServer().getAllContacts();
                String result = String.join("\n", allContacts);
                textArea.setText(result);
            }
        });
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = nameField.getText();
                String address = addressField.getText();
                String phone = phoneField.getText();
                String id = idField.getText();
                int id1 = Integer.parseInt(id);

                new AddressBookServer().updateContact(id1,name,address,phone);
                JOptionPane.showMessageDialog(null, "联系人修改成功！");
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                int id1 = Integer.parseInt(id);
                new AddressBookServer().deleteContact(id1);
                JOptionPane.showMessageDialog(null, "联系人删除成功！");
            }
        });


        add(nameField);
        add(addressField);
        add(phoneField);
        add(idField);
        add(addButton);
        add(updateButton);
        add(deleteButton);
        add(selectButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new AddressBookClient();
    }
}
