package com.example.userfriendlycalculator;
// written by: Edgar
// tested by:
// debugged by:

import android.os.Bundle;

import java.util.*;


public class history extends MainActivity {
    Stack<String> stack = new Stack<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
    }

    //push result into stack
    public void writeResult(String n) {
        stack.push(n);
    }

    //i want this to pop into a result screen, but I don't know how to do that
    //maybe create an array of exactly the size of the stack? probably redundant
    public String[] readResult() {
        int count = stack.size();
        String[] array = new String[count];

        for(int i = 0; i < count; i++){
            array[i] = stack.pop();
        }

        return array;
    }

    public void clear() {
        while(!stack.isEmpty()) {
            stack.pop();
        }
    }

}

