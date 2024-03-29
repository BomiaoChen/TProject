package rpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import entity.Item;

import org.json.JSONArray;

public class RpcHelper {
	
	// Parses a JSONObject from http request.
	public static JSONObject readJSONObject(HttpServletRequest request) {
		StringBuilder sBuilder = new StringBuilder();
		try (BufferedReader reader = request.getReader()) {
			String line = null;
			while((line = reader.readLine()) != null) {
				sBuilder.append(line);
			}
			return new JSONObject(sBuilder.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JSONObject();
	}
	
	//write a JSONArray to http response.
	public static void writeJSONArray(HttpServletResponse response, JSONArray array) throws IOException{
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		out.print(array);
		out.close();
	}
	
	//write a JSONObject to http ressponse.
	public static void writeJsonObject(HttpServletResponse response, JSONObject obj) throws IOException {
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		out.print(obj);
		out.close();
	}
	
	public static JSONArray getJSONArray(List<Item> items ) {
		JSONArray result = new JSONArray();
		try {
			for (Item item : items ) {
				result .put( item .toJSONObject());
			}
		} catch (Exception e ) {
			e .printStackTrace();
		}
		return result ;
	}

}
