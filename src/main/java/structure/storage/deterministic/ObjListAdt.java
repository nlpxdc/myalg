package structure.storage.deterministic;

public interface ObjListAdt {

    Object loadAtNo(int no);

    void addAtNo(int no, Object val);
    void delAtNo(int no);
    void updateAtNo(int no, Object val);

}
