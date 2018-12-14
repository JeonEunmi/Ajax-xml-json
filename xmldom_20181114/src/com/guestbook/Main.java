package com.guestbook;

import java.util.Scanner;

//���� �޴� � Ŭ���� ����
public class Main {

	// main() �޼ҵ常 ����
	public static void main(String[] args) {

		// ���� �޴� ����
		// ->1. ���� �Է� 2. ���� ��� �� �˻� 3. ���� ���� 4.����
		Scanner sc = new Scanner(System.in);
		SubMain sub = new SubMain();

		while (true) {
			try {
				// ���� �޴� ����
				System.out.println("**���� v1.4**");
				System.out.println("1.���� �Է� 2.���� ��� �� �˻� 3. ���� ���� 4.����");
				System.out.print("����>");

				int m = sc.nextInt();
				sc.nextLine();

				if (m == 4 || m == 0) {

					sub.serializable();

					break;
				}

				// switch~case ������ �̿��� �޴� ����
				switch (m) {
				case 1:
					sub.guestbookAdd(sc);
					break;
				case 2:
					sub.guestbookList(sc);
					break;
				case 3:
					sub.guestbookRemove(sc);
					break;
				default:
					System.out.println("�߸��� �޴� �����Դϴ�.");
					break;
				}

			} catch (Exception e) {
				System.out.println("�߸��� �׼��Դϴ�.");
				sc.nextLine();
				// e.printStackTrace();
			}

		}

		sc.close();
		System.out.println("���α׷� ����.");

	}

}
