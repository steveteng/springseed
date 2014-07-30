package au.com.nbnco.springseed;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(JUnit4.class)
public class GreetingControllerAcceptanceTest {
    private static final Logger log = LoggerFactory.getLogger(GreetingControllerAcceptanceTest.class);

    @Test
    @SuppressWarnings("unchecked")
    public void thisAlwaysPasses() {
     // mock creation
     List<String> mockedList = (List<String>)mock(List.class);

     // using mock object ; observe that it didn't throw any "unexpected interaction exception" exception
     mockedList.add("one");
     mockedList.clear();

     // selective & explicit verification
     verify(mockedList).add("one");
     verify(mockedList).clear();

     log.info("tests all passed");
     log.debug("tests debug");

    }

    @Test
    @Ignore
    public void thisIsIgnored() {
    }
}
