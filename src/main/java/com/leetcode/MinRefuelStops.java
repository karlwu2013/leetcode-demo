package com.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。
 *
 * 沿途有加油站，每个 station[i] 代表一个加油站，它位于出发位置东面 station[i][0] 英里处，并且有 station[i][1] 升汽油。
 *
 * 假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1 英里就会用掉 1 升汽油。
 *
 * 当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。
 *
 * 为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。
 *
 * 注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。
 *
 * 输入：target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
 * 输出：2
 * 解释：
 * 我们出发时有 10 升燃料。
 * 我们开车来到距起点 10 英里处的加油站，消耗 10 升燃料。将汽油从 0 升加到 60 升。
 * 然后，我们从 10 英里处的加油站开到 60 英里处的加油站（消耗 50 升燃料），
 * 并将汽油从 10 升加到 50 升。然后我们开车抵达目的地。
 * 我们沿途在1两个加油站停靠，所以返回 2 。
 *
 * 解法：深度优先搜索+回溯+剪枝
 *
 * DP法
 *
 */
public class MinRefuelStops {
    public  class station{
        public int pos;
        public int fuel;

        public station(int pos, int fuel) {
            this.pos = pos;
            this.fuel = fuel;
        }
    }
    public int minRefuelStops(int target, int startFuel, int[][] stations){
        PriorityQueue<station> pq=new PriorityQueue<station>(new Comparator<station>() {
            public int compare(station o1, station o2) {
                return o2.fuel-o1.fuel; // 最大堆
            }
        });
        int stops=0;
        int i=0;
        int len=stations.length;
        int pre=0;
        int curFuel=startFuel; // 当前燃料
        for(i=0;i<len;i++){
            int distance=stations[i][0]-pre;
            curFuel=curFuel-distance; // 从pre -> i 一定消耗 distance
            while (!pq.isEmpty() && curFuel<0){ // 停 加油
                curFuel+=pq.poll().fuel;
                stops++;
            }

            // 如果连下一个加油站都到不了
            if(curFuel<0) return -1;
            // 不用停的话
            pq.add(new station(stations[i][0],stations[i][1]));
            pre=stations[i][0];
        }

        // 所有加油站都路过之后 注意，可能中间都不满足上面条件 没有停，但由于到了最后一个，如果target 不能到达的话，必须前面选择一个停
        curFuel=curFuel-(target-pre);
       while(!pq.isEmpty() && curFuel<0){
           curFuel+=pq.poll().fuel;
           stops++;
       }
       if(curFuel<0) return -1;
       return stops;
    }

    int minStops=Integer.MAX_VALUE;
    public int minRefuelStops_1(int target, int startFuel, int[][] stations){
        if(stations.length==0) {
            if(startFuel>=target) return 0;
            return -1;
        }
        if(startFuel<stations[0][0]) return -1;
        dfs(target,startFuel,stations,-1,0);
        if(minStops==Integer.MAX_VALUE) return -1;
        return minStops;
    }

    public  void dfs(int lefttarget,int startFuel,int[][] stations,int startIdx,int curStops){
        int len=stations.length;
        // 深度搜索停止的条件
        if(startIdx==len-1 && startFuel<lefttarget){

            return;
        }
        if(startIdx==len-1 ){
            if(minStops>curStops) minStops=curStops;
            return;
        }
        int nextStationDistance=0;

        int leftFuel=0;     // 剩下的燃料量
        if(startIdx==-1) {
            nextStationDistance = stations[0][0];
            //leftDistance=target;
        } else {
            nextStationDistance = stations[startIdx + 1][0] - stations[startIdx][0];
            //leftDistance=target-stations[startIdx][0];


        }
        // 如果达不到下一站返回-1
        if(startFuel<nextStationDistance){

            return;
        }
        // 深度搜索停止的条件
        if(curStops>minStops) return;
        if( startFuel>=lefttarget){ // 不必停
            if(minStops>curStops) minStops=curStops;
            return;
        }
        // 从startIdx 出发，到下一个加油站，选择停或者不停
        int i=startIdx+1;
        for(i=startIdx+1;i<len;i++){
            int distance=0;
            if(i==0){
                distance=stations[0][0];
            }else {
                distance = stations[i][0] - stations[i - 1][0];
            }
            // 已经是最后一个站 停。 开不到下一个站了，停。
            if(i==len-1 || stations[i+1][0]-stations[i][0]+distance >startFuel){
                lefttarget=lefttarget-distance;
                startFuel=stations[i][1]+startFuel-distance;
                dfs(lefttarget,startFuel,stations,i,curStops+1);
                return; // 必须停的，不需要回溯
            }else{
                lefttarget=lefttarget-distance;
                dfs(lefttarget,stations[i][1]+startFuel-distance,stations,i,curStops+1);
                startFuel=startFuel-distance;


            }
        }

        if(i==len ){
            if(minStops>curStops) minStops=curStops;
            return;
        }


    }
}
