package leetcode.technique.bitwise;

import java.math.BigInteger;

//这属于trick 也能属于technique？
class MyBitwiseApp {
    public static void main(String[] args) {
        System.out.println("aa");

        BigInteger base = new BigInteger("7");
        BigInteger exp  = new BigInteger("2025");
        BigInteger mod  = new BigInteger("1000000007");
        System.out.println(base.modPow(exp, mod)); // 7^2025 % 1e9+7

    }
}
