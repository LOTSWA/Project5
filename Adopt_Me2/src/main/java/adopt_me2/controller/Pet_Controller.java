package adopt_me2.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import adopt_me2.model.*;
import adopt_me2.view.Pet_View;

/**
 * 
 */
public class Pet_Controller {
    private Pet_Model model;
    private Pet_View view;
    private int randomId;

    /**
     * 
     * 
     * updates table with pet data
     * @param model
     * @param view
     */
    public Pet_Controller(Pet_Model model, Pet_View view) {
        this.model = model;
        this.view = view;
        this.randomId = petIdSelection();

        updateTable();
        attachListeners();
    }

    /**
     * 
     * selects pet ID
     * @return
     */
    private int petIdSelection() 
    {
    	return java.util.concurrent.ThreadLocalRandom.current().nextInt(1, 501);
    }

    /**
     * updates pet table
     */
    private void updateTable() 
    {
        view.updatePetTable(model.getAllPets());
    }

    /**
     * give buttons functionality
     */
    private void attachListeners() 
    {
        view.setAddButtonListener(new ActionListener() 
        {
        	
            /**
             * 
             * 
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String[] petData = view.showAddDialog();
                if (petData != null) {
                    String name = petData[0];
                    String type = petData[1];
                    String species = petData[2];
                    int age = Integer.parseInt(petData[3]);
                    
                    Pet newPet;
                    switch (type) {
                        case "Dog":
                            newPet = new Dog(randomId, name, species, age);
                            break;
                        case "Cat":
                            newPet = new Cat(randomId, name, species, age);
                            break;
                        case "Rabbit":
                            newPet = new Rabbit(randomId, name, species, age);
                            break;
                        default:
                            return;
                    }
                    
                    model.addPet(newPet);
                    randomId++;
                    updateTable();
                }
            }
        });
        
        view.setRemoveButtonListener(new ActionListener() {
            /**
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                int selectedRow = view.getSelectedRow();
                int id = view.getSelectedPetId();
                if (id != -1) 
                {
                    // Pass both ID and table row index to ensure exact match
                    model.removePet(id, selectedRow);
                    updateTable();
                } 
                else 
                {
                    view.showMessage("Please Select a Pet First");
                }
            }
        });
        
        view.setAdoptButtonListener(new ActionListener() 
        {
            /**
             * 
             * event listener
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                int selectedRow = view.getSelectedRow();
                int id = view.getSelectedPetId();
                if (id != -1) 
                {
                    Pet pet = model.getPetByRowIndex(selectedRow);
                    if (pet != null) 
                    {
                        if (pet.isAdopted()) 
                        {
                            view.showMessage("This Pet has an Owner Already");
                        } 
                        else 
                        {
                            model.adoptPet(pet);
                            updateTable();
                            view.showMessage("You're Now a proud owner of " + pet.getName() + "!");
                        }
                    } 
                    else 
                    {
                        view.showMessage("Couldn't Find Pet to Adopt");
                    }
                } 
                else 
                {
                    view.showMessage("Please Select a Pet First");
                }
            }
        });
        
        view.setDetailsButtonListener(new ActionListener() 
        {
        	
            /**
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                int selectedRow = view.getSelectedRow();
                int id = view.getSelectedPetId();
                if (id != -1) 
                {
                    Pet pet = model.getPetByRowIndex(selectedRow);
                    if (pet != null) 
                    {
                        view.showPetDetailsDialog(pet);
                    } 
                    else 
                    {
                        view.showMessage("Couldn't Find a Pet");
                    }
                } 
                else 
                {
                    view.showMessage("Please Select a Pet First");
                }
            }
        });
        
        view.setSaveButtonListener(new ActionListener() 
        {
        	
            /**
             *
             */
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                model.savePets();
                view.showMessage("Save Successful");
            }
        });
        
        view.setSortComboBoxListener(new ActionListener() 
        {
            /**
             *
             */
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String required = view.getSelectedSortCriteria();
                switch (required) 
                {
                    case "Name":
                        model.sortPetsByName();
                        break;
                    case "Age":
                        model.sortPetsByAge();
                        break;
                    case "Species":
                        model.sortPetsBySpecies();
                        break;
                }
                updateTable();
            }
        });
    }
}
