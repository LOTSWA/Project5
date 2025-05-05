package adopt_me2.model;

/**
 * 
 */
public class Cat extends Pet {
    /**
     * @param id
     * @param name
     * @param species
     * @param age
     */
    public Cat(int id, String name, String species, int age)
    {
        super(id, name, "Cat", species, age);
    }
}
