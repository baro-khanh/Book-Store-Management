/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import khanh.db.MyConnection;
import khanh.dto.ApplySaleDTO;

/**
 *
 * @author buido
 */
public class ApplyCodeDAO {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public ApplyCodeDAO() {
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

    public int checkUsedCode(String userID, String discountCode) throws Exception {
        int applyID = -1;
        try {
            String sql = "Select ApplyID From ApplyDiscount Where AccountID = ? and DiscountCode = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userID);
            preStm.setString(2, discountCode);
            rs = preStm.executeQuery();
            if (rs.next()) {
                applyID = rs.getInt("ApplyID");
            }
        } finally {
            closeConnection();
        }
        return applyID;
    }

    public boolean insertApplySale(ApplySaleDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert into ApplyDiscount(AccountID, DiscountCode) values(?,?)";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getUserID());
            preStm.setString(2, dto.getDiscountCode());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
}
