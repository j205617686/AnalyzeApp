package com.example;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;



public class ParseManifest {
	
	
	public ArrayList<Activity> getXMLActivity(String filename) {

	  ArrayList<Activity> AllActivity=new ArrayList<Activity>();
		
		
	  SAXBuilder builder = new SAXBuilder();
	  File xmlFile = new File(filename);

	  try {

		Document document = (Document) builder.build(xmlFile);
		Element rootNode = document.getRootElement();
		Element application = rootNode.getChild("application");
		
		
		//System.out.println(application.getAttributes());
				
		List activitys=application.getChildren("activity");

		
		for (int i = 0; i < activitys.size(); i++) {

			Element Eleactivitys=(Element)activitys.get(i);
			Activity tempActivity=new Activity();
			
				for(int j=0;j<Eleactivitys.getAttributes().size();j++)
				{
					//Attribute value = Eleactivitys.getAttribute("android");
					
					//System.out.println("activity"+i+":"+Eleactivitys.getAttributes().get(j).toString());
					
					
					String afterCut =Eleactivitys.getAttributes().get(j).toString().substring(11, Eleactivitys.getAttributes().get(j).toString().length()-1);
					
					
					
					
					
					
					if(afterCut.split("=")[0].equals(" android:name"))
					{
						
						//System.out.println("Activity "+i+":"+"attribute of"+afterCut.split("=")[0] +" is"+afterCut.split("=")[1]);
						
						tempActivity.setName(afterCut.split("=")[1].substring(1, afterCut.split("=")[1].length()-1));
						
						//AllActivity.add(afterCut.split("=")[1]);
					}
					if(afterCut.split("=")[0].equals(" android:parentActivityName"))
					{
						
						//System.out.println("Activity "+i+":"+"attribute of"+afterCut.split("=")[0] +" is"+afterCut.split("=")[1]);
						
						tempActivity.setParent(afterCut.split("=")[1].substring(1, afterCut.split("=")[1].length()-1));
						
						//AllActivity.add(afterCut.split("=")[1]);
					}
					else
					{
						tempActivity.setParent("");
					}
					
					
					
					
					//System.out.println("activity"+i+":"+value);					
				}
				
			
				if(Eleactivitys.getChild("intent-filter")!=null)
				{
					Element intentfilter=Eleactivitys.getChild("intent-filter");
					
					List actions=intentfilter.getChildren("action");
					Element EleAction;
					Element EleCategory;
					
					ArrayList<String> actionArray=new ArrayList<String>();
					ArrayList<String> categoryArray=new ArrayList<String>();
					
					for(int j=0;j<actions.size();j++ )
					{
						EleAction=(Element)actions.get(j);
						actionArray.add(EleAction.getAttributes().get(0).toString().split("=")[1].substring(1, EleAction.getAttributes().get(0).toString().split("=")[1].length()-2));
					}
					tempActivity.setAction(actionArray);
					
					
					
					
					List categorys=intentfilter.getChildren("category");
					
					for(int k=0;k<categorys.size();k++)
					{
						EleCategory=(Element)categorys.get(k);
						categoryArray.add(EleCategory.getAttributes().get(0).toString().split("=")[1].substring(1, EleCategory.getAttributes().get(0).toString().split("=")[1].length()-2));
					}
					tempActivity.setCategory(categoryArray);
					
				}
				else
				{
					tempActivity.setAction(null);
					tempActivity.setCategory(null);
				}
				
				
				AllActivity.add(tempActivity);
				
				
			//System.out.println("activity"+i+":"+Eleactivitys.getAttributeValue("android:name"));	
			

		}

	  } catch (IOException io) {
		System.out.println(io.getMessage());
	  } catch (JDOMException jdomex) {
		System.out.println(jdomex.getMessage());
	  }
	  
	  
	  /*
	  
	  
	  */
	return AllActivity;
	  
	  
	  
	}
	
	
	
	public ArrayList<String> getXMLService(String filename) {

		  ArrayList<String> AllService=new ArrayList<String>();
			
			
		  SAXBuilder builder = new SAXBuilder();
		  File xmlFile = new File(filename);

		  try {

			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			Element application = rootNode.getChild("application");
			
			
			//System.out.println(application.getAttributes());
					
			List services=application.getChildren("service");

			
			for (int i = 0; i < services.size(); i++) {

				Element Eleservices=(Element)services.get(i);
				
				
					for(int j=0;j<Eleservices.getAttributes().size();j++)
					{
						//Attribute value = Eleactivitys.getAttribute("android");
						
						//System.out.println("activity"+i+":"+Eleactivitys.getAttributes().get(j).toString());
						
						
						String afterCut =Eleservices.getAttributes().get(j).toString().substring(11, Eleservices.getAttributes().get(j).toString().length()-1);
						
						
						
						
						
						
						if(afterCut.split("=")[0].equals(" android:name"))
						{
							//System.out.println("Activity "+i+":"+"attribute of"+afterCut.split("=")[0] +" is"+afterCut.split("=")[1]);
							AllService.add(afterCut.split("=")[1].substring(1, afterCut.split("=")[1].length()-1));
							
							//AllActivity.add(afterCut.split("=")[1]);
						}
						
						
						
						
						
						//System.out.println("activity"+i+":"+value);					
					}
					
				
				
					
					
			
				

			}

		  } catch (IOException io) {
			System.out.println(io.getMessage());
		  } catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		  }
		  
		  
		  
		return AllService;
		  
		  
		  
		  
		  
		  
		  
		  
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}