package adopt_me2.controller;

import adopt_me2.model.PetInformationModel;
import adopt_me2.model.Pet_Model;
import adopt_me2.view.Pet_List_View;
import adopt_me2.view.Pet_View;

public class Pet_Controller {

	private Pet_Model petContainerModel;
	private Pet_View pet_View;
	private Pet_List_View petList;
	
	
	public Pet_Controller(Pet_Model petContainerModell, Pet_View pet_View) {
		// TODO Auto-generated constructor stub
		
		this.pet_View = pet_View;
		this.petContainerModel = petContainerModel;
		this.pet_View.addActionListenerToSubmitButton(new SubmitButtonActionListener());;
	}


	public void initiate() {
		pet_View.setVisible(true);
	}
	
	private class SubmitButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
//			userModel.setUsername(userInputView.getUserName());
//			userModel.setAge(userInputView.getUserAge());
			PetInformationModel user = new PetInformationModel(pet_View.getPetName(), pet_View.getPetAge());
			Pet_Model.getUserList().add(user);
			System.out.println(petContainerModel.getPetList());
			petList.getUserList().addElement(user);
			petList.setVisible(true);
			
		}
		
	}
	
	private class DeleteUserButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
//			int selectedUserIndex = userListView.getSelectedUser();
			int[] multipleSelectedUserIndicies = pet_List_View.getMultipleSelectedUser();
			List<PetInformationModel> userList = new ArrayList<>();
			for(int i=0; i<multipleSelectedUserIndicies.length; i++) {
				userList.add(petList.getUserList().get(multipleSelectedUserIndicies[i]));
			}
			
			for(PetInformationModel user : userList) {
				petList.getUserList().removeElement(user);
				petContainerModel.getUserList().remove(user);
			}
			
			for(petInformationModel user : petContainerModel.getUserList()) {
				System.out.println("User: " + user);
			}
		}
		
	}

}

}
