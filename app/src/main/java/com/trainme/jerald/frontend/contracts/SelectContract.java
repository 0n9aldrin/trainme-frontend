package com.trainme.jerald.frontend.contracts;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import io.realm.RealmResults;

public class SelectContract {

    public interface View<U> {
    }

    public interface Controller<U> {
        RealmResults<U> getAllItem();
        U getSingleItemById(int idItem);
    }

    public interface Repository<U> {
        RealmResults<U> getAllItem();
        U getSingleItemById(int idItem);
    }
}
