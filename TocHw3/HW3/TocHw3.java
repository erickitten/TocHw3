/**
 * Project : TocHw3
 * Author : �d����
 * Author_ID : AN4006048
*/
import org.json.*;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.*;

public class TocHw3 {
	
	/**
	 * args :
	 * [0] source JSON URL
	 * [1] area(�m����)
	 * [2] Road_Name
	 * [3] year
	*/
	public static void main(String[] args) {
		JSONArray sourceArray = null;
		int sum =0 ,count =0;
		int yearAndMonth =0;

		if(args.length != 4){
			System.out.println("incorrect arguments length ,system will exit");
			System.exit(0);
		}
		
		try {//retrieve JSON data array
			URL url = new URL(args[0]);
			//due to encoding issue ,just use url.openStream() will result in error
			//also ,JSONTokener automatically use BufferedReader inside
			JSONTokener tok = new JSONTokener(new InputStreamReader(url.openStream(),"UTF-8"));
			sourceArray = new JSONArray(tok);
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
		
		yearAndMonth = Integer.parseInt(args[3]);
		yearAndMonth *= 100;//for comparison to mounth ,ex: 101 -> 10100
		
		Pattern pattern = Pattern.compile(".*"+args[2]+".*");
		for(int i=0;i<sourceArray.length();i++){
			//for each JSONObject in array
			JSONObject current;
			try {
				current = sourceArray.getJSONObject(i);
				Matcher m = pattern.matcher(current.getString("�g�a�Ϭq��m�Ϋت��Ϫ��P"));
				if(current.getString("�m����").equals(args[1]) && 
						m.matches() && current.getInt("����~��") >= yearAndMonth){//matches
					sum += current.getInt("�`����");
					count++;
				}
			} catch (JSONException e) {
				e.printStackTrace();
				System.exit(0);
			}	
		}
		System.out.println(sum/count);
	}
}
