package operation.generate.random;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
