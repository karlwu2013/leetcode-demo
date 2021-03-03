package com.leetcode;

import org.junit.Test;

public class TopKFrequentTest {
    @Test
    public  void topKFrequent_test(){
        int[] nums={1};
        int k=1;
        TopKFrequent tf=new TopKFrequent();
        int[] res=tf.topKFrequent(nums,k);
        for(int i=0;i<res.length;i++){
            System.out.print(res[i]+",");
        }
    }

}
