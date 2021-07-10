package solution.struct;

import java.util.*;

/**
 * 1. set(string key, string value, int timestamp)
 * 存储键key、值value，以及给定的时间戳timestamp。
 * 2. get(string key, int timestamp)
 * 返回先前调用set(key, value, timestamp_prev)所存储的值，其中timestamp_prev <= timestamp。
 * 如果有多个这样的值，则返回对应最大的timestamp_prev的那个值。
 * 如果没有值，则返回空字符串（""）。
 */
public class TimeMap {

    HashMap<String, List<Point>> map;

    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        List<Point> list;
        if (map.containsKey(key)) {
            list = map.get(key);
        }else {
            list = new ArrayList<>();
            map.put(key,list);
        }
        list.add(new Point(value, timestamp));
    }

    public String get(String key, int timestamp) {
        List<Point> list = map.get(key);
        if (list == null) {
            return "";
        }
        int l = 0, r = list.size() - 1;
        if (list.get(0).t > timestamp) {
            return "";
        }
        while (l < r) {
            int m = (l + r + 1) / 2;
            if (list.get(m).t > timestamp) {
                r = m - 1;
            }else {
                l = m;
            }
        }
        return list.get(l).v;
    }
    static class Point implements Comparable<Point>{
        String v;
        int t;
        public Point(String v, int t) {
            this.v = v;
            this.t = t;
        }

        @Override
        public int compareTo(Point o) {
            return t - o.t;
        }
    }

    public static void main(String[] args) {
        TimeMap kv = new TimeMap();
        kv.set("foo", "bar", 1); // 存储键 "foo" 和值 "bar" 以及时间戳 timestamp = 1  
        System.out.println(kv.get("foo", 1));  // 输出 "bar"  
        System.out.println(kv.get("foo", 3));  // 输出 "bar"    // 输出 "bar" 因为在时间戳 3 和时间戳 2 处没有对应 "foo" 的值，所以唯一的值位于时间戳 1 处（即 "bar"）  
        kv.set("foo", "bar2", 4);
        System.out.println(kv.get("foo", 4));  // 输出 "bar"  
        System.out.println(kv.get("foo", 5));  // 输出 "bar"  
    }
}
