package TrieTree;
import java.util.Arrays;

public class BestArrange {
    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class ProgramComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    // 以会议结束的时间早来安排
    // programs所有的回忆项目
    // timePoint目前为止来到的时间点
    public static int bestArrange(Program[] programs, int timePoint) {
        // 先把所有项目根据结束时间排序
        Arrays.sort(programs, new ProgramComparator());
        int result = 0;
        // 从左向右依次遍历所有会议
        for (int i = 0; i < programs.length; i++) {
            if (timePoint <= programs[i].start) {  // 当前时间点是否早于会议开始
                // 有的话就安排会议
                result++;
                // 时间点来到会议结束位置
                timePoint = programs[i].end;
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
