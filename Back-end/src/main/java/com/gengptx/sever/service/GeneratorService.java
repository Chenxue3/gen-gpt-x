package com.gengptx.sever.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.gengptx.sever.gpt.generators.GPTGenerator;
import com.gengptx.sever.gpt.generators.SynthGenerator;
import com.gengptx.sever.gpt.generators.XMLWriter;
import com.gengptx.sever.gpt.structure.GoalNode;
import com.gengptx.sever.gpt.structure.Literal;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * @author ：xueshanChen
 * @ClassName : GeneratorService
 * @description：
 * @version: v1.0
 */

public class GeneratorService {
    public JSONObject generateGpt(HttpServletRequest request,int seed, int sy_depth, int num_tree, int sy_num_goal, int sy_num_plan, int sy_num_action, int sy_num_var,
                                  int sy_num_selected, double sy_prob_leaf) {
//        check the value
        JSONObject jsonObject = new JSONObject();
        if(sy_depth <= 0) {
           jsonObject.put("msg","Depth must be greater than 0");
            return jsonObject;
        }
        if(sy_num_goal <= 0) {
           jsonObject.put("msg","Maximum number of goals must be greater than 0");
            return jsonObject;
        }
        if(sy_num_plan <= 0) {
           jsonObject.put("msg","Maximum number of plans must be greater than 0");
            return jsonObject;
        }
        if(sy_num_action < 0) {
           jsonObject.put("msg","Maximum number of actions must be greater than 0");
            return jsonObject;
        }
        if(sy_num_var <= 0) {
           jsonObject.put("msg","Total number of variables must be greater than 0");
            return jsonObject;
        }
        if(sy_num_selected <= 0) {
           jsonObject.put("msg","The number of selected variables must be greater than 0");
            return jsonObject;
        }
        if(sy_num_selected > sy_num_var)
        {
           jsonObject.put("msg","The number of selected variables must be less than or equal to the total number of variables");
            return jsonObject;
        }
        if(sy_prob_leaf < 0 || sy_prob_leaf > 1){
           jsonObject.put("msg","probability must be between 0 and 1");
            return jsonObject;
        }
        if(num_tree <= 0) {
           jsonObject.put("msg","Total number of goal-plan tree must be greater than 0");
            return jsonObject;
        }



        /**
         * the generator
         */
        GPTGenerator gen = new SynthGenerator(seed, sy_depth, num_tree, sy_num_goal, sy_num_plan, sy_num_action, sy_num_var, sy_num_selected, sy_prob_leaf);


        HashMap<String, Literal> environment = gen.genEnvironment();
        // generate the tree
        ArrayList<GoalNode> goalForests = new ArrayList<>();

        // write the set of goal plan tree to an XML file
        ArrayList<JSONObject> jsonForests = new ArrayList<>();

        JSONObject originalJSON = new JSONObject();
        for(Integer k = 0; k < num_tree; k++)
        {
            goalForests.add(gen.genTopLevelGoal(k));
        }



//        save data into files
        String path = request.getServletContext().getRealPath("/files/");
        path = "D:/FYP/Development/out/";
        //save XML file
        XMLWriter wxf = new XMLWriter();
        wxf.CreateXML(environment, goalForests, path+"xmlGPT.xml");

        //convert json to target format
        for (Integer i = 0; i < goalForests.size(); i++) {
            JSONObject jsonItem = (JSONObject) JSONObject.toJSON(goalForests.get(i));
            originalJSON.put("T"+i.toString(),jsonItem);
            JSONObject jsonItemOut = JSONConverter(jsonItem);
            jsonObject.put(i.toString(),jsonItemOut);
        }

        //save json file
        String jsonString = originalJSON.toJSONString();
        saveDataToFile(path,"jsonGPT",jsonString);



        return jsonObject;

    }


    /**
     * convert the original json object to the target one
     * @param obj
     * @return
     */
    private static JSONObject JSONConverter(JSONObject obj) {
        JSONObject res = new JSONObject();
        String name = obj.getString("name");
        String type = getType(name);

        res.put("name", name);
        res.put("type", type);
        res.put("attr", getImg(type));

        JSONObject conds = new JSONObject();
        JSONArray children = new JSONArray();

        // Add conditions to the "conds" object
        if (obj.containsKey("goalConds")) {
            JSONArray goalConds = obj.getJSONArray("goalConds");
            for (int i = 0; i < goalConds.size(); i++) {
                JSONObject cond = goalConds.getJSONObject(i);
                conds.put("goal-conditions", cond);
            }
        }
        if (obj.containsKey("pre")) {
            JSONArray preConds = obj.getJSONArray("pre");
            for (int i = 0; i < preConds.size(); i++) {
                JSONObject cond = preConds.getJSONObject(i);
                conds.put("pre-condition", cond);
            }
        }
        if (obj.containsKey("post")) {
            JSONArray postConds = obj.getJSONArray("post");
            for (int i = 0; i < postConds.size(); i++) {
                JSONObject cond = postConds.getJSONObject(i);
                conds.put("post-condition", cond);
            }
        }
        if(obj.containsKey("preC")){
            JSONArray preC = obj.getJSONArray("preC");
            for (int i = 0; i < preC.size(); i++) {
                JSONObject cond = preC.getJSONObject(i);
                conds.put("pre-condition", cond);
            }
        }
        if(obj.containsKey("postC")){
            JSONArray postC = obj.getJSONArray("postC");
            for (int i = 0; i < postC.size(); i++) {
                JSONObject cond = postC.getJSONObject(i);
                conds.put("post-condition", cond);
            }
        }
        // Add children to the "children" array
        if (type.equals("Goal") && obj.containsKey("plans")) {
            JSONArray plans = obj.getJSONArray("plans");
            for (int i = 0; i < plans.size(); i++) {
                JSONObject plan = plans.getJSONObject(i);
                children.add(JSONConverter(plan));
            }
        }
        if (type.equals("Plan")) {
            JSONArray planBody = obj.getJSONArray("planBody");
            for (int i = 0; i < planBody.size(); i++) {
                JSONObject planStep = planBody.getJSONObject(i);
                children.add(JSONConverter(planStep));
            }
        }

        res.put("conds", conds.toJSONString());
        res.put("children", children);
        return res;
    }


    /**
     * get the image of each type of the plan
     * @param type
     * @return
     */
    private static String getImg(String type){
        if(type.equals("Goal")){
            return"https://upload.wikimedia.org/wikipedia/commons/a/a0/Circle_-_black_simple.svg";
        }
        if(type.equals("Action")){
            return "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ec/Regular_triangle.svg/800px-Regular_triangle.svg.png";
        }
        if(type.equals("Plan")){
            return "https://upload.wikimedia.org/wikipedia/commons/thumb/d/dd/Square_-_black_simple.svg/800px-Square_-_black_simple.svg.png";
        }
        return "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpRGYLJ76M2rHNLQdI_r-U4Z5iDv1Jx-ljeQ&usqp=CAU";
    }

    /**
     * get the type
     * @param name
     * @return
     */
    private static String getType(String name) {
        if (name.matches("^T\\d+-G\\d+$")) {
            return "Goal";
        } else if (name.matches("^T\\d+-A\\d+$")) {
            return "Action";
        } else if (name.matches("^T\\d+-P\\d+$")) {
            return "Plan";
        } else {
            return "";
        }
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
