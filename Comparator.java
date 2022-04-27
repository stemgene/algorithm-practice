import java.util.Arrays;
import java.util.PriorityQueue;

public class Comparator {
    public static class Student {
        public String name;
        public int id;
        public int age;

        public Student(String name, int id, int age) {
            this.name = name;
            this.id = id;
            this.age = age;
        }
    }

    public static class IdAscendingComparator implements Comparator<Student> {
        /* 
        对于所有的比较器，有统一的标准：
        1. 返回负数的时候，第一个参数排在前面
        2. 返回正数的时候，第二个参数排在前面
        3. 返回0的时候，谁在前面无所谓
        */
        @Override
        public int compare(Student o1, Student o2) {
            /* 正常的逻辑
            if (o1.id < o2.id) {
                return -1; // 把o1放在前面
            }
            if (o2.id < o1.id) {
                return 1;  // 把o2放在前面
            }
            return 0;
            */
            //用以下一行代码可以实现上述功能
            return o1.id - o2.id;
        }
    }

    public static class IdDesendingComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o2.id - o1.id;
        }
    }

    public static class AgeAscendingComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.age - o2.age;
        }
    }

    public static class AgeDesendingComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o2.age - o1.age;
        }
    }

    public static class AgeShengIdSheng implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.age != o2.age ? (o1.age - o2.age): (o1.id - o2.id);
        }
    }

    // 先按照id排序，id小的，放前面
    // id一样，age大的，放前面
    public static class IdInAgeDe implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.id != o2.id ? (o1.id - o2.id): (o2.age - o1.age);
        }
    }

    public static void printStudents(Student[] students) {
        for (Student student : students) {
            System.out.println("Name : " + student.name + ", id : " + student.id + ", age: " + studnet.age);
        }
    }

    public static void printArray(Integer[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + " ");
        }
        System.out.println();
    }

    public static class MyComp implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    public static class AComp implements Comparator<Integer>{
        /**
         * 数组从大到小排序
         * 如果返回负数，认为第一个参数应该排在前面
         * 如果返回正数，认为第二个参数应该排在前面
         * 如果返回0，认为谁放在前面都行
         */
        @Override
        public int compare(Integer arg0, Integer arg1) {
            return arg1 - arg0;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {5,4,3,2,7,9,1,0};
        Arrays.sort(arr, new AComp());
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        System.out.println("===============================");

        Student student1 = new Student("A", 2, 20);
        Student student2 = new Student("B", 3, 21);
        Student student3 = new Student("C", 1, 23);

        Student[] students = new Student[]{student1, student2, student3};  //组成students数组
        System.out.println("第一条打印");

        Arrays.sort(students, new IdAscendingComparator());
        printStudents(students);

        System.out.println("===============================");
        // 比较器还能用在有序的结构中：如max-heap或min-heap
        PriorityQueue<Student> minHeapBasedId = new PriorityQueue<>(new AgeAscendingComparator());
        
    }
}
