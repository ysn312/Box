package main;

import java.util.Scanner;

import logic.StrNumConvert;

/**
 * @author yuu.s
 *
 * */
public class QuinaryNumber {
	/** 5進数変換用変数 */
	public static final int quinaryNum = 5;

	public static void main(String[] args) {
		/** 入力受け取り：文字列（A～E） */
		String inputAddend1 = null;
		String inputAddend2 = null;
		/** 文字列⇒5進数 格納用変数（String型で格納） */
		String addendNo1;
		String addendNo2;
		/** 変換処理用 */
		StrNumConvert convert = new StrNumConvert();

		Scanner sc = new Scanner(System.in);

		// 入力された文字を受け取る。
		inputAddend1 = sc.next();
		inputAddend2 = sc.next();

		// スキャナーをクローズ
		sc.close();

		// 入力が正しいか、ConvertクラスのinputCheck()メソッドを使って確認する
		if (convert.inputCheck(inputAddend1) && convert.inputCheck(inputAddend2)) {
			// 受け取った2つの文字列を5進数に変換
			addendNo1 = convert.inputConvertQuinary(inputAddend1);
			addendNo2 = convert.inputConvertQuinary(inputAddend2);
		} else {
			// 入力が不正の場合
			return;
		}

		// 5進数⇒10進数に変換し、合計を格納する
		int amountNum = Integer.parseInt(addendNo1, quinaryNum)
				+ Integer.parseInt(addendNo2, quinaryNum);
		// 10進数での合計を5進数に変換（String型で格納）
		String amountQuinaryNum = Integer.toString(amountNum, quinaryNum);

		// ConvertクラスのconvertStr()メソッドを使い、合計を文字列に変換する
		System.out.println(convert.convertStr(amountQuinaryNum));
	}
}
