package sp_class;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.*;
/*
 *  json 실습1
 *  https://github.com/google/gson jar 파일 다운 후 lib 등록 
 *  project 우클릭 property > java build path
 *  
 *  
 *   문제 아래 형식으로 json 파일 저장
 *  {
 *   "name":"spiderman",
 *   "age":45,
 *   "married":true,
 *   "specialty":{"martial art","gun"},
 *   "vaccine":{"1st":"done","2nd":"expected","3rd":null},  //Object 형태
 *   "children":[{"name":"spidergirl","age":10},{"name":"spiderboy","age", 8}], //Array 형태
 *   "address", null
 *   }
 *   
 *   답: 
 *   {"name":"spiderman","age":45,"married":true,"specialty":["martial art","gun"],"vaccine":{"1st":"done","2nd":"expected","3rd":null},"children":[{"name":"spiderboy","age":10},{"name":"spidergirl","age":8}],"address":null}
 *   
 *  // 기본  "name":"spiderman", 
 *  JsonObject jsonObj = new JsonObject();
 *  jsonObj.addProperty("name", "spiderman");
 *  
 *  //"specialty":{"martial art","gun"},
 *  JsonArray jsonArr = new JsonArray();
 *  jsonArr.add("martial art");
 *  jsonObj.add("specialty", jsonArr);
 *  
 *  
 *  //"vaccine":{"1st":"done","2nd":"expected","3rd":null},  //Object 형태
 *  JsonObject jsonObj2 = new JsonObject();
 *  jsonObj2.addProperty("1st", "done");
 *  jsonObj2.addProperty("1st", "done");
 *  jsonObj.add("vaccine", jsonObj2);
 *   
 *  //"children":[{"name":"spidergirl","age":10},{"name":"spiderboy","age", 8}], //Array 형태
 *  JsonArray jsonArr2 = new JsonArray();
 *	jsonObj2 = new JsonObject();
 *	jsonObj2.addProperty("name", "spiderboy");
 *	jsonObj2.addProperty("age", 10);
 *	jsonArr2.add(jsonObj2);
 *		
 *	jsonObj2 = new JsonObject();
 *	jsonObj2.addProperty("name", "spidergirl");
 *	jsonObj2.addProperty("age", 8);
 *	jsonArr2.add(jsonObj2);
 *   
 *   
 *   
 *  // null 
 *  jsonObj.add("address", null);
 *  
 *  
 *  
 *  //파일 저장 
 *  Writer writer = new FileWriter("D:\\교육\\sp기본\\7.json\\sample.json")
 *  Gson gson = new GsonBuilder().serializeNulls().create();
 *  gson.toJson(jsonObj, writer);
 *  
 *  
 *  	 
 */

public class GJsonSolve1 {

	public static void main(String[] args) {
		JsonObject jsonObj = new JsonObject();  //초기 설정 
		jsonObj.addProperty("name", "spiderman"); //
		jsonObj.addProperty("age", 45);
		jsonObj.addProperty("married", true);
		
		JsonArray jsonArr = new JsonArray();
		jsonArr.add("martial art");
		jsonArr.add("gun");
		jsonObj.add("specialty", jsonArr);
		
		JsonObject jsonObj2 = new JsonObject();
		jsonObj2.addProperty("1st", "done");
		jsonObj2.addProperty("2nd", "expected");
		jsonObj2.add("3rd", null);		
		jsonObj.add("vaccine", jsonObj2);
	
		JsonArray jsonArr2 = new JsonArray();
		jsonObj2 = new JsonObject();
		jsonObj2.addProperty("name", "spiderboy");
		jsonObj2.addProperty("age", 10);
		jsonArr2.add(jsonObj2);
	
		jsonObj2 = new JsonObject();
		jsonObj2.addProperty("name", "spidergirl");
		jsonObj2.addProperty("age", 8);
		jsonArr2.add(jsonObj2);

		jsonObj.add("children", jsonArr2);
		
		jsonObj.add("address", null);
		
		//file 읽기 쓰기 
		
		try (Writer writer = new FileWriter("D:\\교육\\sp기본\\7.json\\sample.json")) {
		    Gson gson = new GsonBuilder().serializeNulls().create();
		    gson.toJson(jsonObj, writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
