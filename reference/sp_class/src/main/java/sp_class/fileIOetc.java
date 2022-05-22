package sp_class;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

public class fileIOetc {

	
	public static void main(String[] args) {
		
		//file 을 json으로 호출 
		String filePath = "D:\\교육\\sp기본\\7.json\\sample.json";
		
		try {
			Gson gson = new Gson();
			JsonReader reader = new JsonReader(new FileReader(filePath)); // 파일 호출
			JsonObject jsonObj = gson.fromJson(reader, JsonObject.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		// byte형식으로 file 호출 
		try {
			Path jsonFilePath = Paths.get(filePath);
			String wholeData = new String(Files.readAllBytes(jsonFilePath));
			Gson gson2 = new Gson();
			JsonObject jsonObj = gson2.fromJson(wholeData, JsonObject.class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//서버 시간
		LocalTime currentTime = LocalTime.now();
		String fname = String.format("./OUTPUT/%02d%02d%02d.json", currentTime.getHour(), currentTime.getMinute(), currentTime.getSecond());
		
		
		
		
	}
	
	

}
