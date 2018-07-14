package logic2;

/**
* @author yuu.s
*
* */
public class StrNumConvert2 {
	/** 文字列チェック用配列 */
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
		StringBuffer addendNum = new StringBuffer();

		// for文とswitch文を使い、1文字ずつ5進数に変換
		for (int j = 0; j < inputAddend.length(); j++) {
			switch (inputAddend.charAt(j)) {
			case 'A':
				addendNum.append('0');
				break;
			case 'B':
				addendNum.append('1');
				break;
			case 'C':
				addendNum.append('2');
				break;
			case 'D':
				addendNum.append('3');
				break;
			case 'E':
				addendNum.append('4');
				break;
			}
		}
		// 変換した数値を返す
		return String.valueOf(addendNum);
	}

	//5進数⇒10進数へ変換するメソッド
	public int convertDecimal(String addendNum, int quinaryNum) {
		//10進数に変換した数値格納用
		int convertNum = 0;

		//for文を使い、amountNumの末尾(j)から、5の累乗を掛けていく
		for (int i = 0, j = addendNum.length() - 1; i < addendNum.length(); i++, j--) {
			convertNum += Character.getNumericValue(addendNum.charAt(j))
					* Math.pow(quinaryNum, i);
		}
		return convertNum;
	}

	//10進数⇒5進数へ変換するメソッド
	public String sumConvertQuinary(int amountNum, int quinaryNum) {
		//5進数に変換した数値格納用
		StringBuilder sumQuinaryNum = new StringBuilder();

		//for文を使い、decimalNumに10進数に変換した数値を入れていく
		while (amountNum > quinaryNum) {
			//addNumをquinaryNumで割った余りをsumQuinaryNumに入れる
			sumQuinaryNum.append(amountNum % quinaryNum);
			amountNum /= quinaryNum;
		}
		//割り切れなくなった数値を最後に格納する
		sumQuinaryNum.append(amountNum);

		//decimalNumの文字列を逆さまにしてString型⇒int型に変換して戻す
		return String.valueOf(sumQuinaryNum.reverse());
	}

	// 5進数を文字列に変換するメソッド
	public String convertStr(String amountQuinaryNum) {
		StringBuilder str = new StringBuilder();
		for (int k = 0; k < amountQuinaryNum.length(); k++) {
			switch (amountQuinaryNum.charAt(k)) {
			case '0':
				str.append('A');
				break;
			case '1':
				str.append('B');
				break;
			case '2':
				str.append('C');
				break;
			case '3':
				str.append('D');
				break;
			case '4':
				str.append('E');
				break;
			}
		}
		// 変換した文字列を返す
		return String.valueOf(str);
	}
}