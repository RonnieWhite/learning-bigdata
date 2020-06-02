package practise_java.day16;

import org.junit.Test;

import java.util.*;

public class listDemo {
    @Test
    public void runList() {
        List<Integer> arrList = new ArrayList<Integer>();
        arrList.add(1);
        arrList.add(2);
        arrList.add(3);
        Iterator<Integer> iterator = arrList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("===");
        for (int element : arrList) {
            System.out.println(element);
        }
    }

    @Test
    public void runVector() {
        Vector<Integer> vector = new Vector<Integer>();
        vector.add(10);
        vector.add(11);
        vector.add(12);
        Iterator<Integer> iterator = vector.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("+++");
        for (int i : vector) {
            System.out.println(i);
        }
    }

    @Test
    public void runLinkedList() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        for (String element : linkedList) {
            System.out.println(element);
        }
    }

    @Test
    public void runStudentList() {
        List<Student> list = new ArrayList<>();
        Student element1 = new Student(1, "Bryant");
        Student element2 = new Student(2, "Jordan");
        Student element3 = new Student(3, "James");
        list.add(0, element1);
        list.add(1, element2);
        list.add(2, element3);
        if (!list.isEmpty()) {
            for (Student element : list) {
                System.out.println(element);
            }
        } else {
            System.out.println("list is Null.");
        }
    }

    public void runDefaultParam(Student s) {

    }
}
