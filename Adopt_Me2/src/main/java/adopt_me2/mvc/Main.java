package adopt_me2.mvc;

import javax.swing.SwingUtilities;

import adopt_me2.controller.Pet_Controller;
import adopt_me2.model.Pet_Model;
import adopt_me2.view.Pet_View;

/**
 * 
 * @author Blade, Tim, and Zach
 * 
 * starts MVC
 * 
 * 
 * the main starts the window for adopt me
 * 
 */
public class Main {
	public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() 
        {

		  /**
             *starts MVC parts
             *
             *handles application data
             *starts GUI
             *
             *Displays GUI
             *
             */
            @Override
            public void run() 
            {

                Pet_Model model = new Pet_Model();
                Pet_View view = new Pet_View();
                new Pet_Controller(model, view);

                view.display();
            }
        });
    }
}
