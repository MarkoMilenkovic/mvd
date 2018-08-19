package rs.mvd.domain.listeners;

import rs.mvd.domain.State;

import javax.persistence.PostLoad;

public class StateListener {

    @PostLoad
    private void postLoad(State state){
        System.out.println("USAO U POST LOAD!!!");
    }

}
