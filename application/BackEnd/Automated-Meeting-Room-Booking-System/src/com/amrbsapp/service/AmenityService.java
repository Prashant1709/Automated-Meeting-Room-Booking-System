package com.amrbsapp.service;

import com.amrbsapp.entity.Amenity;

import java.sql.Connection;
import java.util.List;

public interface AmenityService {

    Amenity getAmenityById(int id, Connection connection);
    List<Amenity> getAmenities(Connection connection);
    void saveAmenity(Amenity amenity, Connection connection);
    void updateAmenity(Amenity amenity, Connection connection);
    void deleteAmenity(int id, Connection connection);

}
