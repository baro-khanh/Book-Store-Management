/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.servlers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khanh.dao.OrderDAO;
import khanh.dto.OrderDTO;

/**
 *
 * @author buido
 */
public class SearchOrderServlet extends HttpServlet {

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
            HttpSession session = request.getSession();
            String user = (String) session.getAttribute("USER");
            OrderDAO dao = new OrderDAO();
            List<OrderDTO> result = null;
            String action = request.getParameter("action");
            if (action.equals("View") || action.equals("Confirm")) {
                result = dao.searchOrder(user);
            } else if (action.equals("Search by date")) {
                String role = (String) session.getAttribute("ROLE");
                String dateSearch = request.getParameter("txtDate");
                if (role.equals("admin")) {
                    result = dao.searchOrderByDate(dateSearch);
                } else {
                    result = dao.searchOrderByDate(user, dateSearch);
                }
            } else if (action.equals("Load")) {
                result = dao.loadAllOrder();
            } else if (action.equals("Search by Account")) {
                String search = request.getParameter("txtSearch");
                result = dao.searchOrderLIKEAccount(search);
            } 
            request.setAttribute("LIST_ORDER", result);
        } catch (Exception e) {
            log("Error at SearchOrderServlet: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("view_order.jsp").forward(request, response);
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
