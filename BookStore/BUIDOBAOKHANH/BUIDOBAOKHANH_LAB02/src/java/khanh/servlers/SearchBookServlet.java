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
import khanh.dao.BookDAO;
import khanh.dao.CategoryDAO;
import khanh.dto.BookDTO;
import khanh.dto.CategoryDTO;

/**
 *
 * @author buido
 */
public class SearchBookServlet extends HttpServlet {
    
    private static final String SUCCESS = "list_book.jsp";

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
            BookDAO dao = new BookDAO();
            List<BookDTO> result = null;
            String action = request.getParameter("action");
            if (action.equals("Login") || action.equals("Delete") || action.equals("Update") || action.equals("Continuous shopping")) {
                result = dao.loadAllBook();
                CategoryDAO dao_Cate = new CategoryDAO();
                List<CategoryDTO> listCate = dao_Cate.loadCategory();
                HttpSession session = request.getSession();
                session.setAttribute("LISTCATE", listCate);
            } else if (action.equals("Search by name")) {
                String search = request.getParameter("txtSearch");
                result = dao.searchByName(search);
                request.setAttribute("txtSearch", search);
            } else if (action.equals("Search by range price")) {
                float min = Float.parseFloat(request.getParameter("txtMin"));
                float max = Float.parseFloat(request.getParameter("txtMax"));
                result = dao.searchByRange(min, max);
                request.setAttribute("txtMin", min);
                request.setAttribute("txtMax", max);
            } else if (action.equals("Search by category")) {
                int cateID = Integer.parseInt(request.getParameter("txtCateID"));
                result = dao.searchByCategory(cateID);
            }
            request.setAttribute("LISTBOOK", result);
        } catch (Exception e) {
            log("Error at SearchBookServlet: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(SUCCESS).forward(request, response);
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
