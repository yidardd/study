package com.test;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2020-04-02 16:57
 * @Version 1.0
 * @Description StreamTest
 */
public class StreamTest {

    private static List<PersonModel> list = null;

    static {
        PersonModel wu = new PersonModel("wu qi", 18, "男");
        PersonModel zhang = new PersonModel("zhang san", 19, "男");
        PersonModel wang = new PersonModel("wang si", 20, "女");
        PersonModel zhao = new PersonModel("zhao wu", 20, "男");
        PersonModel chen = new PersonModel("chen liu", 21, "男");
        list = Arrays.asList(wu, zhang, wang, zhao, chen);
    }

    public static List<PersonModel> getData() {
        return list;
    }

    /**
     * 过滤所有的男性
     */
    public static void fiterSex() {
        List<PersonModel> data = getData();

        //old
        List<PersonModel> temp = new ArrayList<>();
        for (PersonModel person : data) {
            if ("男".equals(person.getSex())) {
                temp.add(person);
            }
        }
        System.out.println(temp);
        //new
        List<PersonModel> collect = data
                .stream()
                .filter(person -> "男".equals(person.getSex()))
                .collect(toList());
        System.out.println(collect);
    }

    /**
     * 过滤所有的男性 并且小于20岁
     */
    public static void fiterSexAndAge() {
        List<PersonModel> data = getData();

        //old
        List<PersonModel> temp = new ArrayList<>();
        for (PersonModel person : data) {
            if ("男".equals(person.getSex()) && person.getAge() < 20) {
                temp.add(person);
            }
        }

        //new 1
        List<PersonModel> collect = data
                .stream()
                .filter(person -> {
                    if ("男".equals(person.getSex()) && person.getAge() < 20) {
                        return true;
                    }
                    return false;
                })
                .collect(toList());
        //new 2
        List<PersonModel> collect1 = data
                .stream()
                .filter(person -> ("男".equals(person.getSex()) && person.getAge() < 20))
                .collect(toList());

    }

    /**
     * 取出所有的用户名字
     */
    public static void getUserNameList() {
        List<PersonModel> data = getData();

        //old
        List<String> list = new ArrayList<>();
        for (PersonModel persion : data) {
            list.add(persion.getName());
        }
        System.out.println(list);

        //new 1
        List<String> collect = data.stream().map(person -> person.getName()).collect(toList());
        System.out.println(collect);

        //new 2
        List<String> collect1 = data.stream().map(PersonModel::getName).collect(toList());
        System.out.println(collect1);

        //new 3
        List<String> collect2 = data.stream().map(person -> {
            System.out.println(person.getName());
            return person.getName();
        }).collect(toList());
    }

    @Test
    public void flatMapString() {
        List<PersonModel> data = getData();
        //返回类型不一样
        List<String> collect = data.stream()
                .flatMap(person -> Arrays.stream(person.getName().split(" "))).collect(toList());

        List<Stream<String>> collect1 = data.stream()
                .map(person -> Arrays.stream(person.getName().split(" "))).collect(toList());

        //用map实现
        List<String> collect2 = data.stream()
                .map(person -> person.getName().split(" "))
                .flatMap(Arrays::stream).collect(toList());
        //另一种方式
        List<String> collect3 = data.stream()
                .map(person -> person.getName().split(" "))
                .flatMap(str -> Arrays.asList(str).stream()).collect(toList());
    }


    @Test
    public void reduceTest() {
        //累加，初始化值是 10
        Integer reduce = Stream.of(1, 2, 3, 4)
                .reduce(10, (count, item) -> {
                    System.out.println("count:" + count);
                    System.out.println("item:" + item);
                    return count + item;
                });
        System.out.println(reduce);

        Integer reduce1 = Stream.of(1, 2, 3, 4)
                .reduce(0, (x, y) -> x + y);
        System.out.println(reduce1);

        String reduce2 = Stream.of("1", "2", "3")
                .reduce("0", (x, y) -> (x + "," + y));
        System.out.println(reduce2);
    }

    /**
     * toList
     */
    public static void toListTest(){
        List<PersonModel> data = getData();
        List<String> collect = data.stream()
                .map(PersonModel::getName)
                .collect(Collectors.toList());
    }

    /**
     * toSet
     */
    public static void toSetTest(){
        List<PersonModel> data = getData();
        Set<String> collect = data.stream()
                .map(PersonModel::getName)
                .collect(Collectors.toSet());
    }

    /**
     * toMap
     */
    public static void toMapTest(){
        List<PersonModel> data = getData();
        Map<String, Integer> collect = data.stream()
                .collect(
                        Collectors.toMap(PersonModel::getName, PersonModel::getAge)
                );

        data.stream()
                .collect(Collectors.toMap(per->per.getName(), value->{
                    return value+"1";
                }));
    }

    /**
     * 指定类型
     */
    @Test
    public void toTreeSetTest(){
        List<PersonModel> data = getData();
        TreeSet<PersonModel> collect = data.stream()
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(collect);
    }

    /**
     * 分组
     */
    @Test
    public void toGroupTest(){
        List<PersonModel> data = getData();
        Map<Boolean, List<PersonModel>> collect = data.stream()
                .collect(Collectors.groupingBy(per -> "男".equals(per.getSex())));
        System.out.println(collect);
    }

    /**
     * 分隔
     */
    @Test
    public void toJoiningTest(){
        List<PersonModel> data = getData();
        String collect = data.stream()
                .map(personModel -> personModel.getName())
                .collect(Collectors.joining(",", "{", "}"));
        System.out.println(collect);
    }

    /**
     * 自定义
     */
    @Test
    public void reduce(){
        List<String> collect = Stream.of("1", "2", "3").collect(
                Collectors.reducing(new ArrayList<String>(), x -> Arrays.asList(x), (y, z) -> {
                    y.addAll(z);
                    return y;
                }));
        System.out.println(collect);
    }

    public static void main(String[] args) {
        fiterSex();

    }

}
