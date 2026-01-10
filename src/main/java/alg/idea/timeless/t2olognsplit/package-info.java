package alg.idea.timeless.t2olognsplit;

//一般用于t业务，tx业务，transaction业务，要求快，ologn以内
//独立split
//分治都要处理，最后串行合并，受到阿达姆定律限制，例如归并排序
//减治处理一个
//内存通常二分即可，三分更高效，四分后的边际效益骤减，二分即可，面试通常二分即可，但可以三向有个等于=