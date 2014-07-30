package au.com.nbnco.springseed.controller;

import java.util.List;

import au.com.nbnco.springseed.utils.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.com.nbnco.springseed.data.Hotel;
import au.com.nbnco.springseed.service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    private static final Logger log = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    private HotelService hotelService;

    @Autowired
    private Properties conf;

    @RequestMapping("/{id}")
    public Hotel show(@PathVariable Long id) {
        log.debug("received id:{}", id);
        Hotel hotel = hotelService.findOne(id);

        return hotel;
    }

    @RequestMapping
    public List<Hotel> showList() {
        log.warn("vara:{}", conf.getVara());
        log.warn("varb:{}", conf.getVarb());
        log.warn("varc:{}", conf.getVarc());
        log.warn("vard:{}", conf.getVard());
        return hotelService.findAll();
    }
}