package adopt_me2.others;

import java.util.Comparator;
import adopt_me2.model.Pet;

/**
 * 
 */
public class Pet_Comparator_Age implements Comparator<Pet>{

	/**
	 *
	 */
	@Override
	public int compare(Pet first, Pet second) {
		// TODO Auto-generated method stub
		return Integer.compare(first.getAge(), second.getAge());
	}
}