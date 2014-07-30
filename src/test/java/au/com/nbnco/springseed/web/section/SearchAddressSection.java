package au.com.nbnco.springseed.web.section;

import au.com.nbnco.springseed.web.page.SpringSeedWebComponent;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.fluentlenium.core.filter.FilterConstructor.withName;

public class SearchAddressSection extends SpringSeedWebComponent {

	public SearchAddressSection withLocationId(String locationId){

		fill("#address-nbnLocationId").with(locationId);

		return this;
	}

	public SearchAddressSection search() {

		click("#addressSearchButton");

		await().atMost(10, SECONDS).until("#search-header").isPresent();

		return this;
	}

	public void selectFirstResult() {
		findFirst("input", withName("submitAddressButton")).click();
	}

}
