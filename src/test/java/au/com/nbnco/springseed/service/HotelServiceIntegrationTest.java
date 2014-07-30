package au.com.nbnco.springseed.service;

import au.com.nbnco.springseed.data.Hotel;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HotelServiceIntegrationTest {
    private static final Logger log = LoggerFactory.getLogger(HotelServiceIntegrationTest.class);

    @Mock
    HotelService hotelService;

    @Test
    @SuppressWarnings("unchecked")
    public void shouldReturnZero() {
        List<Hotel> hotelList=hotelService.findAll();
        assert hotelList.size()==0;

        log.info("tests passed");

    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldFindOne() {
        Hotel hotel = new Hotel();
        hotel.setId(102L);
        when(hotelService.findOne(102L)).thenReturn(hotel);

        Hotel found=hotelService.findOne(102L);
        assert found==hotel;

        log.info("tests passed");

    }

    @Test
    @Ignore
    public void thisIsIgnored() {
    }
}
