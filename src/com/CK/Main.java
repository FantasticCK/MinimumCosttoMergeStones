package com.CK;

public class Main {

    public static void main(String[] args) {
        int[] stones = {5, 1, 2};
        int K = 3;
        Solution solution = new Solution();
        System.out.println(solution.mergeStones(stones, K));
    }
}

class Solution {

    public int mergeStones(int[] stones, int K) {
        int n = stones.length;
        if ((n - 1) % (K - 1) != 0) return -1;
        int[] sums = new int[n + 1];
        int[][] dp = new int[n][n];
        for (int i = 1; i < n + 1; ++i) {
            sums[i] = sums[i - 1] + stones[i - 1];
        }
        for (int len = K; len <= n; ++len) {
            for (int i = 0; i + len <= n; ++i) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int t = i; t < j; t += K - 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][t] + dp[t + 1][j]);
                }
                if ((j - i) % (K - 1) == 0) {
                    dp[i][j] += sums[j + 1] - sums[i];
                }
            }
        }
        return dp[0][n - 1];
    }
}