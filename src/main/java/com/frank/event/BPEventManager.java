/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.frank.event;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author wb-wj449816
 * @version $Id: BPEventManager.java, v 0.1 2019年09月02日 17:32 wb-wj449816 Exp $
 */
public class BPEventManager implements InitializingBean {

    public static final String ONDEMAND = "ondemand";

    public static final String PERFORMANCE = "performance";

    public static final String  CONSERVATIVE= "conservative";

    /**
     * serviceWrapper
     */
    private ServiceWrapper                          serviceWrapper;

    /**
     * 本线程缓冲的消息
     */
    private static final ThreadLocal<List<BPEvent>> HOLDER = new ThreadLocal<>();

    /**
     * 处理Async event的线程池
     */
    private final ThreadPoolExecutor           defaultExecutor = new ThreadPoolExecutor(2,5,
            60, TimeUnit.SECONDS,new LinkedBlockingQueue<>(600),
            new ThreadFactoryImpl("DefaultAsyncEvent#"),new ThreadPoolExecutor.DiscardPolicy());
    /**
     * listeners
     */
    private       Map<String, BPEventListener> listeners       = new HashMap<>();

    /**
     * event producer
     */
    private EventProducer eventProducer;

    /**
     * 用户自定义异步Event的执行线程池
     */
    private Executor asyncExecutor;

    /**
     * disruptor buffer size.
     */
    private int bufferSize;

    /**
     * databus event 处理模式分为三种
     *  1，ondemand: 默认模式，将流程完结和取消事件落库再发送，其他事件则是直接发送，失败尝试落库
     *  2，performance: 性能模式，所有事件不落库直接发送
     *  3，conservative: 保守模式，所有事件存库再进行发送
     */
    private String databusEventMode = ONDEMAND;

    /**
     * 放入事件队列,等后续统一处理
     */
    public void push(BPEvent event) {
        /*
         * 先落一个job，并标明被BPEventManager占用执行，以免被定时任务捞起
         * databus event根据是否需要事务决定同步落job。
         * 如果没有找到event对应的Listener，将不会落job
         */
        BPEventListener listener = findEventListener(event.getClass());
        if (listener == null) {
            return;
        }


        String exeCode = "BPEventManager";

        listener.schedule(event, exeCode);
        List<BPEvent> events = HOLDER.get();
        if (events == null) {
            events = new ArrayList<>();
            HOLDER.set(events);
        }
        events.add(event);
    }

    /**
     * 获取所有队列中的event
     */
    List<BPEvent> getAllPendingEvents() {
        return HOLDER.get();
    }

    /**
     * 清空缓冲
     * 使用disruptor来清空线程内的event
     *
     */
    void flush(boolean send) {
        final List<BPEvent> events = HOLDER.get();
        HOLDER.remove();

        if (!send || CollectionUtils.isEmpty(events)) {
            return;
        }

        Executor executor = getAsyncExecutor() != null ? getAsyncExecutor() : defaultExecutor;

        //处理Async event
        events.stream().filter(event -> event instanceof BPAsyncEvent)
                .forEach(
                        event ->
                                executor.execute(() -> {
                                    BPEventListener listener = findEventListener(event.getClass());
                                    if(listener != null){
                                        listener.handle(event);
                                    }
                                })

                );

    }


    /**
     * 寻找listener
     *
     * @param clz
     * @return
     */
    public BPEventListener findEventListener(Class<?> clz) {
        while (clz != null) {
            BPEventListener listener = listeners.get(clz.getSimpleName());
            if (listener != null) {
                return listener;
            }

            clz = clz.getSuperclass();
        }
        return null;
    }

    /**
     * for unit test.
     *
     * @return current thread events.
     */
    public List<BPEvent> getCurrentThreadEvents() {
        return HOLDER.get();
    }

    /**
     * Setter method for property <tt>listeners</tt>.
     *
     * @param listeners value to be assigned to property listeners
     */
    public void setListeners(Map<String, BPEventListener> listeners) {
        this.listeners = listeners;
    }

    /**
     * 实例化Event Producer
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        //buffer size 没有设置或者设置的不是2的指数倍，那么直接使用默认的
        if(getBufferSize() <= 0 || Integer.bitCount(getBufferSize()) != 1){
            eventProducer = new EventProducer(listeners);
        }else{
            eventProducer = new EventProducer(listeners,getBufferSize());
        }

    }

    /**
     * get async exector
     * @return
     */
    public Executor getAsyncExecutor() {
        return asyncExecutor;
    }

    /**
     * set async executor.
     * @param asyncExecutor
     */
    public void setAsyncExecutor(Executor asyncExecutor) {
        this.asyncExecutor = asyncExecutor;
    }

    /**
     * get buffer size.
     * @return
     */
    public int getBufferSize() {
        return bufferSize;
    }

    /**
     * Set buffer size.
     * bufferSize must be a power of 2
     * @param bufferSize
     */
    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    /**
     * 设置databus event mode运行模式
     * @param databusEventMode
     */
    public void setDatabusEventMode(String databusEventMode) {
        this.databusEventMode = databusEventMode;
    }

    /**
     * 获取databus event 运行模式
     * @return
     */
    public String getDatabusEventMode() {
        return databusEventMode;
    }

    /**
     * Setter method for property <tt>serviceWrapper</tt>.
     *
     * @param serviceWrapper value to be assigned to property serviceWrapper
     */
    public void setServiceWrapper(ServiceWrapper serviceWrapper) {
        this.serviceWrapper = serviceWrapper;
    }

}