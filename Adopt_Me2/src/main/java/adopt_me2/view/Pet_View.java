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
 * 
 */
public class Pet_View {
    private JFrame backFrame;
    private JTable petsTable;
    private DefaultTableModel tableModel;
    private JButton addButton, removeButton, adoptButton, detailsButton, saveButton;
    private JComboBox<String> comboBoxSort;

    /**
     * 
     */
    public Pet_View() {

        backFrame = new JFrame("Adopt Me - Pet Adoption System");
        backFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        backFrame.setSize(1000, 1000);
        backFrame.setLayout(new BorderLayout());

        //creates table
        String[] columnNames = {"ID", "Name", "Type", "Species", "Age", "Adopted"};
        tableModel = new DefaultTableModel(columnNames, 0);
        petsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(petsTable);
        backFrame.add(scrollPane, BorderLayout.CENTER);


        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        //creates button objects
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        adoptButton = new JButton("Adopt");
        detailsButton = new JButton("View Details");
        saveButton = new JButton("Save");


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


        backFrame.add(panel, BorderLayout.SOUTH);
    }
    //makes window visible
    /**
     * 
     */
    public void display() 
    {
        backFrame.setVisible(true);
    }
    //method for updating pet Table
    /**
     * @param pets
     */
    public void updatePetTable(List<Pet> pets) 
    {

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
     * @param listener
     */
    public void setAddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    /**
     * @param listener
     */
    public void setRemoveButtonListener(ActionListener listener) {
        removeButton.addActionListener(listener);
    }

    /**
     * @param listener
     */
    public void setAdoptButtonListener(ActionListener listener) {
        adoptButton.addActionListener(listener);
    }

    /**
     * @param listener
     */
    public void setDetailsButtonListener(ActionListener listener) {
        detailsButton.addActionListener(listener);
    }

    /**
     * @param listener
     */
    public void setSaveButtonListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }

    /**
     * @param listener
     */
    public void setSortComboBoxListener(ActionListener listener) {
        comboBoxSort.addActionListener(listener);
    }
    //method for getting pet id
    /**
     * @return
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
    //returns the selected row
    /**
     * @return
     */
    public int getSelectedRow() 
    {
        return petsTable.getSelectedRow();
    }
    //returns sorted items
    /**
     * @return
     */
    public String getSelectedSortCriteria() 
    {
        return (String) comboBoxSort.getSelectedItem();
    }

    //returns message
    /**
     * @param message
     */
    public void showMessage(String message) 
    {
        JOptionPane.showMessageDialog(backFrame, message);
    }
    //prints text into window view
    /**
     * @return
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

        int result = JOptionPane.showConfirmDialog(backFrame, panel, 
                "Add New Pet", JOptionPane.OK_CANCEL_OPTION);
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
    //prints information about pets
    /**
     * @param pet
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
