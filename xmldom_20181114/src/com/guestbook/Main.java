package com.guestbook;

import java.util.Scanner;

//메인 메뉴 운영 클래스 선언
public class Main {

	// main() 메소드만 구성
	public static void main(String[] args) {

		// 메인 메뉴 구성
		// ->1. 일정 입력 2. 일정 출력 및 검색 3. 일정 삭제 4.종료
		Scanner sc = new Scanner(System.in);
		SubMain sub = new SubMain();

		while (true) {
			try {
				// 메인 메뉴 구성
				System.out.println("**방명록 v1.4**");
				System.out.println("1.방명록 입력 2.방명록 출력 및 검색 3. 방명록 삭제 4.종료");
				System.out.print("선택>");

				int m = sc.nextInt();
				sc.nextLine();

				if (m == 4 || m == 0) {

					sub.serializable();

					break;
				}

				// switch~case 구문을 이용한 메뉴 선택
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
					System.out.println("잘못된 메뉴 선택입니다.");
					break;
				}

			} catch (Exception e) {
				System.out.println("잘못된 액션입니다.");
				sc.nextLine();
				// e.printStackTrace();
			}

		}

		sc.close();
		System.out.println("프로그램 종료.");

	}

}
