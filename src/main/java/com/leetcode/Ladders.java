package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 单词接龙
 */
public class Ladders {
    /**
     * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：
     *
     * 每次转换只能改变一个字母。
     * 转换后得到的单词必须是字典中的单词。
     *
     * 深度搜索dfs 加剪枝
     * wordList 转换为 一个图 ，相邻的边，有一个字母不同，
     * adj[]
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList){
        Graphs grp=new Graphs(wordList);
        marked=new int[grp.getVNum()];
        List<List<Integer>> stt=new ArrayList<>();
        for(int i=0;i<wordList.size();i++){
            if(diffString(beginWord,wordList.get(i))==1){
                st=new ArrayList<>();
                dfs(grp,i,endWord);


            }
        }
        List<List<String>> path=new ArrayList<>();
        // 打印出st
        for(int i=0;i<stPath.size();i++){
            if(stPath.get(i).size()>minK) continue;
            ArrayList<String> tmp=new ArrayList<>();
            tmp.add(beginWord);

            System.out.print("['"+beginWord+"',");

            for(int j=0;j<stPath.get(i).size();j++){
                int idx=stPath.get(i).get(j);
                String word=wordList.get(idx);
                tmp.add(word);
                System.out.print("'"+word+"',");
            }
            System.out.println("]");
            path.add(tmp);
        }
        return path;
    }

    // 从顶点V开始深度搜索遍历 grp
    int[] marked;
    List<Integer> st;
    List<List<Integer>> stPath=new ArrayList<>();
    int minK=Integer.MAX_VALUE;
    public void dfs(Graphs grp,int V,String endWord){
        if(grp.wordlist.get(V).equalsIgnoreCase(endWord)) {
            st.add(V);
            List<Integer> tmp=new ArrayList<>(st);
            stPath.add(tmp);
            if(tmp.size()<minK) minK=tmp.size();
            return;
        }

        marked[V]=1;
        st.add(V);
        if(st.size()+1>minK) return;
        for(int i:grp.adj.get(V)){
            if(marked[i]==0){
                dfs(grp,i,endWord);
                int idx=st.size()-1;
                if(idx>=0) {
                    st.remove(idx);
                    marked[i] = 0;
                }
            }
        }
    }

    public int diffString(String str1,String str2){
        int len=str1.length();
        int k=0;
        for(int i=0;i<len;i++){
            if(str2.charAt(i)!= str1.charAt(i)){
                k++;
            }
        }
        return k;
    }

    public class Graphs{
        List<String> wordlist;
        List<List<Integer>> adj=new ArrayList<>();
        int VNum;
        public Graphs(List<String> wordlist) {
            this.wordlist = wordlist;
            int size=wordlist.size();
            this.VNum=size;
            for(int i=0;i<size;i++){
                List<Integer> tmp=new ArrayList<>();
                adj.add(tmp);
                for(int j=0;j<size;j++){
                    if(diffString(wordlist.get(i),wordlist.get(j))==1){
                        adj.get(i).add(j);
                    }
                }
            }
        }

        public  int getVNum(){
            return VNum;
        }
    }
}
