package com.amrbsapp.dao.impl;

import com.amrbsapp.dao.AmenityDAO;
import com.amrbsapp.entity.Amenity;
import com.amrbsapp.exception.AmenityNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AmenityImpl implements AmenityDAO {




//    @Override
//    public Amenity getAmenity(int amenityID, Connection connection) throws AmenityNotFoundException {
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        Amenity amenity = null;
//        try {
//            preparedStatement = connection.prepareStatement("SELECT * FROM amrbsapp.amenities WHERE amenityID = ?");
//            preparedStatement.setInt(1, amenityID);
//            resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                amenity = new Amenity(resultSet.getInt("amenityID"), resultSet.getString("name"),resultSet.getInt("creditCost"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return amenity;
//    }
    @Override
    public Amenity getAmenity(int amenityID, Connection connection) throws AmenityNotFoundException {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Amenity amenity = null;
    try {
        preparedStatement = connection.prepareStatement("SELECT * FROM amrbsapp.amenities WHERE amenityID = ?");
        preparedStatement.setInt(1, amenityID);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            amenity = new Amenity(resultSet.getInt("amenityID"), resultSet.getString("name"), resultSet.getInt("creditCost"));
        } else {
            throw new AmenityNotFoundException("Amenity with ID " + amenityID + " not found.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return amenity;
}

    @Override
    public void saveAmenity(Amenity amenity, Connection connection) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO amrbsapp.amenities (name, creditCost) VALUES (?, ?)");
            preparedStatement.setString(1, amenity.getName());
            preparedStatement.setInt(2, amenity.getCreditCost());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void updateAmenity(Amenity amenity, Connection connection) {
//        PreparedStatement preparedStatement = null;
//        try {
//            preparedStatement = connection.prepareStatement("UPDATE amrbsapp.amenities SET name = ?, creditCost = ? WHERE amenityID = ?");
//            preparedStatement.setString(1, amenity.getName());
//            preparedStatement.setInt(2, amenity.getCreditCost());
//            preparedStatement.setInt(3, amenity.getAmenityID());
//            preparedStatement.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void updateAmenity(Amenity amenity, Connection connection) throws AmenityNotFoundException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // Check if the amenity exists
            preparedStatement = connection.prepareStatement("SELECT amenityID FROM amrbsapp.amenities WHERE amenityID = ?");
            preparedStatement.setInt(1, amenity.getAmenityID());
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new AmenityNotFoundException("Amenity with ID " + amenity.getAmenityID() + " not found.");
            }

            // Amenity exists, proceed with the update
            preparedStatement = connection.prepareStatement("UPDATE amrbsapp.amenities SET name = ?, creditCost = ? WHERE amenityID = ?");
            preparedStatement.setString(1, amenity.getName());
            preparedStatement.setInt(2, amenity.getCreditCost());
            preparedStatement.setInt(3, amenity.getAmenityID());
            preparedStatement.executeUpdate();
            System.out.println("Amenity updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

//    @Override
//    public void deleteAmenity(int amenityID, Connection connection) {
//        PreparedStatement preparedStatement = null;
//        try {
//            preparedStatement = connection.prepareStatement("DELETE FROM amrbsapp.amenities WHERE amenityID = ?");
//            preparedStatement.setInt(1, amenityID);
//            preparedStatement.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void deleteAmenity(int amenityID, Connection connection) throws AmenityNotFoundException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // Check if the amenity exists
            preparedStatement = connection.prepareStatement("SELECT amenityID FROM amrbsapp.amenities WHERE amenityID = ?");
            preparedStatement.setInt(1, amenityID);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new AmenityNotFoundException("Amenity with ID " + amenityID + " not found.");
            }

            // Amenity exists, proceed with the delete
            preparedStatement = connection.prepareStatement("DELETE FROM amrbsapp.amenities WHERE amenityID = ?");
            preparedStatement.setInt(1, amenityID);
            preparedStatement.executeUpdate();
            System.out.println("Amenity deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


//    @Override
//    public List<Amenity> getAllAmenities(Connection connection) {
//        List<Amenity> amenities = new ArrayList<>();
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            preparedStatement = connection.prepareStatement("SELECT * FROM amrbsapp.amenities");
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                amenities.add(new Amenity(resultSet.getInt("amenityID"), resultSet.getString("name"),resultSet.getInt("creditCost")));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return amenities;
//    }


        @Override
        public List<Amenity> getAllAmenities(Connection connection) {
            List<Amenity> amenities = new ArrayList<>();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                preparedStatement = connection.prepareStatement("SELECT * FROM amrbsapp.amenities");
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    amenities.add(new Amenity(resultSet.getInt("amenityID"), resultSet.getString("name"), resultSet.getInt("creditCost")));
                }



            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (resultSet != null) resultSet.close();
                    if (preparedStatement != null) preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return amenities;
        }
    }


