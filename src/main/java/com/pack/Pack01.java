package com.pack;

/**
 * 01 背包问题
 *  有N件物品和容量为V的背包，第 i 将物品的占用容量为Ci，价值为Wi
 *  求把哪些物品放进背包 使得总价值最大？
 *  这是最基础的背包问题：每类物品只有一件，可以选择放或者不放
 *
 *  F(i,v) 表示 把 0...i 件物品 放进容量为 v 的背包 获得的最大总价值 。
 *  那么其转移方程是咋样的呢？
 *  F(i,v)= max(F(i-1,v),F(i-1,v-ci)+wi )
 *  初始边界条件是咋样呢？
 *  F(0,0)=0   F(i,0)=0  F(0,i)=0
 *
 *
 */
public class Pack01 {
    public static  void main(String args[]){
        int N=4;
        int V=5;
        int[] C={1,2,3,4};
        int[] W={2,4,4,5};
        int r=zeroOnePack(C,W,V);
        System.out.println("r="+r);

        int[] coins={2};
        int amount=3;
        r=coinChange(coins,amount);
        System.out.println("r="+r);

    }

    /**
     * 01 背包
     * @param C
     * @param W
     * @param V
     * @return
     */
    public static int zeroOnePack(int[] C,int[] W,int V){
        int N=C.length;
        int[] F=new int[V+1];
        F[0]=0;
        for(int i=0;i<N;i++){
            for(int v=V;v>=C[i];v--){
                F[v]=Math.max(F[v],F[v-C[i]]+W[i]);
            }
        }
        return F[V];
    }

    public static int coinChange(int[] coins, int amount) {
        int len=coins.length;
        int[][] dp=new int[len+1][amount+1];
        // 初始化
        dp[0][0]=0;
        for(int j=1;j<=amount;j++){
            dp[0][j]=Integer.MAX_VALUE;
            if(j%coins[0]==0){
                dp[1][j]=j/coins[0];
            }else
                dp[1][j]=Integer.MAX_VALUE;
        }
        for(int i=1;i<=len;i++){
            for(int j=1;j<=amount;j++){
                if(j>=coins[i-1] && dp[i][j-coins[i-1]] < Integer.MAX_VALUE)
                    dp[i][j]=Math.min(dp[i-1][j],dp[i][j-coins[i-1]]+1);
                else
                    dp[i][j]=dp[i-1][j];
            }
        }
        int min=Integer.MAX_VALUE;
        for(int i=1;i<=len;i++){
            System.out.println("i="+i+",r="+dp[i][amount]);
            if(dp[i][amount]<min) min=dp[i][amount];
        }
        return min==Integer.MAX_VALUE ? -1:min;
    }
}
