/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import khanh.db.MyConnection;
import khanh.dto.DiscountDTO;

/**
 *
 * @author buido
 */
public class DiscountDAO {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public DiscountDAO() {
    }

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public boolean insertDiscount(DiscountDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert into Discount(DiscountCode, Amount, Description, ImportDate) values(?,?,?,?)";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getDiscountCode());
            preStm.setInt(2, dto.getPercent());
            preStm.setString(3, dto.getDescription());
            preStm.setString(4, dto.getImportDate());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<DiscountDTO> loadDiscountList() throws Exception {
        List<DiscountDTO> result = null;
        String discountCode, description, importDate;
        int percent;
        DiscountDTO dto = null;
        try {
            String sql = "Select DiscountCode, Amount, Description, ImportDate From Discount";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                discountCode = rs.getString("DiscountCode");
                description = rs.getString("Description");
                importDate = rs.getString("ImportDate");
                percent = rs.getInt("Amount");
                dto = new DiscountDTO(discountCode, description, importDate, percent);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public int checkExist(String discount) throws Exception {
        int check = -1;
        try {
            String sql = "Select Amount From Discount Where DiscountCode = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, discount);
            rs = preStm.executeQuery();
            if (rs.next()) {
                check = rs.getInt("Amount");
            }
        } finally {
            closeConnection();
        }
        return check;
    }

}
