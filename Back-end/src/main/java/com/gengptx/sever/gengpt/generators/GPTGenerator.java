package com.gengptx.sever.gengpt.generators;

import com.gengptx.sever.gengpt.structure.GoalNode;
import com.gengptx.sever.gengpt.structure.Literal;

import java.util.HashMap;

public interface GPTGenerator {

    HashMap<String, Literal> genEnvironment();

    GoalNode genTopLevelGoal(int index);

}
