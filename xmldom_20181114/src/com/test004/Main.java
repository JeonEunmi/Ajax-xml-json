package com.test004;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Main {

	public static void main(String[] args) {
		// 문제) guestbook.xml 파일 파싱 및 결과 출력
		// blind 정보는 0, 1 대신 blind ON, blind OFF 으로 출력


		try {
			File inputFile = new File("GUESTBOOK.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);

			Element root = doc.getDocumentElement();

			NodeList nList = root.getElementsByTagName("guestbook");
			for (int a = 0; a < nList.getLength(); ++a) {
				Element vElement = (Element) nList.item(a);

				String gid = vElement.getAttribute("gid");
				String name_ = vElement.getElementsByTagName("name_").item(0).getTextContent();
				String pw = vElement.getElementsByTagName("pw").item(0).getTextContent();
				String content = vElement.getElementsByTagName("content").item(0).getTextContent();
				String regDate = vElement.getElementsByTagName("regDate").item(0).getTextContent();
				String clientIP = vElement.getElementsByTagName("clientIP").item(0).getTextContent();
				String blind = vElement.getElementsByTagName("blind").item(0).getTextContent();


				System.out.printf("%s / %s / %s / %s / %s / %s / %s%n", gid, name_, pw, content, regDate, clientIP, Integer.parseInt(blind) == 0 ? "blind OFF" : "blind ON");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
