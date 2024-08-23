package com.amrbsapp.controller;

import com.amrbsapp.entity.Amenity;
import com.amrbsapp.service.AmenityService;
import com.amrbsapp.util.DBConnection;

import java.sql.Connection;
import java.util.List;

public class AmenityController {
    private AmenityService amenityService;
    private final Connection conn = DBConnection.getConnection();
    public AmenityController(AmenityService amenityService) {
        this.amenityService = amenityService;
    }
    Amenity getAmenity(int amenityID){
        return amenityService.getAmenityById(amenityID,conn);
    }
    List<Amenity> getAllAmenities(){
        return amenityService.getAmenities(conn);
    }
    void addAmenity(Amenity amenity){
        amenityService.saveAmenity(amenity,conn);
    }
    void updateAmenity(Amenity amenity){
        amenityService.updateAmenity(amenity,conn);
    }
    void deleteAmenity(int amenityID){
        amenityService.deleteAmenity(amenityID,conn);
    }
}