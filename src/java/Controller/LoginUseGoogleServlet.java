/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.EmailDTO;
import model.EmailHandler;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author admin
 */
public class LoginUseGoogleServlet extends HttpServlet {

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
        String code = request.getParameter("code");
        String accessToken = getToken(code);
        EmailDTO email = getEmailInfo(accessToken);
        String toEmail = email.getEmail();
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
                + "<h1 style=\"margin-left: 150px; font-size: 38px;\">"+ codeVerify +"</h1>"
                + "    <p>Vui lòng nhập mã này vào trang xác thực trên website của chúng tôi để hoàn tất quá trình đăng ký.</p>\n"
                + "    <p><a href=\"http://localhost:9999/onlineshop/verify.jsp\">Quay lại website của chúng tôi</a></p>\n"
                + "    <p>Vui lòng nhập mã này vào trang xác thực trên website của chúng tôi để hoàn tất quá trình đăng nhập.</p>\n"
                + "    <p>Nếu bạn không yêu cầu mã này, vui lòng bỏ qua email này hoặc liên hệ với bộ phận hỗ trợ của chúng tôi.</p>\n"
                + "    <p>Trân trọng,</p>\n"
                + "    <h2>FBT Shoes Shop</h2>\n"
                + "</body>\n"
                + "</html>";
        EmailHandler.sendEMail(toEmail, subject, content);
        HttpSession session = request.getSession();
        session.setAttribute("email", toEmail);
        session.setAttribute("codeVerify", codeVerify);
        session.setAttribute("authenticationfor", "logingoogle");
        request.getRequestDispatcher("verify.jsp").forward(request, response);
    }

    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post("https://accounts.google.com/o/oauth2/token")
                .bodyForm(Form.form().add("client_id", "690034575016-eo2ous2e565daim8dktf09lp925aig4u.apps.googleusercontent.com")
                        .add("client_secret", "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")
                        .add("redirect_uri", "http://localhost:9999/onlineshop/logingoogle")
                        .add("code", code)
                        .add("grant_type", "authorization_code").build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        System.out.println(" Json  " + jobj);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static EmailDTO getEmailInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        EmailDTO email = new Gson().fromJson(response, EmailDTO.class);

        return email;
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
        String codeEnter = request.getParameter("verify");
        int verify = Integer.parseInt(codeEnter);
        HttpSession session = request.getSession();
        int codeVerify = (Integer) session.getAttribute("codeVerify");
        String email = (String) session.getAttribute("email");

        if (codeVerify != verify) {
            request.setAttribute("err", "Code nhập không đúng");
            request.getRequestDispatcher("verify.jsp").forward(request, response);
        } else {
            AccountDAO adao = new AccountDAO();
            if (!adao.checkAccountExit(email)) {
                adao.insertAcountLoginGoogle(email, 2);
                request.getRequestDispatcher("home").forward(request, response);
            }
            Account a = adao.getAccountByEmail(email);
            session.setAttribute("account", a);
            adao.updateLastDateLogin(email);
            request.getRequestDispatcher("shop").forward(request, response);
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
