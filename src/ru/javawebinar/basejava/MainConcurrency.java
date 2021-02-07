package ru.javawebinar.basejava;

import ru.javawebinar.basejava.util.LazySingleton;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    private static final int THREADS_NUMBERS = 10_000;
    private static int counter;
    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
                throw new IllegalStateException();
            }
        };
        thread0.start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            }

            private void inc() {
                synchronized (this) {
                    counter++;
                }
            }
        }).start();

        System.out.println(thread0.getState());


        final MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREADS_NUMBERS);
        for (int i = 0; i < THREADS_NUMBERS; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
        }

        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(counter);
    }

    private synchronized void inc() {
            counter++;
    }
}
