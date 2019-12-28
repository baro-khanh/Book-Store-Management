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
import khanh.dto.BookDTO;
import khanh.dto.OrderDetailDTO;
import khanh.dto.ShoppingCartDTO;

/**
 *
 * @author buido
 */
public class OrderDetailDAO {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public OrderDetailDAO() {
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

    public boolean inserDetail(int order, ShoppingCartDTO cart) throws Exception {
        boolean check = true;
        try {
            String sql = "Insert into OrderDetail(OrderID,BookID, Amount, SubPrice) values(?,?,?,?)";
            conn = MyConnection.getConnection();
            conn.setAutoCommit(false);
            preStm = conn.prepareStatement(sql);
            for (BookDTO dto : cart.getCart().values()) {
                preStm.setInt(1, order);
                preStm.setInt(2, dto.getBookId());
                preStm.setInt(3, dto.getQuantity());
                preStm.setFloat(4, dto.getPrice() * dto.getQuantity());
                preStm.executeUpdate();
            }
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
            check = false;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<OrderDetailDTO> searchDetailByOrderID(int orderID) throws Exception {
        List<OrderDetailDTO> result = null;
        int detailID, bookID, amount;
        float subprice;
        OrderDetailDTO dto = null;
        try {
            String sql = "Select OrderDetailID, BookID, Amount, SubPrice From OrderDetail Where OrderID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, orderID);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                detailID = rs.getInt("OrderDetailID");
                bookID = rs.getInt("BookID");
                amount = rs.getInt("Amount");
                subprice = rs.getFloat("SubPrice");
                dto = new OrderDetailDTO(orderID, bookID, amount, subprice);
                dto.setOrderDetail(orderID);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public void setTitle(List<OrderDetailDTO> list) throws Exception {
        BookDAO dao = new BookDAO();
        String title = "";
        for (OrderDetailDTO orderDetailDTO : list) {
            title = dao.checkExist(orderDetailDTO.getBookID());
            if (title.length() == 0) {
                title = "Not found";
            }
            orderDetailDTO.setTitle(title);
        }
    }
}
