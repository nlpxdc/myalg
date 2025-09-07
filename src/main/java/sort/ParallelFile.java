package sort;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

class ParallelApp {
    public static void main(String[] args) {
        System.out.println("aa");
        List<String> spuIdList = Arrays.asList("abc123", "cde234", "345def");

        SpuIntegration spuIntegration = new SpuIntegration();

        List<CompletableFuture<SpuInfo>> spuInfoCompletableFutureList = spuIdList.stream().map(spuId -> {
            CompletableFuture<SpuInfo> spuInfoCompletableFuture = CompletableFuture.supplyAsync(() -> spuIntegration.getById(spuId));
            return spuInfoCompletableFuture;
        }).collect(Collectors.toList());

        CompletableFuture<Void> allDone = CompletableFuture.allOf(spuInfoCompletableFutureList.toArray(new CompletableFuture[0]));

        CompletableFuture<List<SpuInfo>> allResultList = allDone.thenApply(unused ->
                spuInfoCompletableFutureList.stream().
                map(CompletableFuture::join)
                .collect(Collectors.toList()));

        List<SpuInfo> join = allResultList.join();

    }
}

class SpuInfo {
    String spuId;
    String spuName;
}

class SpuIntegration {

    SpuInfo getById(String spuId) {
        System.out.printf("%d%s", Thread.currentThread().getId(), Thread.currentThread().getName());
        SpuInfo spuInfo = new SpuInfo();
        spuInfo.spuId = spuId;
        spuInfo.spuName = String.format("name%s", spuId);
        return spuInfo;
    }

}
