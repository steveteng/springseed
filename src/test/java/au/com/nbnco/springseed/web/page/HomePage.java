package au.com.nbnco.springseed.web.page;

public class HomePage extends AuthenticatedPage {

	@Override
	public void isAt() {
		assertTitle("Welcome");
		navigate().isAt();
	}

}
