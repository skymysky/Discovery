package com.nepxion.discovery.plugin.strategy.agent.plugin.monitor;

/**
 * <p>Title: Nepxion Discovery</p>
 * <p>Description: Nepxion Discovery</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author zifeihan
 * @version 1.0
 */

import java.security.ProtectionDomain;

import com.nepxion.discovery.plugin.strategy.agent.callback.TransformCallback;
import com.nepxion.discovery.plugin.strategy.agent.callback.TransformTemplate;
import com.nepxion.discovery.plugin.strategy.agent.matcher.ClassMatcher;
import com.nepxion.discovery.plugin.strategy.agent.matcher.MatcherFactory;
import com.nepxion.discovery.plugin.strategy.agent.plugin.Plugin;
import com.nepxion.discovery.plugin.strategy.agent.threadlocal.ThreadLocalCopier;

public class DiscoveryMonitorPlugin extends Plugin {
    @Override
    public void install(TransformTemplate transformTemplate) {
        ClassMatcher classNameMatcher = MatcherFactory.newClassNameMatcher("com.nepxion.discovery.plugin.strategy.monitor.StrategyTracerContext");
        transformTemplate.transform(classNameMatcher, new TransformCallback() {
            @Override
            public byte[] doInTransform(ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
                ThreadLocalCopier.register(new StrategyTracerContextHook());

                return null;
            }
        });
    }
}