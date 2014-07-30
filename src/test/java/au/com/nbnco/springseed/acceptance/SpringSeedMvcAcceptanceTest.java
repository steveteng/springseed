package au.com.nbnco.springseed.acceptance;

import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.adapter.util.SharedDriver;
import org.fluentlenium.adapter.util.SharedDriver.SharedType;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;

@SharedDriver(type = SharedType.PER_CLASS)
public abstract class SpringSeedMvcAcceptanceTest extends FluentTest {

	private static final String SNAPSHOT_PATH = "results/screenshots/";
	protected static WebDriver webDriver;

	private static String baseUrl = "http://localhost:8082";

	@BeforeClass
	public static void classSetUp() throws Exception {

		//Properties properties = LocalConfigurationLoader.load();

		//comment out to run against localhost:8080/online_customers
//		application = Application.start();
//		baseUrl = application.urlFor("").toString();
		initWebDriver();

	}

	@Override
	public WebDriver getDefaultDriver() {
		return webDriver;
	}

	private static void initWebDriver() {
		final FirefoxProfile firefoxProfile = new FirefoxProfile();
		webDriver = new FirefoxDriver(firefoxProfile);
//		webDriver = new ChromeDriver();
//		webDriver = new HtmlUnitDriver(true);
	}

	@AfterClass
	public static void classTearDown() {
		webDriver.quit();
	}

	@Before
	public void setUp() throws Exception {
		setSnapshotMode(Mode.TAKE_SNAPSHOT_ON_FAIL);
		setSnapshotPath(SNAPSHOT_PATH + getClass().getCanonicalName());
	}

	@Override
	public String getBaseUrl() {
		return baseUrl;
	}

	protected String getEntityId() {
		final String currentUrl = getDriver().getCurrentUrl();
		return currentUrl.substring(currentUrl.lastIndexOf('/') + 1);
	}

	protected static boolean isJsEnabled() {
		if (webDriver instanceof FirefoxDriver) {
			return ((FirefoxDriver) webDriver).getCapabilities().isJavascriptEnabled();
		}
		//TODO: for other drivers
		return true;
	}

}
