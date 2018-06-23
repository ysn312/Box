import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ComingToTheOffice {

	public static void main(String[] args) throws Exception{

		Scanner sc = new Scanner(System.in);

		/**���́��e��Ԃ̎���
		 * �Ɓ�A�w�ca��
		 * A�w��B�w�cb��
		 * B�w����Ёcc��*/
		int a = 0;
		int b = 0;
		int c = 0;
		/**���́��d�Ԃ̖{��*/
		int N = 0;

		/**�G���[���b�Z�[�W�p*/
		String message;
		/**�d�Ԏ����p���X�g*/
		ArrayList<LocalTime> trainTimeList = new ArrayList<>();
		/**�Œx�ŉ�Ђɒ����Ȃ��Ƃ����Ȃ�����(8:59)*/
		LocalTime timeLimit = LocalTime.of(8, 59);

		try {
		  //�e��Ԃ̎��Ԃ��󂯎��
		  a = sc.nextInt();
		  b = sc.nextInt();
		  c = sc.nextInt();
		  //���Ԃ�1���������A30���𒴂��Ă���ꍇ�̓G���[
		if(a < 1 || a > 30 || b < 1 || b > 30 || c < 1 || c > 30) {
		  message = "����:���Ԃ�1�`30���ȓ��ɂ��ĉ������B";
		  System.out.println(message);
		  sc.close();
		  return;
		  }
		}catch(InputMismatchException e) {
		  //���l�ł͂Ȃ��ꍇ
		  message = "����:���l����͂��Ă��������B";
		  System.out.println(message);
		  sc.close();
		  return;
		}

		//�d�Ԃ̖{�����󂯎��A�{�����̓d�Ԏ������󂯎��
		try {
		  N = sc.nextInt();
		  //�{����1�`180�܂ŁB�͈͊O�̏ꍇ�̓G���[
		  if(N < 1 || N > 180) {
		    message = "����:�{����1�`180�{�ȓ��ɂ��ĉ������B";
	        System.out.println(message);
	        sc.close();
	        return;
		  }
		}catch(InputMismatchException e) {
		  //���l�ł͂Ȃ��ꍇ
		  message = "����:���l����͂��Ă��������B";
		  System.out.println(message);
		  sc.close();
		  return;
		}

		//���Ԃƕ����󂯎���Ď���(LocalTime)�ɕϊ��AArrayList�ɃZ�b�g
		try {
		  for(int i = 0; i < N; i++) {
		    int h_i = sc.nextInt();
		    int m_i = sc.nextInt();
		    LocalTime lt = LocalTime.of(h_i, m_i);
		    //�d�Ԏ����͈̔͂�6:00�`8:59�B�͈͊O�̓G���[
		    if(lt.isBefore(LocalTime.of(6, 0)) || lt.isAfter(timeLimit)) {
			  message = "����:�d�Ԏ����͈̔͂�6:00�`8:59�ȓ��ɂ��ĉ������B";
			  System.out.println(message);
			  return;
		     }
		     trainTimeList.add(lt);
		  }
		}catch(InputMismatchException e) {
		  message = "����:���l����͂��Ă��������B";
		  System.out.println(message);
		  return;
		}finally {
		  sc.close();
		}

		//LatestTrainTime�ϐ���p�ӂ��A�Œx�d�Ԏ������i�[����
		LocalTime latestTrainTime = LocalTime.of(0, 0);
		for(LocalTime t: trainTimeList) {
		  //�Œx�d�Ԏ��� < 8:59 - (�S��Ԃ̎��� - 1)
		  if(t.isBefore(timeLimit.minusMinutes(a + b + c - 1))) {
			if(t.isAfter(latestTrainTime)) {
			  latestTrainTime = t;
			}
		  }
		}

		//�Œx�d�Ԏ����������Ɠ����Ă��邩�m�F
		if(!latestTrainTime.equals(LocalTime.of(0, 0))) {
		/*�Œx�o�Ў�����\������B
		 * �Œx�o�Ў��� = �Œx�d�Ԏ��� - a��*/
		System.out.println("�Œx�o�Ў����́A" + latestTrainTime.minusMinutes(a) + " �ł��B");
		}else {
		  //�ǂ̓d�Ԏ������Y�����Ȃ��ꍇ�ilatestTrainTime��0:00�̏�ԁj
		  System.out.println("�c�O�ł����A8:59�ɂ͊Ԃɍ����܂���B");
		}
	}
}