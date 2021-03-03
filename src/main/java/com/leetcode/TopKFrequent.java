package com.leetcode;

import java.util.*;

public class TopKFrequent {
    public  class NumFreq{
        int num;
        int freq;

        public NumFreq(int num, int count) {
            this.num = num;
            this.freq = count;
        }
    }
    public int[] topKFrequent(int[] nums, int k){
        HashMap<Integer,Integer> numsMap=new HashMap<Integer, Integer>();
        PriorityQueue<NumFreq> pq=new PriorityQueue<>(new Comparator<NumFreq>() {
            @Override
            public int compare(NumFreq o1, NumFreq o2) {
                return o1.freq-o2.freq;
            }
        });
        int len=nums.length;
        for (int i=0;i<len;i++){
            int K=nums[i];
            int V=1;
            if(numsMap.containsKey(K)){
                V=numsMap.get(K)+1;
            }

            numsMap.put(K,V);
        }
        // 遍历Map
        int nc=0;
        for (Map.Entry<Integer,Integer> entry:numsMap.entrySet()){
            int K=entry.getKey();
            int V=entry.getValue();
            NumFreq KV=new NumFreq(K,V);
            if(nc<k){
                pq.add(KV);
            }else{
                NumFreq kv=pq.peek();
                if(V>kv.freq){
                    pq.poll();
                    pq.add(KV);
                }
            }
            nc++;


        }
        int[] res=new int[k];
        // 输出
        int i=0;
        while (!pq.isEmpty()){
            NumFreq tmp=pq.poll();
            res[i]=tmp.num;
            i++;
        }
        return  res;
    }
}
