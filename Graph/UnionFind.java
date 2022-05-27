package Graph;

import java.util.HashMap;
import java.util.Stack;

public class UnionFind {
    // 题目所给的样本进来之后会包一层，叫做元素
    public static class Element<V> {
        public V value;

        public Element(V value) {
            this.value = value;
        }
    }

    // 并查集结构
    public static class UnionFindSet<V> {
        // 样本对应自己的元素表
        public HashMap<V, Element<V>> elementMap;
        // key 某个元素；value 该元素的父
        public HashMap<Element<V>, Element<V>> fatherMap;
        // key 某个集合的代表（顶点）元素 value 该集合的大小
        public HashMap<Element<V>, Integer> sizeMap;

        // 初始化
        public UnionFindSet(List<V> list) {
            elementMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V value: list) {
                Element<V> element = new Element<V>(value);
                elementMap.put(value, element);
                fatherMap.put(element, element);
                sizeMap.put(element, 1);
            }
        }

        // 给定一个element, 向上一直找，把代表（顶点）元素返回
        private Element<V> findHead(Element<V> element) {
            Stack<Element<V>> path = new Stack<>();
            // 如果element不等于父节点，继续向上查找
            while (element != fatherMap.get(element)) {
                path.push(element); // 把沿途的元素加入到一个栈中
                element = fatherMap.get(element);
            }
            // 扁平化，把沿途所有节点都pop出，把父节点设置成顶点
            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), element);
            }
            return element;
        }

        public boolean isSameSet(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) { // 检查a和b是否经过了初始化成Element元素，否则返回false
                // 调用两个节点的findHead是否一致
                return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
            }
            return false;
        }

        public void union(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                Element<V> aF = findHead(elementMap.get(a)); // 找到a所在集合的代表（顶点）元素
                Element<V> bF = findHead(elementMap.get(b)); // 找到b所在集合的代表（顶点）元素
                if (aF != bF) {
                    Element<V> big = sizeMap.get(aF) >= sizeMap.get(bF) ? aF : bF;
                    Element<V> small = big == aF ? bF : aF;
                    fatherMap.put(small, big); // 把small的顶点的father指向big就可以了
                    sizeMap.put(big, sizeMap.get(aF) + sizeMap.get(bF));  // size合并
                    sizeMap.remove(small);  // 删除small
                }
            }
        }
    }
}
