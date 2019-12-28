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
import khanh.dao.DiscountDAO;
import khanh.dto.DiscountDTO;

/**
 *
 * @author buido
 */
public class InsertDiscountServlet extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String VIEW = "SearchDiscountServlet";
    private static final String INVALID = "insert_discount.jsp";

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
        String url = ERROR;
        try {
            String discountCode = request.getParameter("txtCode");
            int percent = Integer.parseInt(request.getParameter("txtPercent"));
            String des = request.getParameter("txtDes");
            String date = new Timestamp(System.currentTimeMillis()) + "";
            DiscountDAO dao = new DiscountDAO();
            DiscountDTO dto = new DiscountDTO(discountCode, des, date, percent);
            boolean checkInsert = dao.insertDiscount(dto);
            if (checkInsert) {
                url = VIEW;
            } else {
                request.setAttribute("ERROR", "Insert new discount failed");
            }
        } catch (Exception e) {
            log("Error at InsertDiscountServlet: " + e.getMessage());
            if (e.getMessage().contains("duplicate")) {
                String discountCode = request.getParameter("txtDiscountCode");
                request.setAttribute("INVALID", discountCode + " is readly existed");
                url = INVALID;
            }
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
