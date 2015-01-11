package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person 
    implements Comparable<Person>
{
    private int age;
    private String name;
    private double salary;
    private String ssn;
    private boolean propertyChangeFired = false;
    private static int COUNT = 0;
    
    public Person() {
        this("", 0, 0.0d);
    }
    
    public Person(String n, int a, double s) {
        name = n;
        age = a;
        salary = s;

        ssn = "";
        COUNT++;
    }

    public static class AgeComparator implements Comparator<Person> {
        public int compare(Person a, Person b) {
            return a.age - b.age;
        }
    }

/*
Getters: age, name, salary, SSN, COUNT
*/
    public int getAge() {
        return age;
    }
    
    public String getName() {
        return name;
    }
    
    public double getSalary() {
        return salary;
    }
    
    public String getSSN() {
        return ssn;
    }

    public int count() {
        return COUNT;
    }

/*
Setters: age, name, salary, SSN
*/
    public void setAge(int n) {
        if (n >= 0) {
            this.age = n;
        } else {
            throw new IllegalArgumentException("Age cannot be less than 0");
        }
    }

    public void setName(String n) {
        if (n != null) {
            this.name = n;
        } else {
            throw new IllegalArgumentException("Null is not a valid name");
        }
    }

    public void setSalary(double n) {
        this.salary = n;
    }

    public void setSSN(String value) {
        String old = ssn;
        ssn = value;

        this.pcs.firePropertyChange("ssn", old, value);
        propertyChangeFired = true;
    }

    

    public boolean getPropertyChangeFired() {
        return propertyChangeFired;
    }

    public double calculateBonus() {
        return salary * 1.10;
    }
    
    public String becomeJudge() {
        return "The Honorable " + name;
    }
    
    public int timeWarp() {
        return age + 10;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof Person) {
            Person o = (Person) other;
            return (this.name.equals(o.name) && this.age == o.age);
        }
        return false;
    }

    @Override
    public int compareTo(Person other) {
        return (int) (this.salary - other.salary) * -1;
    }

    @Override
    public String toString() {
        return "[Person name:" + this.name + " age:" + this.age + " salary:" + this.salary + "]";
    }

    public static ArrayList getNewardFamily() {
        ArrayList<Person> family = new ArrayList<Person>();
        family.add(new Person("Ted", 41, 250000));
        family.add(new Person("Charlotte", 43, 150000));
        family.add(new Person("Michael", 22, 10000));
        family.add(new Person("Matthew", 15, 0));
        return family;
    }

    // PropertyChangeListener support; you shouldn't need to change any of
    // these two methods or the field
    //
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
}
