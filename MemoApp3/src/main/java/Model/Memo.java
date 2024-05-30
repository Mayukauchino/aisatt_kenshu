package Model;
/**
メモ情報クラス
 */
public class Memo {
	private int memoId; //メモタイトル
	private String memoTitl; //メモタイトル
	private String memoCont; //メモ内容
	private String regiDate; // 登録日
	private String modiDate; // 更新日
	private int deleFlag; // 削除フラグ

	/*
	 コンストラクタ
	 */
	public Memo(
int memoId,			String memoTitl, String memoCont,
			String regiDate, String modiDate,
			int deleFlag) {
		this.memoId = memoId;
		this.memoTitl = memoTitl;
		this.memoCont = memoCont;
		this.regiDate = regiDate;
		this.modiDate = modiDate;
		this.deleFlag = deleFlag;
	}

	/*
	 コンストラクタ
	 */
	public Memo() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/*
	メモIDを送る
	 */
	public int getMemoId() {
		return memoId;
	}

	/*
	メモIDをセット
	 */
	public void setMemoId(int memoId) {
		this.memoId = memoId;
	}

	/*
	メモタイトルを送る
	 */
	public String getMemoTitl() {
		return memoTitl;
	}

	/*
	メモタイトルをセット
	 */
	public void setMemoTitl(String MemoTitl) {
		this.memoTitl = MemoTitl;
	}

	/*
	メモ内容を送る
	 */
	public String getMemoCont() {
		return memoCont;
	}

	/*
	メモ内容をセット
	 */
	public void setMemoCont(String memoCont) {
		this.memoCont = memoCont;
	}

	/*
	メモ登録日を送る
	 */
	public String getRegiDate() {
		return regiDate;
	}
	
	/*
	メモ登録日をセット
	 */
	public void setRegiDate(String regiDate) {
		this.regiDate = regiDate;
	}

	/*
	メモ編集日を送る
	 */
	public String getEditDate() {
		return modiDate;
	}
	
	/*
	メモ編集日をセット
	 */
	public void setEditDate(String modiDate) {
		this.modiDate = modiDate;
	}
	
	/*
	メモ削除フラグを送る
	 */
	public int getDeleFlag() {
		return deleFlag;
	}

	/*
	メモ削除フラグをセット
	 */
	public void setDeleFlag(int deleFlag) {
		this.deleFlag = deleFlag;
	}
}