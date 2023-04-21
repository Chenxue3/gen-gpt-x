package com.gengptx.sever.gengptx.generator;

import com.gengptx.sever.gengptx.structure.*;

import java.util.HashMap;

public interface GPTGenerator {

    HashMap<String, Literal> genEnvironment();

    GoalNode genTopLevelGoal(int index);

}
