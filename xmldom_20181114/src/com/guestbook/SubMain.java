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

//�޴��� �׼� ó�� Ŭ����
public class SubMain {

	private GuestbookDAO dao = new GuestbookDAO();

	public SubMain() {
		// ������ȭ �޼ҵ� ȣ��
		this.dao.deSerialization();
	}

	// gid, name, pw, content, regDate, clientIP, blind
	// public method
	// 1.���� �Է� �޴� �޼ҵ�
	public void guestbookAdd(Scanner sc) {

		System.out.print("�̸�>");
		String name_ = sc.nextLine();
		System.out.print("��й�ȣ(>");
		String pw = sc.nextLine();
		System.out.print("����(200�� �̳�)>");
		String content = sc.nextLine();

		String gid = this.dao.autoGid();
		String regDate = LocalDate.now().toString();
		String ip = this.dao.ip();

		this.dao.guestbookAdd(gid, name_, pw, content, regDate, ip);

		System.out.println("�� ���� ������ �߰��Ǿ����ϴ�.");

	}

	// public method
	// 2.���� ��� �� �˻� �޴� �޼ҵ�
	public void guestbookList(Scanner sc) {

		while (true) {

			// ���� �޴� ����
			System.out.println("**2.���� ��� �� �˻� **");
			System.out.println("1.��ü���� 2.�۹�ȣ �˻� 3.�۾��� �˻� 4.���� �˻� 5.����");
			System.out.print("����>");

			// ������ ����ó�� �ʿ�
			int m = sc.nextInt();
			sc.nextLine();

			if (m == 5 || m == 0)
				break;

			// switch~case ������ �̿��� �޴� ����
			switch (m) {
			// private method�� �����Ѵ�
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
	// 1.��ü ���� �޴� �޼ҵ�
	private void allGuestbook() {

		List<Guestbook> list = new ArrayList<Guestbook>();
		// �˻� ���� �޼ҵ� ȣ��
		// ->null �� ���� �纻 List �÷��� ��ȯ
		// ->��� ���� �޼ҵ� ȣ��
		list = this.dao.guestbookAllPrint();

		System.out.println("�۹�ȣ / �۾��� / �۳��� / �ۼ���");
		for (Guestbook g : list) {
			System.out.printf("%s / %s / %s / %s %n", g.getGid(), g.getName(), g.getContent(), g.getRegDate());
		}
		System.out.println();
	}

	// private method
	// 2.�۹�ȣ �˻� �޴� �޼ҵ�
	private void gidSearch(Scanner sc) {

		System.out.print("�۹�ȣ?>");
		String value = sc.nextLine();

		List<Guestbook> list = new ArrayList<Guestbook>();
		list = this.dao.guestbookSearch("gid", value);
		System.out.println("�۹�ȣ / �۾��� / �۳��� / �ۼ���");
		for (Guestbook g : list) {
			System.out.printf("%s / %s / %s / %s %n", g.getGid(), g.getName(), g.getContent(), g.getRegDate());
		}
		System.out.println();

	}

	// private method
	// 3.�̸� �˻� �޴� �޼ҵ�
	private void nameSearch(Scanner sc) {

		System.out.print("�̸�?>");
		String value = sc.nextLine();

		List<Guestbook> list = new ArrayList<Guestbook>();
		list = this.dao.guestbookSearch("name_", value);
		System.out.println("�۹�ȣ / �۾��� / �۳��� / �ۼ���");
		for (Guestbook g : list) {
			System.out.printf("%s / %s / %s / %s %n", g.getGid(), g.getName(), g.getContent(), g.getRegDate());
		}
		System.out.println();

	}

	// private method
	// 4.�۳��� �˻� �޴� �޼ҵ�
	private void contentSearch(Scanner sc) {
		System.out.print("Ư�� ����?>");
		String value = sc.nextLine();

		List<Guestbook> list = new ArrayList<Guestbook>();
		list = this.dao.guestbookSearch("content", value);
		System.out.println("�۹�ȣ / �۾��� / �۳��� / �ۼ���");
		for (Guestbook g : list) {
			System.out.printf("%s / %s / %s / %s %n", g.getGid(), g.getName(), g.getContent(), g.getRegDate());
		}
		System.out.println();
	}

	public void guestbookRemove(Scanner sc) {
		// �ܼ� �׼� �ʿ�
		// ���� �޴� ����
		// ->1.Ư����ȣ���� 2.Ư�������� 3.Ư�������� 4.Ư���ܾ��������� 5.��ü���� 6.����
		while (true) {

			// ���� �޴� ����
			System.out.println("**3.���� ����**");
			System.out.println("1.Ư����ȣ���� 2.Ư�������� 3.Ư�������� " + "4.Ư���ܾ��������� 5.��ü���� 6.����");
			System.out.print("����>");

			// ������ ����ó�� �ʿ�
			int m = sc.nextInt();
			sc.nextLine();

			if (m == 6 || m == 0)
				break;

			// switch~case ������ �̿��� �޴� ����
			/*
			 * switch (m) { // private method�� �����Ѵ� case 1: this.scheduleRemoveSid(sc);
			 * break; case 2: this.scheduleRemoveDate(sc); break; case 3:
			 * this.scheduleRemoveMonth(sc); break; case 4: this.scheduleRemoveContent(sc);
			 * break; case 5: this.scheduleRemoveAll(sc); break; default: break; }
			 */

		}
	}

	/*
	 * private void scheduleRemoveSid(Scanner sc) { // 1. Ư�� ���� Ȯ�� // 2. Ư�� ������ �����ϴ�
	 * ������ �˻��ϰ� ��� // 3. ���� ���� Ȯ�� // 4. Ư�� ������ �����ϴ� ������ ���� ���� ���� -> scheduleRemove()
	 * �޼ҵ� System.out.print("��ȣ>"); String sid = sc.nextLine();
	 * this.print(this.slist.scheduleSearch("sid", sid));
	 * 
	 * // ���� ���� Ȯ�� + ���� ���� �޼ҵ� this.scheduleRemoveAction(sc, "sid", sid);
	 * 
	 * }
	 * 
	 * private void scheduleRemoveDate(Scanner sc) {
	 * System.out.print("Ư�� ��(YYYY-MM-DD)>"); String date = sc.nextLine();
	 * this.print(this.slist.scheduleSearch("date", date));
	 * 
	 * // ���� ���� Ȯ�� + ���� ���� �޼ҵ� this.scheduleRemoveAction(sc, "date", date); }
	 * 
	 * private void scheduleRemoveMonth(Scanner sc) {
	 * System.out.print("Ư�� ��(YYYY-MM)>"); String month = sc.nextLine();
	 * this.print(this.slist.scheduleSearch("month", month));
	 * 
	 * // ���� ���� Ȯ�� + ���� ���� �޼ҵ� this.scheduleRemoveAction(sc, "month", month); }
	 * 
	 * private void scheduleRemoveContent(Scanner sc) { System.out.print("Ư�� �ܾ�>");
	 * String content = sc.nextLine();
	 * this.print(this.slist.scheduleSearch("content", content));
	 * 
	 * // ���� ���� Ȯ�� + ���� ���� �޼ҵ� this.scheduleRemoveAction(sc, "content", content); }
	 * 
	 * private void scheduleRemoveAction(Scanner sc, String key, String value) {
	 * System.out.print("����>"); int m = sc.nextInt(); sc.nextLine(); if (m == 1) {
	 * // ���� ���� int count = this.slist.scheduleRemove(key, value);
	 * System.out.printf("%d���� ������ �����Ǿ����ϴ�.%n", count); } else { // ���� ���
	 * System.out.println("���� ������ ��ҵǾ����ϴ�."); } }
	 * 
	 * private void scheduleRemoveAll(Scanner sc) { // 1. ���� ���� Ȯ�� // 2. ��ü ���� ���� ����
	 * -> scheduleRemoveAll() �޼ҵ� System.out.println("��ü ������ ������ �����Դϴ�.");
	 * 
	 * System.out.print("����>"); int m = sc.nextInt(); sc.nextLine();
	 * 
	 * if (m == 1) { // ���� ���� this.slist.scheduleRemoveAll();
	 * System.out.println("��ü ������ �����Ǿ����ϴ�."); } else { // ���� ���
	 * System.out.println("���� ������ ����߽��ϴ�."); } }
	 */
	// ����ȭ �޼ҵ� �߰�
	public void serializable() {
		this.dao.serialization();
	}
}
