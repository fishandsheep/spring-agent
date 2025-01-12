package com.zdx.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class MyAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        new AgentBuilder.Default()
                .type(ElementMatchers.nameEndsWith("MyController"))
                .transform((builder, typeDescription, classLoader, module, domain) -> builder
                        .visit(Advice
                                .to(MyAdvices.class)
                                .on(ElementMatchers.named("thin")))
                ).installOn(inst);
    }

}
