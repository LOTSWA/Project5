package adopt_me2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <T>
 */
public class Shelter<T extends Pet>{
	private List<T> pets;

	/**
	 * 
	 */
	public Shelter()
	{
		this.pets = new ArrayList<>();
	}
	
	/**
	 * @param id
	 * @return
	 */
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
	
	/**
	 * @param pet
	 */
	public void addPet(T pet)
	{
		pets.add(pet);
	}
	
	/**
	 * @param id
	 * @return
	 */
	public boolean removePet(int id)
	{
		return pets.removeIf(pet -> pet.getId() == id);
	}

	/**
	 * @return
	 */
	public List<T> getAllPets() 
	{	
		return new ArrayList<>(pets);
	}
	
}
