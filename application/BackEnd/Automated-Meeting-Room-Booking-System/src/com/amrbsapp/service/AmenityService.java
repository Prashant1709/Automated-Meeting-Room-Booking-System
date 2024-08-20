package com.amrbsapp.service;

import com.amrbsapp.entity.Amenity;

import java.util.List;

public interface AmenityService {

    Amenity getAmenityById(int id);
    List<Amenity> getAmenities();
    void saveAmenity(Amenity amenity);
    void updateAmenity(Amenity amenity);
    void deleteAmenity(int id);

}
