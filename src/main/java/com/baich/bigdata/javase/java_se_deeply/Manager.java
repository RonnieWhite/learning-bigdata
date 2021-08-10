package com.baich.bigdata.javase.java_se_deeply;

/**
 * Created By IDEA.
 * Author : bai.chenghui
 * Date : Created in 2020-09-04
 * Time : 10:38
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Manager extends Employee implements Comparable<Manager> {
    private int id;
    private String name;
    private double salary;

    public Manager(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Manager() {
        this.id = 1;
        this.name = "White";
        this.salary = 32.1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public void noWay() {
        System.out.println("no way!!!");
    }

    public void xxx() {
        System.out.println("xxx");
    }

    public static void main(String[] args) {
//        e.noWay();
//        Class<? extends Employee> aClass = e.getClass();
//        System.out.println(aClass);
//        Employee e2 = new Manager();
//        System.out.println(e.equals(e2));
//        System.out.println(e2.getClass().getName());
//        System.out.println(e2.getClass());
//        Manager[] managers = new Manager[24];
//        managers[0] = new Manager();
//        System.out.println(managers.length);
//        try {
//            Class.forName("com.white.learning_bigdata.design_patterns.duck");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

//        Class emClass = Employee.class;
//        Method[] methods = emClass.getMethods();
//        for (Method method : methods) {
//            System.out.println(method.toString());
//        }
//        List<String> list = new ArrayList<>();
//        list.add("hello");
//        list.add("java");
//        list.forEach(System.out::println);
        Manager manager = new Manager();
        double[] res = {20, 30, 40, 50};
        System.out.println(manager.add(res));
        System.out.println(manager.max(res));
    }

    public double add(double... args) {
        double result = 0;
        for (double arg : args) {
            result += arg;
        }
        return result;
    }

    public double max(double... args) {
        double largest = Double.MIN_VALUE;
        for (double arg : args) {
            if (arg >= largest) {
                largest = arg;
            }
        }
        return largest;
    }

    @Override
    public int compareTo(Manager manager) {
        return Double.compare(salary, manager.getSalary());
//        return this.salary > manager.getSalary() ? 1 : -1;
    }

}
