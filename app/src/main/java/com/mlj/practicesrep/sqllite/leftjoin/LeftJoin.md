A left join B. 相当于[合成数据表]{这是理解关键}，合成的数据表中包含A的所有列。和满足Join on的B. 
这其中存在只有A列，没有B列的 行记录。这样的字段都会赋值为null。
赋值为null的字段可以用来过滤。
// 【SQL】sql语句LEFT JOIN（拼接表）详解
// https://blog.csdn.net/u010168781/article/details/120542993