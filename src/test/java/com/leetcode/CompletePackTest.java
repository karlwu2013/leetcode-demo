package com.leetcode;

import com.pack.CompletePack;
import org.junit.Assert;
import org.junit.Test;

public class CompletePackTest {
    @Test
    public void test_complete(){
        int N=4;
        int V=5;
        int[] C={1,2,3,4};
        int[] W={2,4,4,5};
        CompletePack pack=new CompletePack();
        int r=pack.completePack(W,C,N,V);
        int expect=3;
        Assert.assertEquals(expect,r);
    }
}
