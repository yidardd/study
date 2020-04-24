package com.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2020-04-02 16:58
 * @Version 1.0
 * @Description PersonModel
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonModel  implements Comparable{

    private String name;

    private int age;

    private String sex;


    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
