public class TouchTypeTutorialApp {
	User user;
	GUI gui;
	
	public TouchTypeTutorialApp () {
		user = new User();
		user.loadUserProfile();
		gui = new GUI(user);
		
	}

}
