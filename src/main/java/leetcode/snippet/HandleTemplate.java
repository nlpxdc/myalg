package leetcode.snippet;

public interface HandleTemplate {

    default void doThing() {
        mathFunc();
        bruteFunc();
        cleverFunc();
    }

    void mathFunc();

    void bruteFunc();

    void preDo();

    void cleverDo();

    default void cleverFunc() {
        preDo();
        cleverDo();
    }

}
