package com.leetcode;

import org.junit.Test;

public class BSTTest {
    @Test
    public void  test_findMode(){
        int a[]={1,2,3,4,4,5,6};
        BST bst=new BST();
        BST.TreeNode root=bst.constructBST(a,0,6);
        bst.print_treeNode(root);
        int[] r=bst.findMode(root);
        System.out.println("r.size="+r.length);
        for(int i=0;i<r.length;i++){
            System.out.print(r[i]+",");
        }
    }

}
