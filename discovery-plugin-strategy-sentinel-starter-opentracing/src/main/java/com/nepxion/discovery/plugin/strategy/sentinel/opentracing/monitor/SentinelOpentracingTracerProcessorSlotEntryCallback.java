package com.nepxion.discovery.plugin.strategy.sentinel.opentracing.monitor;

/**
 * <p>Title: Nepxion Discovery</p>
 * <p>Description: Nepxion Discovery</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author zhang shun
 * @version 1.0
 */

import io.opentracing.Span;
import io.opentracing.util.GlobalTracer;

import com.nepxion.discovery.plugin.strategy.sentinel.constant.SentinelStrategyConstant;
import com.nepxion.discovery.plugin.strategy.sentinel.monitor.SentinelTracerProcessorSlotEntryCallback;

public class SentinelOpentracingTracerProcessorSlotEntryCallback extends SentinelTracerProcessorSlotEntryCallback<Span> {
    @Override
    protected Span buildSpan() {
        return GlobalTracer.get().buildSpan(SentinelStrategyConstant.SPAN_NAME).start();
    }

    @Override
    protected void outputSpan(Span span, String key, String value) {
        span.setTag(key, value);
    }

    @Override
    protected void finishSpan(Span span) {
        span.finish();
    }
}