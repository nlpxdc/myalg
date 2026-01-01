package leetcode.snippet;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SnippetUtil {

    public static void main(String[] args) {
        int i = Float.floatToRawIntBits(7.8f);
        long l2 = Double.doubleToRawLongBits(8.9);
        float v = Float.intBitsToFloat(i);
        double v1 = Double.longBitsToDouble(l2);
        float maxValue = Float.MAX_VALUE;
        double minValue = Double.MIN_VALUE;
        float minValue1 = Float.MIN_VALUE;
        double minValue2 = Double.MIN_VALUE;
        int maxExponent = Float.MAX_EXPONENT;
        int maxExponent1 = Double.MAX_EXPONENT;
        float minNormal = Float.MIN_NORMAL;
        double minNormal1 = Double.MIN_NORMAL;
        float naN = Float.NaN;
        double naN1 = Double.NaN;
        float positiveInfinity = Float.POSITIVE_INFINITY;
        double positiveInfinity1 = Double.POSITIVE_INFINITY;
        float negativeInfinity = Float.NEGATIVE_INFINITY;
        double negativeInfinity1 = Double.NEGATIVE_INFINITY;
        float f = 1e-8f;
        double d = 1e-9;
        double ulp = Math.ulp(1.0d);
        float ulp1 = Math.ulp(1.0f);
        double pi = Math.PI;
        double e = Math.E;
        double abs = Math.abs(6.5D - 5.4F);
        boolean eq = abs < 1e-8;
        int[] ints = {4, 5};
        long l = key23(ints);
        long l1 = key23(7, 8);
    }

    //时间O(1) 空间O(1)
    public static void swap(int[] ary, int idxLeft, int idxRight) {
        if (idxLeft == idxRight) return;
        swap0(ary,idxLeft, idxRight);
    }

    //时间O(1) 空间O(1)
    public static void swap0(int[] ary, int idxLeft, int idxRight) {
        int t = ary[idxLeft];
        ary[idxLeft] = ary[idxRight];
        ary[idxRight] = t;
    }

    //时间O(1) 空间O(0)
    public static void swap1(int[] ary, int idxLeft, int idxRight) {
//        ary[idxLeft] = ary[idxLeft] + ary[idxRight];
//        ary[idxRight] = ary[idxLeft] - ary[idxRight];
//        ary[idxLeft] = ary[idxLeft] - ary[idxRight];

        ary[idxLeft] += ary[idxRight];
        ary[idxRight] = ary[idxLeft] - ary[idxRight];
        ary[idxLeft] -= ary[idxRight];
    }

    //时间O(1) 空间O(0)
    public static void swap2(int[] ary, int idxLeft, int idxRight) {
//        ary[idxLeft] = ary[idxLeft] * ary[idxRight];
//        ary[idxRight] = ary[idxLeft] / ary[idxRight];
//        ary[idxLeft] = ary[idxLeft] / ary[idxRight];

        ary[idxLeft] *= ary[idxRight];
        ary[idxRight] = ary[idxLeft] / ary[idxRight];
        ary[idxLeft] /= ary[idxRight];
    }

    //时间O(1) 空间O(0)
    public static void swap3(int[] ary, int idxLeft, int idxRight) {
//        ary[idxLeft] = ary[idxLeft] ^ ary[idxRight];
//        ary[idxRight] = ary[idxLeft] ^ ary[idxRight];
//        ary[idxLeft] = ary[idxLeft] ^ ary[idxRight];

//        ary[idxLeft] ^= ary[idxRight];
//        ary[idxRight] = ary[idxLeft] ^ ary[idxRight];
//        ary[idxLeft] ^= ary[idxRight];

        ary[idxLeft] ^= ary[idxRight];
        ary[idxRight] ^= ary[idxLeft];
        ary[idxLeft] ^= ary[idxRight];
    }

    public static int key1(int x) {
        return x;
    }

    public static int unpack1(int key) {
        return key;
    }

    public static long key2ordered(int x, int y) {
        return ((long) x << 32) | y;
    }

    public static long key2unordered(int x, int y) {
        return key2unorderedS2b(x, y);
    }

    //时间O(1) 空间O(1)
    public static long key2unorderedS2b(int x, int y) {
        if (x > y) {int t = x; x = y; y = t;}
        return ((long) x << 32) | y;
    }

    public static long key2unorderedB2s(int x, int y) {
        if (x < y) {int t = x; x = y; y = t;}
        return ((long) x << 32) | y;
    }

    public static int[] unpack2ordered(long key) {
        int first = (int) (key >>> 32);
        int second = (int) (key & 0xFFFF_FFFFL);
        return new int[]{first, second};
    }

    //时间O(1) 空间O(0)
    public static int[] unpack2unordered(long key) {
        int first = (int) (key >>> 32);
        int second = (int) (key & 0xFFFF_FFFFL);
        return first > second ? new int[] {second, first} : new int[]{first, second};
    }

    public static long key3(int x, int y, int z) {
        if (x > y) {int t=x;x=y;y=t;}
        if (x > z) {int t=x;x=z;z=t;}
        if (y > z) {int t=y;y=z;z=t;}
        return ((long) x << 42) | ((long) y << 21) | z;
    }

    //时间O(1) 空间O(0)
    public static int[] unpack3(long key) {
        int z = (int) (key & 0x1F_FFFFL);
        int y = (int) ((key >>> 21) & 0x1F_FFFFL);
        int x = (int) (key >>> 42);
        return new int[]{x, y, z};
    }

    public static long key23(int... ary) {
        if (ary.length == 2) {
            return key2unorderedS2b(ary[0], ary[1]);
        } else if (ary.length == 3) {
            return key3(ary[0], ary[1], ary[2]);
        } else {
            throw new RuntimeException();
        }
    }

//    public static long key23ary(int[] ary) {
//        if (ary.length == 2) {
//            return key2unorderedS2b(ary[0], ary[1]);
//        } else if (ary.length == 3) {
//            return key3(ary[0], ary[1], ary[2]);
//        } else {
//            throw new RuntimeException();
//        }
//    }

    public static String keyN(int... ary) {
        String key = keyN0(ary);
        return key;
    }

    public static String keyN0(int ...ary) {
        String key = Arrays.stream(ary)
                .sorted()
                .mapToObj(Integer::toString)
                .collect(Collectors.joining());
        return key;
    }

    public static int[] keyN1(int ... ary) {
        int[] retAry = new int[ary.length];
        System.arraycopy(ary, 0, retAry, 0, ary.length);
        Arrays.sort(retAry);
        return retAry;
    }

    public static int[] arycopy(int... ary) {
        int[] retAry = new int[ary.length];
        for (int i = 0; i < ary.length; i++) {
            retAry[i] = ary[i];
        }
        return retAry;
    }

    public static byte[] toByteAry0(int x) {
        byte[] retAry = new byte[4];
        retAry[0] = (byte) (x >>> 24);
        retAry[1] = (byte) (x >>> 12);
        retAry[2] = (byte) (x >>> 8);
        retAry[3] = (byte) x;
        return retAry;
    }

    public static byte[] toByteAry1(int x) {
        byte[] retAry = new byte[4];
        ByteBuffer byteBuffer = ByteBuffer.wrap(retAry);
        ByteBuffer bigByteBuffer = byteBuffer.order(ByteOrder.BIG_ENDIAN);
        ByteBuffer byteBuffer1 = bigByteBuffer.putInt(x);
        return retAry;
    }

    public static int toInt0(byte[] bytes) {
        return ((bytes[0] & 0xFF) << 24) | ((bytes[1] & 0xFF) << 16) | (bytes[2] & 0xFF) << 8 | (bytes[3] & 0xFF) ;
    }

    public static int toInt1(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        ByteBuffer bigByteBuffer = byteBuffer.order(ByteOrder.BIG_ENDIAN);
        int retInt = bigByteBuffer.getInt();
        return retInt;
    }

    //时间O(1) 空间O(0)
    public static int digits1(int x) {
        if (x == 0) return 1;
        x = Math.abs(x);
        return (int) Math.log10(x)+1;
    }

    //时间(1) 空间O(1)
    public static int digits(int x) {
        if (x == 0) return 1;
        int cnt = 0;
        for (x = Math.abs(x); x != 0; x /=10) {
            cnt++;
        }
        return cnt;
    }

}
