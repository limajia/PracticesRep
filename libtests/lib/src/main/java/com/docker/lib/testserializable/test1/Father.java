package com.docker.lib.testserializable.test1;

import java.io.Serializable;

public class Father implements Serializable {

    private String parentName;

    //transient关键字 添加后 反序列话的结果为 Son{parentName='null', sonName='父亲father'} 注意读取的时候，读取数据的顺序一定要和存放数据的顺序保持一致
    //没有了parentName 这个字段
    // ¬í sr )com.docker.lib.testserializable.test1.Son}t(Å L sonNamet Ljava/lang/String;xr ,com.docker.lib.testserializable.test1.Fatherüã5+r<  xpt ç¶äº²father
    public Father(String parentName) {
        this.parentName = parentName;
    }

    public String getParentName() {
        return parentName;
    }
}
