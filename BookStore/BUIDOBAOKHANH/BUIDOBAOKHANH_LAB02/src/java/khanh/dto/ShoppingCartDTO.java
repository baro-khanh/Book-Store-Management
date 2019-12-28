/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.dto;

import java.util.HashMap;

/**
 *
 * @author buido
 */
public class ShoppingCartDTO {
    
    private String userID;
    private HashMap<Integer, BookDTO> cart;
    
    public ShoppingCartDTO(String userID) {
        this.userID = userID;
        this.cart = new HashMap<>();
    }
    
    public String getUserID() {
        return userID;
    }
    
    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    public HashMap<Integer, BookDTO> getCart() {
        return cart;
    }
    
    public boolean addToCart(BookDTO dto, int quan) throws Exception {
        if (this.cart.containsKey(dto.getBookId())) {
            //neu co roi
            int quantity = this.cart.get(dto.getBookId()).getQuantity() + quan;
            dto.setQuantity(quantity);
        }
        this.cart.put(dto.getBookId(), dto);
        return true;
    }
    
    public float getTotal() {
        int total = 0;
        for (BookDTO dto : this.cart.values()) {
            total += (dto.getPrice() * dto.getQuantity());
        }
        return total;
    }
    
    public void update(int quantity, int id) {
        if (this.cart.containsKey(id)) {
            this.cart.get(id).setQuantity(quantity);
        }
    }
    
    public void delete(int id) {
        if (this.cart.containsKey(id)) {
            this.cart.remove(id);
        }
    }
    
    public void clearCart(){
        this.cart.clear();
    }

}
