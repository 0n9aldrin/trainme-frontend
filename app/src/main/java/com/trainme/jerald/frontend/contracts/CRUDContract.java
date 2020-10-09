package com.trainme.jerald.frontend.contracts;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class CRUDContract {

    public interface View<U> {
        void showNotification(String title, String header, String message); //Call When add, delete dan update
    }

    public interface ViewController<U>{
        U getUIData();
        void saveData(String tipe); //update;insert
    }

    public interface Controller<U> {
        void addItem(U item);
        void updateItem(String idItem, U item);
        void deleteItem(String idItem);

        void responseCRUD(boolean status, String type); //Call after the process from Repository
    }

    public interface Repository<U> {
        void addItem(U item);
        void updateItem(String idItem, U item);
        void deleteItem(String idItem);
    }
}
