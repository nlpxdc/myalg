package structure.storage.deterministic;

public interface VecListAdt {
    Integer[] loadAtNo(int no);

    void addAtNo(int no, Integer[] val);

    void delAtNo(int no);

    void updateAtNo(int no, Integer[] val);
}
