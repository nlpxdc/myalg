package structure.storage.deterministic;

public interface TListAdt<T> {
    T loadAtNo(int no);

    void addAtNo(int no, T val);
    void delAtNo(int no);
    void updateAtNo(int no, T val);
}
