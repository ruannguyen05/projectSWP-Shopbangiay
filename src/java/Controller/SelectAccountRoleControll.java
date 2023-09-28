/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import dal.AdminDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Account;

/**
 *
 * @author MSI GF
 */
@WebServlet(name="SelectAccountRoleControll", urlPatterns={"/selectRole"})
public class SelectAccountRoleControll extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String roleId_raw = request.getParameter("roleId");
        AdminDAO ad = new AdminDAO();
        int roleId;
        try {
            roleId = Integer.parseInt(roleId_raw);
            List<Account> list = ad.getAllAccountsByRoleId(roleId);
            for (Account a : list) {
                out.println("<tr>\n" +
"                    <td>"+ a.getId() +"</td>\n" +
"                    <td>"+ a.getFullName()+"</td>\n" +
"                    <td>"+ a.getEmail() +"</td>\n" +
"                    <c:if test=\""+ a.getGender() +" == 1\">\n" +
"                        <td>Male</td>\n" +
"                    </c:if>\n" +
"                    <c:if test=\"${"+ a.getGender()+" == 0}\">\n" +
"                        <td>Female</td>\n" +
"                    </c:if>\n" +
"                    <td>"+ a.getPhoneNumber() +"</td>\n" +
"                    <td>"+ a.getAddress() +"</td>\n" +
"                    <c:if test=\""+a.getStatus()+" == 1\">\n" +
"                        <td>Active</td>\n" +
"                    </c:if>\n" +
"                    <c:if test=\""+a.getStatus()+" == 0\">\n" +
"                        <td>Blocked</td>\n" +
"                    </c:if>\n" +
"                    <td>"+a.getCreateDate()+"</td>\n" +
"                    <c:if test=\""+a.getRoleId()+" == 1\">\n" +
"                        <td>Admin</td>\n" +
"                    </c:if>\n" +
"                    <c:if test=\""+a.getRoleId()+" == 2\">\n" +
"                        <td>Customer</td>\n" +
"                    </c:if>\n" +
"                    \n" +
"                    <c:if test=\""+a.getRoleId()+" == 4\">\n" +
"                        <td>Product Manager</td>\n" +
"                    </c:if>\n" +
"                    <c:if test=\""+a.getRoleId()+" == }\">\n" +
"                        <td>Marketing Manager</td>\n" +
"                    </c:if>\n" +
"                    \n" +
"                    <td>\n" +
"                      <a href=\"loadAccount?id="+a.getId()+"\" class=\"tm-product-delete-link\">\n" +
"                        <i class=\"bi bi-pencil-square tm-product-delete-icon\"></i>\n" +
"                      </a>\n" +
"                    </td>\n" +
"                  </tr>");
            }
        } catch (Exception e) {
        }
        
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
