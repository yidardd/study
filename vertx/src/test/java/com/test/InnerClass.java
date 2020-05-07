package com.test;

public class InnerClass {

    class Content {
        private int i;

        public int getVlaue() {
            return i;
        }

    }


    class Description {

        private String lable;

        Description(String lab) {
            this.lable = lab;

        }

        public String readLable() {
            return lable;
        }
    }

    public Content getContentInstance() {

        return new Content();
    }

    public Description getDescriptionIntance(String lable) {

        return new Description(lable);
    }

    public void ship(String lable) {

        Content content = getContentInstance();

        Description description = getDescriptionIntance(lable);

        System.out.println(description.readLable());


        System.out.println(description.readLable());


    }

    /**
     * 在创建外部类对象前，是不可以创建内部类对象的，因为内部类对象会暗暗的连接到外部类对象之上。<p>
     * 如果你想通过外围类对象创建内部类对象  之前已经说过最简单的方法是在外围类声明一个方法指向其内部类的对象。另一种更加简单的做法
     * JAVA支持通过外围类对象.new 语法表达一个外围类对象的引用
     * @param args
     */
    public static void main(String[] args) {

        InnerClass parcle2 = new InnerClass();

        InnerClass.Content c = parcle2.new Content();
        System.out.println(c.getVlaue());
        // parcle2.ship("hi");
        // InnerClass.Content c = parcle2.getContentInstance();// 如果想在外部类的非静态方法之外的任意位置访问某个内部类的对象，那么必须通过OutClass.xx

        InnerClass.Description d = parcle2.new Description("hello");
        System.out.println(d.readLable());
        // InnerClass.Description d = parcle2.getDescriptionIntance("hello");
    }


}