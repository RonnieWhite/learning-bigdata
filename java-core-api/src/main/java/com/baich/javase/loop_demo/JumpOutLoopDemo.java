package com.baich.javase.loop_demo;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2022-05-08
 * Time : 22:57
 * Description: 优雅的跳出循环 - 来自于Java源码
 * Modified By:
 * version : v1.0
 */
public class JumpOutLoopDemo {

    public static void main(String[] args) {
        Person person = new Person(1, "McGrady");
        System.out.println(person.getPerson(2));
    }

    static class Person {
        private Integer id;
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        public Person(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Person getPerson(int number) {
            scan:
            {
                for (int i = 0; i < 5; i += 2) {
                    if (number == 3)
                        break scan;
                }
                return this;
            }
            return new Person(3, "Iverson");
        }
    }
}
