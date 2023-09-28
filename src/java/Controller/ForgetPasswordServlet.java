/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.EmailHandler;
import util.encodePassword;

/**
 *
 * @author admin
 */
@WebServlet(name = "ForgetPasswordServlet", urlPatterns = {"/forget"})
public class ForgetPasswordServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ForgetPasswordServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgetPasswordServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        request.getRequestDispatcher("forgetPassword.jsp").forward(request, response);
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
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        HttpSession session = request.getSession();
        AccountDAO adao = new AccountDAO();
        if (email != null) {
            if (!adao.checkAccountExit(email)) {
                request.setAttribute("err", "email không được đăng ký trong hệ thống");
                request.getRequestDispatcher("forgetPassword.jsp").forward(request, response);
            } else {
                int codeVerify = EmailHandler.generateCodeVerify();
                String subject = "Email Varification!";
                String content = "<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "<head>\n"
                        + "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                        + "    <title>Xác thực email</title>\n"
                        + "</head>\n"
                        + "<body>\n"
                        + "    <h2>Xin Chào</h2>\n"
                        + "    <p>Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi. Mã xác thực của bạn là:</p>\n"
                        + "<h1 style=\"margin-left: 150px; font-size: 38px;\">" + codeVerify + "</h1>"
                        + "    <p>Vui lòng nhập mã này vào trang xác thực trên website của chúng tôi để hoàn tất quá trình đăng ký.</p>\n"
                        + "    <p><a href=\"http://localhost:9999/onlineshop/verify.jsp\">Quay lại website của chúng tôi</a></p>\n"
                        + "    <p>Vui lòng nhập mã này vào trang xác thực trên website của chúng tôi và tiếp tục làm theo hướng dẫn.</p>\n"
                        + "    <p>Nếu bạn không yêu cầu mã này, vui lòng bỏ qua email này hoặc liên hệ với bộ phận hỗ trợ của chúng tôi.</p>\n"
                        + "    <p>Trân trọng,</p>\n"
                        + "    <h2>FBT Shoes Shop</h2>\n"
                        + "</body>\n"
                        + "</html>";
                EmailHandler.sendEMail(email, subject, content);
                session.setAttribute("email", email);
                session.setAttribute("codeVerify", codeVerify);
                request.setAttribute("verified", "verified");
                request.getRequestDispatcher("forgetPassword.jsp").forward(request, response);
            }
        } else if (code != null) {
            int verify = Integer.parseInt(code);
            int codeVerify = (Integer) session.getAttribute("codeVerify");
            if (codeVerify != verify) {
                request.setAttribute("err", "Code nhập không đúng");
                request.setAttribute("verified", "verified");
                request.getRequestDispatcher("forgetPassword.jsp").forward(request, response);
            } else {
                request.setAttribute("verified", "oke");
                request.setAttribute("changepass", "change");
                request.getRequestDispatcher("forgetPassword.jsp").forward(request, response);
            }
        } else {
            String password = request.getParameter("password");
            String emailDK = (String) session.getAttribute("email");
            String pass = encodePassword.getMd5(password);
            adao.changePassword(emailDK, pass);
            request.getRequestDispatcher("login.jsp").forward(request, response);

        }

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
