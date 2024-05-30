
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * メモ登録画面遷移クラス<br>
 * メモ登録画面に遷移を担当
 */
@WebServlet("/TranRegiPageServlet")
public class TranRegiPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**コンストラクタ<br>
     * @see HttpServlet#HttpServlet()
     */
    public TranRegiPageServlet() {
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
	 * 新規登録画面に移動するメソッド
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nextPage = null; //画面遷移変
		//次の遷移先設定
		nextPage  = "/WEB-INF/view/RegisterPage.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}

}
