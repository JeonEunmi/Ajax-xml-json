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
		
		//XML ����(cars.xml)�� ���� ���� ����
		/*
		���� �� XML ���� ---------
		<?xml version = "1.0" encoding = "UTF-8"?>
		<cars>
		   <supercars company = "Ferrari">
		      <carname type = "formula one">Ferrari 101</carname>
		      <carname type = "sports">Ferrari 202</carname>
		   </supercars>
		</cars>
		
		���� �� XML ���� ---------
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
			
			//���� ��ť��Ʈ �ε�
			File inputFile = new File("cars.xml");
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(inputFile);
			
			Node cars = doc.getFirstChild();
			Node supercar = doc.getElementsByTagName("supercars").item(0);
			
			//Ư�� ������Ʈ ���� ���� �� ��ť��Ʈ�� �߰�
			//����) �߰��Ҷ� �θ�, �ڽ� ���踦 �����ؾ� �Ѵ�.
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
			
			System.out.println("XML ����(cars.xml) ���� ���� �Ϸ�!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

}
