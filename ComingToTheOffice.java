import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ComingToTheOffice {
	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);

		/**変更点:所有時間・電車本数の変数名を修正しました。
		 *
		 * 入力＞各区間の所有時間
		 * 家→A駅…requiredTimeA分
		 * A駅→B駅…requiredTimeB分
		 * B駅→会社…requiredTimeC分
		 * */
		int requiredTimeA = 0;
		int requiredTimeB = 0;
		int requiredTimeC = 0;
		/**入力＞電車の本数*/
		int trainNumber = 0;

		/**エラーメッセージ用*/
		String message;
		/**電車時刻用リスト*/
		ArrayList<LocalTime> trainTimeList = new ArrayList<>();
		/**最遅で会社に着かないといけない時間(8:59)*/
		LocalTime timeLimit = LocalTime.of(8, 59);

		try {
			//各区間の時間を受け取る
			requiredTimeA = sc.nextInt();
			requiredTimeB = sc.nextInt();
			requiredTimeC = sc.nextInt();
			//時間が1分未満か、30分を超えている場合はエラー
			if (requiredTimeA < 1 || requiredTimeA > 30 ||
					requiredTimeB < 1 || requiredTimeB > 30 ||
						requiredTimeC < 1 || requiredTimeC > 30) {
				message = "注意:時間は1～30分以内にして下さい。";
				System.out.println(message);
				sc.close();
				return;
			}
		} catch (InputMismatchException e) {
			//数値ではない場合
			message = "注意:数値を入力してください。";
			System.out.println(message);
			sc.close();
			return;
		}

		//電車の本数を受け取り、本数分の電車時刻を受け取る
		try {
			trainNumber = sc.nextInt();
			//本数は1～180まで。範囲外の場合はエラー
			if (trainNumber < 1 || trainNumber > 180) {
				message = "注意:本数は1～180本以内にして下さい。";
				System.out.println(message);
				sc.close();
				return;
			}
		} catch (InputMismatchException e) {
			//数値ではない場合
			message = "注意:数値を入力してください。";
			System.out.println(message);
			sc.close();
			return;
		}

		/**時間と分を受け取って時刻(LocalTime)に変換、ArrayListにセット
		 * h_i … 〇時
		 * m_i … ○○分
		 * */
		try {
			for (int i = 0; i < trainNumber; i++) {
				int h_i = sc.nextInt();
				int m_i = sc.nextInt();
				LocalTime lt = LocalTime.of(h_i, m_i);
				//電車時刻の範囲は6:00～8:59。範囲外はエラー
				if (lt.isBefore(LocalTime.of(6, 0)) || lt.isAfter(timeLimit)) {
					message = "注意:電車時刻の範囲は6:00～8:59以内にして下さい。";
					System.out.println(message);
					return;
				}
				trainTimeList.add(lt);
			}
		} catch (InputMismatchException e) {
			message = "注意:数値を入力してください。";
			System.out.println(message);
			return;
		} finally {
			sc.close();
		}

		/**latestTrainTime変数を用意し、最遅電車時刻を格納する。
		 *（無い場合、0:0のまま。）*/
		LocalTime latestTrainTime = LocalTime.of(0, 0);
		for (LocalTime t : trainTimeList) {
			/**変更点:最遅電車時刻を修正しました。
			 * 最遅電車時刻 < 8:59 - (requiredTimeB + requiredTimeC - 1)*/
			if (t.isBefore(timeLimit.minusMinutes(requiredTimeB + requiredTimeC - 1))) {
				if (t.isAfter(latestTrainTime)) {
					latestTrainTime = t;
				}
			}
		}

		/**変更点:条件分岐を変更しました。
		 *
		 * 確認:最遅電車時刻がちゃんと入っている事。かつ、
		 *      8:59までに間に合うかを確認する。→  最遅電車時刻 + 全区間 -1 < 8:59 */
		if (!latestTrainTime.equals(LocalTime.of(0, 0)) &&
				latestTrainTime.plusMinutes(requiredTimeA + requiredTimeB + requiredTimeC - 1)
						.isBefore(timeLimit)) {
			/*最遅出社時刻を表示する。
			 * 最遅出社時刻 = 最遅電車時刻 - a分*/
			System.out.println("最遅出社時刻は、" +
					latestTrainTime.minusMinutes(requiredTimeA) + " です。");
		} else {
			//どの電車時刻も該当しない場合（latestTrainTimeが0:00の状態）
			System.out.println("残念ですが、8:59には間に合いません。");
		}
	}
}