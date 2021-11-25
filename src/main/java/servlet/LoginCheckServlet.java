package servlet;

import static parameter.Messages.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;

@WebServlet("/LoginCheckServlet")
public class LoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログイン情報から取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			// ログインチェックOK
			super.service(request, response);
		} else {
			// NG
			request.setAttribute("errorMsg", ERR_SESSION_TIMEOUT);
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
			return;
		}
	}
}