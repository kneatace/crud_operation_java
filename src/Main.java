import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;



//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("jdbc");
        //JtabbedPane start
        JTabbedPane tabbedPane = new JTabbedPane();
        frame.add(tabbedPane);
        JPanel createPanel = new JPanel();
        JPanel editPanel = new JPanel();
        JPanel readPanel = new JPanel();
        JPanel deletePanel = new JPanel();


        //createPanel nameInputs
        JLabel label = new JLabel("Name:");
        createPanel.add(label);
        JTextField name = new JTextField();
        name.setMaximumSize(new Dimension(300, 30));
        createPanel.add(name);
        createPanel.setLayout(new BoxLayout(createPanel, BoxLayout.Y_AXIS));

        //createPanel emailInputs
        JLabel label1 = new JLabel("Email:");
        createPanel.add(label1);
        JTextField email = new JTextField();
        email.setMaximumSize(new Dimension(300, 30));
        createPanel.add(email);
        createPanel.setLayout(new BoxLayout(createPanel, BoxLayout.Y_AXIS));

        //createPanel passwordInputs
        JLabel label2 = new JLabel("Password:");
        createPanel.add(label2);
        JPasswordField password = new JPasswordField();
        password.setMaximumSize(new Dimension(300, 30));
        createPanel.add(password);
        createPanel.setLayout(new BoxLayout(createPanel, BoxLayout.Y_AXIS));

        //createPanel button
        JButton button = new JButton("Add/Create");
        createPanel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String n = name.getText();
                String p = password.getText();
                String emailText = email.getText();
                boolean success = DAO.insertUser(n, emailText, p);

                if (success) {
                    JOptionPane.showMessageDialog(createPanel,
                            "User created successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(createPanel,
                            "Failed to create user. Please try again.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //createPanel  Ends

        //ReadUser panel starts//
        JButton usrDetails = new JButton("User details");
        readPanel.add(usrDetails);
        usrDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> userIds = DAO.showUsers();
                if (userIds.isEmpty()) {
                    JOptionPane.showMessageDialog(readPanel,
                            "No users in database.",
                            "User Not Found",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    String ids = userIds.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(", "));
                    JOptionPane.showMessageDialog(readPanel, "User IDs: " + ids);
                }
            }
        });
        JLabel label3 = new JLabel("Id:");
        readPanel.add(label3);
        JTextField id = new JTextField();
        id.setMaximumSize(new Dimension(300, 30));
        readPanel.add(id);
        readPanel.setLayout(new BoxLayout(readPanel, BoxLayout.Y_AXIS));
        JButton button1 = new JButton("Show Details");
        readPanel.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int userId = Integer.parseInt(id.getText()); // Get ID from text field
                    User user = DAO.getUserById(userId); // Fetch user from DB

                    if (user != null) {
                        // Show user details in a popup
                        JOptionPane.showMessageDialog(readPanel,
                                "User Details:\n" +
                                        "ID: " + user.getId() + "\n" +
                                        "Name: " + user.getName() + "\n" +
                                        "Email: " + user.getEmail() + "\n" +
                                        "Password: " + user.getPassword());
                    } else {
                        JOptionPane.showMessageDialog(readPanel,
                                "No user found with ID: " + userId,
                                "User Not Found",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(readPanel,
                            "Please enter a valid numeric ID.",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                }


                }
        });
        //ReadUser panel ends//
        //DeleteUser Panel starts//
        JLabel label4 = new JLabel("UserId:");
        deletePanel.add(label4);
        JTextField id1 = new JTextField();
        id1.setMaximumSize(new Dimension(300, 30));
        deletePanel.add(id1);
        deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.Y_AXIS));
        JButton button2 = new JButton("Delete User");
        deletePanel.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int delUser = Integer.parseInt(id1.getText());
                    boolean success = DAO.deleteUser(delUser);
                    if (success) {
                        JOptionPane.showMessageDialog(deletePanel,
                                "User deleted successfully!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(deletePanel,
                                "User not found or could not be deleted.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(deletePanel,
                            "Please enter a valid numeric ID.",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);


                    }
                }
        });
        //DeleteUser panel ends//
        //Update/edit user panels starts//
        JLabel label5 = new JLabel("User ID:");
        editPanel.add(label5);
        JTextField id2 = new JTextField();
        id2.setMaximumSize(new Dimension(300, 30));
        editPanel.add(id2);
        editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel("New Name:");
        JTextField nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(300, 30));

        JLabel emailLabel = new JLabel("New Email:");
        JTextField emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(300, 30));

        JLabel passLabel = new JLabel("New Password:");
        JTextField passField = new JTextField();
        passField.setMaximumSize(new Dimension(300, 30));

        editPanel.add(nameLabel);
        editPanel.add(nameField);
        editPanel.add(emailLabel);
        editPanel.add(emailField);
        editPanel.add(passLabel);
        editPanel.add(passField);
        JButton button3 = new JButton("Update User Credentials");
        editPanel.add(button3);

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int userId = Integer.parseInt(id2.getText());
                    String newName = nameField.getText();
                    String newEmail = emailField.getText();
                    String newPassword = passField.getText();

                    boolean success = DAO.updateUser(userId, newName, newEmail, newPassword);

                    if (success) {
                        JOptionPane.showMessageDialog(editPanel,
                                "User updated successfully!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(editPanel,
                                "Failed to update user. Check if user ID exists.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(editPanel,
                            "Please enter a valid numeric ID.",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                }

                }

        });



        tabbedPane.add("Create User",createPanel);
        tabbedPane.add("Read User",readPanel);
        tabbedPane.add("Update User",editPanel);
        tabbedPane.add("Delete User",deletePanel);

        //JtabbedPane ends
        frame.setVisible(true);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);





        }
    }
