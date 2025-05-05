package adopt_me2.model;

/**
 * 
 */
public class ExoticAnimal {
    private String uniqueId;
    private String animalName;
    private String category;
    private String subSpecies;
    private int yearsOld;

    /**
     * @param uniqueId
     * @param animalName
     * @param category
     * @param subSpecies
     * @param yearsOld
     */
    public ExoticAnimal(String uniqueId, String animalName, String category, String subSpecies, int yearsOld) {
        this.uniqueId = uniqueId;
        this.animalName = animalName;
        this.category = category;
        this.subSpecies = subSpecies;
        this.yearsOld = yearsOld;
    }

    // Getters and Setters
    /**
     * @return
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * @param uniqueId
     */
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * @return
     */
    public String getAnimalName() {
        return animalName;
    }

    /**
     * @param animalName
     */
    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    /**
     * @return
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return
     */
    public String getSubSpecies() {
        return subSpecies;
    }

    /**
     * @param subSpecies
     */
    public void setSubSpecies(String subSpecies) {
        this.subSpecies = subSpecies;
    }

    /**
     * @return
     */
    public int getYearsOld() {
        return yearsOld;
    }

    /**
     * @param yearsOld
     */
    public void setYearsOld(int yearsOld) {
        this.yearsOld = yearsOld;
    }
}
