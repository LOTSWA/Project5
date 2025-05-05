package adopt_me2.mvc;

import javax.swing.SwingUtilities;

import adopt_me2.controller.Pet_Controller;
import adopt_me2.model.Pet_Model;
import adopt_me2.view.Pet_View;

public class Main {
	public static void main(String[] args) {
        	SwingUtilities.invokeLater(new Runnable() {
			
            	@Override
            	public void run() {
                
               		PetModel model = new PetModel();
                	PetView view = new PetView();
                	new PetController(model, view);

                	view.display();
            	}
        });
    }
}
