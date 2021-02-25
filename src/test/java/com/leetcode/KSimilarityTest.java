package com.leetcode;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.*;
import java.util.Date;

public class KSimilarityTest {
    @Test
    public void test_ksimilarity(){
        String A="aabc";
        String B="abca";
        KSimilarity ks=new KSimilarity();
        int k=ks.kSimilarity(A,B);
        System.out.println("k="+k);
        int expect=2;
        Assert.assertEquals(expect,k);

        A="abc";
        B="bca";
        k=ks.kSimilarity(A,B);
        expect=2;
        Assert.assertEquals(expect,k);

        A="abac";
        B="baca";
        expect=2;
        k=ks.kSimilarity(A,B);
        Assert.assertEquals(expect,k);

        A="bccaba";
        B="abacbc";
        expect=3;
        ks=new KSimilarity();
        k=ks.kSimilarity(A,B);
        Assert.assertEquals(expect,k);

        A="abccaacceecdeea";
        B="bcaacceeccdeaae";
        expect=9;
        ks=new KSimilarity();
        k=ks.kSimilarity(A,B);
        Assert.assertEquals(expect,k);

        A="aabbccddee";
        B="dcacbedbae";
        expect=5;
        ks=new KSimilarity();
        k=ks.kSimilarity(A,B);
        Assert.assertEquals(expect,k);

        A="cdebcdeadedaaaebfbcf";
        B="baaddacfedebefdabecc";
        expect=12;
        ks=new KSimilarity();
        k=ks.kSimilarity(A,B);
        Assert.assertEquals(expect,k);

    }
}
