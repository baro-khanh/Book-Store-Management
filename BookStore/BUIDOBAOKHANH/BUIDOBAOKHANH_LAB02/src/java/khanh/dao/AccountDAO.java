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
import khanh.dto.AccountDTO;

/**
 *
 * @author buido
 */
public class AccountDAO {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public AccountDAO() {
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

    public int checkLogin(String username, String password) throws Exception {
        int roleID = -1;
        try {
            conn = MyConnection.getConnection();
            String sql = "Select RoleID From Account Where UserID = ? and Password = ? and Available = 1";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if (rs.next()) {
                roleID = rs.getInt("RoleID");
            }
        } finally {
            closeConnection();
        }
        return roleID;
    }

    public boolean insertUser(AccountDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert into Account(UserID, Password, RoleID, Available, Email, Phone, Fullname) values(?,?,?,?,?,?,?)";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getUsername());
            preStm.setString(2, dto.getPassword());
            preStm.setInt(3, dto.getRoleID());
            preStm.setBoolean(4, dto.isAvailable());
            preStm.setString(5, dto.getEmail());
            preStm.setString(6, dto.getPhone());
            preStm.setString(7, dto.getFullname());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<AccountDTO> loadUser() throws Exception {
        List<AccountDTO> result = null;
        String username, password, email, phone, fullname;
        int roleID;
        boolean available;
        AccountDTO dto = null;
        try {
            String sql = "Select UserID, Password, RoleID, Available, Email, Phone, Fullname, Available From Account";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                username = rs.getString("UserID");
                password = rs.getString("Password");
                email = rs.getString("Email");
                phone = rs.getString("Phone");
                fullname = rs.getString("Fullname");
                roleID = rs.getInt("RoleID");
                available = rs.getBoolean("Available");
                dto = new AccountDTO(username, password, email, phone, fullname, available, roleID);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        if (result.size() > 0) {
            RoleDAO dao = new RoleDAO();
            dao.setRoleForAccount(result);
        }
        return result;
    }

    public AccountDTO findByPK(String username) throws Exception {
        String password, email, phone, fullname;
        int roleID;
        boolean available;
        AccountDTO dto = null;
        try {
            String sql = "Select Password, RoleID, Available, Email, Phone, Fullname, Available From Account Where UserID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();
            if (rs.next()) {
                password = rs.getString("Password");
                email = rs.getString("Email");
                phone = rs.getString("Phone");
                fullname = rs.getString("Fullname");
                roleID = rs.getInt("RoleID");
                available = rs.getBoolean("Available");
                dto = new AccountDTO(username, password, email, phone, fullname, available, roleID);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}
