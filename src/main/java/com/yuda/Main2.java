package com.yuda;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import java.io.IOException;

/**
 * CreateUser: canyuda
 * CreateTime: 2020/6/20 14:12
 * Description:
 */
public class Main2 {
    public static void main(String[] args) throws IOException, CannotCompileException, NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("HelloWorld");
        CtMethod[] methods = cc.getMethods();
        for (CtMethod method : methods) {
            System.out.println(method.getName());
        }
        cc.writeFile();
    }
}
