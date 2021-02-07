package ru.javawebinar.basejava;

import static java.lang.Thread.sleep;

public class MainDeadlock {

    static class Lock {
        private final String name;

        public Lock(String name) {
            this.name = name;
        }

        private void outInfo() {
            System.out.println(name + ": " + Thread.currentThread().getName() + ": " + Thread.currentThread().getState());
        }

        public synchronized void outInfoAndPass(Lock anotherLock) {
            System.out.println(name + " in the method outInfoAndPass(Lock anotherLock).");
            anotherLock.outInfo();
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            anotherLock.pass(this);
        }

        public synchronized void pass(Lock anotherLock) {
            System.out.println(name + " in the method pass(Lock anotherLock).");
            anotherLock.outInfo();
        }
    }

    public static void main(String[] args) {
        final Lock lockA = new Lock("LockA");
        final Lock lockB = new Lock("LockB");

        new Thread(() -> lockA.outInfoAndPass(lockB)).start();
        new Thread(() -> lockB.outInfoAndPass(lockA)).start();
    }
}
