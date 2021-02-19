package com.leetcode;


import java.util.Arrays;

public class MaxProfit {

    public static void main(String[] args){
        int[] prices={3,2,6,5,0,3};
        int k=2;
        int r=maxProfit(k,prices);
        System.out.println("r="+r);

    }
    /**
     * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 输入：k = 2, prices = [3,2,6,5,0,3]
     * 输出：7
     * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
     *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
     *
     * 解题思路：DP动态规划，DP[i][k][j] 表示 第i天，持有/不持有 股票状态为j，已经进行了K次交易的最大利润
     * DP[i][k][1] 表示持有 DP[i][k][0] 表示不持有，
     * 来看状态方程:
     * 第i天持有股票，要么第i-1天不持有，第i天买入了，要么第i-1天也持有
     * DP[i][k][1]=max{DP[i-1][k][1],DP[i-1][k-1][0]-price[i]}
     * 第i天不持有股票，要么第i-1天持有，第i天卖出了，要么第i-1天也不持有
     * DP[i][k][0]=max{DP[i-1][k-1][1]+price[i],DP[i-1][k][0]}
     *
     * 来看初始条件：
     * 第0天不持有股票：DP[0][k][0]=0
     * 第0天持有股票：DP[0][1][1]=-price[0]
     */
    public static  int maxProfit(int k, int[] prices) {
        // 为了方便编码，我们将DP[i][k][0] 定义为二维数组 hold[i][j]
        //   我们将DP[i][k][1] 定义为二维数组 noHold[i][j]

        int days=prices.length;
        if(days<1) return 0; // 空直接返回
        k=Math.min(k,days/2); // 一次买入/卖出必须要2天，我们在买入第时候交易次数加1
        int[][] hold=new int[days][k+1];
        int[][] noHold=new int[days][k+1];
        // 初始化第0天
        for(int i=0;i<=k;i++){
            hold[0][i]=-prices[0];
            noHold[0][i]=0;
        }

        for(int i=1;i<days;i++){
            for(int j=1;j<=k;j++){
                hold[i][j]=Math.max(hold[i-1][j],noHold[i-1][j-1]-prices[i]);
                noHold[i][j]=Math.max(noHold[i-1][j],hold[i-1][j]+prices[i]);
            }
        }
        // k 不是越大越好的，返回 noHold[days-1][1..k]的最大值即可
       return Arrays.stream(noHold[days-1]).max().getAsInt();
        //return noHold[days-1][k];
    }
}
