import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class LMVPhonebook extends JFrame implements ActionListener {
    
    private JTextField eventField, nameField, phoneField;
    private JTextArea displayArea;
    private String filename = "phonebook.txt", selectedContact = "";
    
    public LMVPhonebook() {
        setTitle("Phonebook System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());
        
        eventField = new JTextField();
        eventField.setEditable(false);
        nameField = new JTextField();
        phoneField = new JTextField();
        displayArea = new JTextArea();
        
        JPanel topPanels = new JPanel(new BorderLayout());
        add(topPanels, BorderLayout.NORTH);
        
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(eventField);
        topPanels.add(headerPanel, BorderLayout.NORTH);
        
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Phone:"));
        inputPanel.add(phoneField);
        topPanels.add(inputPanel, BorderLayout.SOUTH);
        
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        String[] buttons = {"Add", "Search", "Delete", "Select", "Update"};
        for (String button : buttons) {
            JButton b = new JButton(button);
            b.addActionListener(this);
            buttonPanel.add(b);
        }
        add(buttonPanel, BorderLayout.SOUTH);
        
        setVisible(true);
        loadPhonebookData();
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        eventField.setText("");
        
        if (nameField.getText().isEmpty() || phoneField.getText().isEmpty()) {
            eventField.setText("Input field(s) are empty");
            return;
        } else if (phoneField.getText().length() != 11 || !phoneField.getText().startsWith("09")) {
            eventField.setText("Phone number must be 11 digits long starting from 09");
            return;
        }
        for (char c : phoneField.getText().toCharArray()) {
            if (!Character.isDigit(c)) {
                eventField.setText("Phone number must contain only digits");
                return;
            }
        }
        
        if (command.equals("Add")) {
            addPhonebookEntry(nameField.getText(), phoneField.getText());
        } else if (command.equals("Search")) {
            searchPhonebookEntry(nameField.getText(), phoneField.getText());
        } else if (command.equals("Delete")) {
            deletePhonebookEntry(nameField.getText(), phoneField.getText());
        } else if (command.equals("Select")) {
            selectPhonebookEntry(nameField.getText(), phoneField.getText());
        } else if (command.equals("Update")) {
            updatePhonebookEntry(nameField.getText(), phoneField.getText());
        }
    }
    
    public void loadPhonebookData() {
        String line;
        String contacts = "";
        
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            while ((line = reader.readLine()) != null) {
                contacts += line + System.lineSeparator();
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        displayArea.setText(contacts);
    }
    
    public void addPhonebookEntry(String name, String phone) {
        
        String line;
        String contacts = "";
        String[] splitLine;
        boolean alreadyExists = false;
        
        selectedContact = "";
        
        try {
            File file = new File(filename);

            if (!file.exists()) {
                file.createNewFile();
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            
            while ((line = reader.readLine()) != null) {
                splitLine = line.split(":");
                
                if (splitLine[0].equals(name) && splitLine[1].equals(phone)) {
                    alreadyExists = true;
                    break;
                }
                contacts += line + System.lineSeparator();
            }
            
            if (alreadyExists) {
                eventField.setText("Contact Already Exist");
                reader.close();
                return;
            } else {
                eventField.setText("Contact Added");
            }
            
            contacts += name + ":" + phone;
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(contacts);
            
            reader.close();
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        loadPhonebookData();
    }
    
    public void searchPhonebookEntry(String name, String phone) {
        
        String line;
        String contacts = "";
        String[] splitLine;
        boolean alreadyExists = false;
        
        selectedContact = "";
        
        try {
            File file = new File(filename);
            
            if (!file.exists()) {
                file.createNewFile();
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            
            while ((line = reader.readLine()) != null) {
                splitLine = line.split(":");
                
                if (splitLine[0].equals(name) && splitLine[1].equals(phone)) {
                    alreadyExists = true;
                    continue;
                }
                contacts += line + System.lineSeparator();
            }
            
            if (alreadyExists) {
                eventField.setText("Contact Searched");
                contacts = name + ":" + phone + System.lineSeparator() + contacts;
            } else {
                eventField.setText("Contact does not exist");
                reader.close();
                return;
            }
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(contacts);
            
            reader.close();
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        loadPhonebookData();
    }

    public void deletePhonebookEntry(String name, String phone) {
        
        String line;
        String contacts = "";
        String[] splitLine;
        boolean contactExist = false;
        
        selectedContact = "";
        
        try {
            File file = new File(filename);
            
            if (!file.exists()) {
                file.createNewFile();
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            
            while ((line = reader.readLine()) != null) {
                splitLine = line.split(":");
                
                if (splitLine[0].equals(name) && splitLine[1].equals(phone)) {
                    contactExist = true;
                    continue;
                }
                contacts += line + System.lineSeparator();
            }
            
            if (contactExist) {
                eventField.setText("Contact deleted");
            } else {
                eventField.setText("Contact does not exist");
            }
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(contacts);
            
            reader.close();
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        loadPhonebookData();
    }
    
    public void selectPhonebookEntry(String name, String phone) {
        
        String line;
        String contacts = "";
        String[] splitLine;

        try {
            File file = new File(filename);
            
            if (!file.exists()) {
                file.createNewFile();
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            
            while ((line = reader.readLine()) != null) {
                splitLine = line.split(":");
                
                if (splitLine[0].equals(name) && splitLine[1].equals(phone)) {
                    searchPhonebookEntry(name, phone);
                    selectedContact = name + ":" + phone;
                    eventField.setText("Contact selected: " + name + ":" + phone);
                    reader.close();
                    return;
                }
                contacts += line + System.lineSeparator();
            }
            
            eventField.setText("Contact does not exist");
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(contacts);
            
            reader.close();
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        loadPhonebookData();
    }
    
    public void updatePhonebookEntry(String name, String phone) {
        
        String line;
        String contacts = "";
        String[] splitLine;
        String[] splitSelectedContact;
        
        if (selectedContact.isEmpty()) {
            eventField.setText("No contact selected - Please select a contact");
            return;
        }
        
        try {
            File file = new File(filename);
            
            if (!file.exists()) {
                file.createNewFile();
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            
            while ((line = reader.readLine()) != null) {
                splitLine = line.split(":");
                splitSelectedContact = selectedContact.split(":");
                
                if (splitLine[0].equals(splitSelectedContact[0]) && splitLine[1].equals(splitSelectedContact[1])) {
                    eventField.setText(
                            "Contact: " + 
                            splitSelectedContact[0] + ":" + splitSelectedContact[1] + 
                            " updated to " + name + ":" + phone);
                    selectedContact = name + ":" + phone;
                    continue;
                }
                
                contacts += line + System.lineSeparator();
            }
            
            contacts += selectedContact;
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(contacts);
            
            reader.close();
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        loadPhonebookData();
    }

    public static void main(String[] args) {
        new LMVPhonebook();
    }
}
