package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BST {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    HashMap<Integer, Integer> numMap = new HashMap<>();

    public int[] findMode_1(TreeNode root) {
        travelTree(root);
        List<Integer> resList = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : numMap.entrySet()) {
            int K = entry.getKey();
            int V = entry.getValue();
            if (V > max){
                resList.clear();
                max = V;
                resList.add(K);
            }
        }


        int[] res = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        return res;
    }
    public int[] findMode(TreeNode root) {
        searchTree(root);
        System.out.println("maxcount="+maxCount);
        int[] res = new int[resuList.size()];
        for (int i = 0; i < resuList.size(); i++) {
            res[i] = resuList.get(i);
        }
        return res;
    }
    int maxCount=Integer.MIN_VALUE;
    int count=0;
    List<Integer> resuList = new ArrayList<>();
    TreeNode pre=null;
    public void searchTree(TreeNode root){
        if(root==null) return;
        if(root.left!=null){
            searchTree(root.left);
        }
        //
        if(pre==null){ // 开始计数
            count=1;
        }else if(pre.val==root.val){
            count++;
        }else { // 不同了，重新开始
            count=1;
        }
        pre=root;
        if(count==maxCount){
            resuList.add(root.val);
        }
        if(count>maxCount){
            resuList.clear();
            resuList.add(root.val);
            maxCount=count;
        }
        if(root.right!=null){
            searchTree(root.right);
        }
    }
    public void travelTree(TreeNode root) {
        if (root == null) return;

        if (root.left != null) {
            travelTree(root.left);
        }

        int K = root.val;
        int V = 1;
        if (numMap.containsKey(K)) {
            V = numMap.get(K) + 1;
        }
        numMap.put(K, V);

        if (root.right != null) {
            travelTree(root.right);
        }
    }

    // 构建一个搜索二叉树
    public TreeNode constructBST(int[] a, int start, int end) {
        int len = a.length;
        if (a.length <= 0) return null;
        if (start > end) return null;
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(a[mid]);
        root.right=constructBST(a,mid+1,end);
        root.left=constructBST(a,start,mid-1);
        return  root;
    }

    public void print_treeNode(TreeNode root){
        if(root==null) return;
        if(root.left!=null) print_treeNode(root.left);
        System.out.print(root.val+",");
        if(root.right!=null) print_treeNode(root.right);
    }
}