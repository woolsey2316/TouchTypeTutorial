import GUI.GUI;
import Model.User;

public class TouchTypeTutorialApp {
	User user;
	GUI gui;
	
	public TouchTypeTutorialApp () {
		user = new User();
		gui = new GUI(user);
		
	}

}
