package structure.storage.indexno.explicithash;

import com.google.common.hash.Hashing;

public class MyHashUtil {
    //内部可以自己写，自定义，也可以用任何三方的，jdk的等等，自由度很大，不限制
    //这里假设默认hash不会冲突去写
    //如果考虑有冲突，需要考虑解决冲突，开发地址法，或者链表法，这里就先不搞复杂了，重点关注散列地址本身这个策略
    static int hash(int no) {
        return Hashing.murmur3_32_fixed().hashInt(no).asInt();
    }

    static int noIdx(int no, int mod) {
        int noHash = hash(no);
        return noHash % mod;
    }
}
