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
import khanh.dto.ShoppingCartDTO;

/**
 *
 * @author buido
 */
public class BookDAO {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public BookDAO() {
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

    public List<BookDTO> loadAllBook() throws Exception {
        List<BookDTO> result = null;
        int bookId, quantity, cateId;
        float price;
        boolean status;
        String title, image, description, author, importDate;
        BookDTO dto = null;
        try {
            String sql = "Select BookID, Status, Quantity, Title, Image, Description, Price, Author, CateID, ImportDate From Book Where Status = 'true' and Quantity > 0";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                bookId = rs.getInt("BookID");
                status = rs.getBoolean("Status");
                quantity = rs.getInt("Quantity");
                title = rs.getString("Title");
                image = rs.getString("Image");
                description = rs.getString("Description");
                price = rs.getFloat("Price");
                author = rs.getString("Author");
                cateId = rs.getInt("CateID");
                importDate = rs.getString("ImportDate");
                dto = new BookDTO(quantity, cateId, price, status, title, image, description, author, importDate);
                dto.setBookId(bookId);
                result.add(dto);

            }
        } finally {
            closeConnection();
        }
        if (result.size() > 0) {
            CategoryDAO dao = new CategoryDAO();
            dao.setCateName(result);
        }
        return result;
    }

    public String checkExist(int id) throws Exception {
        String title = "";
        try {
            String sql = "Select Title From Book Where BookID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                title = rs.getString("Title");
            }
        } finally {
            closeConnection();
        }
        return title;
    }

    public boolean deleteBook(int id) throws Exception {
        boolean check = false;
        try {
            String sql = "Update Book set Status = 'false' Where BookID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean insert(BookDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert into Book(Status, Quantity, Title, Image, Description, Price, Author, CateID, ImportDate) values(?,?,?,?,?,?,?,?,?)";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, dto.isStatus());
            preStm.setInt(2, dto.getQuantity());
            preStm.setString(3, dto.getTitle());
            preStm.setString(4, dto.getImage());
            preStm.setString(5, dto.getDescription());
            preStm.setFloat(6, dto.getPrice());
            preStm.setString(7, dto.getAuthor());
            preStm.setInt(8, dto.getCateId());
            preStm.setString(9, dto.getImportDate());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public int getMaxBookID() throws Exception {
        int id = -1;
        try {
            String sql = "Select MAX(BookID) as LastID From Book";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()) {
                id = rs.getInt("LastID");
            }
        } finally {
            closeConnection();
        }
        return id;
    }

    public BookDTO findByPK(int bookId) throws Exception {
        BookDTO dto = null;
        int quantity, cateId;
        float price;
        boolean status;
        String title, image, description, author, importDate;
        try {
            String sql = "Select Status, Quantity, Title, Image, Description, Price, Author, CateID, ImportDate From Book Where BookID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, bookId);
            rs = preStm.executeQuery();
            if (rs.next()) {
                status = rs.getBoolean("Status");
                quantity = rs.getInt("Quantity");
                title = rs.getString("Title");
                image = rs.getString("Image");
                description = rs.getString("Description");
                price = rs.getFloat("Price");
                author = rs.getString("Author");
                cateId = rs.getInt("CateID");
                importDate = rs.getString("ImportDate");
                dto = new BookDTO(quantity, cateId, price, status, title, image, description, author, importDate);
                dto.setBookId(bookId);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public List<BookDTO> searchByName(String search) throws Exception {
        List<BookDTO> result = null;
        int bookId, quantity, cateId;
        float price;
        boolean status;
        String title, image, description, author, importDate;
        BookDTO dto = null;
        try {
            String sql = "Select BookID, Status, Quantity, Title, Image, Description, Price, Author, CateID, ImportDate From Book Where Status = 'true' and Quantity > 0 and Title LIKE ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                bookId = rs.getInt("BookID");
                status = rs.getBoolean("Status");
                quantity = rs.getInt("Quantity");
                title = rs.getString("Title");
                image = rs.getString("Image");
                description = rs.getString("Description");
                price = rs.getFloat("Price");
                author = rs.getString("Author");
                cateId = rs.getInt("CateID");
                importDate = rs.getString("ImportDate");
                dto = new BookDTO(quantity, cateId, price, status, title, image, description, author, importDate);
                dto.setBookId(bookId);
                result.add(dto);

            }
        } finally {
            closeConnection();
        }
        if (result.size() > 0) {
            CategoryDAO dao = new CategoryDAO();
            dao.setCateName(result);
        }
        return result;
    }

    public List<BookDTO> searchByRange(float min, float max) throws Exception {
        List<BookDTO> result = null;
        int bookId, quantity, cateId;
        float price;
        boolean status;
        String title, image, description, author, importDate;
        BookDTO dto = null;
        try {
            String sql = "Select BookID, Status, Quantity, Title, Image, Description, Price, Author, CateID, ImportDate From Book Where Status = 'true' and Quantity > 0 and ? <= Price and Price <= ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setFloat(1, min);
            preStm.setFloat(2, max);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                bookId = rs.getInt("BookID");
                status = rs.getBoolean("Status");
                quantity = rs.getInt("Quantity");
                title = rs.getString("Title");
                image = rs.getString("Image");
                description = rs.getString("Description");
                price = rs.getFloat("Price");
                author = rs.getString("Author");
                cateId = rs.getInt("CateID");
                importDate = rs.getString("ImportDate");
                dto = new BookDTO(quantity, cateId, price, status, title, image, description, author, importDate);
                dto.setBookId(bookId);
                result.add(dto);

            }
        } finally {
            closeConnection();
        }
        if (result.size() > 0) {
            CategoryDAO dao = new CategoryDAO();
            dao.setCateName(result);
        }
        return result;
    }

    public List<BookDTO> searchByCategory(int cateID) throws Exception {
        List<BookDTO> result = null;
        int bookId, quantity;
        float price;
        boolean status;
        String title, image, description, author, importDate;
        BookDTO dto = null;
        try {
            String sql = "Select BookID, Status, Quantity, Title, Image, Description, Price, Author, ImportDate From Book Where Status = 'true' and CateID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setFloat(1, cateID);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                bookId = rs.getInt("BookID");
                status = rs.getBoolean("Status");
                quantity = rs.getInt("Quantity");
                title = rs.getString("Title");
                image = rs.getString("Image");
                description = rs.getString("Description");
                price = rs.getFloat("Price");
                author = rs.getString("Author");
                importDate = rs.getString("ImportDate");
                dto = new BookDTO(quantity, cateID, price, status, title, image, description, author, importDate);
                dto.setBookId(bookId);
                result.add(dto);

            }
        } finally {
            closeConnection();
        }
        if (result.size() > 0) {
            CategoryDAO dao = new CategoryDAO();
            dao.setCateName(result);
        }
        return result;
    }

    public boolean update(BookDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Update Book set Status = ?, Quantity = ?, Title = ?, Image = ?, Description = ?, Price = ?, Author = ?, CateID = ?, ImportDate = ? where BookID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, dto.isStatus());
            preStm.setInt(2, dto.getQuantity());
            preStm.setString(3, dto.getTitle());
            preStm.setString(4, dto.getImage());
            preStm.setString(5, dto.getDescription());
            preStm.setFloat(6, dto.getPrice());
            preStm.setString(7, dto.getAuthor());
            preStm.setInt(8, dto.getCateId());
            preStm.setString(9, dto.getImportDate());
            preStm.setInt(10, dto.getBookId());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public String checkQuantityInStock(ShoppingCartDTO cart) throws Exception {
        String check = "";
        int rest;
        List<BookDTO> bookInStock = loadAllBook();
        for (BookDTO dtoInCart : cart.getCart().values()) {
            for (int i = 0; i < bookInStock.size(); i++) {
                if (dtoInCart.getBookId() == bookInStock.get(i).getBookId()) {
                    //tim 
                    rest = bookInStock.get(i).getQuantity() - dtoInCart.getQuantity();
                    if (rest < 0) {
                        check = dtoInCart.getTitle();
                        break;
                    }
                }
            }
        }
        return check;
    }

    public void updateQuantity(ShoppingCartDTO cart) throws Exception {
        List<BookDTO> listBook = loadAllBook();
        int quantity;
        try {
            String sql = "Update Book set Quantity = ? Where BookID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            for (BookDTO dto : cart.getCart().values()) {
                for (int i = 0; i < listBook.size(); i++) {
                    if (dto.getBookId() == listBook.get(i).getBookId()) {
                        quantity = listBook.get(i).getQuantity() - dto.getQuantity();
                        preStm.setInt(1, quantity);
                        preStm.setInt(2, listBook.get(i).getBookId());
                        preStm.addBatch();
                    }
                }
            }
            preStm.executeBatch();
        } finally {
            closeConnection();
        }
    }
}
