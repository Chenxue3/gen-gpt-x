package com.gengptx.sever.gpt.generators;

import com.gengptx.sever.gpt.structure.GoalNode;
import com.gengptx.sever.gpt.structure.Literal;

import java.util.HashMap;

public interface GPTGenerator {

    HashMap<String, Literal> genEnvironment();

    GoalNode genTopLevelGoal(int index);

}
