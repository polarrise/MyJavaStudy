package com.powersi.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：Jinbiao
 * @Date：2023/4/18 14:19
 * @Desc：异步执行时返回的Future对象
 */
public class FutureOperationComplete {

    private List<Listener> listeners=new ArrayList();

    public void addListener(Listener listener){
        listeners.add(listener);
    }

    public List<Listener> getListeners() {
        return listeners;
    }
}
