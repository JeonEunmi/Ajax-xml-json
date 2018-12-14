package com.guestbook;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

//메뉴별 액션 처리 클래스
public class SubMain {

	private GuestbookDAO dao = new GuestbookDAO();

	public SubMain() {
		// 역직렬화 메소드 호출
		this.dao.deSerialization();
	}

	// gid, name, pw, content, regDate, clientIP, blind
	// public method
	// 1.방명록 입력 메뉴 메소드
	public void guestbookAdd(Scanner sc) {

		System.out.print("이름>");
		String name_ = sc.nextLine();
		System.out.print("비밀번호(>");
		String pw = sc.nextLine();
		System.out.print("내용(200자 이내)>");
		String content = sc.nextLine();

		String gid = this.dao.autoGid();
		String regDate = LocalDate.now().toString();
		String ip = this.dao.ip();

		this.dao.guestbookAdd(gid, name_, pw, content, regDate, ip);

		System.out.println("한 개의 방명록이 추가되었습니다.");

	}

	// public method
	// 2.방명록 출력 및 검색 메뉴 메소드
	public void guestbookList(Scanner sc) {

		while (true) {

			// 서브 메뉴 구성
			System.out.println("**2.방명록 출력 및 검색 **");
			System.out.println("1.전체방명록 2.글번호 검색 3.글쓴이 검색 4.내용 검색 5.종료");
			System.out.print("선택>");

			// 적절한 예외처리 필요
			int m = sc.nextInt();
			sc.nextLine();

			if (m == 5 || m == 0)
				break;

			// switch~case 구문을 이용한 메뉴 선택
			switch (m) {
			// private method로 연결한다
			case 1:
				this.allGuestbook();
				break;
			case 2:
				this.gidSearch(sc);
				break;
			case 3:
				this.nameSearch(sc);
				break;
			case 4:
				this.contentSearch(sc);
				break;
			default:
				break;
			}

		}
	}

	// private method
	// 1.전체 방명록 메뉴 메소드
	private void allGuestbook() {

		List<Guestbook> list = new ArrayList<Guestbook>();
		// 검색 전용 메소드 호출
		// ->null 값 없는 사본 List 컬렉션 반환
		// ->출력 전용 메소드 호출
		list = this.dao.guestbookAllPrint();

		System.out.println("글번호 / 글쓴이 / 글내용 / 작성일");
		for (Guestbook g : list) {
			System.out.printf("%s / %s / %s / %s %n", g.getGid(), g.getName(), g.getContent(), g.getRegDate());
		}
		System.out.println();
	}

	// private method
	// 2.글번호 검색 메뉴 메소드
	private void gidSearch(Scanner sc) {

		System.out.print("글번호?>");
		String value = sc.nextLine();

		List<Guestbook> list = new ArrayList<Guestbook>();
		list = this.dao.guestbookSearch("gid", value);
		System.out.println("글번호 / 글쓴이 / 글내용 / 작성일");
		for (Guestbook g : list) {
			System.out.printf("%s / %s / %s / %s %n", g.getGid(), g.getName(), g.getContent(), g.getRegDate());
		}
		System.out.println();

	}

	// private method
	// 3.이름 검색 메뉴 메소드
	private void nameSearch(Scanner sc) {

		System.out.print("이름?>");
		String value = sc.nextLine();

		List<Guestbook> list = new ArrayList<Guestbook>();
		list = this.dao.guestbookSearch("name_", value);
		System.out.println("글번호 / 글쓴이 / 글내용 / 작성일");
		for (Guestbook g : list) {
			System.out.printf("%s / %s / %s / %s %n", g.getGid(), g.getName(), g.getContent(), g.getRegDate());
		}
		System.out.println();

	}

	// private method
	// 4.글내용 검색 메뉴 메소드
	private void contentSearch(Scanner sc) {
		System.out.print("특정 내용?>");
		String value = sc.nextLine();

		List<Guestbook> list = new ArrayList<Guestbook>();
		list = this.dao.guestbookSearch("content", value);
		System.out.println("글번호 / 글쓴이 / 글내용 / 작성일");
		for (Guestbook g : list) {
			System.out.printf("%s / %s / %s / %s %n", g.getGid(), g.getName(), g.getContent(), g.getRegDate());
		}
		System.out.println();
	}

	public void guestbookRemove(Scanner sc) {
		// 콘솔 액션 필요
		// 서브 메뉴 구성
		// ->1.특정번호일정 2.특정일일정 3.특정월일정 4.특정단어포함일정 5.전체일정 6.종료
		while (true) {

			// 서브 메뉴 구성
			System.out.println("**3.일정 삭제**");
			System.out.println("1.특정번호일정 2.특정일일정 3.특정월일정 " + "4.특정단어포함일정 5.전체일정 6.종료");
			System.out.print("선택>");

			// 적절한 예외처리 필요
			int m = sc.nextInt();
			sc.nextLine();

			if (m == 6 || m == 0)
				break;

			// switch~case 구문을 이용한 메뉴 선택
			/*
			 * switch (m) { // private method로 연결한다 case 1: this.scheduleRemoveSid(sc);
			 * break; case 2: this.scheduleRemoveDate(sc); break; case 3:
			 * this.scheduleRemoveMonth(sc); break; case 4: this.scheduleRemoveContent(sc);
			 * break; case 5: this.scheduleRemoveAll(sc); break; default: break; }
			 */

		}
	}

	/*
	 * private void scheduleRemoveSid(Scanner sc) { // 1. 특정 조건 확인 // 2. 특정 조건을 만족하는
	 * 일정을 검색하고 출력 // 3. 삭제 여부 확인 // 4. 특정 조건을 만족하는 일정에 대한 삭제 진행 -> scheduleRemove()
	 * 메소드 System.out.print("번호>"); String sid = sc.nextLine();
	 * this.print(this.slist.scheduleSearch("sid", sid));
	 * 
	 * // 삭제 여부 확인 + 삭제 진행 메소드 this.scheduleRemoveAction(sc, "sid", sid);
	 * 
	 * }
	 * 
	 * private void scheduleRemoveDate(Scanner sc) {
	 * System.out.print("특정 일(YYYY-MM-DD)>"); String date = sc.nextLine();
	 * this.print(this.slist.scheduleSearch("date", date));
	 * 
	 * // 삭제 여부 확인 + 삭제 진행 메소드 this.scheduleRemoveAction(sc, "date", date); }
	 * 
	 * private void scheduleRemoveMonth(Scanner sc) {
	 * System.out.print("특정 월(YYYY-MM)>"); String month = sc.nextLine();
	 * this.print(this.slist.scheduleSearch("month", month));
	 * 
	 * // 삭제 여부 확인 + 삭제 진행 메소드 this.scheduleRemoveAction(sc, "month", month); }
	 * 
	 * private void scheduleRemoveContent(Scanner sc) { System.out.print("특정 단어>");
	 * String content = sc.nextLine();
	 * this.print(this.slist.scheduleSearch("content", content));
	 * 
	 * // 삭제 여부 확인 + 삭제 진행 메소드 this.scheduleRemoveAction(sc, "content", content); }
	 * 
	 * private void scheduleRemoveAction(Scanner sc, String key, String value) {
	 * System.out.print("선택>"); int m = sc.nextInt(); sc.nextLine(); if (m == 1) {
	 * // 삭제 진행 int count = this.slist.scheduleRemove(key, value);
	 * System.out.printf("%d건의 일정이 삭제되었습니다.%n", count); } else { // 삭제 취소
	 * System.out.println("일정 삭제가 취소되었습니다."); } }
	 * 
	 * private void scheduleRemoveAll(Scanner sc) { // 1. 삭제 여부 확인 // 2. 전체 일정 삭제 진행
	 * -> scheduleRemoveAll() 메소드 System.out.println("전체 일정을 삭제할 예정입니다.");
	 * 
	 * System.out.print("삭제>"); int m = sc.nextInt(); sc.nextLine();
	 * 
	 * if (m == 1) { // 삭제 진행 this.slist.scheduleRemoveAll();
	 * System.out.println("전체 일정이 삭제되었습니다."); } else { // 삭제 취소
	 * System.out.println("일정 삭제를 취소했습니다."); } }
	 */
	// 직렬화 메소드 추가
	public void serializable() {
		this.dao.serialization();
	}
}
