package sp_class;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;


/*
 * json 실습 3\
 * sample.com 파일을 읽은 후 
 * 첫번째 leveldml value을 읽고 다음과 같이 출력 하시오
 * 
 * 
 * Key : name / Value Type : String
 * Key : age / Value Type : Number
 * Key : married / Value Type : Boolean
 * Key : specialty / Value Type : Array
 * Key : vaccine / Value Type : Object
 * Key : children / Value Type : Array
 * Key : address / Value Type : null
 * 
 * 
 * 
 * 파일 호출
 * String filePath = "D:\\교육\\sp기본\\7.json\\sample.json";
 * Path jsonFilePath = Paths.get(filePath);
 * 
 * String wholeData = new String(Files.readAllBytes(jsonFilePath));
 * Gson gson = new Gson();
 * JsonObject jsonObj = gson.fromJson(wholeData, JsonObject.class);
 * 
 *  //기본 gson 출력 
 *  
 *  JsonElement je = jsonObj.get(key);
 *  
 *  JsonElement jsoElement = JsonParser.parseString("{ \"key\":\"value\"}");
 *  System.out.println(jsoElement.toString());
 *  
 * 
 */


public class GJsonSolve3 {

	public static void main(String[] args) {
		String filePath = "D:\\교육\\sp기본\\7.json\\sample.json";
		Path jsonFilePath = Paths.get(filePath);
		try {
			String wholeData = new String(Files.readAllBytes(jsonFilePath));
			Gson gson = new Gson();
			JsonObject jsonObj = gson.fromJson(wholeData, JsonObject.class);
		
			for (String key : jsonObj.keySet()) {
				System.out.print("Key : "+key+" / Value Type : ");
				JsonElement je = jsonObj.get(key);
				if (je.isJsonPrimitive()) {
					if (je.getAsJsonPrimitive().isString()) {
						System.out.println("String"); 
					}
					else if (je.getAsJsonPrimitive().isNumber())
					{
						System.out.println("Number");
					}
					else if (je.getAsJsonPrimitive().isBoolean()) {
						System.out.println("Boolean");
					}
					else if (je.getAsJsonPrimitive().isJsonNull()) {
						System.out.println("null");
					}
				}
				else if (je.isJsonArray()) {
					System.out.println("Array");
				}
				else if (je.isJsonObject()) {
					System.out.println("Object");					
				}
				else if (je.isJsonNull()) {
					System.out.println("null");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
