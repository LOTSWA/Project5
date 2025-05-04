package adopt_me2.model;

public abstract class Pet implements Comparable<Pet> {
    private int id;
    private String name;
    private String type;
    private String species;
    private int age;
    private boolean adopted;

    public Pet(int id, String name, String type, String species, int age) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.species = species;
        this.age = age;
        this.adopted = false;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isAdopted() {
        return adopted;
    }

    public void setAdopted(boolean adopted) {
        this.adopted = adopted;
    }

    @Override
    public int compareTo(Pet other)
    {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", species='" + species + '\'' +
                ", age=" + age +
                ", adopted=" + adopted +
                '}';
    }
}
