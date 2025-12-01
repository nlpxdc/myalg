package structure.storage.deterministic;

public interface IntListAdt {
    //read

    //单个
    Integer loadAtNo(int no);
    //多个
    //search?

    //write
    //单个
    //增
    void addAtNo(int no, Integer val);
    //删
    void delAtNo(int no);
    //改
    void updateAtNo(int no, Integer val);

    //多个
    //增
    //删
    //改
}
