package au.com.nbnco.springseed.web.page;

import au.com.nbnco.springseed.web.section.NavigationBar;
import org.fluentlenium.core.annotation.Page;

import static org.fluentlenium.core.filter.FilterConstructor.withText;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsNot.not;

public abstract class AuthenticatedPage extends SpringSeedWebComponent {

	@Page
	private NavigationBar navigationBar;

	public NavigationBar navigate() {
		return navigationBar;
	}

	public void assertTitle(String title){
		assertThat(find("h2", withText(title)), not(empty()));
	}

}
