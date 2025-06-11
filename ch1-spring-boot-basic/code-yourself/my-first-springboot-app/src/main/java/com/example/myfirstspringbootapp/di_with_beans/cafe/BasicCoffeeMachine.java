package com.example.myfirstspringbootapp.di_with_beans.cafe;

public class BasicCoffeeMachine implements CoffeeMachine {
    @Override
    public String brew() {
        return "Basic CoffeeMachine";
    }
}
