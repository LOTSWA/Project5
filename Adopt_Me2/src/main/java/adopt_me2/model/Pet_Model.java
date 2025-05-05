package adopt_me2.model;

import adopt_me2.others.Adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

public class Pet_Model {
    private Shelter<Pet> shelter;
    private Gson gson;
    private static final String petsFd = "src/main/resources/pets.json";
    private static final String ePetsFd = "src/main/resources/exotic_animals.json";

    public Pet_Model() {
        shelter = new Shelter<>();
        gson = new GsonBuilder().setPrettyPrinting().create();
        loadPets();
    }

    private void loadPets() {
        try {

            Reader reader = new FileReader(petsFd);
            Type listType = new TypeToken<List<Map<String, Object>>>(){}.getType();
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

    public Shelter<Pet> getShelter() 
    {
        return shelter;
    }

    public void addPet(Pet pet) 
    {
        shelter.addPet(pet);
    }

    public void removePet(Pet pet) 
    {
        shelter.removePet(pet);
    }
    
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
    
    public Pet getPetByRowIndex(int rowIndex) {
        List<Pet> allPets = getAllPets();
        if (rowIndex >= 0 && rowIndex < allPets.size()) {
            return allPets.get(rowIndex);
        }
        return null;
    }
    
    public Pet getPet(int id) {
        return shelter.getPet(id);
    }

    public List<Pet> getAllPets() {
        return shelter.getAllPets();
    }

    public boolean adoptPet(int id) {
        return shelter.adoptPet(id);
    }
    
    public boolean adoptPet(Pet pet) {
        if (pet != null && !pet.isAdopted()) {
            pet.setAdopted(true);
            return true;
        }
        return false;
    }

    public void sortPetsByName() {
        shelter.sortPets();
    }

    public void sortPetsByAge() {
        shelter.sortPets(new adopt_me2.others.Pet_Comparator_Age());
    }

    public void sortPetsBySpecies() {
        shelter.sortPets(new adopt_me2.others.Pet_Comparator_Species());
    }
}
