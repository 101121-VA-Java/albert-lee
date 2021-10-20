package com.revature.services;

public class OopService {
    private int i;
    protected String s;
    String something; // default access modifier

    public void doStuff(){
        System.out.println(User.getNumOfUsers());
        User u = new User();
        User k = new User();
        u.setAge(-25);
        Object o = new Object(); // o is a reference to an object/instance of the Object class
        System.out.println("u age: " + u.getAge());
        System.out.println(k);
        System.out.println("Is k.equals(u)? " + k.equals(u));
        Task t1 = new Task("Laundry", LocalDate.parse("2021-10-20"));
        System.out.println(t1);
        u.setTask(t1);
        System.out.println(u);
        ImportantTask it = new ImportantTask("Quiz", LocalDate.now());
        it.stress();
        System.out.println(it);
    }

    public void test(){
        System.out.println("test successful");
    }
}