import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Test {

  @org.junit.Test
  public void test1() {
    //创建一个EnumSet集合，集合元素就是Season枚举类的全部枚举值
    EnumSet es = EnumSet.allOf(Season.class);
    System.out.println(es);

    //创建一个EnumSet空集合，只顶其集合元素是Season类的枚举值
    EnumSet es2 = EnumSet.noneOf(Season.class);
    System.out.println(es2);

    //以枚举值来创建EnumSet集合
    EnumSet es3 = EnumSet.of(Season.SUMMER, Season.WINTER);
    System.out.println(es3); //输出[SUMMER, WINTER]

    //创建一个包含Season枚举值从SUMMER到WINTER的EnumSet集合
    EnumSet es4 = EnumSet.range(Season.SUMMER, Season.WINTER);
    System.out.println(es4);//输出[SUMMER, Fall, WINTER]

    //创建一个EnumSet集合，其元素和es4集合元素类型相同， es4元素+es5元素=Sesson枚举的全部枚举值
    EnumSet es5 = EnumSet.complementOf(es4);
    System.out.println(es5);//输出[SPRING]
  }

  @org.junit.Test
  public void test2() {
    EnumMap<Season, String> enumMap = new EnumMap<>(Season.class);
    HashMap<Season, String> aaa = new HashMap();
    enumMap.put(Season.SPRING, "春天");
    enumMap.put(Season.SUMMER, "夏天");
    enumMap.put(Season.AUTUMN, "秋天");
    enumMap.put(Season.WINTER, "冬天");
    System.out.println(enumMap);

    Iterator<Map.Entry<Season, String>> iterator = enumMap.entrySet().iterator();

    aaa.put(Season.SPRING, "春天");
    aaa.put(Season.SUMMER, "夏天");
    aaa.put(Season.AUTUMN, "秋天");
    aaa.put(Season.WINTER, "冬天");
    System.out.println(aaa);
  }

  @org.junit.Test
  public void test3() {
    EnumMap<Season, String> enumMap = new EnumMap<>(Season.class);
    HashMap<Season, String> aaa = new HashMap();
    long l = System.currentTimeMillis();
    for (int i = 0; i < 100000; i++) {
      enumMap.put(Season.SPRING, "春天");
      enumMap.put(Season.SUMMER, "夏天");
      enumMap.put(Season.AUTUMN, "秋天");
      enumMap.put(Season.WINTER, "冬天");
      enumMap.remove(Season.SPRING);
      enumMap.remove(Season.SUMMER);
      enumMap.remove(Season.AUTUMN);
      enumMap.remove(Season.WINTER);
    }
    long l2 = System.currentTimeMillis();
    System.out.println(l2 - l);

    long l3 = System.currentTimeMillis();
    for (int i = 0; i < 100000; i++) {
      aaa.put(Season.SPRING, "春天");
      aaa.put(Season.SUMMER, "夏天");
      aaa.put(Season.AUTUMN, "秋天");
      aaa.put(Season.WINTER, "冬天");
      aaa.remove(Season.SPRING);
      aaa.remove(Season.SUMMER);
      aaa.remove(Season.AUTUMN);
      aaa.remove(Season.WINTER);
    }
    long l4 = System.currentTimeMillis();
    System.out.println(l4 - l3);

  }


}

enum Season {
  SPRING, SUMMER, AUTUMN, WINTER;
}