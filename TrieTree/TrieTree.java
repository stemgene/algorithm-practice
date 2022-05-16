package TrieTree;

public class TrieTree {
    public static class TrieNode {
        public int pass;  // 每个点上有一个pass值，指在加前缀树时，这个节点到达（通过）多少次
        public int end; // 节点是否是一个字符串的结尾节点，如果是的话，是多少个字符串的结尾节点
        public TrieNode[] nexts; // 如果字符种类特别多，应使用HashMap<Char, Node> nexts或有序表TreeMap<Char, Node> nexts;

        public TrieNode() {
            pass = 0;
            end = 0;
            // 创建好26条通道
            // nexts[0] == null 没有走向‘a’的路
            // nexts[0] != null 有走向‘a’的路
            // ...
            // nexts[25] != null 有走向'z'的路
            nexts = new TrieNode[26];
        }
    }

    public static class Trie {
        // 头部节点
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // 加入一个字符串
        public void insert(String word) {
            if (word == null) {
                return;
            }
            // 把字符串转化成字符类型的数组
            char[] chs = word.toCharArray();
            // 从根节点出发
            TrieNode node = root;
            node.pass++;
            // 变量index用来存储各个字母对‘a’ASCII码的偏移量，对应着26条通道，value是0-25
            int index = 0;
            for (int i = 0; i < chs.length; i++) { // 从左往右遍历字符
                index = chs[i] - 'a'; // 
                if (node.nexts[index] == null) {  // 在下一个节点上有没有指向这个字母的路径
                    // 如果没有就创建节点
                    node.nexts[index] = new TrieNode();
                }
                // 如果有的话，或者刚刚建完，直接复用
                node = node.nexts[index];
                node.pass++;
            }
            node.end++;
        }

        //delete
        public void delete(String word) {
            if (search(word) != 0) { // 确定树中确实加入过word，才删除
                char[] chs = word.toCharArray();
                TrieNode node = root;
                node.pass--;
                int index = 0;
                for (int i = 0; i < chs.length; i++) {
                    index = chs[i] - 'a';
                    // 判断如果当前节点的下一级节点的pass值在减减之后为0，后序所有都为空
                    if (--node.nexts[index].pass == 0) {  
                        // 直接把下级节点标空
                        node.nexts[index] = null;
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }
        }

        // word这个单词之前加入过几次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                // 根据当前的字符决定index选择哪条路
                index = chs[i] - 'a';
                // 如果查到下面已经没有路径了，但是字符仍存在，如路径是‘abc’，而word是‘abcd’
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            // 返回的是pass值
            return node.pass;
        }
    }
}