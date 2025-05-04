package adopt_me2.others;

import java.util.Comparator;
import adopt_me2.model.Pet;

public class Pet_Comparator_Species implements Comparator<Pet>{

	@Override
	public int compare(Pet first, Pet second) {
		// TODO Auto-generated method stub
		return first.getSpecies().compareTo(second.getSpecies());
	}
}