package com.devdojo.domain;

public abstract class Person {
    public enum Sex{
        MASCULINO,
        FEMININO
    }
    private String name,cpf;
    private int age;
    private Sex sex;

    public Person(String name, String cpf, int age, Sex sex) {
        this.name = name;
        this.cpf = cpf;
        this.age = age;
        this.sex = sex;
    }

    public Person(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
