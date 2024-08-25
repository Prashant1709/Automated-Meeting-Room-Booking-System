package com.amrbsapp.dao;

import com.amrbsapp.entity.Amenity;
import com.amrbsapp.exception.AmenityNotFoundException;

import java.sql.Connection;
import java.util.List;

public interface AmenityDAO {
    Amenity getAmenity(int amenityID, Connection connection) throws AmenityNotFoundException;
    void saveAmenity(Amenity amenity,Connection connection);
    void updateAmenity(Amenity amenity,Connection connection) throws AmenityNotFoundException;
    void deleteAmenity(int amenityID,Connection connection) throws AmenityNotFoundException;
    List<Amenity> getAllAmenities(Connection connection) ;
}
