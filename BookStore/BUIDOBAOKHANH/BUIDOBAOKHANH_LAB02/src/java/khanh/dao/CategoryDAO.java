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
import khanh.dto.CategoryDTO;

/**
 *
 * @author buido
 */
public class CategoryDAO {
    
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;
    
    public CategoryDAO() {
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
    
    public List<CategoryDTO> loadCategory() throws Exception {
        List<CategoryDTO> result = null;
        String cateName;
        int cateID;
        CategoryDTO dto = null;
        try {
            String sql = "Select CateID, CateName From Category";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                cateName = rs.getString("CateName");
                cateID = rs.getInt("CateID");
                dto = new CategoryDTO(cateName, cateID);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public String findCateName(int cateID) throws Exception {
        String cateName = "";
        CategoryDTO dto = null;
        try {
            String sql = "Select CateName From Category Where CateID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, cateID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                cateName = rs.getString("CateName");
            }
        } finally {
            closeConnection();
        }
        return cateName;
    }
    
    public void setCateName(List<BookDTO> listBook) throws Exception {
        List<CategoryDTO> listCate = loadCategory();
        int check;
        for (int i = 0; i < listBook.size(); i++) {
            check = -1;
            for (CategoryDTO categoryDTO : listCate) {
                if (listBook.get(i).getCateId() == categoryDTO.getCateId()) {
                    check++;
                    listBook.get(i).setCateName(categoryDTO.getCateName());
                }
            }
            if (check < 0) {
                listCate.get(i).setCateName("No support");
            }
        }
    }
}
