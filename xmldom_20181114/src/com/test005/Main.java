package com.test005;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

public class Main {

	public static void main(String[] args) {
		// XML ���Ͽ� ���� ���� ����
		
		/*
		 <?xml version = "1.0" encoding = "UTF-8"  standalone = "no"?>
		 <cars>
		    <supercars company = "Ferrari">
		          <carname type = "formula one">Ferarri 101</carname>
		          <carname type = "sports car">Ferarri 201</carname>
		          <carname type = "sports car">Ferarri 301</carname>
		    </supercars>
		 </cars>
		*/
		
		try {
			// �� ��ť��Ʈ ����
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();

			// Ư�� ������Ʈ ���� ���� �� ��ť��Ʈ�� �߰�
			// ����) �߰��� �� �θ�, �ڽ� ���踦 �����ؾ� �Ѵ�.
			// createElement(), createTexstNode(), createAttribute()
			// appendChild(), setAttributeNode()
			
			// root element
			Element rootElement = doc.createElement("cars");
			doc.appendChild(rootElement);
			/*
			<?xml version = "1.0" encoding = "UTF-8"  standalone = "no"?>
		 	<cars>
		 	</cars>
			*/
			
			// supercars element
	        Element supercar = doc.createElement("supercars");
	        rootElement.appendChild(supercar);
	        /*
	        <?xml version = "1.0" encoding = "UTF-8"  standalone = "no"?>
		 	<cars>
		    	<supercars></supercars>
		 	</cars>
	         */
	        
	        
	        // setting attribute to element
	        Attr attr = doc.createAttribute("company");
	        attr.setValue("Ferrari");
	        supercar.setAttributeNode(attr);
		    /*
		    <?xml version = "1.0" encoding = "UTF-8"  standalone = "no"?>
			<cars>
			<supercars company = "Ferrari"></supercars>
			</cars>
		    */

			// carname element
			Element carname = doc.createElement("carname");
			Attr attrType = doc.createAttribute("type");
			attrType.setValue("formula one");
			carname.setAttributeNode(attrType);
			carname.appendChild(doc.createTextNode("Ferrari 101"));
			supercar.appendChild(carname);

			Element carname1 = doc.createElement("carname");
			Attr attrType1 = doc.createAttribute("type");
			attrType1.setValue("sports");
			carname1.setAttributeNode(attrType1);
			carname1.appendChild(doc.createTextNode("Ferrari 202"));
			supercar.appendChild(carname1);

			
			// ������ ����!
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("cars.xml"));
			transformer.transform(source, result);

			// Output to console for testing
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);
			
			System.out.println("XML ���� �������� �Ϸ�!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
