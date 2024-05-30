import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Model.Memo;

/**
 * DAOクラス<br>
 * DBとのやり取りを担当
 */
public class MemoDao {
	protected Connection con = null;
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	//日付取得変数
	Date now = new Date();					//Date型インスタンス生成
	String nowStr = dateFormat.format(now); //yyyy-MM-ddの文字列に変換

	/**
	 * コンストラクタ<br>
	 * 初期処理としてDBに接続します
	 * @throws MemoException DB接続に失敗した際に発生します
	 */
	public MemoDao() throws MemoException {
		// DBに接続
		getConnection();
	}

	/**	 * DB接続処理
	 * @throws MemoException DB接続に失敗した際に発生します
	 */
	private void getConnection() throws MemoException {
		try {
			if (con == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				// （注意）接続するDB名、ユーザ名、パスワードはご自身の環境に合わせて設定してください！
				String url = "jdbc:mysql://localhost:3306/mydb1?serverTimezone=JST";
				String user = "root";
				String password = "1998Mayu";
				// DB接続
				con = DriverManager.getConnection(url, user, password);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new MemoException("JDBCドライバが見つかりません");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MemoException("SQL実行中に例外が発生しました");
		}
	}


	/**
	 * メモテーブルに登録されているすべてのメモ情報を検索します<br>
	 * @return メモ情報の入ったリスト
	 * @throws MemoException 検索失敗の際に発生します
	 **/
	public List<Memo> findAllMemo() throws MemoException {
		// メモ情報のリスト
		Memo memo = null; //メモ型変数
		String sql = null; //SQL文変数
		List<Memo> memoList = new ArrayList<>(); //Memo型リスト変数
		try {
			// SQL文
			sql = "SELECT * FROM memo";

			// 検索の実行
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// 検索結果からID、タイトル、内容、削除フラグを取得してリストに格納
			while (rs.next()) {
				memo = new Memo();
				memo.setMemoId(rs.getInt("id"));
				memo.setMemoTitl(rs.getString("title"));
				memo.setMemoCont(rs.getString("content"));
				memo.setDeleFlag(rs.getInt("del_flg"));
				memoList.add(memo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MemoException("メモの取得に失敗しました");
		}

		// メモ情報のリストを返却
		return memoList;
	}

	/**
	 * メモテーブルで該当するメモを検索<br>
	 * @param memoId 表示対象とするメモのID
	 * @throws MemoException 検索失敗の際に発生します
	 **/
	public Memo findMemo(int findMemoId) throws MemoException {
		// メモ情報
		Memo memo = null; //メモ型変数
		String sql = null; //SQL文変数
		int memoId = 0; //メモID変数
		String memoTitle = null; //メモtitle変数
		String memoContent = null; //メモcontent変数
		String memoInsDt = null; //メモ登録日変数
		String memoUpdDt = null; //メモ更新日変数
		int memoDelFlg = 0; //メモdeleteFlag変数
		try {
			// SQL文
			sql = "SELECT * FROM memo WHERE id = ?";

			// SQL文にメモIDをセットして検索を実行
			ps = con.prepareStatement(sql);
			ps.setInt(1, findMemoId);
			rs = ps.executeQuery();

			// 検索結果からメモ情報を作成
			while (rs.next()) {
				memoId = rs.getInt("id");
				memoTitle = rs.getString("title");
				memoContent = rs.getString("content");
				memoInsDt = rs.getString("ins_dt");
				memoUpdDt = rs.getString("upd_dt");
				memoDelFlg = rs.getInt("del_flg");
				memo = new Memo(memoId, memoTitle, memoContent, memoInsDt, memoUpdDt, memoDelFlg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MemoException("指定されたメモ情報の取得に失敗しました");
		}
		// メモ情報を返却
		return memo;
	}

	/**
	 * DBにメモを新規追加します<br>
	 * @param memoTitl 追加対象とするメモのタイトル
	 * @param memoCont 追加対象とするメモの内容
	 * @throws MemoException メモ情報の登録に失敗した際に発生します
	 */
	public void insertMemo(String memoTitl, String memoCont) throws MemoException {
		// DBに登録するタイトルと内容値を取得
		String regiMemoTitl = memoTitl; //メモtitle変数
		String regiMemoCont = memoCont; //メモcontent変数
		int deleFlag = 0; //メモdeleteFlag変数
		String sql = null; //SQL文変数
		try {
			//メモテーブルへの追加
			sql = "INSERT INTO memo(id, title, content, ins_dt, upd_dt, del_flg) VALUE(default, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, regiMemoTitl);
			ps.setString(2, regiMemoCont);
			ps.setString(3, nowStr);
			ps.setString(4, nowStr);
			ps.setInt(5, deleFlag);
			System.out.println(ps);
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			throw new MemoException("メモ情報の登録に失敗しました");
		}
	}

	/**
	 * 指定されたメモ情報を更新します<br>
	 * 今回は更新対象はタイトルと内容
	 * @param memoId 更新対象とするメモのID
	 * @param memoTitl 更新対象とするメモのタイトル
	 * @param memoCont 更新対象とするメモの内容
	 * @throws MemoException メモ情報の更新に失敗した際に発生します
	 */
	public void editMemo(int memoId, String memoTitl, String memoCont) throws MemoException {
		// ID、タイトル、内容を取得
		int editMemoId = memoId; //メモID変数
		String editMemoTitl = memoTitl; //メモtitle変数
		String editMemoCont = memoCont; //メモcontent変数
		String sql = null; //SQL文変数
		try {
			// 更新処理、タイトル、内容、更新日
			sql = "UPDATE memo SET title = ?, content = ?, upd_dt = ? WHERE id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, editMemoTitl);
			ps.setString(2, editMemoCont);
			ps.setString(3, nowStr);
			ps.setInt(4, editMemoId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MemoException("メモの更新に失敗しました");
		}
	}

	/**
	 * 指定されたメモ情報を削除します<br>
	 * @param memoId 削除対象とするメモのID
	 * @throws MemoException メモ情報の削除に失敗した際に発生します
	 */
	public void deleMemo(int memoId) throws MemoException {
		// IDを取得
		int deleMemoId = memoId; //メモID変数
		String sql = null; //SQL文変数
		try {
			// 削除処理
			sql = "UPDATE memo SET del_flg = 1 WHERE id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, deleMemoId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MemoException("メモの削除に失敗しました");
		}
	}
}