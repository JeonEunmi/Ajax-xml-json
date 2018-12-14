package com.guestbook;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class GuestbookDAO {

	// XML 파일(guestbooks.xml) 저장소 준비
	// XML Object 저장소 준비
	private Document doc = null;
	InetAddress local;

	public GuestbookDAO() {
		this.deSerialization();
	}

	// 게시글 입력
	public void guestbookAdd(String gid, String name_, String pw, String content, String regDate, String clientIP) {

		try {

			Element root = doc.getDocumentElement();

			Element guestbook = doc.createElement("guestbook");
			root.appendChild(guestbook);

			Attr attr = doc.createAttribute("gid");
			attr.setValue(gid);
			guestbook.setAttributeNode(attr);

			Element nameTemp = doc.createElement("name_");
			nameTemp.appendChild(doc.createTextNode(name_));
			guestbook.appendChild(nameTemp);

			Element pwTemp = doc.createElement("pw");
			pwTemp.appendChild(doc.createTextNode(pw));
			guestbook.appendChild(pwTemp);

			Element contentTemp = doc.createElement("content");
			contentTemp.appendChild(doc.createTextNode(content));
			guestbook.appendChild(contentTemp);

			Element regDateTemp = doc.createElement("regDate");
			regDateTemp.appendChild(doc.createTextNode(regDate));
			guestbook.appendChild(regDateTemp);

			Element clientIPTemp = doc.createElement("clientIP");
			clientIPTemp.appendChild(doc.createTextNode(clientIP));
			guestbook.appendChild(clientIPTemp);

			Element blind = doc.createElement("blind");
			blind.appendChild(doc.createTextNode("1"));
			guestbook.appendChild(blind);

			// Guestbook b = new Guestbook(gid, name_, pw, content, regDate, ip, "1");
			// 물리적 저장!
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("GUESTBOOK.xml"));
			transformer.transform(source, result);

			System.out.println("XML 파일 동적생성 완료!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String autoGid() {

		String temp = "";

		try {

			Element root = doc.getDocumentElement();

			NodeList nList = root.getElementsByTagName("guestbook");
			temp = String.format("G%03d", (nList.getLength() + 1));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return temp;

	}

	// 게시글 출력
	public List<Guestbook> guestbookAllPrint() {
		List<Guestbook> result = new ArrayList<Guestbook>();

		try {
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

				result.add(new Guestbook(gid, name_, pw, content, regDate, clientIP, blind));
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;

	}

	// 게시글 검색(글내용 부분 검색)
	public List<Guestbook> guestbookSearch(String key, String value) {
		List<Guestbook> result = new ArrayList<Guestbook>();

		try {
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

				if (key.equals("gid") && gid.equals(value)) {
					result.add(new Guestbook(gid, name_, pw, content, regDate, clientIP, blind));
				}

				if (key.equals("name_") && name_.contains(value)) {
					result.add(new Guestbook(gid, name_, pw, content, regDate, clientIP, blind));
				}

				if (key.equals("content") && regDate.contains(value)) {
					result.add(new Guestbook(gid, name_, pw, content, regDate, clientIP, blind));
				}
			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	// 게시글 삭제 removeChild() 부모가 타겟이면 자식도 지워진다.
	//게시글 삭제
	public void remove(Guestbook g) {
		
		
	}

	// guestbooks.xml 동적 수정 (덮어쓰기)
	// 메모리에 존재하는 XML Object 에 대한 물리적 저장
	// ->직렬화
	public void serialization() {


	}

	// guestbooks.xml 동적 읽어오기
	// ->역직렬화
	public void deSerialization() {
		try {
			// 물리적 파일(guestbooks.xml)을 메모리로 로딩하는 과정
			File inputFile = new File("guestbooks.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			// 필드로 선언된 XML Object 저장소에 연결
			this.doc = dBuilder.parse(inputFile);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String ip() {

		try {
			local = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		String ip = local.getHostAddress();

		return ip;
	}
}
