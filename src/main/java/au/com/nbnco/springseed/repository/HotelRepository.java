package au.com.nbnco.springseed.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.nbnco.springseed.data.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}