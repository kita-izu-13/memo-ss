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
import model.MemoAdd;
import model.MemoAddLogic;


public class MemoFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String datetext = request.getParameter("date");
		String title =request.getParameter("title");
		String memo =request.getParameter("memo");
		
		//datetext(String型)をDate型に変換　「date」へ	
		Date date = Date.valueOf(datetext);
			
		//セッションスコープの取得
		HttpSession session = request.getSession();
		Form form = (Form)session.getAttribute("form");
		System.out.println("セッションスコープよりフォーム取得"+form);
		
		//メモフォームより追加
		MemoAdd memoadd = new MemoAdd(date,title,memo);
		
		//フォーム追加処理の実行
		MemoAddLogic bo = new MemoAddLogic();
		boolean result = bo.execute(memoadd,form);
		
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