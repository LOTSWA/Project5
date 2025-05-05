package adopt_me2.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Shelter<T extends Pet>{
    private List<T> pets;

    public Shelter() {
        this.pets = new ArrayList<>();
    }

    public T getPet(int id) {
        for (int i = 0; i < pets.size(); i++) {
        	T pet = pets.get(i);
            if (pet.getId() == id) {
                return pet;
            }
        }
        return null;
    }

    public void addPet(T pet) {
    	pets.add(pet);
    }

    public void removePet(T pet) {
        pets.remove(pet);
    }

    public List<T> getAllPets() {
        return new ArrayList<>(pets);
    }


    public void sortPets() {
        Collections.sort(pets); 
    }

    public void sortPets(Comparator<Pet> comparator) {
        pets.sort(comparator);
    }

    public boolean adoptPet(int id) {
        T pet = getPet(id);
        if (pet != null && !pet.isAdopted())
        {
            pet.setAdopted(true);
            return true;
        }
        return false;
    }
}
