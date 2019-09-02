/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.frank.event;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author wb-wj449816
 * @version $Id: EventProducer.java, v 0.1 2019年09月02日 17:37 wb-wj449816 Exp $
 */
public class EventProducer {

    private final RingBuffer<Event> ringBuffer;

    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 8, 60,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000), new ThreadFactoryImpl("EventThread"));

    private int bufferSize;

    private final static int DEFAULT_BUFFER_SIZE = 2048;

    private final Disruptor<Event> disruptor;

    public EventProducer(Map<String, BPEventListener> eventListenerMap, int bufferSize) {
        Assert.notEmpty(eventListenerMap);
        disruptor = new Disruptor<>(new EventFactory() {
            @Override
            public Object newInstance() {
                return null;
            }
        }, bufferSize, executor, ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.handleEventsWith(new EventHandler() {
            @Override
            public void onEvent(Object event, long sequence, boolean endOfBatch) throws Exception {

            }
        });
        disruptor.setDefaultExceptionHandler(new ExceptionHandler<Event>() {
            @Override
            public void handleEventException(Throwable ex, long sequence, Event event) {
                try {
                    if (event.getEvent() != null) {
                    }
                } finally {
                    event.setEvent(null);
                }

            }

            @Override
            public void handleOnStartException(Throwable ex) {

            }

            @Override
            public void handleOnShutdownException(Throwable ex) {

            }
        });
        disruptor.start();

        ringBuffer = disruptor.getRingBuffer();
    }

    public EventProducer(Map<String, BPEventListener> eventListenerMap) {
        this(eventListenerMap, DEFAULT_BUFFER_SIZE);
    }

    public void onHandler(BPEvent event) {
        long sequence = ringBuffer.next();
        try {
            Event e = ringBuffer.get(sequence);
            e.setEvent(event);
        } finally {
            ringBuffer.publish(sequence);
        }
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

}