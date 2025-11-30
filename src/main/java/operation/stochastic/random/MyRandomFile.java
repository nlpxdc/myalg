package operation.stochastic.random;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

//带时间的hash，讲周期，seed+time 有time就要研究周期
//“移位 + 异或 + 乘法” 这条组合拳在两者里都出现，原因是它既廉价又能快速扩散比特。
//PRNG 用它拉长周期并降低自相关
//评价指标和调参方向完全不同，只能算“共用砖块”，不是“共用建筑图纸”。
//PRNG 用本原多项式、Lagged Fibonacci 的借位链、WELL 的巨型状态矩阵——这些在 hash 里根本不会出现；
//hash 用长度编码、分块并行、树形合并、尾部填充——PRNG 没有“任意长度”问题，也用不到。
//PRNG 玩的是周期长度 + 序列均匀性；
//但“不重复”背后的理论工具、评价指标、调参目标完全不同
class MyRandomApp {
    public static void main(String[] args) {
        System.out.println("aa");

        int seed = 123;

        Random prng  = new Random();

        Random random = new Random(seed);
        Random random1 = new Random(seed);

        //根据时间戳也可以，时间序列
        for (int i = 0; i < 9; i++) {
            int v = random.nextInt(100);
            System.out.print(v);
        }
        System.out.println();
        for (int i = 0; i < 9; i++) {
            int v = random1.nextInt(100);
            System.out.print(v);
        }

        //无法指定seed，内部自动赋值
        ThreadLocalRandom current = ThreadLocalRandom.current();

        //熵池 DRBG
        SecureRandom trng  = new SecureRandom("123".getBytes(StandardCharsets.UTF_8));
        try {
            SecureRandom sr = SecureRandom.getInstance("DRBG");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }


}
