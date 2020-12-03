package com.cmp.javahowto.framework.sample;

public class Human {

    private Move move;

    public Human(Move move) {
        this.move = move;
    }

    public void doMove() {
        move.action();
    }

}
