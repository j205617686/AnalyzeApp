package com.example;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class CatchLayoutComponent {

	HashMap<String,String> component =new HashMap<String,String>();
	
	public void testGetRoot(String Layoutfilename) throws Exception {
		SAXBuilder sax = new SAXBuilder();
		File xmlFile = new File(Layoutfilename);
		Document document = sax.build(xmlFile);
		
		Element root = document.getRootElement();
		this.getNodes(root);
		
		
	}
	
	
	public void getNodes(Element node) {
		
		
		
		
		List<Attribute> listAttr = node.getAttributes();
	
		
		
		String tempId = "Layout";
		String tempComName=node.getName();
		
		//System.out.println(tempComName);
	
		for(Attribute attr:listAttr)
		{
			if(attr.getName().equals("id"))
			{
				
				
				String name = attr.getName();// 撅�批�妍
				String value = attr.getValue();// 撅�抒���
				
			
				tempId=value;
				
			
				
				
			}
			
		}
	
		List<Element> listElement = node.getChildren();// �����蝥批����ist
		for (Element e : listElement) {// �������蝥批��
			this.getNodes(e);// ���
		}
		
		component.put(tempId, tempComName);
	
	
	
	}
	
	
	public HashMap<String, String> getComponent() {
		return component;
	}

	public void setComponent(HashMap<String, String> component) {
		this.component = component;
	}
}
	