package main2;

import java.util.Scanner;

import logic2.StrNumConvert2;

/**
 * @author yuu.s
 *
 * */
public class QuinaryNumber2 {
	/** 5進数変換用変数 */
	public static final int quinaryNum = 5;

	public static void main(String[] args) {
		/** 入力受け取り：文字列（A～E） */
		String inputAddend1 = null;
		String inputAddend2 = null;
		/** 文字列⇒5進数 格納用変数（String型で格納） */
		String addendNum1;
		String addendNum2;
		/** 変換処理用 */
		StrNumConvert2 convert2 = new StrNumConvert2();

		Scanner sc = new Scanner(System.in);

		// 入力された文字を受け取る。
		inputAddend1 = sc.next();
		inputAddend2 = sc.next();

		// スキャナーをクローズ
		sc.close();

		// 入力が正しいか、convertインスタンスのinputCheck()メソッドを使って確認する
		if (convert2.inputCheck(inputAddend1) && convert2.inputCheck(inputAddend2)) {
			// 受け取った2つの文字列を5進数に変換
			addendNum1 = convert2.inputConvertQuinary(inputAddend1);
			addendNum2 = convert2.inputConvertQuinary(inputAddend2);
		} else {
			// 入力が不正の場合
			return;
		}

		/* convert2インスタンスのconvertDecimal()メソッドを使い、
		 * 5進数⇒10進数に変換し、その合計を格納する*/
		int amountNum = convert2.convertDecimal(addendNum1, quinaryNum)
				+ convert2.convertDecimal(addendNum2, quinaryNum);

		/* convert2インスタンスのsumConvertQuinary()メソッドを使い、
		 * 10進数での合計を5進数に変換*/
		String amountQuinaryNum = convert2.sumConvertQuinary(amountNum, quinaryNum);

		// convertインスタンスのconvertStr()メソッドを使い、合計を文字列に変換する
		System.out.println(convert2.convertStr(amountQuinaryNum));
	}
}
