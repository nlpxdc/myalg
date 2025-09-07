package sort;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

class ParallelApp {
    public static void main(String[] args) {
        List<String> spuIdList = Arrays.asList("abc123", "cde234", "345def");

        ExecutorService threadPool = getThreadPool();
        SpuIntegration spuIntegration = new SpuIntegration();

        List<CompletableFuture<SpuInfo>> spuInfoCompletableFutureList = spuIdList.stream().map(spuId -> {
            CompletableFuture<SpuInfo> spuInfoCompletableFuture = CompletableFuture.supplyAsync(() -> spuIntegration.getById(spuId), threadPool);
            return spuInfoCompletableFuture;
        }).collect(Collectors.toList());

        CompletableFuture<Void> allOf = CompletableFuture.allOf(spuInfoCompletableFutureList.toArray(new CompletableFuture[0]));

        CompletableFuture<List<SpuInfo>> allResultListCompletableFuture = allOf
                .thenApply(unused -> spuInfoCompletableFutureList.stream().
                        map(CompletableFuture::join)
                        .collect(Collectors.toList()));

        List<SpuInfo> spuInfoList = allResultListCompletableFuture.join();
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
        System.out.println(String.format("%s %s", Thread.currentThread().getName(), spuId));
        SpuInfo spuInfo = new SpuInfo();
        spuInfo.spuId = spuId;
        spuInfo.spuName = String.format("name%s", spuId);
        return spuInfo;
    }

}
