package solution.struct;

import solution.Solution;

import java.util.HashMap;
import java.util.HashSet;

/*
实现一个 MapSum 类，支持两个方法，insert 和 sum：
MapSum() 初始化 MapSum 对象
void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
 */
public class MapSum {
    Trie trie;
    HashMap<String,Integer> map;
    public MapSum() {
        trie = new Trie();
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        if(!map.containsKey(key)){
            trie.add(key, val);
            map.put(key,val);
        }else {
            int old = map.get(key);
            trie.add(key, val - old);
            map.put(key,val);
        }
    }

    public int sum(String prefix) {
        return trie.sum(prefix);
    }
     class Trie {
        Trie[] nexts = new Trie[26];
        int val = 0;
        public void add(String s, int val) {
            char[] cs = s.toCharArray();
            Trie cur = this;
            for (int i = 0; i < cs.length; i++) {
                int pos = cs[i] - 'a';
                if (cur.nexts[pos] == null) {
                    cur.nexts[pos] = new Trie();
                }
                cur.nexts[pos].val += val;
                cur = cur.nexts[pos];
            }
        }

        public int sum(String prefix) {
            int sum = 0;
            char[] cs = prefix.toCharArray();
            Trie cur = this;
            for (int i = 0; i < cs.length; i++) {
                int pos = cs[i] - 'a';
                if (cur.nexts[pos] == null) {
                    return 0;
                }
                cur = cur.nexts[pos];
            }
            return cur.val;
        }
    }

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple",3);
        System.out.println(mapSum.sum("ap"));
        mapSum.insert("app",2);
        mapSum.insert("apple",2);
        System.out.println(mapSum.sum("ap"));
    }
}
