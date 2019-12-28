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
import khanh.dto.OrderDTO;

/**
 *
 * @author buido
 */
public class OrderDAO {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public OrderDAO() {
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

    public boolean insertOrder(OrderDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert into tbl_Order(AccountID, Total, Datetime) values(?,?,?)";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getUserID());
            preStm.setFloat(2, dto.getTotal());
            preStm.setString(3, dto.getDatetime());
//            preStm.setString(4, dto.getDiscount());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public int findOrderID() throws Exception {
        int orderID = -1;
        try {
            String sql = "Select MAX(OrderID) as LastID From tbl_Order";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()) {
                orderID = rs.getInt("LastID");
            }
        } finally {
            closeConnection();
        }
        return orderID;
    }

    public List<OrderDTO> searchOrder(String user) throws Exception {
        List<OrderDTO> result = null;
        int orderID;
        String datetime, discountcode;
        float total;
        OrderDTO dto = null;
        try {
            String sql = "Select OrderID, Datetime, Total From tbl_Order Where AccountID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, user);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                orderID = rs.getInt("OrderID");
                datetime = rs.getString("Datetime");
                total = rs.getFloat("Total");
                dto = new OrderDTO(user, datetime, total);
                dto.setOrderID(orderID);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<OrderDTO> searchOrderByDate(String user, String date) throws Exception {
        List<OrderDTO> result = null;
        int orderID;
        String datetime, discountcode;
        float total;
        OrderDTO dto = null;
        try {
            String sql = "Select OrderID, Datetime, Total From tbl_Order Where AccountID = ? and Datetime = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, user);
            preStm.setString(2, date);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                orderID = rs.getInt("OrderID");
                datetime = rs.getString("Datetime");
                total = rs.getFloat("Total");
                dto = new OrderDTO(user, datetime, total);
                dto.setOrderID(orderID);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public List<OrderDTO> searchOrderByDate( String date) throws Exception {
        List<OrderDTO> result = null;
        int orderID;
        String datetime, discountcode, user;
        float total;
        OrderDTO dto = null;
        try {
            String sql = "Select OrderID, Datetime, Total, AccountID From tbl_Order Where Datetime = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, date);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                orderID = rs.getInt("OrderID");
                datetime = rs.getString("Datetime");
                total = rs.getFloat("Total");
                user = rs.getString("AccountID");
                dto = new OrderDTO(user, datetime, total);
                dto.setOrderID(orderID);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<OrderDTO> loadAllOrder() throws Exception {
        List<OrderDTO> result = null;
        int orderID;
        String datetime, discountcode, user;
        float total;
        OrderDTO dto = null;
        try {
            String sql = "Select OrderID, Datetime, Total, AccountID From tbl_Order";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                orderID = rs.getInt("OrderID");
                datetime = rs.getString("Datetime");
                total = rs.getFloat("Total");
                user = rs.getString("AccountID");
                dto = new OrderDTO(user, datetime, total);
                dto.setOrderID(orderID);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<OrderDTO> searchOrderLIKEAccount(String search) throws Exception {
        List<OrderDTO> result = null;
        int orderID;
        String datetime, discountcode, user;
        float total;
        OrderDTO dto = null;
        try {
            String sql = "Select OrderID, Datetime, Total, AccountID From tbl_Order Where AccountID LIKE ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                orderID = rs.getInt("OrderID");
                datetime = rs.getString("Datetime");
                total = rs.getFloat("Total");
                user = rs.getString("AccountID");
                dto = new OrderDTO(user, datetime, total);
                dto.setOrderID(orderID);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
