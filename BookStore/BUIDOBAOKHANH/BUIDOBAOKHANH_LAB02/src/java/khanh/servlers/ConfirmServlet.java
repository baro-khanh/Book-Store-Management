/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.servlers;

import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khanh.dao.ApplyCodeDAO;
import khanh.dao.BookDAO;
import khanh.dao.OrderDAO;
import khanh.dao.OrderDetailDAO;
import khanh.dto.ApplySaleDTO;
import khanh.dto.OrderDTO;
import khanh.dto.ShoppingCartDTO;

/**
 *
 * @author buido
 */
public class ConfirmServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "error.jsp";
        try {
            HttpSession session = request.getSession();
            ShoppingCartDTO cart = (ShoppingCartDTO) session.getAttribute("CART");
            BookDAO dao = new BookDAO();
            String checkRest = dao.checkQuantityInStock(cart);
            if (checkRest.length() == 0) {
                //ok het
                //tao order
                String user = (String) session.getAttribute("USER");
                float total = Float.parseFloat(request.getParameter("txtTotal"));
                String date = new Timestamp(System.currentTimeMillis()) + "";
                String discount = "";
                OrderDTO dto = new OrderDTO(user, date, total);
                OrderDAO daoOrder = new OrderDAO();
                boolean checkInsertOrder = daoOrder.insertOrder(dto);
                if (checkInsertOrder) {
                    int orderID = daoOrder.findOrderID();
                    OrderDetailDAO daoDetail = new OrderDetailDAO();
                    boolean checkDetail = daoDetail.inserDetail(orderID, cart);
                    if (checkDetail) {
                        ApplyCodeDAO daoApplyCode = new ApplyCodeDAO();
                        String discountCode = (String) session.getAttribute("DISCOUNT_CODE");
                        if (discount.length() != 0) {
                            ApplySaleDTO dtoApplyCode = new ApplySaleDTO(discountCode, user);
                            daoApplyCode.insertApplySale(dtoApplyCode);
                        }
                        session.removeAttribute("DISCOUNT_CODE");
                        session.removeAttribute("DISCOUNT");
                        dao.updateQuantity(cart);
                        cart.clearCart();
                        url = "SearchOrderServlet";
                    } else {
                        request.setAttribute("ERROR", "Some error occur");
                    }
                } else {
                    request.setAttribute("ERROR", "Create bill failed");
                }
            } else {
                request.setAttribute("INVALID", "Book tite: " + checkRest + " in stock not enought for your order");
                url = "view_cart.jsp";
            }
        } catch (Exception e) {
            log("Error at ConfirmServlet: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
