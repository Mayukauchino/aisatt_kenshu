import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Memo;

/**
 * 初期リスト表示クラス<br>
 * メモ情報をJSPに渡す担当
 */
@WebServlet("/DispListServlet")
public class DispListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**コンストラクタ<br>
	 * @see HttpServlet#HttpServlet()
	 */
	public DispListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 今回はdoPostメソッドに処理を移譲するようにしています
		doPost(request, response);
	}

	/**
	 * DAOクラスからメモのリストを取得して送るメソッド
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nextPage = null; //画面遷移変数
		MemoDao memoDao  = null; //DAO変数
		List<Memo> memoList = null;//Memo LIST変数
		try {
			// メモ一覧の取得
			memoDao = new MemoDao();
			memoList = memoDao.findAllMemo();
			//メモのリストをセット
			request.setAttribute("memoList", memoList);

		} catch(MemoException e) {
			request.setAttribute("message", "メモリスト取得中にエラーが発生しました。");
			e.printStackTrace();
		}
		//次の遷移先設定
		nextPage = "/WEB-INF/view/ListPage.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}
}

