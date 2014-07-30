package au.com.nbnco.springseed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import au.com.nbnco.springseed.data.Hotel;
import au.com.nbnco.springseed.repository.HotelRepository;

@Component
@Transactional
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel findOne(Long id) {
        return hotelRepository.findOne(id);
    }

    @Override
    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }
}