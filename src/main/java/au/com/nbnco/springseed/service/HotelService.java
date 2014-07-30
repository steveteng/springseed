package au.com.nbnco.springseed.service;

import java.util.List;

import au.com.nbnco.springseed.data.Hotel;

public interface HotelService {

    Hotel findOne(Long id);

    List<Hotel> findAll();
}