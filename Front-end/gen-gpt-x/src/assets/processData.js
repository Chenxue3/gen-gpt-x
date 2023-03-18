// var blocksword={
//     "blocksworld": {
// 		"template": {
// 			"goaltemp": [
// 				{
// 					"_type": "GoalMoveTo(x, y)",
// 					"_goal-condition": "on(x, y)"
// 				},
// 				{
// 					"_type": "GoalMoveToTable(x)",
// 					"_goal-condition": "on(x, table)"
// 				}
// 			],
// 			"plantemp": [
// 				{
// 					"_type": "PlanKeepOn(x, y)",
// 					"_pre-condition": "on(x, y), y != table"
// 				},
// 				{
// 					"_type": "PlanMoveToClear(x, y)",
// 					"_pre-condition": "on(z, x), clear(y)"
// 				},
// 				{
// 					"_type": "PlanClearMoveTo(x, y)",
// 					"_pre-condition": "on(z, y), clear(x)"
// 				},
// 				{
// 					"_type": "PlanMoveTo(x, y)",
// 					"_pre-condition": "clear(x), clear(y)"
// 				},
// 				{
// 					"_type": "PlanKeepOnTable(x, y)",
// 					"_pre-condition": "clear(x), y == table"
// 				}
// 			],
// 			"actiontemp": [
// 				{
// 					"_type": "ActionDummy",
// 					"_pre-condition": "",
// 					"_post-condition": ""
// 				},
// 				{
// 					"_type": "ActionMove(x, y)",
// 					"_pre-condition": "clear(x), clear(y)",
// 					"_post-condition": "on(x, y)"
// 				}
// 			]
// 		},
// 		"instacne": {
// 			"goal": {
// 				"plan": [
// 					{
// 						"action": {
// 							"_name": "A0",
// 							"_type": "ActionDummy"
// 						},
// 						"_name": "P0",
// 						"_type": "PlanKeepOn(x, y)",
// 						"_sub": "x = a, y = b"
// 					},
// 					{
// 						"goal": [
// 							{
// 								"_name": "G1",
// 								"_type": "GoalMoveToTable(x)",
// 								"_sub": "x = z"
// 							},
// 							{
// 								"_name": "G2",
// 								"_type": "GoalMoveTo(x, y)",
// 								"_sub": "x = a, y = b"
// 							}
// 						],
// 						"_name": "P1",
// 						"_type": "PlanMoveToClear(x, y)",
// 						"_sub": "x = a, y = b"
// 					},
// 					{
// 						"goal": [
// 							{
// 								"_name": "G3",
// 								"_type": "GoalMoveToTable(x)",
// 								"_sub": "x = z"
// 							},
// 							{
// 								"_name": "G4",
// 								"_type": "GoalMoveTo(x, y)",
// 								"_sub": "x = a, y = b"
// 							}
// 						],
// 						"_name": "P2",
// 						"_type": "PlanClearMoveTo(x, y)",
// 						"_sub": "x = a, y = b"
// 					},
// 					{
// 						"action": {
// 							"_name": "A1",
// 							"_type": "ActionMove(x, y)",
// 							"_sub": "x = a, y = b"
// 						},
// 						"_name": "P3",
// 						"_type": "PlanMoveTo(x, y)",
// 						"_sub": "x = a, y = b"
// 					},
// 					{
// 						"action": {
// 							"_name": "A2",
// 							"_type": "ActionDummy"
// 						},
// 						"_name": "P4",
// 						"_type": "PlanKeepOnTable(x, y)",
// 						"_sub": "x = a, y = b"
// 					}
// 				],
// 				"_name": "G0",
// 				"_type": "GoalMoveTo(x, y)",
// 				"_sub": "x = a, y = b"
// 			}
// 		}
// 	}
// }


// function praseJson(jsonStr){
//     var myObject = jsonStr.parseJSON();
//     for(var p in myObject){
//         alert(p + " " + myObject[p]);
//     }
// }