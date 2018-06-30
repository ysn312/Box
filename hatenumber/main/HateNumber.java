package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author yuu.s
 *
 * */

public class HateNumber {
	/** 入力>嫌いな数字のインデックス */
	public static final int INDEX_HATE_NUMBER = 0;
	/**例外用エラーメッセージ*/
	public static final String ERROR_MSG = "エラー：数字以外、または入力が不正です。";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		/** 入力受け取り：嫌いな数字 */
		int inputHateNum = 0;
		/** 入力受け取り：部屋数 */
		int inputRoomCnt = 0;
		/** 入力受け取り：部屋番号 */
		int inputRoomNum = 0;
		/** 嫌いな数字と、部屋数を格納するリスト（String型で格納） */
		List<String> inputStrList = new ArrayList<>();
		/** 部屋番号を格納するリスト （String型で格納） */
		List<String> roomNumList = new ArrayList<>();

		// 嫌いな数字を受け取る
		try {
			String hateNum = sc.next();
			inputHateNum = Integer.parseInt(hateNum);
		} catch (NumberFormatException e) {
			// 数値でない場合
			sc.close();
			System.out.println(ERROR_MSG);
			return;
		}

		// 嫌いな数字をinputStrListに入れる。
		if (0 <= inputHateNum && inputHateNum <= 9) {
			inputStrList.add(String.valueOf(inputHateNum));
		} else {
			// 数字が1~9の範囲外の場合
			sc.close();
			System.out.println("注意：嫌いな数字は0～9以内で入力してください。");
			return;
		}

		// 病室の部屋数を受け取る
		try {
			String inputCnt = sc.next();
			inputRoomCnt = Integer.parseInt(inputCnt);
		} catch (NumberFormatException e) {
			// 数値でない場合
			sc.close();
			System.out.println(ERROR_MSG);
			return;
		}

		// 部屋数をinputStrListに入れる
		if (1<= inputRoomCnt && inputRoomCnt <= 100) {
			inputStrList.add(String.valueOf(inputRoomCnt));
		} else {
			// 部屋数が1~100の範囲外の場合
			sc.close();
			System.out.println("注意：部屋数は1~100以内で入力してください。");
			return;
		}

		// 受け取った部屋数の分、各部屋番号を受け取る
		for (int i = 0; i < inputRoomCnt; i++) {
			try {
				String roomNum = sc.next();
				inputRoomNum = Integer.valueOf(roomNum);
			} catch (NumberFormatException e) {
				// 数値でない場合
				sc.close();
				System.out.println(ERROR_MSG);
			}

			// 数値が1～1000以内だったら、部屋番号をリストに格納する
			if (1 <= inputRoomNum && inputRoomNum <= 1000) {
				roomNumList.add(String.valueOf(inputRoomNum));
			} else {
				// 範囲外の場合
				sc.close();
				System.out.println("注意：部屋番号は1～1000以内で入力してください。");
				return;
			}
		}
		// スキャナーをクローズ
		sc.close();

		/* stream()とfilter()とCollector()メソッドを使い、
		 * 嫌いな数字が入ってない部屋番号のみを、withoutHateNumに格納。
		 * filter()メソッドでは、indexOfメソッドの結果が-1(嫌いな数字が入っていない)となる部屋番号のみ該当する。 */
		List<String> withoutHateNum = roomNumList.stream()
				.filter(s -> s.indexOf(inputStrList.get(INDEX_HATE_NUMBER)) == -1).collect(Collectors.toList());

		// 格納された部屋番号を入力順に出力する。
		if (!(withoutHateNum.isEmpty())) {
			withoutHateNum.stream().forEach(System.out::println);
		} else {
			// どの部屋番号も格納されなかった場合、"none"を出力する。
			System.out.println("none");
		}

	}
}
