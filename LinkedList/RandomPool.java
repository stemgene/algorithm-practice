package LinkedList;

import java.util.HashMap;

public class RandomPool {
    public static class Pool<K> {
        private HashMap<K, Integer> keyIndexMap;
        private HashMap<Integer, K> indexKeyMap;
        private int size;

        public Pool() {
            this.keyIndexMap = new HashMap<K, Integer>();
            this.indexKeyMap = new HashMap<Integer, K>();
            this.size = 0;
        }

        public void insert(K key) {
            // 如果之前没加过就加，加过了就跳过
            if (!this.keyIndexMap.containsKey(key)) {
                this.keyIndexMap.put(key, this.size);  // key是第size进来的
                this.indexKeyMap.put(this.size++, key);
            }
        }

        public void delete(K key) {

            if (!this.keyIndexMap.containsKey(key)) {
                int deleteIndex = this.keyIndexMap.get(key);
                int lastIndex = --this.size;  // 最后一个元素的index
                K lastKey = this.indexKeyMap.get(lastIndex); // 最后一个元素
                this.keyIndexMap.put(lastKey, deleteIndex);
                this.indexKeyMap.put(deleteIndex, lastKey);
                this.keyIndexMap.remove(key);
                this.indexKeyMap.remove(lastIndex);
            }
        }

        public K getRandom() {
            if (this.size == 0) {
                return null;
            }
            int randomIndex = (int) (Math.random() * this.size); // 0 ~ size - 1
            return this.indexKeyMap.get(randomIndex);
        }
    }
}
