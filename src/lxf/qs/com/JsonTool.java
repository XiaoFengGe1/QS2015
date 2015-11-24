package lxf.qs.com;

import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonTool {

	public JsonTool() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void readJosn(String JsonContext){
		
		JSONArray jsonArray = JSONArray.fromObject(JsonContext);
		int size = jsonArray.size();
		System.out.println("Size: " + size);
		for(int  i = 0; i < size; i++){
		JSONObject jsonObject = jsonArray.getJSONObject(i);
		//String name = jsonObject.next();
		System.out.println("[" + i + "]question=" + jsonObject.get("question"));
		System.out.println("[" + i + "]answer=" + jsonObject.get("answer"));
		}
	}
	
	public void getJsonArrayBy(HashMap<String,String> data,String JsonContext,String str){
		if(!data.isEmpty()){
		data.clear();
		}
		JSONArray jsonArray = JSONArray.fromObject(JsonContext);
		int size = jsonArray.size();
		System.out.println("Size: " + size);
		for(int  i = 0; i < size; i++){
		JSONObject jsonObject = jsonArray.getJSONObject(i);
		data.put(jsonObject.get("question").toString(), jsonObject.get("answer").toString()); 
		}
	}
	
	public void getJsonArray(HashMap<String,String> data,String JsonContext){
		if(!data.isEmpty()){
		data.clear();
		}
		JSONArray jsonArray = JSONArray.fromObject(JsonContext);
		int size = jsonArray.size();
		System.out.println("Size:" + size);
		for(int  i = 0; i < size; i++){
		JSONObject jsonObject = jsonArray.getJSONObject(i);
		data.put(jsonObject.get("question").toString(), jsonObject.get("answer").toString()); 
		}
	}
}
