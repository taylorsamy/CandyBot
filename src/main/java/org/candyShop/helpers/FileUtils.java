package org.candyShop.helpers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public class FileUtils {

    public static JSONObject readJSON(String file) {
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("src/main/resources/" + file + ".json")) {

            return (JSONObject) parser.parse(reader);


        } catch (IOException | ParseException e) {
            System.out.println("no file");
            return null;
        }
    }


    public static void writeJSON(String file, String memberID, int content) {
        JSONObject obj = readJSON(file);
        if (obj == null) {
            obj = new JSONObject();
            obj.put("Name", "CandyLand");
            JSONArray list = new JSONArray();
            obj.put("Members", list);
        }

        JSONArray list = (JSONArray) obj.get("Members");
        JSONObject existingMember = null;
        Long tempContent = 0L;

        for (Object o : list) {

            if (o instanceof JSONObject) {
                if (((JSONObject) o).get("MemberID").equals(memberID)) {
                    existingMember = (JSONObject) o;
                    tempContent = (Long) existingMember.get("Content");
                }
            }
        }


        list.remove(existingMember);

        JSONObject member = new JSONObject();
        member.put("MemberID", memberID);
        member.put("Content", tempContent + content);
        list.add(member);
        obj.put("Members", list);

        try (FileWriter fw = new FileWriter("src/main/resources/" + file + ".json")) {
            fw.write(obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
