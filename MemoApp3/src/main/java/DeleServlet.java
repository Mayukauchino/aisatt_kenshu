
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *メモ削除クラス<br>
 * メモ削除の要求を担当
 */
@WebServlet("/DeleServlet")
public class DeleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**コンストラクタ<br>
     * @see HttpServlet#HttpServlet()
     */
    public DeleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * 取得したメモIDからDAOクラスにメモを削除を要求するメソッド
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nextPage = null;	//画面遷移変数
		int memoId = 0;	//メモID変数
		MemoDao memoDao = null;	//DAO変数
		request.setCharacterEncoding("UTF-8"); 
		memoId = Integer.parseInt(request.getParameter("memo_id")); //メモのID取得
		
		try {
			// メモテーブルを検索
			// メモIDがある場合は削除、ない場合エラー				
			// DAOクラスの削除メソッドにメモIDをを格納
			memoDao = new MemoDao();	
			memoDao.deleMemo(memoId);

		} catch (MemoException e) {
			request.setAttribute("message", "エラーが発生しました。");
			e.printStackTrace();
		}
		
		//次の遷移先設定
 		nextPage = "./DispListServlet";
	  	RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
    	requestDispatcher.forward(request, response);
	}

}
