import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ComingToTheOffice {
	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);

		/**�ύX�_:���L���ԁE�d�Ԗ{���̕ϐ������C�����܂����B
		 *
		 * ���́��e��Ԃ̏��L����
		 * �Ɓ�A�w�crequiredTimeA��
		 * A�w��B�w�crequiredTimeB��
		 * B�w����ЁcrequiredTimeC��
		 * */
		int requiredTimeA = 0;
		int requiredTimeB = 0;
		int requiredTimeC = 0;
		/**���́��d�Ԃ̖{��*/
		int trainNumber = 0;

		/**�G���[���b�Z�[�W�p*/
		String message;
		/**�d�Ԏ����p���X�g*/
		ArrayList<LocalTime> trainTimeList = new ArrayList<>();
		/**�Œx�ŉ�Ђɒ����Ȃ��Ƃ����Ȃ�����(8:59)*/
		LocalTime timeLimit = LocalTime.of(8, 59);

		try {
			//�e��Ԃ̎��Ԃ��󂯎��
			requiredTimeA = sc.nextInt();
			requiredTimeB = sc.nextInt();
			requiredTimeC = sc.nextInt();
			//���Ԃ�1���������A30���𒴂��Ă���ꍇ�̓G���[
			if (requiredTimeA < 1 || requiredTimeA > 30 ||
					requiredTimeB < 1 || requiredTimeB > 30 ||
						requiredTimeC < 1 || requiredTimeC > 30) {
				message = "����:���Ԃ�1�`30���ȓ��ɂ��ĉ������B";
				System.out.println(message);
				sc.close();
				return;
			}
		} catch (InputMismatchException e) {
			//���l�ł͂Ȃ��ꍇ
			message = "����:���l����͂��Ă��������B";
			System.out.println(message);
			sc.close();
			return;
		}

		//�d�Ԃ̖{�����󂯎��A�{�����̓d�Ԏ������󂯎��
		try {
			trainNumber = sc.nextInt();
			//�{����1�`180�܂ŁB�͈͊O�̏ꍇ�̓G���[
			if (trainNumber < 1 || trainNumber > 180) {
				message = "����:�{����1�`180�{�ȓ��ɂ��ĉ������B";
				System.out.println(message);
				sc.close();
				return;
			}
		} catch (InputMismatchException e) {
			//���l�ł͂Ȃ��ꍇ
			message = "����:���l����͂��Ă��������B";
			System.out.println(message);
			sc.close();
			return;
		}

		/**���Ԃƕ����󂯎���Ď���(LocalTime)�ɕϊ��AArrayList�ɃZ�b�g
		 * h_i �c �Z��
		 * m_i �c ������
		 * */
		try {
			for (int i = 0; i < trainNumber; i++) {
				int h_i = sc.nextInt();
				int m_i = sc.nextInt();
				LocalTime lt = LocalTime.of(h_i, m_i);
				//�d�Ԏ����͈̔͂�6:00�`8:59�B�͈͊O�̓G���[
				if (lt.isBefore(LocalTime.of(6, 0)) || lt.isAfter(timeLimit)) {
					message = "����:�d�Ԏ����͈̔͂�6:00�`8:59�ȓ��ɂ��ĉ������B";
					System.out.println(message);
					return;
				}
				trainTimeList.add(lt);
			}
		} catch (InputMismatchException e) {
			message = "����:���l����͂��Ă��������B";
			System.out.println(message);
			return;
		} finally {
			sc.close();
		}

		/**latestTrainTime�ϐ���p�ӂ��A�Œx�d�Ԏ������i�[����B
		 *�i�����ꍇ�A0:0�̂܂܁B�j*/
		LocalTime latestTrainTime = LocalTime.of(0, 0);
		for (LocalTime t : trainTimeList) {
			/**�ύX�_:�Œx�d�Ԏ������C�����܂����B
			 * �Œx�d�Ԏ��� < 8:59 - (requiredTimeB + requiredTimeC - 1)*/
			if (t.isBefore(timeLimit.minusMinutes(requiredTimeB + requiredTimeC - 1))) {
				if (t.isAfter(latestTrainTime)) {
					latestTrainTime = t;
				}
			}
		}

		/**�ύX�_:���������ύX���܂����B
		 *
		 * �m�F:�Œx�d�Ԏ����������Ɠ����Ă��鎖�B���A
		 *      8:59�܂łɊԂɍ��������m�F����B��  �Œx�d�Ԏ��� + �S��� -1 < 8:59 */
		if (!latestTrainTime.equals(LocalTime.of(0, 0)) &&
				latestTrainTime.plusMinutes(requiredTimeA + requiredTimeB + requiredTimeC - 1)
						.isBefore(timeLimit)) {
			/*�Œx�o�Ў�����\������B
			 * �Œx�o�Ў��� = �Œx�d�Ԏ��� - a��*/
			System.out.println("�Œx�o�Ў����́A" +
					latestTrainTime.minusMinutes(requiredTimeA) + " �ł��B");
		} else {
			//�ǂ̓d�Ԏ������Y�����Ȃ��ꍇ�ilatestTrainTime��0:00�̏�ԁj
			System.out.println("�c�O�ł����A8:59�ɂ͊Ԃɍ����܂���B");
		}
	}
}