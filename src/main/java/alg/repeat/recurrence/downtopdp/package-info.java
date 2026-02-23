package alg.repeat.recurrence.downtopdp;

//tabulation dp
//记不全，不一定能回溯，要回溯需要额外记录结构，并记全，那就和topdown的memo一样了
//自底向上记录，。。。当前和来源列表，最终多值，森林
//最终形式上都是同一个结构，要记录前者，角度不同，上下不同
//各种不同类型的dp填表类型
//全局结果和dp memo逻辑上独立，但是实际上可能是同一个结果，比如dp memo记全了，然后全局结果正好要给出这个，那其实可以公用一个，或者全局结果等于dp memo即可