/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cal;

import java.util.Stack;

/**
 *
 * @author emmablomsterberg
 * @param <Integer>
 *
 */
public class ArrayStack<Integer> extends Stack {

    private Integer[] data = null;


    public ArrayStack() {
        data = (Integer[]) new Object[10];
    }

   

//    public void push(Integer element) {
//        data[top] = element;
//        top++;
//    }
//
//    public Integer pop() {
//        Integer popValue = data[top - 1];
//        
//        // top--;
//        return popValue;
//    }
//
//    public boolean isEmpty() {
//        if (data == null) {
//            return true;
//        } else {
//            return false;
//        }
//    }
 
}
