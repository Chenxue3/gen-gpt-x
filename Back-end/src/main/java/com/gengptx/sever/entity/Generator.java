package com.gengptx.sever.entity;

/**
 * @author ：xueshanChen
 * @ClassName : Parameter
 * @description：
 * @version: v1.0
 */

public class Generator {
//    number of seed
    private int seed;
//    max depth of the GPT
    private int sy_depth;
//    number of trees
    private int sy_num_tree;
//    number of goals in each plan
    private int sy_num_goal;
//    number of plans for each goal
    private int sy_num_plan;
//    number of actions
    private int sy_num_action;
//    number of variables
    private int sy_num_var;
//    number of selected variables
    private int sy_num_selected;
//    probability of a node becomes a leaf node
    private double sy_prob_leaf;

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public int getSy_depth() {
        return sy_depth;
    }

    public int getSy_num_tree() {
        return sy_num_tree;
    }

    public void setSy_num_tree(int sy_num_tree) {
        this.sy_num_tree = sy_num_tree;
    }

    public void setSy_depth(int sy_depth) {
        this.sy_depth = sy_depth;
    }

    public int getSy_num_goal() {
        return sy_num_goal;
    }

    public void setSy_num_goal(int sy_num_goal) {
        this.sy_num_goal = sy_num_goal;
    }

    public int getSy_num_plan() {
        return sy_num_plan;
    }

    public void setSy_num_plan(int sy_num_plan) {
        this.sy_num_plan = sy_num_plan;
    }

    public int getSy_num_action() {
        return sy_num_action;
    }

    public void setSy_num_action(int sy_num_action) {
        this.sy_num_action = sy_num_action;
    }

    public int getSy_num_var() {
        return sy_num_var;
    }

    public void setSy_num_var(int sy_num_var) {
        this.sy_num_var = sy_num_var;
    }

    public int getSy_num_selected() {
        return sy_num_selected;
    }

    public void setSy_num_selected(int sy_num_selected) {
        this.sy_num_selected = sy_num_selected;
    }

    public double getSy_prob_leaf() {
        return sy_prob_leaf;
    }

    public void setSy_prob_leaf(double sy_prob_leaf) {
        this.sy_prob_leaf = sy_prob_leaf;
    }


}
