package com.example.weatherapp;

public interface BaseView<T> {
    void setPresenter(T presenter);
}