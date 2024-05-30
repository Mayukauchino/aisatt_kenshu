
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Memo;

/**
 * メモ詳細表示クラス<br>
 * メモ詳細を取得して、JSPに渡す担当
 */
@WebServlet("/TranDetaPageServlet")
public class TranDetaPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**コンストラクタ<br>
	 * @see HttpServlet#HttpServlet()
	 */
	public TranDetaPageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 取得したメモIDからDAOクラスにメモの情報を要求し、詳細画面に移動するメソッド
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 詳細画面に表示するメモIDをリクエストパラメータから取得
		String nextPage = null; //画面遷移変数
		int memoId = Integer.parseInt(request.getParameter("memo_id")); //メモのID取得
		Memo memo = null; //Memo型変数
		MemoDao memoDao = null;	//DAO変数

		try { 
			// メモが指定されている場合は詳細表示（更新）
			// メモに紐づく情報を検索してセット
			memoDao = new MemoDao();
			memo = memoDao.findMemo(memoId);
			// 次の画面に情報をセット
			request.setAttribute("memo", memo);
		} catch (MemoException e) {
			request.setAttribute("message", "エラーが発生しました。");
			e.printStackTrace();
		}
		//次の遷移先設定
		nextPage = "/WEB-INF/view/DetailPage.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
