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
import khanh.dao.BookDAO;
import khanh.dto.BookDTO;

/**
 *
 * @author buido
 */
public class UpdateServlet extends HttpServlet {

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
            int bookID = Integer.parseInt(request.getParameter("txtBookID"));
            String title = request.getParameter("txtTitle");
            int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
            String des = request.getParameter("txtDes");
            float price = Float.parseFloat(request.getParameter("txtPrice"));
            String author = request.getParameter("txtAuthor");
            int cateID = Integer.parseInt(request.getParameter("txtCate"));
            String importdate = new Timestamp(System.currentTimeMillis()) + "";
            
            String image = "";
            String photo = request.getParameter("txtImage"); //hinh cu
            String fileUp = request.getParameter("txtFileUp"); //hinh moi
            if (fileUp.length() != 0) {
                image = fileUp.substring(fileUp.lastIndexOf("\\") + 1);
            } else {
                image = photo;
            }
            
            BookDTO dto = new BookDTO(quantity, cateID, price, true, title, image, des, author, importdate);
            dto.setBookId(bookID);
            BookDAO dao = new BookDAO();
            boolean check = dao.update(dto);
            if (check) {
                url = "SearchBookServlet";
            } else {
                request.setAttribute("ERROR", "Update book failed");
            }
        } catch (Exception e) {
            log("Error at UpdateServlet: " + e.getMessage());
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
