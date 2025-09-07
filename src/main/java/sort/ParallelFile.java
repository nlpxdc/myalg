package sort;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ParallelApp {
    public static void main(String[] args) {
        List<String> spuIdList = Arrays.asList("sabc123", "scde234", "sdef345");
        List<String> couponIdList = Arrays.asList("caaa", "cbbb", "cccc");

        ExecutorService threadPool = getThreadPool();
        SpuIntegration spuIntegration = new SpuIntegration();
        CouponIntegration couponIntegration = new CouponIntegration();

        List<CompletableFuture<SpuInfo>> spuCfList = spuIdList.stream().map(spuId -> CompletableFuture.supplyAsync(() -> spuIntegration.getById(spuId), threadPool)).collect(Collectors.toList());
        List<CompletableFuture<CouponInfo>> couponCfList = couponIdList.stream().map(couponId -> CompletableFuture.supplyAsync(() -> couponIntegration.getById(couponId), threadPool)).collect(Collectors.toList());
        List<? extends CompletableFuture<?>> cfList = Stream.of(spuCfList, couponCfList).flatMap(List::stream).collect(Collectors.toList());
        CompletableFuture<Void> allOf = CompletableFuture.allOf(cfList.toArray(new CompletableFuture[0]));
        Void join = allOf.join();

        List<SpuInfo> spuInfoList = spuCfList.stream().map(CompletableFuture::join).collect(Collectors.toList());
        List<CouponInfo> couponInfoList = couponCfList.stream().map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println();
        CompletableFuture<SpuInfo> spuCf = CompletableFuture.supplyAsync(() -> spuIntegration.getById("sabc123"), threadPool);
//        CompletableFuture<CouponInfo> couponCf = CompletableFuture.supplyAsync(() -> couponIntegration.getById("caaa"), threadPool);
//
//        CompletableFuture<Void> allOf = CompletableFuture.allOf(spuCf, couponCf);
//        allOf.join();
//        SpuInfo spuInfo = spuCf.join();
//        CouponInfo coupInfo = couponCf.join();
        //combine

//        List<CompletableFuture<SpuInfo>> spuInfoCompletableFutureList = spuIdList.stream().map(spuId -> {
//            CompletableFuture<SpuInfo> spuInfoCompletableFuture = CompletableFuture.supplyAsync(() -> spuIntegration.getById(spuId), threadPool);
//            return spuInfoCompletableFuture;
//        }).collect(Collectors.toList());

//        CompletableFuture<Void> allOf = CompletableFuture.allOf(spuInfoCompletableFutureList.toArray(new CompletableFuture[0]));
//        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(spuInfoCompletableFutureList.toArray(new CompletableFuture[0]));
//        SpuInfo spuInfo = (SpuInfo) anyOf.join();

        //需要依赖内部的自己的超时
//        allOf.join();
//        List<SpuInfo> collect = spuInfoCompletableFutureList.stream().map(CompletableFuture::join).collect(Collectors.toList());

        threadPool.shutdown();

    }

    static ExecutorService getThreadPool() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                4, 8, 60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(200),
                new ThreadFactory() {
                    private final AtomicInteger seq = new AtomicInteger();
                    @Override public Thread newThread(Runnable r) {
                        Thread t = new Thread(r, "cf-pool-" + seq.incrementAndGet());
                        t.setDaemon(false);
//                        t.setDaemon(true);
                        return t;
                    }
                },
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        return pool;
    }

}

class SpuInfo {
    String spuId;
    String spuName;
}

class SpuIntegration {

    SpuInfo getById(String spuId) {
//        System.out.println(String.format("%s %s", Thread.currentThread().getName(), spuId));
        SpuInfo spuInfo = new SpuInfo();
        spuInfo.spuId = spuId;
        spuInfo.spuName = String.format("spuName%s", spuId);
        return spuInfo;
    }

}

class CouponInfo {
    String couponId;
    String couponName;
}

class CouponIntegration {
    CouponInfo getById(String couponId) {
        CouponInfo couponInfo = new CouponInfo();
        couponInfo.couponId = couponId;
        couponInfo.couponName = String.format("couponName%s", couponId);
        return couponInfo;
    }
}
