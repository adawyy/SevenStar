package com.mte.base;

import com.mte.util.PropUtil;

import java.util.ResourceBundle;

/**
 * Project :  mtesense
 * Created :  java
 * Date    :  4/17/15
 */
public class MteSenseBaseTask {

    protected MteSenseCore asCore;

    public MteSenseBaseTask(MteSenseCore senseCore) {
        this.asCore = senseCore;
    }

    public PropUtil getPropUtil(String path) {
        return new PropUtil(path);

    }
    public PropUtil getPropUtil(ResourceBundle resource) {
        return new PropUtil(resource);

    }

}
