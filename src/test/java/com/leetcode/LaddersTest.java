package com.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LaddersTest {
    @Test
    public void findLadders_test(){
        String beginWord="hit";
        String endWord="cog";
        List<String> wordList= Arrays.asList("hot","dot","dog","lot","log","cog");
        Ladders lad=new Ladders();
        lad.findLadders(beginWord,endWord,wordList);
    }
}
