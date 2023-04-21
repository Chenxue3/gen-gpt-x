package com.gengptx.sever.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.ArrayList;

/**
 * @author ：xueshanChen
 * @ClassName : CraftWorldService
 * @description：
 * @version: v1.0
 */

public class CraftWorldService {

    public JSONObject createCraftWorld(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fromTo(x1,x2,y1,y2)",createGoalTemp());

        jsonObject.put("createPlank",createPlank());
        jsonObject.put("createRope",createRope());
        jsonObject.put("createStick",createStick());
        ArrayList<String> axeList = new ArrayList<>();
        axeList.add("Iron");
        jsonObject.put("createAxe", createGoals("Axe",axeList,"Stick","Tool-shed"));

        ArrayList<String> bedList = new ArrayList<>();
        bedList.add("Grass");
        jsonObject.put("createBed",createGoals("Bed",bedList,"Plank","Workbench"));

        ArrayList<String> bridgeList = new ArrayList<>();
        bridgeList.add("Iron");
        bridgeList.add("Wood");
        jsonObject.put("createBridge",createGoals("Bridge",bridgeList,"none","Factory"));

        ArrayList<String> clothList = new ArrayList<>();
        clothList.add("Grass");
        jsonObject.put("createCloth",createGoals("Cloth",bedList,"none","Factory"));

        jsonObject.put("createGold",createGettingNode("Gold","Bridge"));
        jsonObject.put("createGem",createGettingNode("Gem","Axe"));

        String jsonString = jsonObject.toJSONString();
        saveDataToFile("craftworld.json",jsonString);
        return  jsonObject;
    }

    //goal
    private JSONObject createGoalTemp(){
        JSONObject goalTemp = createNode("fromTo(x1,x2,y1,y2)","Goal","goal-condition:{x1==x2 , y1==y2}");
        JSONArray plans = new JSONArray();
        //no move
        JSONObject planNoMove = createNode("noMove","Plan","pre-condition: {x1==x2 , y1==y2}");
        JSONArray arrayNoMove = new JSONArray();
        arrayNoMove.add(createNode("ActionDummy","Action","pre-conditions:none; post-conditions:none"));
        planNoMove.put("children",arrayNoMove);
        plans.add(planNoMove);

        //right
        JSONObject planMoveRight = createNode("agentMoveToRight","Plan"," pre-condition:{x2>x1}");
        JSONArray planMoveRightChildren = new JSONArray();
        planMoveRightChildren.add(createNode("agentMoveToRight","Action","pre-conditions:{x2>x1}; post-conditions:{x1==x2}"));
        planMoveRightChildren.add(createNode("fromTo(x1,x2,y1,y2)","Goal","goal-condition:{x1==x2 , y1==y2}"));
        planMoveRight.put("children",planMoveRightChildren);
        plans.add(planMoveRight);

        //left
        JSONObject planMoveLeft = createNode("agentMoveToLeft","Plan"," pre-condition:{x1>x2}");
        JSONArray planMoveLeftChildren = new JSONArray();
        planMoveLeftChildren.add(createNode("agentMoveToLeft","Action","pre-conditions:{x1>x2}; post-conditions:{x1==x2}"));
        planMoveLeftChildren.add(createNode("fromTo(x1,x2,y1,y2)","Goal","goal-condition:{x1==x2 , y1==y2}"));
        planMoveLeft.put("children",planMoveLeftChildren);
        plans.add(planMoveLeft);

        //up
        JSONObject planMoveUp = createNode("agentMoveToUp","Plan"," pre-condition:{y2>y1}");
        JSONArray planMoveUpChildren = new JSONArray();
        planMoveUpChildren.add(createNode("agentMoveToUp","Action","pre-conditions:{y2>y1}; post-conditions:{y1==y2}"));
        planMoveUpChildren.add(createNode("fromTo(x1,x2,y1,y2)","Goal","goal-condition:{x1==x2 , y1==y2}"));
        planMoveUp.put("children",planMoveUpChildren);
        plans.add(planMoveUp);

        //Down
        JSONObject planMoveDown = createNode("agentMoveToDown","Plan"," pre-condition:{y1>y2}");
        JSONArray planMoveDownChildren = new JSONArray();
        planMoveDownChildren.add(createNode("agentMoveToDown","Action","pre-conditions:{y1>y2}; post-conditions:{y1==y2}"));
        planMoveDownChildren.add(createNode("fromTo(x1,x2,y1,y2)","Goal","goal-condition:{x1==x2 , y1==y2}"));
        planMoveDown.put("children",planMoveDownChildren);
        plans.add(planMoveDown);

        goalTemp.put("children",plans);
        return goalTemp;
    }

    //create Plank
    private JSONObject createPlank(){
        JSONObject createRope = createNode("createPlank","Goal","goal-condition: {have(plank)}");
        JSONArray plans = new JSONArray();

        //no action
        JSONObject planNoMove = createNode("PlanDummy","Plan","pre-condition: {have(plank)}");
        JSONArray arrayNoMove = new JSONArray();
        arrayNoMove.add(createNode("ActionDummy","Action","pre-conditions:none; post-conditions:none"));
        planNoMove.put("children",arrayNoMove);
        plans.add(planNoMove);

        //Plank
        JSONObject getGrass = createNode("getWood","Plan"," pre-condition:{!have(wood), at(agent,a,b), at(wood, c, d)}");
        JSONArray getGrassChildren = new JSONArray();
        getGrassChildren.add(createNode("fromTo(x1,x2,y1,y2)","Goal","goal-condition:{x1==x2 , y1==y2}, sub:{x1=a,y1=b,x2=c,y2=d}"));
        getGrassChildren.add(createNode("pickUpWood","Action","pre-condition:{!have(wood), at(agent, a, b), at(wood, c, d),a==c,b==d},post-condition:{have(wood)},post-condition:{have(wood)}"));
        getGrassChildren.add(createNode("createPlank","Goal","goal-condition:{have(plank)}"));
        getGrass.put("children",getGrassChildren);
        plans.add(getGrass);

        //plank
        JSONObject processPlank = createNode("processPlank","Plan"," pre-condition:{have(wood), at(agent,a,b), at(toolshed, c, d)}");
        JSONArray getRopeChildren = new JSONArray();
        getRopeChildren.add(createNode("fromTo(x1,x2,y1,y2)","Goal","goal-condition:{x1==x2 , y1==y2}, sub:{x1=a,y1=b,x2=c,y2=d}"));
        getRopeChildren.add(createNode("processRope","Action","pre-conditions:{have(wood), at(agent,a,b), at(toolshed, c, d), equal(a, c), equal(b, d)}; post-conditions:{have(plank)}"));
        processPlank.put("children",getRopeChildren);
        plans.add(processPlank);

        createRope.put("children",plans);
        return createRope;

    }

    //create Rope
    private JSONObject createRope(){
        JSONObject createRope = createNode("createRope","Goal","goal-condition: {have(rope)}");
        JSONArray plans = new JSONArray();

        //no action
        JSONObject planNoMove = createNode("PlanDummy","Plan","pre-condition: {have(rope)}");
        JSONArray arrayNoMove = new JSONArray();
        arrayNoMove.add(createNode("ActionDummy","Action","pre-conditions:none; post-conditions:none"));
        planNoMove.put("children",arrayNoMove);
        plans.add(planNoMove);

        //grass
        JSONObject getGrass = createNode("getGrass","Plan"," pre-condition:{!have(grass), at(agent,a,b), at(grass, c, d)}");
        JSONArray getGrassChildren = new JSONArray();
        getGrassChildren.add(createNode("fromTo(x1,x2,y1,y2)","Goal","goal-condition:{x1==x2 , y1==y2}, sub:{x1=a,y1=b,x2=c,y2=d}"));
        getGrassChildren.add(createNode("pickUpGrass","Action","pre-condition:{!have(grass), at(agent, a, b), at(grass, c, d),a==c,b==d},post-condition:{have(grass)}, post-condition:{have(grass)}"));
        getGrassChildren.add(createNode("createRope","Goal","goal-condition:{have(rope)}"));
        getGrass.put("children",getGrassChildren);
        plans.add(getGrass);

        //rope
        JSONObject processRope = createNode("processRope","Plan"," pre-condition:{have(grass), at(agent,a,b), at(toolshed, c, d)}");
        JSONArray getRopeChildren = new JSONArray();
        getRopeChildren.add(createNode("fromTo(x1,x2,y1,y2)","Goal","goal-condition:{x1==x2 , y1==y2}, sub:{x1=a,y1=b,x2=c,y2=d}"));
        getRopeChildren.add(createNode("processRope","Action","pre-conditions:{have(grass), at(agent,a,b), at(toolshed, c, d), equal(a, c), equal(b, d)}; post-conditions:{have(rope)}"));
        processRope.put("children",getRopeChildren);
        plans.add(processRope);

        createRope.put("children",plans);
        return createRope;

    }
    //create Stick
    private JSONObject createStick(){
        JSONObject createStick = createNode("createStick","Goal","goal-condition: {have(stick)}");
        JSONArray plans = new JSONArray();

        //no action
        JSONObject planNoMove = createNode("PlanDummy","Plan","pre-condition: {have(stick)}");
        JSONArray arrayNoMove = new JSONArray();
        arrayNoMove.add(createNode("ActionDummy","Action","pre-conditions:none; post-conditions:none"));
        planNoMove.put("children",arrayNoMove);
        plans.add(planNoMove);

        //wood
        JSONObject getWood = createNode("getWood","Plan"," pre-condition:{!have(wood), at(agent,a,b), at(wood, c, d)}");
        JSONArray getWoodChildren = new JSONArray();
        getWoodChildren.add(createNode("fromTo(x1,x2,y1,y2)","Goal","goal-condition:{x1==x2 , y1==y2}, sub:{x1=a,y1=b,x2=c,y2=d}"));
        getWoodChildren.add(createNode("pickUpWood","Action","pre-condition:{!have(wood), at(agent, a, b), at(wood, c, d),a==c,b==d},post-condition:{have(wood)}"));
        getWoodChildren.add(createNode("createStick","Goal","goal-condition:{have(stick)}"));
        getWood.put("children",getWoodChildren);
        plans.add(getWood);

        //stick
        JSONObject processStick = createNode("processStick","Plan"," pre-condition:{have(wood), at(agent,a,b), at(workbench, c, d)}");
        JSONArray getStickChildren = new JSONArray();
        getStickChildren.add(createNode("fromTo(x1,x2,y1,y2)","Goal","goal-condition:{x1==x2 , y1==y2}, sub:{x1=a,y1=b,x2=c,y2=d}"));
        getStickChildren.add(createNode("processStick","Action","pre-conditions:{have(wood), at(agent,a,b), at(workbench, c, d), equal(a, c), equal(b, d)}; post-conditions:{have(stick)}"));
        processStick.put("children",getStickChildren);
        plans.add(processStick);

        createStick.put("children",plans);
        return createStick;

    }

    //create Goals
    private JSONObject createGoals(String goalName, ArrayList<String> sourceName, String sourceNeedBuildName, String positionName){
        JSONObject createAxe = createNode("create"+goalName,"Goal","goal-condition: {have("+goalName+")}");
        JSONArray plans = new JSONArray();

        //no action
        JSONObject planNoMove = createNode("PlanDummy","Plan","pre-condition: {have("+goalName.toLowerCase()+")}");
        JSONArray arrayNoMove = new JSONArray();
        arrayNoMove.add(createNode("ActionDummy","Action","pre-conditions:none; post-conditions:none"));
        planNoMove.put("children",arrayNoMove);
        plans.add(planNoMove);

        //sources
        String preC = new String();

        String init = "have(";
        String tail = "),";
        for(int i = 0; i<sourceName.size();i++) {
            preC = preC+init+sourceName.get(i).toLowerCase()+tail;
            JSONObject getIron = createNode("get" + sourceName.get(i), "Plan", " pre-condition:{!have(" + sourceName.get(i).toLowerCase() + "), at(agent,a,b), at(" + sourceName.get(i).toLowerCase() + ", c, d)}");
            JSONArray getIronChildren = new JSONArray();
            getIronChildren.add(createNode("fromTo(x1,x2,y1,y2)", "Goal", "goal-condition:{x1==x2 , y1==y2}, sub:{x1=a,y1=b,x2=c,y2=d}"));
            getIronChildren.add(createNode("pickUp"+sourceName.get(i), "Action", "pre-condition:{!have(" + sourceName.get(i).toLowerCase() + "), at(agent,a,b), at(" + sourceName.get(i).toLowerCase() + ", c, d), a==c, b==d}, post-condition:{have(" + sourceName.get(i).toLowerCase() + ")}"));
            getIronChildren.add(createNode("create"+goalName, "Goal", "goal-condition:{have(" + goalName + ")}"));
            getIron.put("children", getIronChildren);
            plans.add(getIron);
        }

        //sources which need to be produced
        if(!sourceNeedBuildName.equals("none")) {
            JSONObject getStick = createNode("get" + sourceNeedBuildName, "Plan", " pre-condition:{!have(" + sourceNeedBuildName + "),at(agent,a,b), at(" + sourceNeedBuildName + ",c,d)}");
            JSONArray getStickChildren = new JSONArray();
            getStickChildren.add(createNode("create"+sourceNeedBuildName, "Goal", "goal-condition:{have(" + sourceNeedBuildName.toLowerCase() + ")}"));
            getStickChildren.add(createNode("create"+ goalName, "Goal", "goal-condition:{have(" + goalName.toLowerCase() + "}"));
            getStick.put("children", getStickChildren);
            plans.add(getStick);
        }

        JSONObject processGoal = createNode("process"+goalName,"Plan"," pre-condition:{"+preC+"have("+sourceNeedBuildName.toLowerCase()+ "), at(agent,a,b), at("+positionName.toLowerCase()+", c, d)}");
        JSONArray getChildren = new JSONArray();
        getChildren.add(createNode("fromTo(x1,x2,y1,y2)","Goal","goal-condition:{x1==x2 , y1==y2}, sub:{x1=a,y1=b,x2=c,y2=d}"));

        getChildren.add(createNode("process"+goalName,"Action","pre-conditions:{"+preC+" at(agent,a,b), at("+positionName.toLowerCase()+", c, d), equal(a, c), equal(b, d)}; post-conditions:{have("+goalName.toLowerCase()+")}"));
        processGoal.put("children",getChildren);
        plans.add(processGoal);

        createAxe.put("children",plans);
        return createAxe;

    }

    private JSONObject createGettingNode(String goalName, String toolName){
        JSONObject jsonObject = new JSONObject();
        jsonObject = createNode("create"+goalName,"Goal","goal-condition: {have("+goalName.toLowerCase()+")}");
        JSONArray plans = new JSONArray();
        //no action
        JSONObject planNoMove = createNode("PlanDummy","Plan","pre-condition: {have("+goalName.toLowerCase()+")}");
        JSONArray arrayNoMove = new JSONArray();
        arrayNoMove.add(createNode("ActionDummy","Action","pre-conditions:none; post-conditions:none"));
        planNoMove.put("children",arrayNoMove);
        plans.add(planNoMove);

        //get tool
        JSONObject getTool = createNode("get" + toolName, "Plan", " pre-condition:{!have(" + toolName.toLowerCase() + "),at(agent,a,b), at(" + toolName.toLowerCase() + ",c,d)}");
        JSONArray getStickChildren = new JSONArray();
        getStickChildren.add(createNode("create"+toolName, "Goal", "goal-condition:{have(" + toolName.toLowerCase() + ")}"));
        getStickChildren.add(createNode("create"+ goalName, "Goal", "goal-condition:{have(" + goalName.toLowerCase() + "}"));
        getTool.put("children", getStickChildren);
        plans.add(getTool);

        //get goal
        JSONObject processGoal = createNode("process"+goalName,"Plan","pre-condition: {have("+goalName.toLowerCase()+")}");
        JSONArray getChildren = new JSONArray();
        getChildren.add(createNode("process"+goalName,"Action","pre-conditions:{ have("+toolName.toLowerCase()+")}; post-conditions:{have("+goalName.toLowerCase()+")}"));
        processGoal.put("children",getChildren);
        plans.add(processGoal);

        jsonObject.put("children",plans);
        return jsonObject;
    }

    /**
     *
     * @param name
     * @param type
     * @param conds
     * @return
     */
    private JSONObject createNode(String name, String type, String conds){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type",type);
        jsonObject.put("name",name);
        jsonObject.put("conds", conds);
        jsonObject.put("attr",getImg(type));
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

    /**
     * add json data into the file
     *
     * @param fileName
     * @param data
     */
    private static void saveDataToFile(String fileName, String data) {
        BufferedWriter writer = null;
        File file = new File( fileName);
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
        System.out.println("json file store in "+fileName+" success!");
    }
}
