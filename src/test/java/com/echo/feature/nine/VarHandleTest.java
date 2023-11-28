package com.echo.feature.nine;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * VarHandle的基础使用
 */
public class VarHandleTest {

    int x = 0;

    static VarHandle handle;

    static {
        try {
            handle = MethodHandles.lookup().findVarHandle(VarHandleTest.class, "x", int.class);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        VarHandleTest varHandleTest = new VarHandleTest();
        varHandleTest.x = 0;

        System.out.println((int)handle.get(varHandleTest));

        handle.set(varHandleTest, 9);
        System.out.println((int)handle.get(varHandleTest));

        handle.compareAndSet(varHandleTest, 9, 1);
        System.out.println((int)handle.get(varHandleTest));

        handle.getAndAdd(varHandleTest, 9);
        System.out.println((int)handle.get(varHandleTest));
    }
}
