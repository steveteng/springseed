package au.com.nbnco.springseed.acceptance;

import au.com.nbnco.springseed.web.page.HomePage;
import au.com.nbnco.springseed.web.page.LoginPage;
import com.google.common.collect.Lists;
import org.fluentlenium.core.annotation.Page;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LoginAcceptanceSampleUsingPage extends SpringSeedMvcAcceptanceTest {

	@Page
	private LoginPage loginPage;

	@Page
	private HomePage homePage;

	@Test
	public void shouldFailLoginWithWrongPassword() {

		goTo(loginPage);

		loginPage.fillAndSubmitForm("super", "wrong");

		assertAt(loginPage);

		final List<String> expectedErrors = Lists.newArrayList("Login failed. Please try again.");
		assertThat(loginPage.getErrors(), is(expectedErrors));
	}

	@Test
	public void shouldLoginAsSuper() {

		goTo(loginPage);

		loginPage.fillAndSubmitForm("super", "super");

		assertAt(homePage);
	}

}
