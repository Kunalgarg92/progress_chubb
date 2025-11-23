package com.app.work2;

class Room {
    String roomName;
    boolean isBooked = false;

    public Room(String roomName) {
        this.roomName = roomName;
    }

    public synchronized boolean book(String user) {
        if (!isBooked) {
            System.out.println(user + " booked " + roomName);
            isBooked = true;
            return true;
        } else {
            System.out.println(user + " tried to book " + roomName + " but it's already booked.");
            return false;
        }
    }
}

class Payment {
    public synchronized void pay(String user, int amount) {
        System.out.println(user + " is paying ₹" + amount);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(user + " payment completed.");
    }
}

public class hotelmanagement {
    public static void main(String[] args) {
        Room room = new Room("x1");
        Payment payment = new Payment();

        Runnable user1 = () -> bookAndPay(room, payment, "kunal");
        Runnable user2 = () -> bookAndPay(room, payment, "lakshay");

        Thread t1 = new Thread(user1);
        Thread t2 = new Thread(user2);

        t1.start();
        t2.start();
    }
    private static void bookAndPay(Room room, Payment payment, String user) {
        synchronized (room) {
            System.out.println(user + " locked the room for booking .");
            try {
                Thread.sleep(50); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (payment) {
                if (room.book(user)) {
                    payment.pay(user, 2000);
                }
            }
        }
    }
}
