package com.amrbsapp.service;

import com.amrbsapp.entity.Amenity;
import com.amrbsapp.exception.AmenityNotFoundException;

import java.sql.Connection;
import java.util.List;

public interface AmenityService {

    Amenity getAmenityById(int id, Connection connection) throws AmenityNotFoundException;
    List<Amenity> getAmenities(Connection connection);
    void saveAmenity(Amenity amenity, Connection connection);
    void updateAmenity(Amenity amenity, Connection connection) throws AmenityNotFoundException;
    void deleteAmenity(int id, Connection connection) throws AmenityNotFoundException;

}
