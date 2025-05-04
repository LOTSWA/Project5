package adopt_me2.mvc;

import javax.swing.SwingUtilities;

import adopt_me2.controller.Pet_Controller;
import adopt_me2.model.Pet_Model;
import adopt_me2.view.Pet_View;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						
						Pet_Controller controller = new Pet_Controller(
								
								new Pet_Model(),
								new Pet_View());
								controller.intiate();
								
								
						
						
					}
					
				});
	}

}
