/*
 * Copyright 2016 Yuan Yao
 * University of Nottingham
 * Email: yvy@cs.nott.ac.uk (yuanyao1990yy@icloud.com)
 *
 * Modified 2019 IPC Committee
 * Contact: https://www.intentionprogression.org/contact/
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details 
 *  <http://www.gnu.org/licenses/gpl-3.0.html>.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.gengptx.sever.gengptx.generator;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import com.gengptx.sever.gengptx.structure.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @version 2.0
 */
public class XMLWriter
{

	Literal curParent;
	public XMLWriter()
	{

	}

	/**
	 * Write the rich environment and GPT forest
	 * @param environment The full passed environment
	 * @param goalForests The GPT forest
	 * @param path The file to write to
	 */
	public void CreateXML(HashMap<String, Literal> environment, ArrayList<GoalNode> goalForests, String path) {
		try
		{
			Element forest = new Element("Forest");
			Document document = new Document(forest);
			// Small, short forest of variables
			Element environmentElement = new Element("Environment");
			// Write all the variables to this element

			//copy a environment
			HashMap<String, Literal> environmentVar = new HashMap<String, Literal>();
			for (int i = 0; i < environment.size(); i++) {
				if(environment.get("v"+i)!=null) {
					writeEnvVar(environment.get("v"+i), environmentElement);
				}
				if (environment.get("G-"+i)!=null) {
					environmentVar.put("G-" + i, environment.get("G-" + i));
				}

			}
			for (Literal l : environmentVar.values()) {
				writeEnvVar(l, environmentElement);
			}
			this.curParent = new Literal("Predictor",false,"P");
			// Add the environment element to the XML
			forest.addContent(environmentElement);
			// write each top-level goals
			for (GoalNode gl : goalForests) {
				writeGoal(gl, forest);
			}
			XMLOutputter xmlOutputer = new XMLOutputter();
			xmlOutputer.setFormat(Format.getPrettyFormat());
			xmlOutputer.output(document, new FileWriter(path));
			System.out.println("XML File was created successfully!");
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}

	private void writePredictor(HashMap<String,Literal> environment, int curIndex, Element parent){
		if (curIndex == environment.size()-1){
			Element literal = new Element("Literal");
			literal.setAttribute("name", environment.get("v"+curIndex).getId());
			literal.setAttribute("value", environment.get("v"+curIndex).getState()+"");
			literal.setAttribute("group", environment.get("v"+curIndex).getGroup()+"");
			parent.addContent(literal);
			return;
		}

		int next = curIndex +1;
		// get this environment and the next
		Literal curEnv = environment.get("v"+curIndex);
		Literal nextEnv = environment.get("v"+next);

		// if the next environment is a subgroup of the current environment
		// 如果下一个环境是当前环境的子组，则将其添加到当前环境的子组中
		if(curEnv.isSubGroup(this.curParent)){
			if(curIndex == 0 || !curEnv.getGroup().equals(nextEnv.getGroup())){
				Element group = new Element("Group");
				group.setAttribute("name", curEnv.getGroup());
				parent.addContent(group);
				writePredictor(environment, curIndex+1, group);
			}
			writeEnvVar(curEnv, parent);
		}
		else{
			if(curEnv.getGroup().length() == this.curParent.getGroup().length()){
				// there are sublings
				Element group = new Element("Group");
				group.setAttribute("name", curEnv.getGroup());
				parent.addContent(group);
				writePredictor(environment, curIndex+1, group);
			}
			else{
				writeEnvVar(environment.get("v"+curIndex), parent);
			}
		}

		writePredictor(environment, curIndex+1, parent);
	}

	/**
	 * write a literal
	 * @param envVar The Literal to be written
	 * @param parent The Parent
	 */
	private void writeEnvVar(Literal envVar, Element parent){
		//write the variables in a tree structure
		// if the group of the state is a subgroup of the parent group, then it is a child of the parent


		Element var = new Element("Literal");
		var.setAttribute("name", envVar.getId());
		var.setAttribute("value", envVar.getState()+"");
		if(!envVar.getId().startsWith("G")) {
			var.setAttribute("group", envVar.getGroup() + "");
		}
		parent.addContent(var);
	}
	
	/**
	 * write a plan
	 * @param pl The target plan
	 * @param parent The goal to achieve
	 */
	private void writePlan(PlanNode pl, Element parent) {
		Element plan = new Element("Plan");
		plan.setAttribute(new Attribute("name", pl.getName()));

		// precondition
		ArrayList<Literal> st = pl.getPre();
		
		if(st != null && st.size() > 0){
			StringBuilder pre = new StringBuilder();
			for(int i = 0 ; i < st.size(); i++)
			{
				if (i > 0){
					pre.append(", ");
				}
				pre.append(st.get(i).writeLiteral());
			}
			pre.append(";");
			plan.setAttribute(new Attribute("precondition", pre.toString()));
		}
		
		// write all actions, subgoals and parallel compositions it contains
		for(int i = 0; i < pl.getPlanBody().size(); i++)
		{
			if(pl.getPlanBody().get(i) instanceof ActionNode)
			{
				ActionNode act = (ActionNode) pl.getPlanBody().get(i);
				writeAction(act, plan);
			}
			if(pl.getPlanBody().get(i) instanceof GoalNode)
			{
				GoalNode gl = (GoalNode) pl.getPlanBody().get(i);
				writeGoal(gl, plan);
			}
		}
		parent.addContent(plan);
	}
	
	/**
	 * write action
	 * @param act The target action
	 * @param parent The plan which contain this action
	 */
	private void writeAction(ActionNode act, Element parent)
	{
		Element action = new Element("Action");
		action.setAttribute(new Attribute("name", act.getName()));
		// pre-condition
		ArrayList<Literal> st = act.getPreC();
		if(st != null && st.size() > 0){
			StringBuilder pre = new StringBuilder();
			for(int i = 0 ; i < st.size(); i++)
			{
				if (i > 0){
					pre.append(", ");
				}
				pre.append(st.get(i).writeLiteral());
			}
			pre.append(";");
			action.setAttribute(new Attribute("precondition", pre.toString()));
		}
		
		// postcondition
		st = act.getPostC();
		if(st != null && st.size() > 0){
			StringBuilder post = new StringBuilder();
			for(int i = 0 ; i < st.size(); i++)
			{
				if (i > 0){
					post.append(", ");
				}
				post.append(st.get(i).writeLiteral());
			}
			post.append(";");
			action.setAttribute(new Attribute("postcondition", post.toString()));
		} 
		parent.addContent(action);

	}
	
	/**
	 * write goal
	 * @param gl The target goal
	 * @param parent the Plan which contain this goal
	 */
	private void writeGoal(GoalNode gl, Element parent)
	{
		Element goal = new Element("Goal");
		goal.setAttribute(new Attribute("name", gl.getName()));

		// goal-condition
		ArrayList<Literal> st = gl.getGoalConds();
		if(st != null && st.size() > 0){
			StringBuilder goalCond = new StringBuilder();
			for(int i = 0 ; i < st.size(); i++)
			{
				if (i > 0){
					goalCond.append(", ");
				}
				goalCond.append(st.get(i).writeLiteral());
			}
			goalCond.append(";");
			goal.setAttribute(new Attribute("goal-condition", goalCond.toString()));
		}

		for(int i = 0; i < gl.getPlans().size(); i++)
		{
			PlanNode pl = gl.getPlans().get(i);
			writePlan(pl, goal);
		}
		parent.addContent(goal);
	}

}
