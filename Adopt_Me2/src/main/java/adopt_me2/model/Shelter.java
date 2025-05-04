package adopt_me2.model;

import java.util.ArrayList;
import java.util.List;

public class Shelter<T extends Pet>{
	private List<T> pets;

	public Shelter()
	{
		this.pets = new ArrayList<>();
	}
	
	public T getPet(int id) 
	{
		for(int i = 0; i < pets.size(); i++)
		{
			T pet = pets.get(i);
			if(pet.getId() == id)
			{
				return pet;
			}
		}
		return null;
	}
	
	public void addPet(T pet)
	{
		pets.add(pet);
	}
	
	public boolean removePet(int id)
	{
		return pets.removeIf(pet -> pet.getId() == id);
	}

	public List<T> getAllPets() 
	{	
		return new ArrayList<>(pets);
	}
	
}
