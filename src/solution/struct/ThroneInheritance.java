package solution.struct;

import java.util.*;

public class ThroneInheritance {
    Node root;
    HashMap<String,Node> map = new HashMap<>();
    public ThroneInheritance(String kingName) {
        root = new Node(kingName);
        map.put(kingName,root);
    }

    public void birth(String parentName, String childName) {
        Node parent = map.get(parentName);
        Node child = new Node(childName);
        parent.next.add(child);
        map.put(childName,child);
    }

    public void death(String name) {
        map.get(name).isAlive = false;
    }

    public List<String> getInheritanceOrder() {
        List<String> res = new ArrayList<>();
        bianli(res,root);
        return res;
    }
    public void bianli(List<String> list,Node cur) {
        if (cur.isAlive) {
            list.add(cur.name);
        }
        for (Node node : cur.next){
            bianli(list,node);
        }
    }
    static class Node{
        String name;
        boolean isAlive = true;
        List<Node> next = new ArrayList<>();
        public Node(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        ThroneInheritance throneInheritance = new ThroneInheritance("king");
        throneInheritance.birth("king","andy");
        throneInheritance.birth("king","bob");
        throneInheritance.birth("king","catherine");
        throneInheritance.birth("andy","matthew");
        throneInheritance.birth("bob", "alex");
        throneInheritance.birth("bob", "asha");
        System.out.println(throneInheritance.getInheritanceOrder());
        throneInheritance.death("bob");
        System.out.println(throneInheritance.getInheritanceOrder());
    }
}
