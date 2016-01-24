package com.example;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import de.hunsicker.io.FileFormat;
import de.hunsicker.jalopy.Jalopy;

public class CatchIntent {
	public ArrayList<ArrayList<String>> ParseActivityToGetIntent(String Activityfilename) throws IOException {

		
		
		FileReader fr = new FileReader(Activityfilename);

		BufferedReader br = new BufferedReader(fr);
		String allstr=new String();
		while (br.ready()) {

			//System.out.println(br.readLine());
			String tempstr=br.readLine();
			
			
			//System.out.println(br.readLine());
			
			allstr+=tempstr;
			allstr+="\n";

		}

		fr.close();

		
		
		//System.out.println(allstr);
		
		
		allstr =allstr.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");
		
		
		
		//String formatCode=formatJavaCode(allstr);
		
		
		String[] strA=allstr.split("\n");
		/*
		for(int i=0;i<strA.length;i++)
		{
			strA[i]=strA[i].trim();
			
			if(strA[i].contains("\""))
			{
				if(strA[i].substring(strA[i].indexOf("\""),strA[i].lastIndexOf("\"")).contains("//"))
				{
					
				}
			}
			else
				strA[i]=strA[i].split("//")[0];
			
		}
		*/
		//System.out.println("================");
		//System.out.println(strA[1]);
		
		ArrayList<String> IntentArray=new ArrayList<String>();
		ArrayList<String> actionArray = new ArrayList<String>();
		ArrayList<String> categoryArray = new ArrayList<String>();
		
		for(int i=0;i<strA.length;i++)
		{
			if(strA[i].contains("new Intent"))
			{
				
				
				
				
				if(strA[i].endsWith(";")!=true && (strA[i].endsWith("{")!=true))
				{
					if(i!=strA.length-1)
					{
						if(strA[i+1].endsWith(";")!=true)
						{
								if(strA[i+2].endsWith(";")!=true)
									IntentArray.add(strA[i]+strA[i+1]+strA[i+2]+strA[i+3]);
								else
									IntentArray.add(strA[i]+strA[i+1]+strA[i+2]);
								
								
								
						}
						else
							IntentArray.add(strA[i]+strA[i+1]);
						
						
					}
						
				}
				else
					IntentArray.add(strA[i]);
				
			}
			if(strA[i].contains("setClass"))
			{
				
				
				IntentArray.add(strA[i]);
				
			}
			
			
			
			if(strA[i].contains("setAction"))
			{
				actionArray.add(strA[i].split("\\(")[strA[i].split("\\(").length-1].substring(1, strA[i].split("\\(")[strA[i].split("\\(").length-1].length()-3));
				
			}
			
			if(strA[i].contains("addCategory"))
			{
				categoryArray.add(strA[i].split("\\(")[strA[i].split("\\(").length-1].substring(1, strA[i].split("\\(")[strA[i].split("\\(").length-1].length()-3));
				
			}


			
				
			
	}
		
		
		
		
			
			
		ArrayList<String> fromIntentArray=new ArrayList<String>();
		ArrayList<String> toIntentArray=new ArrayList<String>();
		
		for(String IntentStr:IntentArray)
		{
			if(IntentStr.contains(","))
			{
				if(IntentStr.split(",")[0].substring(IntentStr.split(",")[0].length()-2,IntentStr.split(",")[0].length()).equals("()"))
				{
					String tempstr=IntentStr.split(",")[0].substring(0, IntentStr.split(",")[0].length()-2);
					fromIntentArray.add(tempstr.split("\\(")[tempstr.split("\\(").length-1]+"()");
					
					
				
				}
				else
				{
					fromIntentArray.add(IntentStr.split(",")[0].split("\\(")[IntentStr.split(",")[0].split("\\(").length-1].trim());
				}
				
				
				toIntentArray.add(IntentStr.split(",")[1].substring(0,IntentStr.split(",")[1].length()-2).trim());
			}
			
				
		}
		
		/*
		for(String str : toIntentArray)
		{
			System.out.println(str);
			
			
		}
		*/
		
				/*==============將重複值去掉============*/	
		
		ArrayList<String> nonDfromIntentArray=new ArrayList<String>();
		ArrayList<String> nonDtoIntentArray=new ArrayList<String>();
		
		
		Set<String> fromSet = new HashSet<String>();
		for (String element : fromIntentArray) {
			fromSet.add(element);
	    }
		
		
		 Object[] tempfromArray = fromSet.toArray();
		for (int i = 0; i < tempfromArray.length; i++) {
			nonDfromIntentArray.add((String)tempfromArray[i]);
		  }
		
		Set<String> toSet = new HashSet<String>();
		for (String element : toIntentArray) {
			toSet.add(element);
	    }
		
		
		 Object[] temptoArray = toSet.toArray();
		for (int i = 0; i < temptoArray.length; i++) {
			nonDtoIntentArray.add((String)temptoArray[i]);
		  }
		
		//System.out.println("fromIntentActivity:");
		for(String str : nonDfromIntentArray)
		{
			//System.out.println(str);
			
			
		}
		
		//System.out.println("===================================");
		//System.out.println("toIntentActivity:");
		for(String str : nonDtoIntentArray)
		{
			//System.out.println(str);
			
			
		}
		
		
		
		ArrayList<ArrayList<String>> Intent=new ArrayList<ArrayList<String>>();
		
		
		Intent.add(nonDfromIntentArray);
		Intent.add(nonDtoIntentArray);
		Intent.add(actionArray);
		Intent.add(categoryArray);
		
		return Intent;
		
		
		
		
		
		
	}
	

	public static String formatJavaCode(String code)
	{
		File tempFile = null;
		StringBuffer b = new StringBuffer();
		try
		{
			tempFile = new File("target" + File.separatorChar + "temp.java");
			tempFile.getParentFile().mkdirs();
			tempFile.createNewFile();

			PrintWriter xwriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(tempFile, false), "UTF-8"));
			xwriter.write(code.toString());
			xwriter.flush();

			Jalopy jalopy = new Jalopy();
			jalopy.setFileFormat(FileFormat.DEFAULT);
			jalopy.setInput(tempFile);
			jalopy.setOutput(b);
			jalopy.format();

			xwriter.close();

			String result = null;

			if (jalopy.getState() == Jalopy.State.OK
					|| jalopy.getState() == Jalopy.State.PARSED)
			{
				result = b.toString();
			} else if (jalopy.getState() == Jalopy.State.WARN)
			{
				result = code;// formatted with warnings
			} else if (jalopy.getState() == Jalopy.State.ERROR)
			{
				result = code; // could not be formatted
			}

			return result.toString();

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			tempFile.delete();
		}

		return null;// could not be formatted
	}
	
	
	
	public String getSetContentView(String Activityfilename) throws IOException
	{
		
		FileReader fr = new FileReader(Activityfilename);

		BufferedReader br = new BufferedReader(fr);
		
		
		String catchStr="";
		while (br.ready()) {

			//System.out.println(br.readLine());
			String tempstr=br.readLine();
			
			
			//System.out.println(br.readLine());
			
			if(tempstr.contains("setContentView"))
			{
				catchStr+=tempstr.trim();
			}

		}

		fr.close();

		
		
		//System.out.println(catchStr.substring(15,catchStr.length()-2));
		
		
		
		
		
		
		
		
		return (catchStr.substring(15,catchStr.length()-2));
		
		
		
		
		
		
		
	}
	
	
	
}
