package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ルーム作成画面を制御するServlet 本Servletの実行にはログインを必要とする
 */
@WebServlet("/CreateRoomServlet")
public class CreateRoomServlet extends LoginCheckServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO 必要な処理
		
//		try {
//			// TODO 画面に必要な項目を渡す
//
//		} catch (SwackException e) {
//			e.printStackTrace();
//			request.setAttribute("errorMsg", ERR_SYSTEM);
//			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
//			return;
//		}

		request.getRequestDispatcher("/WEB-INF/jsp/createroom.jsp").forward(request, response);

	}

}