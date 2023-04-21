/*
 * Copyright 2021 Yuan Yao
 * University of Nottingham
 * Zhejiang University of Technology
 * Email: yaoyuan@zjut.edu.cn (yuanyao1990yy@icloud.com)
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

package com.gengptx.sever.gengptx.structure;

/**
 * @version 3.1
 */
public class Literal implements Comparable<Literal> {

	/**
	 * identity of the literal, i.e., its name
	 */
	final private String id;

	/**
	 * the group of this variable
	 */
	final private String group;

	/**
	 * state of the literal, i.e., its value
	 */
	private boolean state;



	/**
	 * constructor
	 * @param id
	 * @param state
	 */
	public Literal(String id, boolean state, String group) {
		this.id = id;
		this.group = group;
		this.state = state;
	}

	/**
	 * @return the id of this literal
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @return the state of this literal
	 */
	public boolean getState() {
		return state;
	}


	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	/**
	 * flip the state of this literal
	 * @return the state after flipping
	 */
	public boolean flip(){
		this.state = !this.state;
		return this.state;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Literal literal = (Literal) o;

		return id.equals(literal.id) && (state == literal.state);
	}

	/**
	 * check if the node is a sub-group of the current group, if yes, return true
	 * @param node the node under check
	 * @return true or false
	 */
	public boolean isSubGroup(Literal node){

		//if and only if the node's group is start with this node's group and not the same then return true

		Boolean result =false;

		if (this.group.equals(node.getGroup())) {
			return false;
		}
		//get the group of the node
		String nodeGroup = node.getGroup();
		//if node's group is start with the current node's group, then it is a sub-group
		if(this.group.startsWith(nodeGroup) ){
//			System.out.println(this.group+"||"+node.group);
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * check if the node is the same group of the current group, if yes, return true
	 * @param o
	 * @return
	 */
	public boolean isSameGroup(Literal o){
		Boolean result =false;
		//get the group of the node
		String nodeGroup = o.getGroup();
		//if node's group is start with the current node's group, then it is a sub-group
		if(nodeGroup.equals(this.group)){
			result = true;
		}
		return result;
	}

	@Override
	public int compareTo(Literal o) {
		return this.getId().compareTo(o.getId());
	}

	public String getGroup() {
		return group;
	}

	@Override
	public String toString() {
		return "Literal{" +
				"id='" + id + '\'' +
				", group='" + group + '\'' +
				", state=" + state +
				'}';
	}

	/**
	 * write the literal in the format group(id)
	 * @return
	 */
	public String writeLiteral(){
		//write the literal in the format group(id)
		return this.group+"("+this.id+")";
	}

	/** write the literal*/
	public String toSimpleString()
	{
//		String out = "";
//		for (int i = 0; i < predictors.size(); i++) {
//			out += predictors.get(i).printPredictor();
//		}
		return "(" + this.id + ","+this.state +"),";
	}

	@Override
	public Literal clone(){
		return new Literal(id, state,group);
	}
}
