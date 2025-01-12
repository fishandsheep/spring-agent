package com.zdx.agent;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bytecode.assign.Assigner;

import java.util.HashMap;

public class MyAdvices {
    @Advice.OnMethodEnter
    public static long onEnter(@Advice.Origin String methodName) {
        System.out.println("method name is " + methodName);
        return System.nanoTime();
    }

    @Advice.OnMethodExit
    public static void onExit(@Advice.Enter long time, @Advice.Return(readOnly = false, typing = Assigner.Typing.DYNAMIC) Object res) {
        System.out.println("Inside exit method . . .");
        System.out.println("Method Execution Time: " + (System.nanoTime() - time) + " nano seconds");

        System.out.println("========================");
        System.out.println(res.getClass());

        res = new HashMap<>();

        System.out.println(res.getClass());
    }

}