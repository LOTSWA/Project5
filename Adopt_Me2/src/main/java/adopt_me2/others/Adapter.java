package adopt_me2.others;

import adopt_me2.model.ExoticAnimal;
import adopt_me2.model.Pet;

public class Adapter extends Pet {
    private ExoticAnimal specialPet;

    public Adapter(ExoticAnimal specialPet)
    {
        super(Integer.parseInt(specialPet.getUniqueId().substring(3)), specialPet.getAnimalName(), specialPet.getCategory(), specialPet.getSubSpecies(), specialPet.getYearsOld());
        
        this.specialPet = specialPet;
    }

    public ExoticAnimal getExoticAnimal() 
    {
        return specialPet;
    }
}