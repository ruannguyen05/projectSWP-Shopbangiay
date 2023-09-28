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
import model.Account;
import model.EmailHandler;

/**
 *
 * @author admin
 */
@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

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
            out.println("<title>Servlet ProfileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        request.setAttribute("acc", a);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
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
        String fullName = request.getParameter("fullname");
        String sex = request.getParameter("gender");
        String phone = request.getParameter("phonenumber");
        String address = request.getParameter("address");
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        String codeEnter = request.getParameter("verify");
        AccountDAO adao = new AccountDAO();
        if (codeEnter == null && (!email.equals(a.getEmail()))) {
            if (adao.checkAccountExit(email) && (!email.equals(a.getEmail()))) {
                request.setAttribute("msg", "Email đã tồn tại");
                request.setAttribute("acc", a);
                request.getRequestDispatcher("profile.jsp").forward(request, response);
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
                        + "    <h2>Xin Chào!!</h2>\n"
                        + "    <p>Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi. Mã xác thực của bạn là:</p>\n"
                        + "<h1 style=\"margin-left: 150px; font-size: 38px;\">" + codeVerify + "</h1>"
                        + "    <p>Vui lòng nhập mã này vào trang xác thực trên website của chúng tôi để hoàn tất quá trình đổi email.</p>\n"
                        + "    <p><a href=\"http://localhost:9999/onlineshop/verify.jsp\">Quay lại website của chúng tôi</a></p>\n"
                        + "    <p>Nếu bạn không yêu cầu mã này, vui lòng bỏ qua email này hoặc liên hệ với bộ phận hỗ trợ của chúng tôi.</p>\n"
                        + "    <p>Trân trọng,</p>\n"
                        + "    <h2>FBT Shoes Shop</h2>\n"
                        + "</body>\n"
                        + "</html>";
                int gender = Integer.parseInt(sex);
                EmailHandler.sendEMail(email, subject, content);
                session.setAttribute("codeVerify", codeVerify);
                session.setAttribute("authenticationfor", "profile");
                Account account = new Account(email, a.getPassword(), fullName, gender, phone, address);
                session.setAttribute("account", account);
                session.setAttribute("id", a.getId());
                request.getRequestDispatcher("verify.jsp").forward(request, response);
            }
        } else if (codeEnter != null) {
            int id = (Integer) session.getAttribute("id");
            adao.updateProfile(id,a.getEmail(), a.getFullName(), a.getGender(), a.getPhoneNumber(), a.getAddress());
            request.setAttribute("msg", "Thay đổi thông tin thành công !!");
            request.setAttribute("acc", adao.getAccountByEmail(a.getEmail()));

            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } else {
            int gender = Integer.parseInt(sex);
            adao.updateProfile(adao.getAidByEmail(a.getEmail()),a.getEmail(), fullName, gender, phone, address);
            request.setAttribute("msg", "Thay đổi thông tin thành công !");
            request.setAttribute("acc", adao.getAccountByEmail(a.getEmail()));
            request.getRequestDispatcher("profile.jsp").forward(request, response);
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
