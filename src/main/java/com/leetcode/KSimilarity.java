package com.leetcode;

public class KSimilarity {
    /**
     * 如果可以通过将 A 中的两个小写字母精确地交换位置 K 次得到与 B 相等的字符串，我们称字符串 A 和 B 的相似度为 K（K 为非负整数）。
     * 给定两个字母异位词 A 和 B ，返回 A 和 B 的相似度 K 的最小值。
     * 1 <= A.length == B.length <= 20
     * A 和 B 只包含集合 {'a', 'b', 'c', 'd', 'e', 'f'} 中的小写字母。
     *
     * 例：
     * 输入：A = "abac", B = "baca"
     * 输出：2
     */
    int minK=Integer.MAX_VALUE;
    public  int kSimilarity(String A,String B){
        // 将String 转换为2个char数组
        char[] charA=A.toCharArray();
        char[] charB=B.toCharArray();
        int len=A.length();
        dfs(charA,charB,len,0,0);
        return minK;
    }
    public static int kSimilarity_1(String A, String B){
        // 将String 转换为2个char数组
        char[] charA=A.toCharArray();
        char[] charB=B.toCharArray();
        int len=A.length();
        int k=0;
        // 首选预处理，将所有 类似 a..b 和b..a 这种情况 ab交换,
        // 为什么要提前交换？ 是为了避免后面交换过程中破坏这种情况  比如下面例子
        // A="aabbccddee"; B="dcacbedbae";
        for(int i=0;i<len-1;i++){
            if(charA[i]!=charB[i]){
                int j=i+1;
                while (!(charA[i]==charB[j] && charA[j]==charB[i] && charA[j]!=charB[j])){
                    j++;
                    if(j>=len) break;
                }
                if(j<len) { // 证明找到了最好情况
                    char tmp = charA[i];
                    charA[i] = charA[j];
                    charA[j] = tmp;
                    k++;
                }
            }
        }
        // 正式开始交换
        for(int i=0;i<len-1;i++){
            if(charA[i]!=charB[i]){
                //说明需要交换，往下找一个相等的做交换,并且找到的这个相等的字符对应的B字符不同，说明它不在正确位置上需要被交换
                //最好情况是 这个交换位置j 对应的 B[j] 刚好等于 A[i]  ，这样一次交换 ，解决了2个位置。
                // a..b 和 b..a  交换
                int j=i+1;
                while (charB[i]!=charA[j] || charA[j]==charB[j]){
                    j++;
                }
                //把j记录下来之后，继续往后查找，看能否找到最好情况

                for(int t=j+1;t<len;t++){
                    if(charA[t]==charB[i] && charB[t]!=charA[t] && charA[i]==charB[t]){
                        j=t;
                        break;
                    }
                }
                char tmp=charA[i];
                charA[i]=charA[j];
                charA[j]=tmp;
                k++;
            }
        }
        return  k;
    }

    // 深度搜索，由于可能会出现多个满足条件的情况
    public  void dfs(char[] charA,char[] charB,int len,int curK,int curIdx){
        if(curK>minK) return;
        if(curIdx==len-1){
            if(curK<minK) minK=curK;
            return;
        }
        int i=curIdx;
        for(i=curIdx;i<len-1;i++){
            if(charA[i]!=charB[i]){

                for(int j=i+1;j<len;j++){
                    if(charB[i]==charA[j] && charA[j]!=charB[j]){ // 交换
                        swap(charA,i,j);
                        dfs(charA,charB,len,curK+1,i+1);
                        swap(charA,i,j); // 回溯
                    }
                }
                return; // 接下来的都没有找到 ，这一层都回溯则结束了
            }

        }
        // 处理那些都相同，不用交换的
        if(i==len-1){
            if(curK<minK) minK=curK;
        }

    }
    public  void swap(char[] charA,int i,int j){
        char tmp=charA[i];
        charA[i]=charA[j];
        charA[j]=tmp;
    }

    /* 如果采用DP动态规划算法呢，看起来可以采用
     * DP(A,B,i) 表示第i个下标开始的 A[i..len] , B[i..len] 最小相似度k
     * 转移函数 ：如果A[i-1]==B[i-1] DP(A,B,i)=DP(A,B,i-1)
     * 如果A[i-1]!=B[i-1] 发生了交换 则 DP(A,B,i)=DP(A',B,i-1)+1
     *
     * 初始条件：
     * DP(A,B,len-1)=0
     *
     *
    */
}
