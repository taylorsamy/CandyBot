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
            e.printStackTrace();
            return null;
        }
    }


    public static void writeJSON(String file, String memberID, String messageTime) {
        JSONObject obj = readJSON(file);

        if (obj == null) {
            obj = new JSONObject();
            obj.put("name", "CandyLand");
            JSONArray list = new JSONArray();
            obj.put("members", list);
        }

        System.out.println(obj.get("name"));


        JSONArray list = (JSONArray) obj.get("members");

        JSONObject existingMember = null;

        for (Iterator i = list.iterator(); i.hasNext(); ) {

            Object o = i.next();
            if (o instanceof JSONObject) {
                if (((JSONObject) o).get("MemberID").equals(memberID)) {

                    existingMember = (JSONObject) o;
                }
            }


        }

        list.remove(existingMember);

        JSONObject member = new JSONObject();
        member.put("MemberID", memberID);
        member.put("Time", messageTime);
        list.add(member);

        obj.put("members", list);

        try (FileWriter fw = new FileWriter("src/main/resources/" + file + ".json")) {
            fw.write(obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    public static void writeJSON(String file, String memberID) {
//        JSONObject obj = readJSON(file);
//
//        if (obj == null) {
//            obj = new JSONObject();
//            obj.put("name", "CandyLand");
//            JSONArray list = new JSONArray();
//            obj.put("members", list);
//        }
//
//
//        JSONArray list = (JSONArray) obj.get("members");
//
//        JSONObject existingMember = null;
//
//        for (Iterator i = list.iterator(); i.hasNext(); ) {
//
//            Object o = i.next();
//            if (((JSONObject) o).get("MemberID").equals(memberID)) {
//                System.out.println(memberID);
//                existingMember = (JSONObject) o;
//            }
//        }
//
//        System.out.println(existingMember);
//
//        Integer count = 0;
//        if (existingMember == null) {
//             existingMember = new JSONObject();
//            existingMember.put("MemberID", memberID);
//            existingMember.put("count", count);
//        } else {
//            list.remove(existingMember);
//            existingMember.put("count", (Integer)existingMember.get("count") + 1);
//        }
//
//        System.out.println(list.toString());
//
//        list.add(existingMember);
//        System.out.println(existingMember);
//        System.out.println(list.toString());
//        obj.put("members", list);
//
//        try (FileWriter fw = new FileWriter("src/main/resources/" + file + ".json")) {
//            fw.write(obj.toJSONString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
}
