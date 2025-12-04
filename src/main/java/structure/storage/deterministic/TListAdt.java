package structure.storage.deterministic;

public interface TListAdt<T> {
    int size();
    T loadAtNo(int no);
    //val -> idx
    int[] search(T val);
    int searchFirst(T val);
    int searchLast(T val);

//    void addAtNo(int no, T val);
    void add(T val);
    void delAtNo(int no);
    void updateAtNo(int no, T val);
}
