package com.amrbsapp.dao;

import com.amrbsapp.entity.Amenity;

import java.sql.Connection;
import java.util.List;

public interface AmenityDAO {
    Amenity getAmenity(int amenityID, Connection connection);
    void saveAmenity(Amenity amenity,Connection connection);
    void updateAmenity(Amenity amenity,Connection connection);
    void deleteAmenity(int amenityID,Connection connection);
    List<Amenity> getAllAmenities(Connection connection);
}
