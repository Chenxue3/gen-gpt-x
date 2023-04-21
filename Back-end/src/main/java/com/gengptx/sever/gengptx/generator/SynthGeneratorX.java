package com.gengptx.sever.gengptx.generator;

import com.gengptx.sever.gengptx.structure.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * @author ：xueshanChen
 * @ClassName : SynthGeneratorX
 * @description：
 * @version: v1.0
 */

public class SynthGeneratorX extends AbstractGenerator{

    /** Default values */
    static final int def_depth = 3,
            def_num_goal = 3,
            def_num_plan = 3,
            def_num_action = 3,
            def_num_var = 10, // now it means the number of variables in each group
            def_num_selected = 6, //the selected variables in each group
            def_num_group = 6;
    static final double def_prob_leaf = 0d;
    /** id of the tree */
    private int id;

    /** total number of goals in this goal plan tree */
    private int treeGoalCount;

    /** total number of plans in this goal plan tree */
    private int treePlanCount;

    /** total number of actions in this goal plan tree */
    private int treeActionCount;

    /** random generators */
    final private Random rm = new Random();

    /** depth of the tree */
    final private int tree_depth;

    /** number of trees */
    final private int num_tree;

    /** number of goals */
    final private int num_goal;

    /** number of plans */
    final private int num_plan;

    /** number of actions */
    final private int num_action;

    /** number of variables for each group*/
    final private int num_var;

    /** number of environment variables that can be used as post-condition of actions in each group */
    final private int num_sel;

    /** probabilty of a plan being leaf plan */
    final private double prob;

    final private int num_group;

    /** the set of variables selected*/
    private ArrayList<Integer> selected_indexes;

    /** the set of irrelevant literals*/
    private ArrayList<Literal> is;



    public SynthGeneratorX(int tree_depth, int num_tree, int num_goal, int num_plan, int num_action, int num_var, int num_sel, double prob, int num_group) {
        this.tree_depth = tree_depth;

        this.num_tree = num_tree;
        this.num_goal = num_goal;
        this.num_plan = num_plan;
        this.num_action = num_action;
        this.num_var = num_var;
        // the selected variables are the variables that can be used as post-condition of actions
        // in GenGPT-x, the selected variables are number of groups and the number of variables that can be selected in each group
        this.num_sel = num_sel*num_group;
        this.prob = prob;
        this.num_group = num_group;
    }

    @Override
    /**
     * Generate environment
     * @return the generated environment*/
    public HashMap<String, Literal> genEnvironment(){
        environment = new HashMap<>();
        Literal workingLit;
        ArrayList<String> nodeList = generateGroupingTree(this.num_group);
        for (int i = 0; i < num_tree; i++) {

            // generate goal literals, all of which are false initially
            // the generated TLG are all in the highest group
            workingLit = new Literal("G-" + i, false, "P1");
            environment.put(workingLit.getId(), workingLit);
        }
        int id = 0;
        // generate all the  environment literals with their initial value

        for (int j = 0; j < nodeList.size(); j++) {
            String group = nodeList.get(j);
            for (int i = 0; i < num_var; i++) {
                boolean v = rm.nextBoolean();
                //all the V's id are from 0 to num_var*num_group- 1

                workingLit = new Literal("v" + id, v, group);
                environment.put(workingLit.getId(), workingLit);
                id++;
            }
        }

        return environment;
    }

    @Override
    public GoalNode genTopLevelGoal(int index) {
        // Set the generator id
        this.id = index;
        // Set the counters for this tree to 0
        this.treeGoalCount = 0;
        this.treePlanCount = 0;
        this.treeActionCount = 0;
        // Generate the environment, where from (0.num_sel) is selected, and other are irrelevant variables
        ArrayList<Literal> selected = selectVar(this.num_sel);
        // create the set of actions, which are been selected from the environment
        ArrayList<Literal> actL = new ArrayList<>(selected.subList(0,this.num_sel));

        // create the clip set of selected literals
        for(int i = 0; i < this.num_sel; i++){
            Literal cu = actL.get(i).clone();
            cu.setState(!actL.get(i).getState());
            actL.add(cu);
        }
        // create the set of irrelevant literals
        this.is = new ArrayList<>(selected.subList(this.num_sel,selected.size()));
        // the goal-condition
        ArrayList<Literal> gcs = new ArrayList<>();
        // add the goal condition
        gcs.add(new Literal("G-" + index, true, "P1"));

        GoalNode tpg = createGoal(0, actL, new ArrayList<>(), gcs);
        return tpg;
    }

    /**
     * Select a set of variables from the environment
     * @param depth the depth of the tree
     * @param as the set of actions
     * @param ps the set of plans
     * @param gcs  the set of goal conditions
     * @return the generated goal node
     */
    private GoalNode createGoal(int depth, ArrayList<Literal> as, ArrayList<Literal> ps, ArrayList<Literal> gcs){
        // create the goal node
        GoalNode goalNode = new GoalNode("T" + this.id + "-G" + this.treeGoalCount++);
        // generate all plans to achieve this goal
        ArrayList<PlanNode> plans = new ArrayList<>();
        // clone the irrelevant literals, we assume the number of literals in potential is greater than or equals to
        // the number of plans need to be generated, these conditions are treated as pure environment variables which
        // cannot be affected by the GPT itself (i.e., can be changed by the environment itself or other intentions)
        ArrayList<Literal> potential = (ArrayList<Literal>) is.clone();
        // create each plan one by one
        for(int i = 0; i < this.num_plan; i++){
            // generate its precondition (context condition), the p-effect part
            ArrayList<Literal> prec = new ArrayList<>(ps);
            // if there are pure environment conditions remains
            if(potential.size() > 0){
                if (prec.size() == 2){
                    prec.remove(1);
                }
                // randomly select a pure environmental condition
                int j = rm.nextInt(potential.size());
                // add it to the pre-condtion of this plan
                prec.add(potential.get(j));
                // remove it from the set of possible environmental literals
                potential.remove(j);
            }

            // create the plan
            PlanNode plan;
            // each plan has l% chance to be a leaf plan
            if(rm.nextDouble() < this.prob){
                // if it is a leaf plan, its depth is set to the maximum
                plan = createPlan(this.tree_depth - 1, as, prec, gcs);
            }else {
                plan = createPlan(depth, as, prec, gcs);
            }
            // add it to the set of plans
            plans.add(plan);
        }
        // attach all plans to the goal
        goalNode.getPlans().addAll(plans);
        // add its goal-condition
        goalNode.getGoalConds().addAll(gcs);
        // return the goal node
        return goalNode;
    }

    /**
     * a function to generate plans to achieve a particular goal
     * @param depth the depth of this plan
     * @param as the set of conditions that can be postcondition of actions
     * @param prec the precondition of this context condition
     * @param gcs the goal condition this plan is going to achieve
     * @return the plan
     */
    private PlanNode createPlan(int depth, ArrayList<Literal> as, ArrayList<Literal> prec, ArrayList<Literal> gcs){
        PlanNode planNode = new PlanNode("T" + this.id + "-P" + this.treePlanCount++);

        // initialise the plan body
        ArrayList<Node> planbody = new ArrayList<>();
        // the number of steps in a plan
        int stepnum;
        // if it is a leaf plan then it only contains actions
        if(depth == this.tree_depth - 1){
            stepnum = this.num_action;
        }
        // otherwise, it contains both actions and subgoals
        else{
            stepnum = this.num_action + this.num_goal;
        }

        // initialise the planbody, we assume they are all actions at first
        ArrayList<ActionNode> steps = new ArrayList<>();

        // create the list of execution steps (actions) based on p-effect rules, and return the resulting post-condition
        ArrayList<Literal> postc = createPlanBody(stepnum, prec, gcs, as, steps);
        // assign type for each step, i.e., in fact not all steps are actions
        ArrayList<Boolean> types = assignPosition(stepnum);
        // calculate the safe conditions for subgoals
        ArrayList<Literal> safeC = safeCondition(steps, as);

        // create each action and subgoal
        for(int i = 0; i < types.size(); i++){
            // if it is an action
            if(types.get(i)){
                // create the action
                ActionNode actionNode = new ActionNode("T" + this.id + "-A" + this.treeActionCount++, steps.get(i).getPreC(),
                        steps.get(i).getPostC());
                // and add it to the plan body
                planbody.add(actionNode);
            }
            // if it is a subgoal
            else{
                // remove the goal-condition of a subgoal from the plan's postcondition
                ArrayList<Literal> pc = steps.get(i).getPostC();
                for(int m = 0; m < pc.size(); m++){
                    for(int n = 0; n < postc.size(); n++){
                        if(postc.get(n).getId().equals(pc.get(m).getId()) &&
                                postc.get(n).getState() == pc.get(m).getState()){
                            postc.remove(n);
                            break;
                        }
                    }
                }
                // create the subgoal
                GoalNode subgoal = createGoal(depth+1, safeC, steps.get(i).getPreC(), steps.get(i).getPostC());
                // add the subgoal to the plan body
                planbody.add(subgoal);
            }
        }

        // add these to its plan body
        planNode.getPlanBody().addAll(planbody);
        // add the context condition
        planNode.getPre().addAll(prec);

        // remove the postcondition
        for(int m = 0; m < prec.size(); m++){
            for(int n = 0; n < postc.size(); n++){
                if(postc.get(n).getId().equals(prec.get(m).getId()) &&
                        postc.get(n).getState() == prec.get(m).getState()){
                    postc.remove(n);
                    break;
                }
            }
        }
        return planNode;

    }

    /**
     * a function to create a list execution steps in a plan body (we assume all these steps are actions)
     * @param stepNum the number of steps
     * @param prec the context condition of the plan
     * @param gcs the goal condition this plan is going to achieve
     * @param as the set of conditions that can be the postcondition of the actions
     * @param steps the initially empty plan body
     * @return
     */
    private ArrayList<Literal> createPlanBody(int stepNum, ArrayList<Literal> prec, ArrayList<Literal> gcs, ArrayList<Literal> as, ArrayList<ActionNode> steps){
        // current states, copied from the precondition of the plan
        ArrayList<Literal> current = new ArrayList<>();
        for(int i = 0; i < prec.size(); i++){
            current.add(prec.get(i));
        }
        // possible action literals copied from as, we also ensure that there is no action make the current state true
        ArrayList<Literal> actionLiteral = new ArrayList<>(as);
        // construct each step
        for(int i = 0; i < stepNum; i++){
            // the precondition of the action
            ArrayList<Literal> precondition = new ArrayList<>();
            // the precondition of the first step is the same as the precondition of this plan
            if(i == 0){
                precondition = prec;
            }
            // if it is not then randomly select a literal from the current state.
            else {
                // select a literal
                int sx = rm.nextInt(current.size());
                // ignore the precondition that already appears or this is not a sub-group of the first precondition
                while(precondition.contains(current.get(sx))){
                    for (Literal l : precondition
                         ) {
                        if(l.isSubGroup(current.get(sx))){
                            sx = rm.nextInt(current.size());
                            break;
                        }
                    }
                }
                // add it to the set of preconditions
                precondition.add(current.get(sx));
            }

            // the postcondition of the action
            ArrayList<Literal> postcondition = new ArrayList<>();
            // if this step is the last action, then it has the goal-condition as its postcondition
            if(i == stepNum - 1){
                postcondition = gcs;
            }
            // otherwise, the postcondition is randomly generated apart from the last action
            else{
                // randomly select a postcondition
                int index = rm.nextInt(actionLiteral.size());
                Literal p = actionLiteral.get(index);
                postcondition.add(p);
                // update the current state
                updateCurrentLiterals(current, p);
                // update the set of action
                updateActionLiterals(actionLiteral, p);
            }
            // create the corresponding action
            ActionNode action = new ActionNode("", precondition, postcondition);
            steps.add(action);
        }

        // add the goal-condition
        for(int i = 0; i < gcs.size(); i++){
            current.add(gcs.get(i));
        }
        return current;
    }

    /**
     * checks whether a literal l is in ls
     * @param ls
     * @param l
     * @return
     */
    private boolean contains(ArrayList<Literal> ls, Literal l){
        for(int i = 0; i < ls.size(); i++){
            if(ls.get(i).getId().equals(l.getId()) && ls.get(i).getState() == l.getState())
                return true;
        }
        return false;
    }

    /**
     * update the current state based on a literal l. If a literal l(its negation) is in ls, then remove it.
     * Add l in the tail of this list
     * @param ls
     * @param l
     */
    private void updateCurrentLiterals(ArrayList<Literal> ls, Literal l){
        for(int i = 0; i < ls.size(); i++){
            if(ls.get(i).getId().equals(l.getId())){
                ls.remove(i);
                break;
            }
        }
        ls.add(l);
    }

    /**
     * update the list of action literals. If a literal l is achieved, then remove l from ls and add its negation to ls
     * @param ls
     * @param l
     */
    private void updateActionLiterals(ArrayList<Literal> ls, Literal l){
        int index = -1;
        for(int i = 0; i < ls.size(); i++){
            if(ls.get(i).getId().equals(l.getId())){
                // if they are exactly the same
                if(ls.get(i).getState() == l.getState()){
                    // if its negation has not been found yet
                    if(index == -1){
                        index = i;
                    }
                    ls.remove(i);
                    break;
                }
                // if its negation was found
                else{
                    index = - 2;
                }
            }
        }

        if(index != -2){
            for(int i = index; i < ls.size(); i++){
                if(ls.get(i).getState() == l.getState()){
                    index = -2;
                    break;
                }
            }
        }

        if(index != -2) {
            ls.add(new Literal(l.getId(), !l.getState(),l.getGroup()));
        }
    }


    /**
     * assign the types for each execution steps
     * @param stepNum
     * @return a list of boolean indicating the type of each step
     */
    private ArrayList<Boolean> assignPosition(int stepNum){
        // a list of booleans
        ArrayList<Boolean> positions = new ArrayList<>();
        for(int i = 0; i < stepNum; i++){
            positions.add(true);
        }
        if(stepNum != this.num_action){
            ArrayList<Integer> goal_pos = new ArrayList<>();
            while (goal_pos.size() < this.num_goal){
                int index = rm.nextInt(stepNum-2);
                if(!goal_pos.contains(index+1)){
                    goal_pos.add(index+1);
                    positions.set(index+1,false);
                }
            }
        }
        return positions;
    }

    /**
     * @param steps
     * @param conds
     * @return a list of safe literals which won't cause any conflicts
     */
    private ArrayList<Literal> safeCondition(ArrayList<ActionNode> steps, ArrayList<Literal> conds){
        // clone the current action literals
        ArrayList<Literal> actionLiteral = (ArrayList<Literal>) conds.clone();

        // remove all conflicting conditions
        removeConflicting(actionLiteral, steps.get(0).getPreC());
        for(int i = 0; i < steps.size(); i++){
            removeConflicting(actionLiteral, steps.get(i).getPostC());
        }
        return actionLiteral;
    }

    /**
     * remove all the conflicting literals
     * @param ls
     * @param l
     */
    private void removeConflicting(ArrayList<Literal> ls, ArrayList<Literal> l){
        int index = ls.size();
        for(int j = 0; j < l.size(); j++){
            for(int i = 0; i < ls.size(); i++){
                if(ls.get(i).getId().equals(l.get(j).getId())) {
                    ls.remove(i);
                    index = i;
                }
            }
            for(int i = index; i < ls.size(); i++){
                if(ls.get(i).getId().equals(l.get(j).getId())) {
                    ls.remove(i);
                    break;
                }
            }
        }

    }



    /**
     * Generate a grouping tree
     * @param n the number of nodes in the tree
     * @return the generated grouping tree
     */
    public static ArrayList<String> generateGroupingTree(int n) {

        ArrayList<String> nodeList = new ArrayList<>();
        for (int i = 1; i < n+1; i++) {
            String str = toBinary(i);
            nodeList.add("P"+str);
        }

        return nodeList;
    }

    /**
     * Convert a decimal number to binary
     * @param decimal the decimal number
     * @return the binary number
     */
    public static String toBinary(int decimal) {
        if (decimal == 0) {
            return "0";
        }
        StringBuilder binary = new StringBuilder();
        while (decimal > 0) {
            binary.append(decimal % 2 + "-");
            decimal /= 2;
        }
        return binary.reverse().toString();
    }


    /**
     * Generate a random grouping tree
     * @param m the number of variables to be selected
     * @return a list of literals that are selected
     */
    private ArrayList<Literal> selectVar(int m){
        this.selected_indexes = new ArrayList<>();
        // the list of selected indexes
        while (selected_indexes.size() < m){
            int index = rm.nextInt(this.num_var*this.num_group);
            if(!selected_indexes.contains(index)){
                selected_indexes.add(index);
            }
        }
        // return the corresponding literal in the current environment
        ArrayList<Literal> result = new ArrayList<>();
        // the set of literals that are not selected
        ArrayList<Literal> irr = new ArrayList<>();

        for(int i = 0; i < this.num_var*num_group; i++){
            // if the index of this literal is selected
            if(this.selected_indexes.contains(i)){
                result.add(environment.get("v" + i));
            }
            // otherwise, this literal is categorised as irrelevant
            else {
                irr.add(environment.get("v" + i));
            }

        }
        result.addAll(irr);
        return  result;
    }



}
