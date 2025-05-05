package adopt_me2.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import adopt_me2.model.Pet;


/**
 * responsible for displaying the information from the model classes
 */
public class Pet_View {
    private JFrame backFrame;
    private JTable petsTable;
    private DefaultTableModel tableModel;
    private JButton addButton, removeButton, adoptButton, detailsButton, saveButton;
    private JComboBox<String> comboBoxSort;

    /**
     * making the pet_view, and initializing all of the UI
     * laying the UI in frame
     */
    public Pet_View() {

    		//init the window
        backFrame = new JFrame("Adopt Me - Pet Adoption System");
        backFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        backFrame.setSize(1000, 1000);
        backFrame.setLayout(new BorderLayout());
        	//creating the table with columns of attributes
        String[] columnNames = {"ID", "Name", "Type", "Species", "Age", "Adopted"};
        tableModel = new DefaultTableModel(columnNames, 0);
        petsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(petsTable);
        backFrame.add(scrollPane, BorderLayout.CENTER);
        	//making a panel that will be our bottom
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        	//making the buttons for operations
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        adoptButton = new JButton("Adopt");
        detailsButton = new JButton("View Details");
        saveButton = new JButton("Save");

        	//combo box for sorting
        JLabel sortLabel = new JLabel("Sort by:");
        comboBoxSort = new JComboBox<>(new String[]{"Name", "Age", "Species"});

        	//adds buttons to panel
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(adoptButton);
        panel.add(detailsButton);
        panel.add(sortLabel);
        panel.add(comboBoxSort);
        panel.add(saveButton);
        	//placing the frame at the bottom of the frame
        backFrame.add(panel, BorderLayout.SOUTH);
    }
    //makes window visible
    /**
     * makes the window visible 
     */
    public void display() 
    {
        backFrame.setVisible(true);
    }
    /**
     * method for updating pet Table
     * updates data in the window
     * @param pets
     * 				pets turns into a list
     */
    public void updatePetTable(List<Pet> pets) 
    {
    		//removing old rows
        tableModel.setRowCount(0);
        for (int i = 0; i < pets.size(); i++) 
        {	
        	Pet pet = pets.get(i);
        	String tempVar;
        	if(pet.isAdopted())
        	{
        		tempVar = "Yes";
        	}
        	else
        	{
        		tempVar = "No";
        	}
            Object[] rowData = {
                pet.getId(),
                pet.getName(),
                pet.getType(),
                pet.getSpecies(),
                pet.getAge(),
                tempVar
            };
            tableModel.addRow(rowData);
        }
    }
    //Action listeners to give buttons functionality 
    /**
     * 
     * linking an actionlistener to the addbutton
     * 
     * @param listener
     */
    public void setAddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    /**
     * linking an actionlistener to the removebutton
     * @param listener
     */
    public void setRemoveButtonListener(ActionListener listener) {
        removeButton.addActionListener(listener);
    }

    /**
     * linking an actionlistener to the adoptbutton
     * @param listener
     */
    public void setAdoptButtonListener(ActionListener listener) {
        adoptButton.addActionListener(listener);
    }

    /**
     * linking an actionlistener to the detaisbutton
     * @param listener
     */
    public void setDetailsButtonListener(ActionListener listener) {
        detailsButton.addActionListener(listener);
    }

    /**
     * linking an actionlistener to the savebutton
     * @param listener
     */
    public void setSaveButtonListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }

    /**
     * linking an actionlistener to the combosortbox
     * @param listener
     */
    public void setSortComboBoxListener(ActionListener listener) {
        comboBoxSort.addActionListener(listener);
    }
    /**
     * method for getting pet id
     * returns id of the pet
     * @return
     * 			returns either the id of the pet on success or -1 on failure
     */
    public int getSelectedPetId() 
    {
        int selectedRow = petsTable.getSelectedRow();
        if (selectedRow != -1) 
        {
            return (int) tableModel.getValueAt(selectedRow, 0);
        }
        return -1;
    }
    /**
     * returns the selected row
     * returns selected row
     * @return
     */
    public int getSelectedRow() 
    {
        return petsTable.getSelectedRow();
    }
    /**
     * returns sorted items
     * shorts and returns requested criteria
     * @return
     */
    public String getSelectedSortCriteria() 
    {
        return (String) comboBoxSort.getSelectedItem();
    }

    /**
     * returns message
     * displays messages
     * @param message
     */
    public void showMessage(String message) 
    {
        JOptionPane.showMessageDialog(backFrame, message);
    }
    /**
     * prints text into window view
     * adds dialog to view
     * opens a dialog that prompts the user to enter new information for a pet
     * @return
     * 			returns pet fields on success or null on cancel
     */
    public String[] showAddDialog() 
    {
        JTextField nameField = new JTextField();
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Dog", "Cat", "Rabbit"});
        JTextField speciesField = new JTextField();
        JTextField ageField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Type:"));
        panel.add(typeCombo);
        panel.add(new JLabel("Species:"));
        panel.add(speciesField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);

        int result = JOptionPane.showConfirmDialog(backFrame, panel, "Add New Pet", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) 
        {
            return new String[]{
                nameField.getText(),
                (String) typeCombo.getSelectedItem(),
                speciesField.getText(),
                ageField.getText()
            };
        }
        return null;
    }
    /**
     * prints information about pets
     * shows details of requested pet
     * @param pet
     * 				pet param is the pet that's details is displayed
     */
    public void showPetDetailsDialog(Pet pet) 
    {
        if (pet == null) {
            showMessage("Select a Pet First");
            return;
        }

        JLabel tempLabel;
        if (pet.isAdopted()) 
        {
            tempLabel = new JLabel("Yes");
        } 
        else 
        {
            tempLabel = new JLabel("No");
        }
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("ID:"));
        panel.add(new JLabel(String.valueOf(pet.getId())));
        panel.add(new JLabel("Name:"));
        panel.add(new JLabel(pet.getName()));
        panel.add(new JLabel("Type:"));
        panel.add(new JLabel(pet.getType()));
        panel.add(new JLabel("Species:"));
        panel.add(new JLabel(pet.getSpecies()));
        panel.add(new JLabel("Age:"));
        panel.add(new JLabel(String.valueOf(pet.getAge())));
        panel.add(new JLabel("Adopted:"));
        panel.add(tempLabel);

        JOptionPane.showMessageDialog(backFrame, panel, "Pet Details", JOptionPane.INFORMATION_MESSAGE);
    }
}
