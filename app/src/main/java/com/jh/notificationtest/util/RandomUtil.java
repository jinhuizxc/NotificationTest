package com.jh.notificationtest.util;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomUtil {

    //Java中产生随机数
    // 1 . 调用java.lang下面Math类中的random()方法产生随机数
    // 新建一个文件后缀名为java的文件，文件名取为MyRandom,该类中编写如下的代码：
//    int radom = (int)(Math.random()*10);
//    System.out.println(radom);
//    其中Math.random() //产生0~1之间的一个随机小数。
//    产生一个0~9之间的整数为：(int)(Math.random()*10);
//    产生一个1~10之间的整数则可以写着：(int)(Math.random()*10 + 1);
//    以此类推：产生一个0~n之间的数就应写作：Math.random()*n;

//    取出一个指定长度大小的随机正整数：

    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    // 2 . 调用java.util下面Random类,此类的实例用于生成伪随机数流，产生一个随机整数，则调用该类的nextInt()方法
    // 使用Random类之前，则在包下导入java.util.Random ;代码为：
//    Random rand = new Random();
//    int rInt = rand.nextInt(10);
//    System.out.println(rInt);

//    其中Random rand = new Random()
//    是创建一个新随机数生成器；rand.nextInt(int n)
//    是从此随机数生成器的序列中取出的、在 0（包括）和指定值n（不包括）
//    之间均匀分布的 int值。

//    Java中在指定的整数范围类，循环产生不相同的随机数
//    以产生6位20以内不相同的随即整数 为例如下：

//    public static void main(String[] args) {
//        int n = 20;
//        Random rand = new Random();
//        boolean[] bool = new boolean[n];
//        int randInt = 0;
//        for (int i = 0; i < 6; i++) {
//            do {
//                randInt = rand.nextInt(n);
//            } while (bool[randInt]);
//            bool[randInt] = true;
//            System.out.println(randInt);
//        }
//    }

    // 其中用布尔变量数组来存储是否生成了该数字。生成后，该数字作为布尔数组下表的对应布尔值变成了true，
    // 下一次生成该数字就会再次进入do...while循环生成数字直到产生没有生成过的数字。
    // 例如：生成一个int类型的数组,长度为50的，并向其中随即插入0-50之间的数，并且不能重复。
    // 代码如下：


    public static void main(String[] args) {
        int[] intRandom = new int[50];
        List mylist = new ArrayList(); //生成数据集，用来保存随即生成数，并用于判断
        Random rd = new Random();
        while (mylist.size() < 50) {
            int num = rd.nextInt(51);
            if (!mylist.contains(num)) {
                mylist.add(num); //往集合里面添加数据。
            }

        }
        for (int i = 0; i < mylist.size(); i++) {
            intRandom[i] = (Integer) (mylist.get(i));
        }
    }


    /**
     * 随机产生指定的范围不重复的集合
     *
     * @param size
     * @return
     */

    public static Set<Integer> generateRandomArray(int size) {
        Set<Integer> set = new LinkedHashSet<Integer>(); //集合是没有重复的值,LinkedHashSet是有顺序不重复集合,HashSet则为无顺序不重复集合
        Integer num = size;
        Integer range = size;
        Random ran = new Random();
        while (set.size() < num) {
            Integer tmp = ran.nextInt(range); //0-51之间随机选一个数
            set.add(tmp);//直接加入，当有重复值时，不会往里加入，直到set的长度为52才结束
        }
        return set;
    }

}
