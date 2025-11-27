package storage.linked.static4dynamic;

//下标 索引 充当指针引用 受限动态
class StaticLinkedListFileApp {
    public static void main(String[] args) {
        System.out.println("aa");

        MyObj[] myObjs = new MyObj[100000];

        int chainAHeadIdx = 1123;
        int chainBHeadIdx = 2456;
        int chainCHeadIdx = 3789;

        int[] chainHeadIdxAry = new int[3];
        chainHeadIdxAry[0] = chainAHeadIdx;
        chainHeadIdxAry[1] = chainBHeadIdx;
        chainHeadIdxAry[2] = chainCHeadIdx;

        //头插O1 尾插On

        MyObj myObja0 = new MyObj();
        myObjs[1123] = myObja0;
        myObja0.nextIdx = 1133;

        MyObj myObja1 = new MyObj();
        myObjs[1133] = myObja1;
        myObja1.nextIdx = 1143;

        MyObj myObja2 = new MyObj();
        myObjs[1143] = myObja2;
        myObja2.nextIdx = -1;

        MyObj myObjb0 = new MyObj();
        myObjs[2456] = myObjb0;
        myObjb0.nextIdx = 2466;

        MyObj myObjb1 = new MyObj();
        myObjs[2466] = myObjb1;
        myObjb1.nextIdx = 2476;

        MyObj myObjb2 = new MyObj();
        myObjs[2476] = myObjb2;
        myObjb2.nextIdx = -1;

        MyObj myObjc0 = new MyObj();
        myObjs[3789] = myObjc0;
        myObjc0.nextIdx = 3779;

        MyObj myObjc1 = new MyObj();
        myObjs[3779] = myObjc1;
        myObjc1.nextIdx = 3769;

        MyObj myObjc2 = new MyObj();
        myObjs[3769] = myObjc2;
        myObjc2.nextIdx = -1;

    }

}

class MyObj {
    Object val;
    int nextIdx;
}
