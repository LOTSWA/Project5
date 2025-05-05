package adopt_me2.model;

/**
 * 
 */
public class Dog extends Pet {
    /**
     * @param id
     * @param name
     * @param species
     * @param age
     */
    public Dog(int id, String name, String species, int age)
    {
        super(id, name, "Dog", species, age);
    }
}