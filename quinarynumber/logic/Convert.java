package logic;

/**
*
* @author yuu.s
* @version 2018/7/2
* */

public class Convert {
	/** 5進数変換チェック用配列 */
	public static final String[] quinaryWords = { "A", "B", "C", "D", "E" };

	// 受け取った文字列が正しいかを確認するメソッド
	public boolean inputCheck(String inputAddend) {

		// 文字数が1～10字以内であるか
		if (1 <= inputAddend.length() && inputAddend.length() <= 10) {
			// for文を使い、文字列がA～Eかを1文字ずつ確認する
			for (int i = 0; i < inputAddend.length(); i++) {
				int checkCnt = 0;
				for (String s : quinaryWords) {
					// A~Eいずれかと一致した場合、checkCntをインクリメント
					if (String.valueOf(inputAddend.charAt(i)).equals(s)) {
						checkCnt++;
					}
				}
				// checkCntが0（A～Eでない）の場合
				if (checkCnt == 0) {
					System.out.println("注意：A～Eの文字列を入力してください。");
					return false;
				}
			}
		} else {
			// 文字数が範囲外の場合
			System.out.println("文字数は1～10字以内で入力してください。");
			return false;
		}

		// 文字列が2文字以上、先頭がAから始まる場合
		if (2 <= inputAddend.length() && inputAddend.startsWith("A")) {
			// for文を使い、二文字目からの入力が正しいかを確認する
			for (int i = 1; i < inputAddend.length(); i++) {
				// B～Eの文字が入っているか
				for (int j = 1; j < quinaryWords.length; j++) {
					if (String.valueOf(inputAddend.charAt(i)).equals(quinaryWords[j])) {
						System.out.println("注意：0以外の数字を表現するとき、"
								+ "先頭にAを付けることはできません。");
						return false;
					}
				}
			}
		}

		// 入力が正しければtrueを返す
		return true;
	}

	// 文字列を5進数に変換するメソッド
	public String inputConvertQuinary(String inputAddend) {
		String fifthNo = "";

		// for文とswitch文を使い、1文字ずつ5進数に変換
		for (int j = 0; j < inputAddend.length(); j++) {
			switch (inputAddend.charAt(j)) {
			case 'A':
				fifthNo += '0';
				break;
			case 'B':
				fifthNo += '1';
				break;
			case 'C':
				fifthNo += '2';
				break;
			case 'D':
				fifthNo += '3';
				break;
			case 'E':
				fifthNo += '4';
				break;
			}
		}
		// 変換した数値を返す
		return fifthNo;
	}

	// 5進数を文字列に変換するメソッド
	public String convertStr(String amountFifthNum) {
		String str = "";
		for (int k = 0; k < amountFifthNum.length(); k++) {
			switch (amountFifthNum.charAt(k)) {
			case '0':
				str += 'A';
				break;
			case '1':
				str += 'B';
				break;
			case '2':
				str += 'C';
				break;
			case '3':
				str += 'D';
				break;
			case '4':
				str += 'E';
				break;
			}
		}
		// 変換した文字列を返す
		return str;
	}
}