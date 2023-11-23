/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monopoly;
/**
 *
 * @author ramez
 */

public class Queue {
    private int SIZE = 0; 
    private int CAPACITY; 
    private int[] ARRAY;
    private int back = 0;
    private int front = 0;
    
    public Queue() {}
    
    public Queue(int CAPACITY) {
        this.CAPACITY = CAPACITY;
        ARRAY = new int[this.CAPACITY];
        for (int i = 0;i<CAPACITY;i++) {
            ARRAY[i] = -1;
        }
    }
    
    public void dequeue() {
        if (isEmpty()) {
            return;
        }
        
        front++;
        front%= CAPACITY;
        SIZE--;
    }
    
    public void enqueue(int num) {
        if (isFull()) {
            return;
        }
        ARRAY[back] = num;
        back++;
        back%=CAPACITY;
        SIZE++;
    }
    
    public Boolean isEmpty() {
        if (SIZE == 0) {
            return true;
        }
        return false;
    }
    
    public Boolean isFull() {
        if (SIZE == CAPACITY) {
            return true;
        }
        return false;
    }
    
    public int front() {
        return ARRAY[front];
    }
    
    public void display() {
        if (isEmpty()) {
            return;
        }
        int x = front;
        for (int i = 0;i<SIZE;i++) {
            
            System.out.println(ARRAY[x%CAPACITY] + " ");
            x++;
        }
        System.out.println();
    }
    
    public void cycle() {
       int temp = front();
       dequeue();
       enqueue(temp);
    }
    
    public int getSize() {
        return SIZE;
    }
    
    public int capacity() {
        return CAPACITY;
    }
    
}

