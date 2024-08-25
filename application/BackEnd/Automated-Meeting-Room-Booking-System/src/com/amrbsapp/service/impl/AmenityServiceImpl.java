package com.amrbsapp.service.impl;

import com.amrbsapp.dao.AmenityDAO;
import com.amrbsapp.entity.Amenity;
import com.amrbsapp.exception.AmenityNotFoundException;
import com.amrbsapp.service.AmenityService;

import java.sql.Connection;
import java.util.List;

public class AmenityServiceImpl implements AmenityService {
    private AmenityDAO amenityDAO;
    public AmenityServiceImpl(AmenityDAO amenityDAO) {
        this.amenityDAO = amenityDAO;
    }
    @Override
    public Amenity getAmenityById(int id, Connection connection) throws AmenityNotFoundException {
        return amenityDAO.getAmenity(id, connection);
    }

    @Override
    public List<Amenity> getAmenities(Connection connection) {
        return amenityDAO.getAllAmenities(connection);
    }

    @Override
    public void saveAmenity(Amenity amenity, Connection connection) {
        amenityDAO.saveAmenity(amenity, connection);
    }

    @Override
    public void updateAmenity(Amenity amenity, Connection connection) throws AmenityNotFoundException {
        amenityDAO.updateAmenity(amenity, connection);
    }

    @Override
    public void deleteAmenity(int id, Connection connection) throws AmenityNotFoundException {
        amenityDAO.deleteAmenity(id, connection);
    }
}
