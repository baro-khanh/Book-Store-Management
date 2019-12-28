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
import khanh.dao.BookDAO;
import khanh.dao.CategoryDAO;
import khanh.dto.BookDTO;

/**
 *
 * @author buido
 */
public class EditServlet extends HttpServlet {

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
            String action = request.getParameter("action");
            BookDAO dao = new BookDAO();
            CategoryDAO daoCate = new CategoryDAO();
            BookDTO dto = null;
            if (action.equals("Insert")) {
                int bookID = dao.getMaxBookID();
                dto = dao.findByPK(bookID);
                int cateID = dto.getCateId();
                dto.setCateName(daoCate.findCateName(cateID));
                url = "detail_book.jsp";
            } else if (action.equals("Edit")) {
                int bookID = Integer.parseInt(request.getParameter("txtBookID"));
                dto = dao.findByPK(bookID);
                url = "update_book.jsp";
            } else if (action.equals("Buy")) {
                int bookID = Integer.parseInt(request.getParameter("txtBookID"));
                dto = dao.findByPK(bookID);
                int cateID = dto.getCateId();
                dto.setCateName(daoCate.findCateName(cateID));
                url = "detail_book.jsp";
            }
            request.setAttribute("DTO", dto);
        } catch (Exception e) {
            log("Error at EditServlet: " + e.getMessage());
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
