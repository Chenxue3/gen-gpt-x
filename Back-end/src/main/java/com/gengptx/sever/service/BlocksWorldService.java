package com.gengptx.sever.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * @author ：xueshanChen
 * @ClassName : BlocksWorldService
 * @description：
 * @version: v1.0
 */

public class BlocksWorldService {
    public JSONObject returnBlocksWorld(HttpServletRequest request) {

        JSONObject jsonObject = new JSONObject();
        JSONArray plans = new JSONArray();
        //put goals in
        jsonObject.put("type", "G0");
        jsonObject.put("name", "GoalMoveTo(x,y)");
        jsonObject.put("attr", getImg("Goal"));
        JSONObject conds = new JSONObject();
        conds.put("sub", "x = a, y = b");
        conds.put("goal-condition", "on(x, y)");
        jsonObject.put("conds", conds.toJSONString());

        //generate plans
        //p0
        JSONObject p0 = new JSONObject();
        p0.put("type", "P0");
        p0.put("name", "PlanKeepOn(x, y)");
        p0.put("attr", getImg("Plan"));
        JSONArray children_P0 = new JSONArray();
        JSONObject conds_P0 = new JSONObject();
        conds_P0.put("sub", "x = a, y = b");
        conds_P0.put("pre-condition", "on(x, y), y != table");
        p0.put("conds", conds_P0.toJSONString());
        //A0
        JSONObject A0 = new JSONObject();
        A0.put("name", "ActionDummy");
        A0.put("type", "A0");
        A0.put("attr", getImg("Action"));
        JSONObject conds_A0 = new JSONObject();
        conds_A0.put("pre-condition", "none");
        conds_A0.put("post-condition", "none");
        A0.put("conds",conds_A0.toJSONString());
        children_P0.add(A0);
        p0.put("children", children_P0);
        plans.add(p0);

        //p1
        JSONObject p1 = new JSONObject();
        p1.put("type", "P1");
        p1.put("name", "PlanMoveToClear(x, y)");
        p1.put("attr", getImg("Plan"));
        JSONArray children_P1 = new JSONArray();
        JSONObject conds_P1 = new JSONObject();
        conds_P1.put("sub", "x = a, y = b");
        conds_P1.put("pre-condition", "on(z, x), clear(y)");
        p1.put("conds", conds_P1.toJSONString());

        //G1
        JSONObject G1 = generateGoalToTable("G1");
        children_P1.add(G1);
        //G2
        JSONObject G2 = generateGoalTo("G2");
        children_P1.add(G2);

        //end of p1
        p1.put("children", children_P1);
        plans.add(p1);

        //p2
        JSONObject p2 = new JSONObject();
        p2.put("type", "P2");
        p2.put("name", "PlanClearMoveTo(x, y)");
        p2.put("attr", getImg("Plan"));
        JSONArray children_P2 = new JSONArray();
        JSONObject conds_P2 = new JSONObject();
        conds_P2.put("sub", "x = a, y = b");
        conds_P2.put("pre-condition", "on(z, y), clear(x)");
        p2.put("conds", conds_P2.toJSONString());

        //G3
        JSONObject G3 = generateGoalToTable("G3");
        children_P2.add(G3);
        //G4
        JSONObject G4 = generateGoalTo("G4");
        children_P2.add(G4);
        //end of p1
        p2.put("children", children_P2);
        plans.add(p2);

        //p3
        JSONObject p3 = new JSONObject();
        p3.put("type", "P3");
        p3.put("name", "PlanMoveTo(x, y)");
        p3.put("attr", getImg("Plan"));
        JSONArray children_P3 = new JSONArray();
        JSONObject conds_P3 = new JSONObject();
        conds_P3.put("sub", "x = a, y = b");
        conds_P3.put("pre-condition", "clear(x), clear(y)");
        p3.put("conds", conds_P3.toJSONString());
        //A1
        JSONObject A1 = new JSONObject();
        A1.put("name", "ActionMove(x, y)");
        A1.put("type", "A1");
        A1.put("attr", getImg("Action"));
        JSONObject conds_A1 = new JSONObject();
        conds_A1.put("pre-condition", "clear(x), clear(y)");
        conds_A1.put("post-condition", "on(x, y)");
        A1.put("conds",conds_A1.toJSONString());
        children_P3.add(A1);
        //end of p3
        p3.put("children", children_P3);
        plans.add(p3);


        //p4
        JSONObject p4 = new JSONObject();
        p4.put("type", "P4");
        p4.put("name", "PlanKeepOnTable(x, y)");
        p4.put("attr", getImg("Plan"));
        JSONArray children_P4 = new JSONArray();
        JSONObject conds_P4 = new JSONObject();
        conds_P4.put("sub", "x = a, y = b");
        conds_P4.put("pre-condition", "clear(x), y == table");
        p4.put("conds", conds_P4.toJSONString());
        //A2
        JSONObject A2 = new JSONObject();
        A2.put("name", "ActionDummy");
        A2.put("type", "A2");
        A2.put("attr", getImg("Action"));
        JSONObject conds_A2 = new JSONObject();
        conds_A2.put("pre-condition", "none");
        conds_A2.put("post-condition", "none");
        A2.put("conds",conds_A2.toJSONString());
        children_P4.add(A2);
        //end of p4
        p4.put("children", children_P4);
        plans.add(p4);

        jsonObject.put("children",plans);
        String jsonString = jsonObject.toJSONString();
        String path = request.getServletContext().getRealPath("/files/");
        path = "D:/FYP/Development/out/";
        saveDataToFile(path,"BlocksWorldJSON",jsonString);
        return jsonObject;

    }

    /**
     * get the image of each type of the plan
     *
     * @param type
     * @return
     */
    private static String getImg(String type) {
        if (type.equals("Goal")) {
            return "https://upload.wikimedia.org/wikipedia/commons/a/a0/Circle_-_black_simple.svg";
        }
        if (type.equals("Action")) {
            return "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ec/Regular_triangle.svg/800px-Regular_triangle.svg.png";
        }
        if (type.equals("Plan")) {
            return "https://upload.wikimedia.org/wikipedia/commons/thumb/d/dd/Square_-_black_simple.svg/800px-Square_-_black_simple.svg.png";
        }
        return "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpRGYLJ76M2rHNLQdI_r-U4Z5iDv1Jx-ljeQ&usqp=CAU";
    }


    private JSONObject generateGoalTo(String name) {
        JSONObject G1 = new JSONObject();
        G1.put("type", name);
        G1.put("name", "GoalMoveToTable(x)");
        G1.put("attr", getImg("Goal"));
        JSONObject conds_G1 = new JSONObject();
        conds_G1.put("sub", "x = z");
        conds_G1.put("goal-condition", "on(x, table)");
        G1.put("conds", conds_G1.toJSONString());
        return G1;
    }

    private JSONObject generateGoalToTable(String name) {
        JSONObject goal = new JSONObject();
        goal.put("type", name);
        goal.put("name", "GoalMoveTo(x, y)");
        goal.put("attr", getImg("Goal"));
        JSONObject conds = new JSONObject();
        conds.put("sub", "x = a, y = b");
        conds.put("goal-condition", "on(x, y)");
        goal.put("conds", conds.toJSONString());
        return goal;
    }

    /**
     * add json data into the file
     *
     * @param fileName
     * @param data
     */
    private static void saveDataToFile(String path, String fileName, String data) {
        BufferedWriter writer = null;
        File file = new File( path+fileName + ".json");
        //if the file nor exits, create a new one
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //write in
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"));
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("json file store in "+path+ fileName+" success!");
    }
}