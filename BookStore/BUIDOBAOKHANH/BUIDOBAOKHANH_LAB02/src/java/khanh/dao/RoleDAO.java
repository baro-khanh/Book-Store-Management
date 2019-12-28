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
import khanh.dto.RoleDTO;

/**
 *
 * @author buido
 */
public class RoleDAO {
    
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;
    
    public RoleDAO() {
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
    
    public List<RoleDTO> loadRole() throws Exception {
        List<RoleDTO> result = null;
        int id;
        String name;
        RoleDTO dto = null;
        try {
            String sql = "Select RoleID, RoleName From Role";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                id = rs.getInt("RoleID");
                name = rs.getString("RoleName");
                dto = new RoleDTO(id, name);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public void setRoleForAccount(List<AccountDTO> listAccount) throws Exception {
        List<RoleDTO> listRole = loadRole();
        int check;
        for (int i = 0; i < listAccount.size(); i++) {
            check = -1;
            for (RoleDTO roleDTO : listRole) {
                if (listAccount.get(i).getRoleID() == roleDTO.getRoleID()) {
                    listAccount.get(i).setRoleName(roleDTO.getRoleName());
                    check++;
                    break;
                }
            }
            if (check < 0) {
                listAccount.get(i).setRoleName("No support");
            }
        }
    }
}
