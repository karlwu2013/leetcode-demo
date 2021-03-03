package com.pack;

import java.util.ArrayList;

/**
 * 将01 背包问题稍作修改：
 * 假设有N中物品，背包容量为V，每种物品价值为Wi，占用背包容量为Ci
 * 把这些物品放进背包V 使得 价值最大
 * 如果每种物品的数量是无限的，则为完全背包问题
 * 如果每种物品的数量是有限的，则为多重背包问题
 * 先看完全背包问题，
 * F(i,v) 表示前i 种 物品，装进容量为v的背包 的 最大价值
 * 状态转移方程为：
 * F(i,v)=max { F(i-1,v),F(i,v-Ci)+Wi }
 * 边界初始条件：
 * F(0,0)=0
 * 如果最终满足恰好容量是V：
 * F(0,1...v)=Interger.Min
 * 如果最终容量<=V即可
 * F(0,1....v)=0
 *
 *
 */
public class CompletePack {
    public int completePack(int[] W,int[] C,int N,int V){
        int[][] F=new int[N+1][V+1];
        F[0][0]=0;

        for(int j=1;j<=V;j++){
            F[0][j]=Integer.MIN_VALUE;
        }

        for(int i=1;i<=N;i++){
            for(int j=1;j<=V;j++){
                if(j>=C[i-1])
                    F[i][j]=Math.max(F[i-1][j],F[i][j-C[i-1]]+W[i-1]);
            }
        }
        int max=Integer.MIN_VALUE;
        for(int i=0;i<=N;i++){
            if (F[i][V]>max) max=F[i][V];
        }
        return max==Integer.MIN_VALUE ? -1 :max;
    }

    /**
     * 多重背包与完全背包类似
     * Mi表示每种物品的个数
     * F(i,v) 表示前i 种 物品，装进容量为v的背包 的 最大价值
     * 转移状态方程
     * F(i,v)=max(F(i-1,v),F(i-1,v-KCi)+KWi  其中K=1～Mi
     * 直接求解的话，会有三重循环。优化之
     * 将Mi分解 1，2，2^2，2^3,…… Mi-2^k+1 共K+1 件
     *
     * @param W
     * @param C
     * @param M
     * @param N
     * @param V
     * @return
     */
    public int multiplePack(int[] W,int[] C,int[] M,int N,int V){
        // 分解Mi
        int len=W.length;
        int idx=len;
        ArrayList<Integer> WArray=new ArrayList<>();
        ArrayList<Integer> CArray=new ArrayList<>();
        for(int i=0;i<N;i++){
            int k=(int)Math.log(M[i]);
            for(int j=0;j<=k;j++){
                WArray.add(2^j*W[i]);
                CArray.add(2^j*C[i]);

            }
        }
        // zeropack

        return zeroPack(WArray,CArray,N,V);

    }

    public int zeroPack(ArrayList<Integer>W,ArrayList<Integer>C,int N,int V){
        int[] F=new int[N+1];
        F[0]=0;
        for(int i=1;i<=N;i++){
            for(int j=V;j>=C.get(i);j--){
                F[j]=Math.max(F[j],F[j-C.get(i)]+W.get(i));
            }
        }
        return F[V];
    }
}
