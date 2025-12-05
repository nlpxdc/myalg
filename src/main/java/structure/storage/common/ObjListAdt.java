package structure.storage.common;

public interface ObjListAdt {

    Object loadAtNo(int no);

    void addAtNo(int no, Object val);
    void delAtNo(int no);
    void updateAtNo(int no, Object val);

}
