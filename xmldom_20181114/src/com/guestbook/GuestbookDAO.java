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

	// XML ����(guestbooks.xml) ����� �غ�
	// XML Object ����� �غ�
	private Document doc = null;
	InetAddress local;

	public GuestbookDAO() {
		this.deSerialization();
	}

	// �Խñ� �Է�
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
			// ������ ����!
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("GUESTBOOK.xml"));
			transformer.transform(source, result);

			System.out.println("XML ���� �������� �Ϸ�!");

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

	// �Խñ� ���
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

	// �Խñ� �˻�(�۳��� �κ� �˻�)
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

	// �Խñ� ���� removeChild() �θ� Ÿ���̸� �ڽĵ� ��������.
	//�Խñ� ����
	public void remove(Guestbook g) {
		
		
	}

	// guestbooks.xml ���� ���� (�����)
	// �޸𸮿� �����ϴ� XML Object �� ���� ������ ����
	// ->����ȭ
	public void serialization() {


	}

	// guestbooks.xml ���� �о����
	// ->������ȭ
	public void deSerialization() {
		try {
			// ������ ����(guestbooks.xml)�� �޸𸮷� �ε��ϴ� ����
			File inputFile = new File("guestbooks.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			// �ʵ�� ����� XML Object ����ҿ� ����
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
