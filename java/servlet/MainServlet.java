package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.AccountsUserDeleteDAO;
import dao.MemosDAO;
import dao.MemosDeleteDAO;
import dao.MemosDropDAO;
import dao.MemosOrderDAO;
import dao.MemosSelectDAO;
import dao.MemosUpdateDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Form;
import model.Memo;


public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		//セッションスコープよりユーザー情報とユーザーIDを取得
		Form form=(Form)session.getAttribute("form");
		String loginUser = (String)session.getAttribute("userId");

		//メモリストを取得して、セッションスコープに保存
		List<Memo> memoList=new ArrayList<>();
		MemosDAO memosDAO=new MemosDAO();
		memoList=memosDAO.findAll(form);
		session.setAttribute("memoList", memoList);

		if(loginUser == null) { //ログインしていない場合
			//リダイレクト
			response.sendRedirect("index.jsp");
		} else { //ログイン済みの場合
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/memo.jsp");
			dispatcher.forward(request, response);
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		
		// ボタンの選択された値を取得
		String select= request.getParameter("select");
	
		RequestDispatcher dispatcher;
		
		//ログインしているか確認するため　セッションスコープからフォーム、ユーザー情報を取得
		HttpSession session = request.getSession();
		String loginUser = (String)session.getAttribute("userId");
		Form form = (Form)session.getAttribute("form");
		
		//セレクトが選択された場合（追加・編集・削除）
		if(select != null) {
			session.setAttribute("select", select);
			
			if(loginUser == null) { //ログインしていない場合
				response.sendRedirect("index.jsp");
			} else { //ログイン済みの場合
					//それぞれのフォワード先へ
				switch(select) {
				case "追加":
					//フォワード
					dispatcher = request.getRequestDispatcher("WEB-INF/jsp/memoform.jsp");
					dispatcher.forward(request, response);
					break;
				case "編集":
					//フォワード＆セッションスコープへ保存
					dispatcher = request.getRequestDispatcher("WEB-INF/jsp/memoUpdate.jsp");
					session.setAttribute("select", select);
					dispatcher.forward(request, response);
					break;
				case "削除":
					dispatcher = request.getRequestDispatcher("WEB-INF/jsp/memoDelete.jsp");
					session.setAttribute("select", select);
					dispatcher.forward(request, response);
					break;
				case "検索":
					dispatcher = request.getRequestDispatcher("WEB-INF/jsp/memosSelect.jsp");
					session.setAttribute("select", select);
					dispatcher.forward(request, response);
					break;
				case "アカウント削除":
					dispatcher = request.getRequestDispatcher("WEB-INF/jsp/AccountDelete.jsp");
					session.setAttribute("select", select);
					dispatcher.forward(request, response);
					break;
				//"キャンセル"
				default:
					dispatcher = request.getRequestDispatcher("WEB-INF/jsp/memo.jsp");
					request.setAttribute("select", select);
					dispatcher.forward(request, response);
					break;
				}
			}
		}
		
		// 編集ボタンの選択された値(id)を取得
		String update= request.getParameter("update");
		
		//編集ボタンが選択された場合
		if(update != null) {			
		
			//メモフォームへフォワード（選択したメモを取得して表示）
			if(loginUser == null) { //ログインしていない場合
				//リダイレクト
				response.sendRedirect("index.jsp");
			} else { //ログイン済みの場合
				
				//update(id)を、int型へ
				int id=Integer.parseInt(update);
				
				//idをリクエストスコープに保存
				request.setAttribute("id", id);
				
				//update(id）により、該当メモ部分のみ取得して、リクエストスコープに保存
				MemosUpdateDAO memoUpdateDAO=new MemosUpdateDAO();
				List<Memo> memoUpdate = memoUpdateDAO.update(id,form);
				request.setAttribute("memoUpdate", memoUpdate);
				
				//メモ編集画面へフォワード
				dispatcher = request.getRequestDispatcher("WEB-INF/jsp/memoform.jsp");
				dispatcher.forward(request, response);
			}
		}
			
		// 削除ボタンの選択された値を取得
		String deletecheck=request.getParameter("delete");
		
		//削除ボタンが選択された場合
		if(deletecheck != null) {			
		
			//メモフォームへフォワード（選択したメモを取得して表示）
			if(loginUser == null) { //ログインしていない場合
				//リダイレクト
				response.sendRedirect("index.jsp");
			} else { //ログイン済みの場合
								
			//リストを準備　
			String[] deleteList=request.getParameterValues("delete");
			List<Integer> delete=new ArrayList<>();	

					
			for (String idtext : deleteList) {
				//update(id)を、int型へ
				int id=Integer.parseInt(idtext);
				delete.add(0,id);	//先頭に追加
			    }
				
				//idの行を削除する（DAO）
				MemosDeleteDAO memodeleteDAO = new MemosDeleteDAO();
				List<Integer> memoDelete = memodeleteDAO.delete(delete,form);
				
				//リクエストスコープに保存
				request.setAttribute("delete", deletecheck);
				
				//メモ削除画面へフォワード
				dispatcher = request.getRequestDispatcher("WEB-INF/jsp/memoResult.jsp");
				dispatcher.forward(request, response);
			}
		}
			
		// 検索ボタンの選択された値を取得
		String selectr=request.getParameter("selectr");
		String selecttext=request.getParameter("selecttext");
		
		
		//検索ボタンが選択された場合
			if(selecttext != null) {
		
			//メモフォームへフォワード
			if(loginUser == null) { //ログインしていない場合
				//リダイレクト
				response.sendRedirect("index.jsp");
			} else { //ログイン済みの場合					
				
				//入力された文字を検索してリストに入れる（DAO）
				MemosSelectDAO memoSelectDAO = new MemosSelectDAO();
				List<Memo> memoSelect =memoSelectDAO.selectt(selectr,selecttext,form);
				
				//セッションスコープに保存
				session.setAttribute("memoList", memoSelect);
				
				//メモ画面へフォワード
				dispatcher = request.getRequestDispatcher("WEB-INF/jsp/memo.jsp");
				dispatcher.forward(request, response);
			}
		}			
	
		// 昇順または降順ボタンの選択された値を取得
		String order=request.getParameter("order");
		// ラジオボタンの選択された値を取得
		String radio=request.getParameter("radio");
		
		//昇順または降順ボタンが選択された場合
		if(order != null) {				
		
			//メモフォームへフォワード
			if(loginUser == null) { //ログインしていない場合
				//リダイレクト
				response.sendRedirect("index.jsp");
			} else { //ログイン済みの場合
				
				//orderをもとに並べ替えたメモリスト取得して、セッションスコープに保存
				MemosOrderDAO memoOrderDAO=new MemosOrderDAO();
				List<Memo> memoOrder = memoOrderDAO.order(order,radio,form);
				//orderの値とradioの値をセッションスコープに保存
				session.setAttribute("memoList", memoOrder);
				session.setAttribute("radio", radio);
				
				//フォワード
				dispatcher = request.getRequestDispatcher("WEB-INF/jsp/memo.jsp");
				dispatcher.forward(request, response);
			}
		}
			
		// アカウント削除ボタンの選択された値を取得
		String accountD=request.getParameter("AccountD");
			
		//アカウント削除ボタンが選択された場合
		if(accountD != null) {							
			
			//メモフォームへフォワード
			if(loginUser == null) { //ログインしていない場合
				//リダイレクト
				response.sendRedirect("index.jsp");
			} else { //ログイン済みの場合
											
				//メモリストテーブル削除（MemosDAO）
				MemosDropDAO memosDropDAO = new MemosDropDAO();
				Boolean memos =memosDropDAO.drop(form);
				
				//アカウントDAOからアカウント一行削除（AccountDAO）
				AccountsUserDeleteDAO accountsUserDeleteDAO = new AccountsUserDeleteDAO();
				Boolean user =accountsUserDeleteDAO.userdelete(form);
	
				session.setAttribute("select", accountD);
	
				//メモ結果画面へフォワード
				dispatcher = request.getRequestDispatcher("WEB-INF/jsp/memoResult.jsp");
				dispatcher.forward(request, response);
				
				//セッションスコープを破棄
				session.invalidate();		
			}
		}
	}
}
		