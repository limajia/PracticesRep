package com.docker.libalgorithm.mianshi;

//股票最⼤收益-贪⼼算法+动态规划
//给定⼀个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；⾮负整数 fee 代表了交易股票的⼿续费⽤。
//你可以⽆限次地完成交易，但是你每笔交易都需要付⼿续费。如果你已经购买了⼀个股票，在卖出它之前你就不能再继续购买股票了。
//返回获得利润的最⼤值。
//注意：这⾥的⼀笔交易指买⼊持有并卖出股票的整个过程，每笔交易你只需要为⽀付⼀次⼿续费。
//⽰例 1:
//输⼊: prices = [1, 3, 2, 8, 4, 9], fee = 2
//输出: 8
//解释: 能够达到的最⼤利润:
//在此处买⼊ prices[0] = 1
//在此处卖出 prices[3] = 8
//在此处买⼊ prices[4] = 4
//在此处卖出 prices[5] = 9
//总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
//注意:
//0 < prices.length <= 50000.
//0 < prices[i] < 50000.
//0 <= fee < 50000.
public class Test1 {
    public static void main(String[] args) {
//        int[] prices = new int[]{1, 3, 2, 8, 4, 9};
        int[] prices = new int[]{1, 8, 15, 18};
        int i = maxProfit(prices);
        System.out.println(i);
        int maxProfit = maxProfit(prices, 2);
        System.out.println(maxProfit);
    }

    // 这种情况是只购买一次的 最大差值的情况
    public static int maxProfit(int[] prices) {
        int maxprofit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxprofit) {
                    maxprofit = profit;
                }
            }
        }
        return maxprofit;
    }

    //貌似这个题目，只能以依次的向后买才可以，看不懂
    //记忆：1.假装第一次就购买，2.从后一个比较找到小的购买价格【prices[i] + fee】 3.大于价格就卖出【prices[i]】，更新购买价格
    public static int maxProfit(int[] prices, int fee) {
        int result = 0;
        // 定义⼀个变量表⽰ 【当前的价格】 是最合适的 并不是真正的买⼊
        // 通过对 buyPrice 控制买⼊卖出
        int buyPrice = prices[0] + fee; //可以把收费 放到买入的消费里面去
        for (int i = 1; i < prices.length; i++) {
            if (buyPrice > prices[i] + fee) {//相邻的买入的价格，比较，哪一个适合买入
                // 表⽰上⼀天应该卖掉或者上⼀天没有买⼊
                // 此时变更buyPrice为当前值(并不是真正的买⼊)
                buyPrice = prices[i] + fee;
            } else if (buyPrice < prices[i]) {//可以卖出了
                // 表⽰上⼀天应该买⼊或者上⼀天没有卖掉
                // 此时计算收益并变更为buyPrice当前值(并不是真正的卖掉)
                result += prices[i] - buyPrice;
                buyPrice = prices[i];
            }
        }
        return result;
    }
}
