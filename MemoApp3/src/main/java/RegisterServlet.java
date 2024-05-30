
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *メモ登録クラス<br>
 *メモ登録の要求を担当
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**コンストラクタ<br>
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
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
	 * 取得したメモID、内容、タイトルからDAOクラスにメモの新しく登録を要求するメソッド
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//次の画面
		String nextPage = null; //画面遷移変数
		MemoDao memoDao = null;	//DAO変数
		String memoTitl = null;	//メモタイトル変数
		String memoCont = null;	//メモ内容変数
		final int TITL_LENGTH_CHECK = 50; //タイトル文字数チェック定数　
		final int CONT_LENGTH_CHECK = 1000; //内容文字数チェック定数　

		try {
			// 画面に表示（入力）されたメモを取得
			request.setCharacterEncoding("UTF-8");
			memoTitl = request.getParameter("memo_titl").replace("&", "&amp;").replace("\"", "&quot;").replace("<", "&lt;").replace(">", "&gt;").replace("'", "&#39;");
			memoCont = request.getParameter("memo_cont").replace("&", "&amp;").replace("\"", "&quot;").replace("<", "&lt;").replace(">", "&gt;").replace("'", "&#39;");

			if(memoTitl.length() < TITL_LENGTH_CHECK && memoTitl.length() < CONT_LENGTH_CHECK) {
				// メモIDがある場合は更新、無い場合（戻り値が0の場合）は新規登録
				memoDao = new MemoDao();			
				memoDao.insertMemo(memoTitl, memoCont);	
			}else{
				//文字数がオーバーした場合のメッセージをセット
				request.setAttribute("message", "文字数がオーバーしました。");
			}
		} catch (MemoException e) {
			request.setAttribute("message", "メモ登録中にエラーが発生しました。");
			e.printStackTrace();
		}
		//次の遷移先設定
		nextPage = "./DispListServlet";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

}
