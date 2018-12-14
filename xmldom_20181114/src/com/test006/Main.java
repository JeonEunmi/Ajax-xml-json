package com.test006;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main {

	public static void main(String[] args) {
		
		//XML 파일(cars.xml)에 대한 동적 수정
		/*
		수정 전 XML 상태 ---------
		<?xml version = "1.0" encoding = "UTF-8"?>
		<cars>
		   <supercars company = "Ferrari">
		      <carname type = "formula one">Ferrari 101</carname>
		      <carname type = "sports">Ferrari 202</carname>
		   </supercars>
		</cars>
		
		수정 후 XML 상태 ---------
		<?xml version = "1.0" encoding = "UTF-8"?>
		<cars>
		   <supercars company = "Ferrari">
		      <carname type = "formula one">Ferrari 101</carname>
		      <carname type = "sports">Ferrari 202</carname>
		   </supercars>
		   <luxurycars company = "Benteley">
		      <carname>Benteley 1</carname>
		      <carname>Benteley 2</carname>
		      <carname>Benteley 3</carname>
		   </luxurycars>		   
		</cars>
		*/

		try {
			
			//기존 도큐먼트 로드
			File inputFile = new File("cars.xml");
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(inputFile);
			
			Node cars = doc.getFirstChild();
			Node supercar = doc.getElementsByTagName("supercars").item(0);
			
			//특정 엘리먼트 동적 생성 및 도큐먼트에 추가
			//주의) 추가할때 부모, 자식 관계를 지정해야 한다.
			//createElement(), createTextNode(), createAttribute()
			//appendChild(), setAttributeNode()
			
	        // root element
			Element rootElement = doc.getDocumentElement();

			
	        // luxurycars element
			Element luxurycars = doc.createElement("luxurycars");
			rootElement.appendChild(luxurycars);
			/*
			<?xml version = "1.0" encoding = "UTF-8"?>
			<cars>
			   <supercars company = "Ferrari">
			      <carname type = "formula one">Ferrari 101</carname>
			      <carname type = "sports">Ferrari 202</carname>
			   </supercars>
			   <luxurycars>
			   </luxurycars>
			</cars>
			*/		
			
			// setting attribute to element
			Attr attr = doc.createAttribute("company");
			attr.setValue("Benteley");
			luxurycars.setAttributeNode(attr);
			/*
			<?xml version = "1.0" encoding = "UTF-8"?>
			<cars>
			  <supercars company = "Ferrari">
			      <carname type = "formula one">Ferrari 101</carname>
			      <carname type = "sports">Ferrari 202</carname>
			   </supercars>
			   <luxurycars company = "Benteley">
			   </luxurycars>
			</cars>
			*/         

			
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("cars.xml"));
			transformer.transform(source, result);
			
			System.out.println("XML 파일(cars.xml) 동적 수정 완료!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

}
