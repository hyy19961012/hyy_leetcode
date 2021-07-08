package solution;

import javafx.util.Pair;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int[] del = {0,0,0,0,0};

        System.out.println(numSubarraysWithSum(del,0));
    }

    /**
     *930:给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
     *子数组 是数组的一段连续部分。
     * @param nums
     * @param goal
     * @return
     */
    public static int numSubarraysWithSum(int[] nums, int goal) {
        int[] ans = new int[30001];
        ans[0] = 1;
        int sum = 0;
        int res = 0;
        for (int i : nums) {
            sum += i;
            if (sum - goal >= 0) {
                res += ans[sum - goal];
            }
            ans[sum]++;
        }
        return res;
    }

    public static int countPairs(int[] deliciousness) {
        int[] arr = new int[30];
        arr[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] * 2;
        }
        HashMap<Integer,Integer> counter = new HashMap<>(deliciousness.length);
        long res = 0;
        for (int d : deliciousness) {
            counter.put(d,counter.getOrDefault(d,0) + 1);
        }
        for (Map.Entry<Integer,Integer> entry : counter.entrySet()) {
            int k = entry.getKey();
            for (int sum : arr) {
                if (sum - k > k)break;
                if (counter.containsKey(sum - k)) {
                    long v1 = entry.getValue();
                    long v2 = counter.get(sum - k);
                    System.out.println(k +" : " + v1+"   "+(sum - k) + " : " + v2);
                    if (sum -k == k) {
                        res += v1 * (v1 - 1) / 2;
                    }else {
                        res += v1 * v2;
                    }
                }
            }
        }
        return (int)(res%1000000007);
    }
    public static List<List<String>> displayTable(List<List<String>> orders) {
        List<List<String>> res = new ArrayList<>();
        HashMap<String,Integer>[] tables = new HashMap[501];
        HashSet<String> cais = new HashSet<>();
        int count = 0;
        for (List<String> order : orders) {
            int num = Integer.parseInt(order.get(1));
            if (tables[num] == null) {
                tables[num] = new HashMap<String,Integer>();
                count++;
            }
            String c = order.get(2);
            cais.add(c);
            tables[num].put(c,tables[num].getOrDefault(c,0) + 1);
        }
        for (int i = 0; i <= count; i++) {
            res.add(new ArrayList<>());
        }
        List<String> cs = new ArrayList<>(cais);
        cs.sort(null);
        res.get(0).add("Table");
        for (String s : cs) {
            res.get(0).add(s);
        }
        int pos = 1;
        for (int i = 0; i < tables.length; i++) {
            if (tables[i] != null) {
                List<String> cur = res.get(pos);
                cur.add(i+"");
                for (String s : cs) {
                    cur.add(tables[i].getOrDefault(s,0)+"");
                }
                pos++;
            }
        }
        return res;
    }

//    public static int[] findErrorNums(int[] nums) {
//        int[] res = new int[2];
//        for(int i = 0; i < nums.length; i++) {
//            while (nums[i] - 1 != i && nums[i] != -1) {
//                if (nums[nums[i] - 1] == nums[i]) {
//                    res[0] = nums[i];
//                    nums[nums[i] - 1] = nums[i];
//                    nums[i] = -1;
//                    break;
//                }else {
//                    int t = nums[nums[i] - 1];
//                    nums[nums[i] - 1] = nums[i];
//                    nums[i] = t;
//                }
//            }
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] != i + 1) {
//                res[1] = i + 1;
//            }
//        }
//        return res;
//    }

//    public static String frequencySort(String s) {
//        char[] cs = s.toCharArray();
//        int[] ans = new int[128];
//        StringBuilder res = new StringBuilder();
//        for (char c : cs) {
//            ans[c]++;
//        }
//        while (true) {
//            int max = 0;
//            int pos = -1;
//            for (int i = 0; i < 128; i++) {
//                if (ans[i] > max) {
//                    max = ans[i];
//                    pos = i;
//                }
//            }
//            if (pos == -1){
//                break;
//            }
//            for (int i = 0; i < max; i++) {
//                res.append((char)pos);
//            }
//            ans[pos] = 0;
//        }
//        return res.toString();
//    }
//    public static int helper(int[] costs, int begin, int end, int coins) {
//        if(end > begin) {
//            return 0;
//        }else if (end == begin) {
//            if(costs[begin] <= coins) {
//                return 1;
//            }else {
//                return 0;
//            }
//        }
//        int piv = (begin + end) /2;
//        int sum = 0;
//
//        swap(costs,piv,begin);
//    }
//    public static void swap(int[] arr, int i, int j) {
//        int temp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = temp;
//    }
//    static int res;
//    public static int numWays(int n, int[][] relation, int k) {
//        boolean[][] path = new boolean[n][n];
//        for (int[] rela : relation) {
//            path[rela[0]][rela[1]] = true;
//        }
//        res = 0;
//        find(0,path,n,0,k);
//        return res;
//    }
//    public static void find(int cur, boolean[][] path, int n, int step, int k) {
//        if (step == k) {
//            if (cur == n - 1) {
//                res++;
//            }
//        }else {
//            for (int i = 0; i < n; i++) {
//                if (path[cur][i]) {
//                    find(i,path,n,step + 1,k);
//                }
//            }
//        }
//    }
//    public static String convertToTitle(int columnNumber) {
//        StringBuilder stringBuilder = new StringBuilder();
//        while (columnNumber != 0) {
//            int cur = columnNumber % 26;
//            if (cur == 0) {
//                cur = 26;
//            }
//            stringBuilder.insert(0,(char)('A' + cur - 1));
//            columnNumber = (columnNumber - 1) / 26;
//        }
//        return stringBuilder.toString();
//    }

//    public static int snakesAndLadders(int[][] board) {
//        int n = board.length;
//        Deque<Integer> deque = new ArrayDeque<>(n * n);
//        boolean[] visited = new boolean[n * n + 1];
//        int[] ans = new int[n * n + 1];
//        deque.offerLast(1);
//        while (! deque.isEmpty()) {
//            int cur = deque.pollFirst();
//            if (cur == n * n) {
//                return ans[cur];
//            }
//            visited[cur] = true;
//            for (int num = 1; num < 7 && cur + num <= n * n; num++) {
//                int[] pix = getPxiel(cur + num, n);
//                int i = pix[0];
//                int j = pix[1];
//                if (board[i][j] == -1) {
//                    if (!visited[cur + num]) {
//                        deque.offerLast(cur + num);
//                        ans[cur + num] = ans[cur] + 1;
//                        visited[cur + num] = true;
//                    }
//                }else {
//                    if (!visited[board[i][j]]) {
//                        deque.offerLast(board[i][j]);
//                        ans[board[i][j]] = ans[cur] + 1;
//                        visited[board[i][j]] = true;
//                    }
//                }
//            }
//        }
//        return -1;
//    }
//    public static int[] getPxiel(int location, int n) {
//        int y = (location - 1) / n;
//        int x;
//        if (y % 2 == 0) {
//            x = (location - 1) % n;
//        }else {
//            x = n - (location - 1) % n - 1;
//        }
//        return new int[]{n - y - 1,x};
//    }

//    public static int hammingWeight(int n) {
//        int res = 0;
//        while (n != 0) {
//            res += (n & 1);
//            n = n >>> 1;
//        }
//        return res;
//    }
//    public static int longestSubarray(int[] nums, int limit) {
//        int l = 0;
//        int r = 0;
//        LinkedList<Integer> minQue = new LinkedList<>();
//        LinkedList<Integer> maxQue = new LinkedList<>();
//        int res = 0;
//        while (r < nums.length) {
//            while (!minQue.isEmpty() && minQue.getFirst() > nums[r]) {
//                minQue.pollFirst();
//            }
//            while (!maxQue.isEmpty() && maxQue.getFirst() < nums[r]) {
//                maxQue.pollFirst();
//            }
//            minQue.addFirst(nums[r]);
//            maxQue.addFirst(nums[r]);
//            while (l < r && maxQue.getLast() - minQue.getLast() > limit) {
//                if (maxQue.getLast() == nums[l]) {
//                    maxQue.pollLast();
//                }
//                if (minQue.getLast() == nums[l]) {
//                    minQue.pollLast();
//                }
//                l++;
//            }
//            r++;
//            res = Math.max(res,r - l);
//        }
//        return res;
//    }
//
//
//    public static boolean stoneGame(int[] piles) {
//        int n = piles.length;
//        int[][] dp = new int[n][n];
//        for (int i = n - 1; i >= 0; i--) {
//            for (int j = i; j < n; j++) {
//                if (j == i) {
//                    dp[i][j] = piles[i];
//                }else if (j == i + 1) {
//                    dp[i][j] = Math.abs(piles[i] - piles[j]);
//                }else {
//                    dp[i][j] = Math.max(piles[i] - dp[i + 1][j],piles[j] - dp[i][j - 1]);
//                }
//            }
//        }
//        return dp[0][n - 1] > 0;
//    }
//    public int guessNumber(int n) {
//        int l = 0;
//        int r = n;
//        while (l <= r) {
//            int m = l + (r - l) / 2;
//            int cur = guess(m);
//            if (cur == 0){
//                return m;
//            }else if(cur == 1){
//                l = m + 1;
//            }else {
//                r = m - 1;
//            }
//        }
//        return 0;
//    }
//    int guess(int num){
//        return 0;
//    }
//    public static int minInsertions(String s) {
//        int left = 0;
//        int res = 0;
//        char[] cs = s.toCharArray();
//        boolean last = false;
//        for (int i = 0; i < s.length() - 1; i++) {
//            if (cs[i] == '(') {
//                left++;
//            }else {
//                if (cs[i + 1] == ')') {
//                    left--;
//                    i++;
//                    if(i == s.length() - 1){
//                        last = true;
//                    }
//                }else {
//                    res++;
//                    if (left == 0) {
//                        res++;
//                    }else {
//                        left--;
//                    }
//                }
//            }
//        }
//        if (cs[s.length() - 1] == '(') {
//            return res + left * 2;
//        }else {
//            if(last){
//                return res + left * 2;
//            }
//            else return res + left * 2 + 1;
//        }
//    }
//
//    public static int findKthPositive(int[] arr, int k) {
//        int pos = 1;
//        for (int i : arr) {
//            while (pos < i) {
//                pos++;
//                k--;
//                if (k == 0) {
//                    return pos - 1;
//                }
//            }
//            pos++;
//        }
//        return pos + k -1;
//    }
//
//    public static int firstBadVersion(int n) {
//        int l = 1;
//        int r = n;
//        while (l < r){
//            int m = l + (r - l) / 2;
//            if (!isBadVersion(m)) {
//                l = m + 1;
//            }else {
//                r = m;
//            }
//        }
//        return l;
//    }
//    static boolean isBadVersion(int version){
//        if (version > 1702766719) return true;
//        return false;
//    }
//    public static int[] shuffle(int[] nums, int n) {
//        int mid = nums.length / 2;
//        int[] ans = new int[mid];
//        for (int i = 0; i < mid; i++) {
//            ans[i] = nums[i];
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if(i % 2 == 0) {
//                nums[i] = ans[i / 2];
//            }else {
//                nums[i] = nums[mid];
//                mid++;
//            }
//        }
//        return nums;
//    }
//
//    public static int hammingDistance(int x, int y) {
//        x ^= y;
//        int res = 0;
//        while (x != 0) {
//            if ((x & 1) == 1) {
//                res++;
//            }
//            x = x>>1;
//        }
//        return res;
//    }
//
//    public static double trimMean(int[] arr) {
//        double res = 0;
//        int k = arr.length / 20;
//        Arrays.sort(arr);
//        for (int i = k; i < arr.length - k; i++){
//            res += arr[i];
//        }
//        return res / (arr.length - 2 * k);
//    }
//
//    public static int countTriplets(int[] arr) {
//        int res = 0;
//        for (int i = 0; i < arr.length; i++) {
//            int cur = arr[i];
//            for (int j = i + 1; j < arr.length; j++) {
//                cur ^= arr[j];
//                if (cur == 0){
//                    res += j - i;
//                }
//            }
//        }
//        return res;
//    }
//
//    public static int findMaximumXOR(int[] nums) {
//        int max = 0;
//        Trie trie = new Trie();
//        String[] strings = new String[nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            StringBuilder sb = new StringBuilder(Integer.toBinaryString(nums[i]));
//            while (sb.length() < 31) {
//                sb.insert(0,'0');
//            }
//            strings[i] = sb.toString();
//            trie.add(strings[i]);
//        }
//        for (int i = 0; i < nums.length; i++) {
//            max = Math.max(max,Integer.valueOf(trie.maxXor(strings[i]),2));
//        }
//        return max;
//    }
//    static class Trie{
//        Trie one;
//        Trie zero;
//        public void add(String s) {
//            Trie cur = this;
//            for (int i = 0; i < s.length(); i++) {
//                if (s.charAt(i) == '0') {
//                    if (cur.zero == null) {
//                        cur.zero = new Trie();
//                    }
//                    cur = cur.zero;
//                }else {
//                    if (cur.one == null) {
//                        cur.one = new Trie();
//                    }
//                    cur = cur.one;
//                }
//            }
//        }
//        public String maxXor(String s){
//            StringBuilder sb = new StringBuilder();
//            Trie cur = this;
//            for (char c : s.toCharArray()) {
//                if(c == '0'){
//                    if (cur.one != null) {
//                        sb.append(1);
//                        cur = cur.one;
//                    }else {
//                        sb.append(0);
//                        cur = cur.zero;
//                    }
//                }else {
//                    if (cur.zero != null) {
//                        sb.append(1);
//                        cur = cur.zero;
//                    }else {
//                        sb.append(0);
//                        cur = cur.one;
//                    }
//                }
//            }
//            return sb.toString();
//        }
//    }
}
