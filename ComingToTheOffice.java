import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ComingToTheOffice {

	public static void main(String[] args) throws Exception{

		Scanner sc = new Scanner(System.in);

		/**入力＞各区間の時間
		 * 家→A駅…a分
		 * A駅→B駅…b分
		 * B駅→会社…c分*/
		int a = 0;
		int b = 0;
		int c = 0;
		/**入力＞電車の本数*/
		int N = 0;

		/**エラーメッセージ用*/
		String message;
		/**電車時刻用リスト*/
		ArrayList<LocalTime> trainTimeList = new ArrayList<>();
		/**最遅で会社に着かないといけない時間(8:59)*/
		LocalTime timeLimit = LocalTime.of(8, 59);

		try {
		  //各区間の時間を受け取る
		  a = sc.nextInt();
		  b = sc.nextInt();
		  c = sc.nextInt();
		  //時間が1分未満か、30分を超えている場合はエラー
		if(a < 1 || a > 30 || b < 1 || b > 30 || c < 1 || c > 30) {
		  message = "注意:時間は1～30分以内にして下さい。";
		  System.out.println(message);
		  sc.close();
		  return;
		  }
		}catch(InputMismatchException e) {
		  //数値ではない場合
		  message = "注意:数値を入力してください。";
		  System.out.println(message);
		  sc.close();
		  return;
		}

		//電車の本数を受け取り、本数分の電車時刻を受け取る
		try {
		  N = sc.nextInt();
		  //本数は1～180まで。範囲外の場合はエラー
		  if(N < 1 || N > 180) {
		    message = "注意:本数は1～180本以内にして下さい。";
	        System.out.println(message);
	        sc.close();
	        return;
		  }
		}catch(InputMismatchException e) {
		  //数値ではない場合
		  message = "注意:数値を入力してください。";
		  System.out.println(message);
		  sc.close();
		  return;
		}

		//時間と分を受け取って時刻(LocalTime)に変換、ArrayListにセット
		try {
		  for(int i = 0; i < N; i++) {
		    int h_i = sc.nextInt();
		    int m_i = sc.nextInt();
		    LocalTime lt = LocalTime.of(h_i, m_i);
		    //電車時刻の範囲は6:00～8:59。範囲外はエラー
		    if(lt.isBefore(LocalTime.of(6, 0)) || lt.isAfter(timeLimit)) {
			  message = "注意:電車時刻の範囲は6:00～8:59以内にして下さい。";
			  System.out.println(message);
			  return;
		     }
		     trainTimeList.add(lt);
		  }
		}catch(InputMismatchException e) {
		  message = "注意:数値を入力してください。";
		  System.out.println(message);
		  return;
		}finally {
		  sc.close();
		}

		//LatestTrainTime変数を用意し、最遅電車時刻を格納する
		LocalTime latestTrainTime = LocalTime.of(0, 0);
		for(LocalTime t: trainTimeList) {
		  //最遅電車時刻 < 8:59 - (全区間の時間 - 1)
		  if(t.isBefore(timeLimit.minusMinutes(a + b + c - 1))) {
			if(t.isAfter(latestTrainTime)) {
			  latestTrainTime = t;
			}
		  }
		}

		//最遅電車時刻がちゃんと入っているか確認
		if(!latestTrainTime.equals(LocalTime.of(0, 0))) {
		/*最遅出社時刻を表示する。
		 * 最遅出社時刻 = 最遅電車時刻 - a分*/
		System.out.println("最遅出社時刻は、" + latestTrainTime.minusMinutes(a) + " です。");
		}else {
		  //どの電車時刻も該当しない場合（latestTrainTimeが0:00の状態）
		  System.out.println("残念ですが、8:59には間に合いません。");
		}
	}
}