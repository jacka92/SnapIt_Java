package helper;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JSONhandle {
	@SuppressWarnings("unchecked")
	public static void initJSON() 
	{
		JSONObject obj = new JSONObject();
		obj.put("isEnabled", true);
		obj.put("timeLapse",0);
		obj.put("timeWarp",0);
		obj.put("soundMode",0);
		obj.put("simpleMode",0);

		try {
			FileUtils.writeStringToFile(new File("jsonText"),obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args){
		initJSON();
		readJSON();
		
		
		System.out.println(getEnabled());
		System.out.println(getTimeLapse());
		System.out.println(getTimeWarp());
		System.out.println(getSoundMode());
		System.out.println(getSimpleMode());
		
	}
	
	@SuppressWarnings("unchecked")
	public static void writeJSON(int timeLapse, int timeWarp, int soundMode, int simpleMode, boolean enabled) 
	{
		JSONObject obj = new JSONObject();
		obj.put("isEnabled", enabled);
		obj.put("timeLapse",timeLapse);
		obj.put("timeWarp",timeWarp);
		obj.put("soundMode",soundMode);
		obj.put("simpleMode",simpleMode);

		try {
			FileUtils.writeStringToFile(new File("jsonText"),obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void readJSON(){

		JSONParser parser=new JSONParser();
		StringBuffer s = new StringBuffer();
		try {
			 s = s.append(FileUtils.readFileToString(new File("jsonText")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try{

			Object obj = parser.parse(s.toString());
			JSONObject js = (JSONObject)obj;
			int timeLapse = Integer.parseInt(js.get("timeLapse") + "");
			System.out.println(++timeLapse);
			System.out.println(obj);
			
		}catch(ParseException pe){
			System.out.println("position: " + pe.getPosition());
			System.out.println(pe);
		}

	}
	
	public static boolean getEnabled(){
		boolean analytics = false;
		StringBuffer s = new StringBuffer();
		try {
			 s = s.append(FileUtils.readFileToString(new File("jsonText")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try{
			JSONParser parser=new JSONParser();
			Object obj = parser.parse(s.toString());
			JSONObject js = (JSONObject)obj;
			analytics = Boolean.parseBoolean(js.get("isEnabled") + "");
			
		}catch(ParseException pe){
			System.out.println("position: " + pe.getPosition());
			System.out.println(pe);
		}
		return analytics;
	}
	
	public static int getTimeLapse(){
		int count = 0;
		StringBuffer s = new StringBuffer();
		try {
			 s = s.append(FileUtils.readFileToString(new File("jsonText")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try{
			JSONParser parser=new JSONParser();
			Object obj = parser.parse(s.toString());
			JSONObject js = (JSONObject)obj;
			count = Integer.parseInt(js.get("timeLapse")+"");
			
		}catch(ParseException pe){
			System.out.println("position: " + pe.getPosition());
			System.out.println(pe);
		}
		
		return count;
	}
	
	
	public static int getTimeWarp(){
		int count = 0;
		StringBuffer s = new StringBuffer();
		try {
			 s = s.append(FileUtils.readFileToString(new File("jsonText")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try{
			JSONParser parser=new JSONParser();
			Object obj = parser.parse(s.toString());
			JSONObject js = (JSONObject)obj;
			count = Integer.parseInt(js.get("timeWarp")+"");
			
		}catch(ParseException pe){
			System.out.println("position: " + pe.getPosition());
			System.out.println(pe);
		}
		
		return count;
	}
	
	public static int getSoundMode(){
		int count = 0;
		StringBuffer s = new StringBuffer();
		try {
			 s = s.append(FileUtils.readFileToString(new File("jsonText")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try{
			JSONParser parser=new JSONParser();
			Object obj = parser.parse(s.toString());
			JSONObject js = (JSONObject)obj;
			count = Integer.parseInt(js.get("soundMode")+"");
			
		}catch(ParseException pe){
			System.out.println("position: " + pe.getPosition());
			System.out.println(pe);
		}
		
		return count;
	}
	
	public static int getSimpleMode(){
		int count = 0;
		StringBuffer s = new StringBuffer();
		try {
			 s = s.append(FileUtils.readFileToString(new File("jsonText")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try{
			JSONParser parser=new JSONParser();
			Object obj = parser.parse(s.toString());
			JSONObject js = (JSONObject)obj;
			count = Integer.parseInt(js.get("simpleMode")+"");
			
		}catch(ParseException pe){
			System.out.println("position: " + pe.getPosition());
			System.out.println(pe);
		}
		
		return count;
	}
	

	public static void setEnabled(boolean enabled){
		writeJSON(getTimeLapse(), getTimeWarp(), getSoundMode(), getSimpleMode(), enabled);
	}
	
	public static void increaseTimeLapse(){
		if(getEnabled())
		writeJSON(getTimeLapse() + 1, getTimeWarp(), getSoundMode(), getSimpleMode(), getEnabled());
	}
	
	public static void increaseTimeWarp(){
		if(getEnabled())
		writeJSON(getTimeLapse() , getTimeWarp() + 1, getSoundMode(), getSimpleMode(), getEnabled());
	}
	
	public static void increaseSoundMode(){
		if(getEnabled())
		writeJSON(getTimeLapse() , getTimeWarp(), getSoundMode() + 1, getSimpleMode(), getEnabled());
	}
	
	public static void increaseSimpleMode(){
		if(getEnabled())
		writeJSON(getTimeLapse() , getTimeWarp(), getSoundMode(), getSimpleMode() + 1, getEnabled());
	}
}
