package adopt_me2.model;

import adopt_me2.others.Adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * pet model class is responsible for the model part in the MVC
 * managing the converting the json files into readable data that is
 * converted into pet data for the functions to read and use
 * handling both regular pets and exotic pets, exporting into a single pet file
 */
public class Pet_Model {
    private Shelter<Pet> shelter;
    	//gson is responsible to serialize and deserialize
    private Gson gson;
    private static final String petsFd = "src/main/resources/pets.json";
    private static final String ePetsFd = "src/main/resources/exotic_animals.json";
    /**
     * makes a pet model instance that inits the shelter and
     * configures the gson object, using pretty printing for better practice
     * also loading the pet data from the json files
     */
    public Pet_Model() {
        shelter = new Shelter<>();
        gson = new GsonBuilder().setPrettyPrinting().create();
        loadPets();
    }
    /**
     * loads the pets from the json files and places
     * them into the shelter hopefully
     * reading all json files to use
     * uses the adapter for exotic animals
     */
    private void loadPets() {
        try {
        		//loading regular pet file
            Reader reader = new FileReader(petsFd);
            Type listType = new TypeToken<List<Map<String, Object>>>(){}.getType();
            	//had to use mapping in order to serialize and deserialize, it was
            	//the best solution i could find online
            List<Map<String, Object>> petsData = gson.fromJson(reader, listType);
            if (petsData != null) 
            {
                for (int i = 0; i < petsData.size(); i++) 
                {
                	Map<String, Object> petData = petsData.get(i);
                    int id = ((Double) petData.get("id")).intValue();
                    String name = (String) petData.get("name");
                    String type = (String) petData.get("type");
                    String species = (String) petData.get("species");
                    int age = ((Double) petData.get("age")).intValue();
                    boolean adopted = (Boolean) petData.get("adopted");
                    
                    Pet pet;
                    switch (type) {
                        case "Dog":
                            pet = new Dog(id, name, species, age);
                            break;
                        case "Cat":
                            pet = new Cat(id, name, species, age);
                            break;
                        case "Rabbit":
                            pet = new Rabbit(id, name, species, age);
                            break;
                        default:
                            continue;
                    }
                    pet.setAdopted(adopted);
                    shelter.addPet(pet);
                }
            }
            reader.close();
            
            	//loading the exotic pet file
            reader = new FileReader(ePetsFd);
            listType = new TypeToken<List<Map<String, Object>>>(){}.getType();
            List<Map<String, Object>> exoticPetData = gson.fromJson(reader, listType);
            
            if (exoticPetData != null) 
            {
                for (int i = 0; i < exoticPetData.size(); i++) 
                {
                	Map<String, Object> animalData = exoticPetData.get(i);
                    String uniqueId = (String) animalData.get("uniqueId");
                    String animalName = (String) animalData.get("animalName");
                    String category = (String) animalData.get("category");
                    String subSpecies = (String) animalData.get("subSpecies");
                    int yearsOld = ((Double) animalData.get("yearsOld")).intValue();
                    ExoticAnimal exoticAnimal = new ExoticAnimal(uniqueId, animalName, category, subSpecies, yearsOld);
                    Adapter adapter = new Adapter(exoticAnimal);
                    shelter.addPet(adapter);
                }
            }
            reader.close();
            
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            System.err.println("Error occured with reading the file" + e.getMessage());
        }
    }
    /**
     * saves all the pets in the shelter to a new json file that combines the exotic and normal
     * using a time stamp of the specified layout to tell the time of when we saved it
     */
    public void savePets() {
        try {
            
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String outputFile = "src/main/resources/" + timestamp + "_Pet_Store_Selection.json";
            List<Pet> allPets = shelter.getAllPets();
                
            List<Map<String, Object>> petsTotalData = new ArrayList<>();
            
            for (int i = 0; i < allPets.size(); i++) 
            {
            	Pet pet = allPets.get(i);
            	Map<String, Object> petMap = new HashMap<>();
                if (pet instanceof Adapter) 
                {
                    ExoticAnimal exoticAnDat = ((Adapter) pet).getExoticAnimal();
                    petMap.put("uniqueId", exoticAnDat.getUniqueId());
                    petMap.put("animalName", exoticAnDat.getAnimalName());
                    petMap.put("category", exoticAnDat.getCategory());
                    petMap.put("subSpecies", exoticAnDat.getSubSpecies());
                    petMap.put("yearsOld", exoticAnDat.getYearsOld());
                } 
                else 
                {
                    petMap.put("id", pet.getId());
                    petMap.put("name", pet.getName());
                    petMap.put("type", pet.getType());
                    petMap.put("species", pet.getSpecies());
                    petMap.put("age", pet.getAge());
                    petMap.put("adopted", pet.isAdopted());
                }
                petsTotalData.add(petMap);
            }

            Writer petsWriter = new FileWriter(outputFile);
            gson.toJson(petsTotalData, petsWriter);
            petsWriter.close();          
            
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            System.err.println("Error occured with reading the file" + e.getMessage());
        }
    }
    /**
     * shelter that has all pets
     * @return
     * 			returns the well shelter of pets
     */
    public Shelter<Pet> getShelter() 
    {
        return shelter;
    }
    /**
     * adds a new pet to the shelter
     * @param pet
     * 			pet is added to shelter
     */
    public void addPet(Pet pet) 
    {
        shelter.addPet(pet);
    }
    /**
     * removes a pet from the shelter
     * @param pet
     * 				pet is to be removed from shelter
     */
    public void removePet(Pet pet) 
    {
        shelter.removePet(pet);
    }
    /**
     * i first tried to use boolean then went to public void,
     * i was super confused on this tbh
     *(got complicated) tries to remove a pet by both id and row
     * index, and if it fails it goes to finding id yet again
     * @param id
     * 			id of the pet to be removes
     * @param rowIndex
     * 				row index of the pet within the table
     */
    public void removePet(int id, int rowIndex) 
    {
        List<Pet> allPets = getAllPets();
        if (rowIndex >= 0 && rowIndex < allPets.size()) 
        {
            Pet petToRemove = allPets.get(rowIndex);
            if (petToRemove.getId() == id) 
            {
                shelter.removePet(petToRemove);
            }
        }
        else 
        {
            Pet petToRemove = getPet(id);
            if (petToRemove != null) 
            {
                shelter.removePet(petToRemove);
            }
        }
    }
    /**
     * gets the pet by its row index in the table
     * part of the complicated process
     * @param rowIndex
     * 				index of the pet
     * @return
     * 			returns either the pet or null showing failure
     */
    public Pet getPetByRowIndex(int rowIndex) {
        List<Pet> allPets = getAllPets();
        if (rowIndex >= 0 && rowIndex < allPets.size()) {
            return allPets.get(rowIndex);
        }
        return null;
    }
    /**
     * returns a pet based on the id
     * @param id
     * 			id is the id of the pet
     * @return
     */
    public Pet getPet(int id) {
        return shelter.getPet(id);
    }
    /**
     * returns a list of the pets within the shelter
     * @return
     */
    public List<Pet> getAllPets() {
        return shelter.getAllPets();
    }
    /**
     * marks a pet as adopted based off the id
     * @param id
     * 			id of the pet that will be adopted
     * @return
     * 			returning true if the pet is adopted and false if not
     */
    public boolean adoptPet(int id) {
        return shelter.adoptPet(id);
    }
    /**
     * marks a pet as adopted again
     * (got complicated and this was the fix)
     * @param pet
     * 			pet is the pet to be adopted
     * @return
     * 			returns true if successful and false if not
     */
    public boolean adoptPet(Pet pet) {
        if (pet != null && !pet.isAdopted()) {
            pet.setAdopted(true);
            return true;
        }
        return false;
    }
    /**
     * sorts pet by name
     */
    public void sortPetsByName() {
        shelter.sortPets();
    }
    /**
     * sorts pets by age
     */
    public void sortPetsByAge() {
        shelter.sortPets(new adopt_me2.others.Pet_Comparator_Age());
    }
    /**
     * sorts pet by species
     */
    public void sortPetsBySpecies() {
        shelter.sortPets(new adopt_me2.others.Pet_Comparator_Species());
    }
}
