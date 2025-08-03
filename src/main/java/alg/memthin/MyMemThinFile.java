package alg.memthin;

//内存紧缩技巧，该 1 字节就别 8 字节，该指针就别拷贝，该对齐空洞就重排，该压缩就压缩
//数值压缩：让 8/16/32/64 位按需出现
//字符串/字节序列压缩：去掉浪费的“空气”
//结构布局压缩：让“对齐空洞”消失
class MyMemThinApp {
    public static void main(String[] args) {

    }
}
