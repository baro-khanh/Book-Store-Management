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
import khanh.dao.AccountDAO;
import khanh.dao.RoleDAO;
import khanh.dto.RoleDTO;
import khanh.dto.ShoppingCartDTO;

/**
 *
 * @author buido
 */
public class LoginServlet extends HttpServlet {
    
    private static final String SUCCESS = "SearchBookServlet";
    private static final String ERROR = "error.jsp";

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
        String url = ERROR;
        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            AccountDAO dao = new AccountDAO();
            int roleID = dao.checkLogin(username, password);
            if (roleID > -1) {
                HttpSession session = request.getSession();
                session.setAttribute("USER", username);
                if (roleID == 1) {
                    session.setAttribute("ROLE", "admin");
                    url = SUCCESS;
                    RoleDAO daoRole = new RoleDAO();
                    List<RoleDTO> listRole = daoRole.loadRole();
                    session.setAttribute("LISTROLE", listRole);
                } else if (roleID == 2) {
                    session.setAttribute("ROLE", "user");
                    url = SUCCESS;
                    ShoppingCartDTO cart = new ShoppingCartDTO(username);
                    session.setAttribute("CART", cart);
                } else {
                    request.setAttribute("ERROR", "Your role is no longer support");
                }
            } else {
                request.setAttribute("ERROR", "Invalid username or password");
            }
        } catch (Exception e) {
            log("Error at LoginServlet: " + e.getMessage());
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
