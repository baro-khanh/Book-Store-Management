/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.servlers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khanh.dao.ApplyCodeDAO;
import khanh.dao.DiscountDAO;

/**
 *
 * @author buido
 */
public class CheckSaleCodeServlet extends HttpServlet {

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
        try {
            String discountCode = request.getParameter("txtSale");
            HttpSession session = request.getSession();
            String userID = (String) session.getAttribute("USER");
            ApplyCodeDAO dao = new ApplyCodeDAO();
            DiscountDAO daoDiscount = new DiscountDAO();
            int checkExist = daoDiscount.checkExist(discountCode);
            if (checkExist > -1) {
                //co ton tai
                int applyID = dao.checkUsedCode(userID, discountCode);
                if (applyID < 0) {
                    //chua dung
                    request.setAttribute("MESS", "Available discount codes");
                    session.setAttribute("DISCOUNT", checkExist);
                    session.setAttribute("DISCOUNT_CODE", discountCode);
                } else {
                    //dung roi
                    request.setAttribute("MESS", "This discount code is out of range");
                }
            } else {
                request.setAttribute("MESS", "This discount code is no longer exist");
            }
            request.setAttribute("SALECODE", discountCode);
        } catch (Exception e) {
            log("Error at CheckSaleCodeServlet: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("view_cart.jsp").forward(request, response);
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
