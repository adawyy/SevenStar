package com.mte.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;  

/**
 * Project :  mtesense
 * Created :  Jerry
 * Date    :  1/04/16
 */

public class JsonUtil {

	
	
	public static void ttt(String str) throws JsonParseException, JsonMappingException, IOException{
		
		ObjectMapper mapper = new ObjectMapper();  
		System.out.println(str);
		Map<String, Map<String, List<Map<String,List<Map<String,List<String>>>>>>> map = mapper.readValue(str, Map.class);
		System.out.println(map.get("search").get("entry").get(0).get("attribute").get(0).get("value").get(0));
//		List<Object> List=  (List<Object>) map.get("search").get("entry");
//		System.out.println(List.get(0));
//		Map<String, Object> map1 = mapper.readValue("{"map.get("search").toString(), new TypeReference<Map<String, Object>>() {});
        for (String key : map.keySet()) {
            System.out.println(key+","+map.get(key));
        }
        Iterator<?> iterator = map.get("search").get("entry").get(0).get("attribute").iterator();   
        while ( iterator.hasNext() ) {
        	Map<String,?> sm = (Map<String, ?>) iterator.next();
        	System.out.println(sm.get("name").toString());
 //       	 System.out.println(sm.get("name")+sm.get("value").get(0));
        	 if(sm.get("name").toString().equals("alternateuserid")){
        		 List<String> L = (List<String>) sm.get("value");
         		System.out.println(L.get(0).toString());
         	}
        }
	}
	
	public static void talentplaybook(String str) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();  
		System.out.println(str);
		List<LinkedHashMap<String,Object>> Lsm =  mapper.readValue(str, List.class);
		Iterator<?> iterator = Lsm.iterator(); 
		 while ( iterator.hasNext() ) {
			 LinkedHashMap<String,?> sm = (LinkedHashMap<String, ?>)iterator.next();
			 for(String key:sm.keySet()){
				 System.out.println(key+" "+sm.get(key));
				 if(key.equals("pbcYears")){
					 List<String> ls = (List<String>)sm.get("pbcYears");
					 Iterator<?> it = ls.iterator(); 
					 while (it.hasNext()){
						 System.out.println(it.next());
					 }
				 }
			 }
		 }
	}
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
/*		String aaa = "{\"search\": { \"entry\": [{ \"dn\": \"uid=823798897,c=us,ou=bluepages,o=ibm.com\",\"attribute\": [{\"name\": \"alternateuserid\", \"value\": [ \"HWPMGR7\" ] }]"
				+"}],\"return\": {\"code\": 0,\"message\": \"Success\",\"count\": 1}}}";
		String aa ="{\"password\":\"888888\",\"username\":\"����\"}";*/
		String aaa = "[{\"cnum\": \"03968A744\",\"firstName\": \" Lavanya\",\"pbcYears\": [2012,2013,2014]},{\"cnum\": \"123\",\"firstName\": \" 123\",\"pbcYears\": [2022,2023,2024]}]";
		JsonUtil.talentplaybook(aaa);
	}
}
