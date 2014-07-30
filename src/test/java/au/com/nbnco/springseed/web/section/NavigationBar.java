package au.com.nbnco.springseed.web.section;

import au.com.nbnco.springseed.web.page.SpringSeedWebComponent;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

public class NavigationBar extends SpringSeedWebComponent {

	@FindBy(className = "login-user")
	private FluentWebElement welcomeMessage;

	@FindBy(linkText = "Logout")
	private FluentWebElement logout;

	@FindBy(linkText = "Orders")
	private FluentWebElement ordersLink;

	@FindBy(linkText = "Change Management")
	private FluentWebElement changeManagementLink;


	@Override
	public void isAt() {
		logout.getText();
	}

	public void logout(){
		click(logout);
	}

	public String getWelcomeText() {
		return welcomeMessage.getText().trim();
	}

	public void toOrders() {
		click(ordersLink);
	}

	public void toChangeActivities() {
		click(changeManagementLink);
	}

}
