package structure.storage.deterministic;

public interface StrListAdt {
    String loadAtNo(int no);

    void addAtNo(int no, String val);

    void delAtNo(int no);

    void updateAtNo(int no, String val);
}
