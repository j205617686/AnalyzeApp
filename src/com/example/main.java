package com.example;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class main {

	public static void main(String[] args) throws Exception {


		JSONObject anylyzeAppJSON=new JSONObject();
		
		
		
		JSONObject activityOBJ=new JSONObject();
		
		JSONArray allAct=new JSONArray();
		JSONArray allServ=new JSONArray();
		
		
		
		
		
		anylyzeAppJSON.put("All Activity", allAct);
		anylyzeAppJSON.put("All Service", allServ);
		
		
		
		ParseManifest PM=new ParseManifest();
		
		
		
		ArrayList<Activity> AllActivity=PM.getXMLActivity("./resource/manifest/AndroidManifest.xml");
		ArrayList<String> AllService=PM.getXMLService("./resource/manifest/AndroidManifest.xml");
		
		ArrayList<ArrayList<String>> allActivityCateAfterkill=new ArrayList<ArrayList<String>>();
		
		
		
		for(int i=0;i<AllService.size();i++)
		{
			allServ.put(AllService.get(i));
			
		}
		
		for(int i=0;i<AllActivity.size();i++)
		{
			
			
		}
		
		
		
		
		
		
		for(int i=0;i<AllActivity.size();i++)
		{
			ArrayList<String> tempcate=new ArrayList<String>();
			for(int j=0;j<AllActivity.get(i).getCategory().size();j++)
			{
				
				if(AllActivity.get(i).getCategory().get(j).contains("android.intent.category")!=true)
					tempcate.add(AllActivity.get(i).getCategory().get(j));
				
			}
			allActivityCateAfterkill.add(tempcate);
		}
		
		
		
		
		/*
		System.out.println("All Activity:");
		for(Activity activity : AllActivity)
		{
			System.out.println("Name: "+activity.name+" / Parent: "+activity.parent);
			
		}
		
		System.out.println("\nAll Service:");
		for(String service : AllService)
		{
			System.out.println("Name: "+service);
		}
		
		System.out.println("============================================");
		
		*/
		
		CatchLayoutComponent CLC=new CatchLayoutComponent();
		/*
		System.out.print("All Layout : ");
		ArrayList<String> layoutFileList=getFileList("./resource/layout/");
		
		
		String LayoutPath="./resource/layout/";
		
		for(String layoutfile :layoutFileList)
		{
		
		String LayoutName=LayoutPath+layoutfile;
		CLC.testGetRoot(LayoutName);
		
		System.out.println(LayoutName);
		
		 for (Object key : CLC.getComponent().keySet()) {
	            System.out.println(key + " : " + CLC.getComponent().get(key));
	        }
		
		 System.out.println("----------------------");
		 
		
		}
		
		*/
		
		
		//System.out.println("=========================================");
		
		CatchIntent CI =new CatchIntent();
		
		
		String ActivityPath="./resource/activity/";
		
		
		
	for(Activity act:AllActivity)
	{
		String activity=act.getName();	
		JSONObject actobj=new JSONObject();
		
		JSONArray actionJA=new JSONArray();
		JSONArray categoryJA=new JSONArray();
		//System.out.println("Activity "+activity+" : ");
	
		String activityName =ActivityPath+activity.split("\\.")[activity.split("\\.").length-1]+".java";
		
		
		ArrayList<ArrayList<String>> Intent =CI.ParseActivityToGetIntent(activityName);
		ArrayList<String> fromIntentArray=Intent.get(0);
		ArrayList<String> toIntentArray=Intent.get(1);
		
		
		
		for(String action:act.getAction())
		{
			actionJA.put(action);
		}
		for(String category:act.getCategory())
		{
			categoryJA.put(category);
		}
		
		
		
		/*                        implicit Intent                   */
		
		String action = "";
		if(Intent.get(2).isEmpty()!=true)
			action=Intent.get(2).get(0);
		
		ArrayList<String> categoryArray=Intent.get(3);
		
		
		
		/*
		System.out.println("action:");
		System.out.println(action);
		
		System.out.println("category:");
		for(String catA:categoryArray)
			System.out.println(catA);
		
		System.out.println();
	
		
		*/
		
		int[] flag=new int[AllActivity.size()];
		HashMap<String,ArrayList<String>> cate=new HashMap<String,ArrayList<String>>();
		ArrayList<String> ca=new ArrayList<String>();
		int f=0;
		for(Activity a : AllActivity)
		{
			
			
			
			
			
			
			//ArrayList<String> ca=new ArrayList<String>();
			for(int i=0;i<a.getCategory().size();i++)
			{
				
				
				if(a.getCategory().get(i).contains("android.intent.category")!=true)
					ca.add(a.getCategory().get(i));
					
			}
			
			cate.put(a.getName(), ca);
			
			
			
			
			
			//int[] flag=new int[AllActivity.size()];
			for(String actstr:a.getAction())
			{
				
				if(actstr.equals(action))
				{
					for(String catstr:ca)
					{
						for(String catnow:categoryArray)
						{
							if(catstr.equals(catnow))
							{
							flag[f]++;
							}
						
						}
					}
				}
				
			}
			
			f++;
			
		}
		
		/*
		System.out.println("from:");
		for(String from :fromIntentArray)
			//System.out.println(from);
		System.out.println("to:");
		for(String to :toIntentArray)
		{
			//System.out.println(to);
		}
		*/
		
		ArrayList<String> ImplicitIntent=new ArrayList<String>();
		
		
		
		for(int i=0;i<flag.length;i++)
		{
			
	
			if(flag[i]>0)
			{
				ImplicitIntent.add(AllActivity.get(i).getName());
				
				//System.out.println(AllActivity.get(i).getName());
			}
			
		}
		
		
		
		/*                        implicit Intent                   */
		
		
		
		ArrayList<String> layout=new ArrayList<String>();
	
		//System.out.println("LayoutName:"+CI.getSetContentView(activityName));
		
		String layoutName=CI.getSetContentView(activityName).split("\\.")[CI.getSetContentView(activityName).split("\\.").length-1];
		//System.out.println(CI.getSetContentView(activityName).split("\\.")[CI.getSetContentView(activityName).split("\\.").length-1]);
		
		String LayoutName="./resource/layout/"+layoutName+".xml";
		CLC.testGetRoot(LayoutName);
		
		
		 JSONArray layoutJSONArr=new JSONArray();
			
		
		 
		 for (Object key : CLC.getComponent().keySet()) {
			 	
			
			 JSONObject layoutComponent=new JSONObject();
			 layoutComponent.put(key.toString(), CLC.getComponent().get(key));
			 layoutJSONArr.put(layoutComponent);
	          //  System.out.println(key + " : " + CLC.getComponent().get(key));
	        }
		
		 
		
		
		
		
		
		// System.out.println("----------------------------------------------");
		
		 
		 
		 
		 
		 
		 JSONArray TOIntentJSONArr=new JSONArray();
		 JSONArray FromIntentJSONArr=new JSONArray();
		
		 for(int i=0;i<ImplicitIntent.size();i++)
		 {
			 TOIntentJSONArr.put(ImplicitIntent.get(i));
		 }
		 
		 
		 for(int i=0;i<toIntentArray.size();i++)
		 {
			 TOIntentJSONArr.put(toIntentArray.get(i));
		 }
		 
		 for(int i=0;i<fromIntentArray.size();i++)
		 {
			 FromIntentJSONArr.put(fromIntentArray.get(i));
		 }
		
		 
		 
		 
		 	actobj.put("Action",actionJA);
		 	actobj.put("Category",categoryJA);
			actobj.put("LayoutComponent",layoutJSONArr);
		 	actobj.put("FromIntent",FromIntentJSONArr);
		 	actobj.put("ToIntent",TOIntentJSONArr);
		 	actobj.put("Name",act.getName());
		 	actobj.put("Parent",act.getParent());
			allAct.put(actobj);
		 
		 
		 
		 
		 
		 
		 
		 
		 
		}
		
	
	
	
	
	
	
	
	
	
	
	
	
	System.out.println(anylyzeAppJSON);
	
	
	
	
	
	
	
		
		
		

	}
	
	
	
	
	 public static ArrayList<String> getFileList(String folderPath){
	        //String folderPath = "./resource/layout/";//鞈�冗頝臬��
	        StringBuffer fileList = new StringBuffer();
	        ArrayList<String> filelist=new ArrayList<String>();
	            try{
	               java.io.File folder = new java.io.File(folderPath);
	               String[] list = folder.list();           
	                         for(int i = 0; i < list.length; i++){
	                             fileList.append(list[i]).append(", ");
	                             filelist.add(list[i]);
	                             
	                        }
	                }catch(Exception e){
	                      //System.out.println("'"+folderPath+"'甇方��冗銝�");
	                } 
	                System.out.println(fileList);
					return filelist;
	        }
	
	
	
	

}
