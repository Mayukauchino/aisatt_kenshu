
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Memo;

/**
 * メモ編集画面遷移クラス<br>
 * メモ編集画面に遷移を担当
 */
@WebServlet("/TranEditPageServlet")
public class TranEditPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**コンストラクタ<br>
	 * @see HttpServlet#HttpServlet()
	 */
	public TranEditPageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	/**
	 * 取得したメモIDを保持し、編集画面に移動するメソッド
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO
		String nextPage = null; //画面遷移変数
		int memoId = Integer.parseInt(request.getParameter("memo_id")); //メモのID取得
		Memo memo = null; //memo型変数
		MemoDao memoDao = null;	//DAO変数

		try {
			// メモが指定されている場合は詳細表示（更新）
			// メモに紐づく情報を検索してセット
			memoDao = new MemoDao();
			memo = memoDao.findMemo(memoId);
			request.setAttribute("memo", memo);

		} catch (MemoException e) {
			request.setAttribute("message", "メモ検索中にエラーが発生しました。");
			e.printStackTrace();
		}
		//次の遷移先設定
		nextPage  = "/WEB-INF/view/EditPage.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}

}
