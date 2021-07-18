package com.baich.bigdata.design_patterns.builder;

/**
 * Created By IDEA.
 * Author : BaiCH
 * Date : Created in 2021-07-18
 * Time : 18:03
 * Description:
 * Modified By:
 * version : v1.0
 */
public class Person {
    private String name;
    private Integer age;
    private String gender;
    private Double salary;


    public static final class PersonBuilder {
        private String name;
        private Integer age;
        private String gender;
        private Double salary;

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }

        public String getGender() {
            return gender;
        }

        public Double getSalary() {
            return salary;
        }

        private PersonBuilder() {
        }

        public PersonBuilder aPerson() {
            return new PersonBuilder();
        }

        public PersonBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder age(Integer age) {
            this.age = age;
            return this;
        }

        public PersonBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public PersonBuilder salary(Double salary) {
            this.salary = salary;
            return this;
        }

        public Person build() {
            Person person = new Person();
            person.salary = this.salary;
            person.name = this.name;
            person.gender = this.gender;
            person.age = this.age;
            return person;
        }
    }
}
