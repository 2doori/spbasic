package sp_class;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;


/*
 * json 실습 2
 * 
 * 앞에서 만든 sample json을 이용하여, 출력
 * name:spiderman(45)
 * name:spidergirl(8)
 * 
 * 
 * Gson gson = new Gson();  
 * JsonReader reader = new JsonReader(new FileReader(filePath));
 * JsonObject jsonObj = gson.fromJson(reader, JsonObject.class);
 *  
 * JsonArray jsonArr = jsonObj.get("children").getAsJsonArray();
 * 
 * //기본 json "name":"spiderman" 호출
 * String name = jsonObj.get("name").getAsString();
 * JsonArray jsonArr = jsonObj.get("children").getAsJsonArray();
 * 
 * 
 * // 처믐 children을 jsonArray로 호출 > JsonObject로 
 * //"children":[{"name":"spidergirl","age":10},{"name":"spiderboy","age", 8}],
 * JsonArray jsonArr = jsonObj.get("children").getAsJsonArray();
 * JsonObject jsonObj2 = jsonArr.get(1).getAsJsonObject();
 * name = jsonObj2.get("name").getAsString();
 * 
 * 
 * 
 * 
 * >>최종값 까지 object key : value 형태로 빼내어서 작업 
 * 
 * boolean bo =  jsonObj.get("married").getAsBoolean();
 * 
 * 
 */

public class GJsonSolve2 {

	public static void main(String[] args) {
		String filePath = "D:\\교육\\sp기본\\7.json\\sample.json"; 
		try {
			Gson gson = new Gson();  
			JsonReader reader = new JsonReader(new FileReader(filePath)); //파일 호출
			JsonObject jsonObj = gson.fromJson(reader, JsonObject.class);

			//기본 json "name":"spiderman" 호출
			String name = jsonObj.get("name").getAsString();
			int age = jsonObj.get("age").getAsInt();
			System.out.println("name:"+name + "("+age+")");
			
			
			//"children":[{"name":"spidergirl","age":10},{"name":"spiderboy","age", 8}],
			// 처믐 children을 jsonArray로 호출 > JsonObject로 
			JsonArray jsonArr = jsonObj.get("children").getAsJsonArray();
			JsonObject jsonObj2 = jsonArr.get(1).getAsJsonObject();
			name = jsonObj2.get("name").getAsString();
			age = jsonObj2.get("age").getAsInt();
			System.out.println("name:"+name + "("+age+")");
			
			boolean bo =  jsonObj.get("married").getAsBoolean();
			
			System.out.println("married:"+bo );
					
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
