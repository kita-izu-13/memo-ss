package servlet;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Form;
import model.MemoUpdate;
import model.MemoUpdateLogic;


public class MemoUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String idtext =request.getParameter("id");
		String datetext = request.getParameter("date");
		String title =request.getParameter("title");
		String memo =request.getParameter("memo");
		
		//idをint型へ
		int id=Integer.parseInt(idtext);
		
		//datetext(String型)をDate型に変換　「date」へ	
		Date date = Date.valueOf(datetext);

		//ログインしているか確認するため　セッションスコープからフォーム、ユーザー情報を取得
		HttpSession session = request.getSession();
		Form form = (Form)session.getAttribute("form");
		
		//メモフォームより更新
		MemoUpdate memoupdate = new MemoUpdate(id,date,title,memo);
		
		//フォーム更新処理の実行
		MemoUpdateLogic bo = new MemoUpdateLogic();
		boolean result = bo.update(memoupdate,form);
		
		//処理の成否によって処理を分岐
		if(result) {	//登録成功時
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/memoResult.jsp");
			dispatcher.forward(request, response);
		}	else {	//失敗時
			//リダイレクト
			response.sendRedirect("MemoFormServlet");
		}

	}
}