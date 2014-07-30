package au.com.nbnco.springseed.web.page;

import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LoginPage extends SpringSeedWebComponent {

	@FindBy(name = "submit")
	private FluentWebElement submit;

	@Override
	public void isAt() {
		assertThat(submit.getValue(), is("Login"));
	}
	@Override
	public String getUrl() {
		return "";
	}

	public void fillAndSubmitForm(String... paramsOrdered) {
		fill("#username").with(paramsOrdered[0]);
		fill("#password").with(paramsOrdered[1]);
		click(submit);
	}

	public List<String> getErrors() {
		return  find(".error").getTexts();
	}

}

