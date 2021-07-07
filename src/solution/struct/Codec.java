package solution.struct;

import solution.TreeNode;

public class Codec {
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;
        Codec codec = new Codec();
        String dec = codec.serialize(n1);
        System.out.println(dec);
        TreeNode root = codec.deserialize(dec);
        System.out.println(codec.serialize(root));
    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        serialize(root,stringBuilder);
        return stringBuilder.toString();
    }

    public void serialize(TreeNode root,StringBuilder stringBuilder) {
        if (root == null) {
            stringBuilder.append("# ");
        }else {
            stringBuilder.append(root.val);
            stringBuilder.append(" ");
            serialize(root.left,stringBuilder);
            serialize(root.right,stringBuilder);
        }
    }

    // Decodes your encoded data to tree.
    static int idx;
    public TreeNode deserialize(String data) {
        String[] strings = data.split(" ");
        return deserialize(strings);
    }

    public TreeNode deserialize(String[] strings) {
        if (idx >= strings.length || strings[idx].equals("#")) {
            idx++;
            return null;
        }
        int val = Integer.valueOf(strings[idx]);
        TreeNode node = new TreeNode(val);
        idx++;
        node.left = deserialize(strings);
        node.right = deserialize(strings);
        return node;
    }
}
