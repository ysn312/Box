package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author yuu.s
 * @version 1.1 2018/7/4
 * */

public class HateNumber {
	/** 例外用エラーメッセージ */
	public static final String ERROR_MSG = "エラー：数字以外、または入力が不正です。";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		/** 入力受け取り：嫌いな数字（String型） */
		String inputHateNum;
		/** 入力受け取り：部屋数 */
		int inputRoomCnt = 0;
		/** 入力受け取り：部屋番号 */
		int inputRoomNum = 0;
		/** 部屋番号を格納するリスト （String型で格納） */
		List<String> roomNumList = new ArrayList<>();

		/* 嫌いな数字を受け取る
		 * ※inputHateNum変数は下記filter()メソッドで使うため、
		 * 実質finalな変数の必要がある。
		 * */
		try {
			inputHateNum = sc.next();
			// 入力された数字が1~9の範囲外の場合、エラー
			if (Integer.parseInt(inputHateNum) < 0 || 9 < Integer.parseInt(inputHateNum)) {
				sc.close();
				System.out.println("注意：嫌いな数字は0～9以内で入力してください。");
				return;
			}
		} catch (NumberFormatException e) {
			// 数値でない場合
			sc.close();
			System.out.println(ERROR_MSG);
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

		// 部屋数が1~100の範囲外の場合、エラー
		if (inputRoomCnt < 0 || 100 < inputRoomCnt) {
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
		 * filter()メソッドでは、contains()メソッドの結果がtrue(嫌いな数字が入っていない)
		 * となる部屋番号のみ該当する。 */
		List<String> withoutHateNum = roomNumList.stream()
				.filter((s) -> !s.contains(inputHateNum)).collect(Collectors.toList());

		// 格納された部屋番号を入力順に出力する。
		if (!(withoutHateNum.isEmpty())) {
			withoutHateNum.stream().forEach(System.out::println);
		} else {
			// どの部屋番号も格納されなかった場合、"none"を出力する。
			System.out.println("none");
		}

	}
}
