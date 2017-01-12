package com.pands.dev.pands;

import android.app.Application;

import com.squareup.otto.Bus;

public class App extends Application
{
    private static Bus bus;
    int pageNumber = 1;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();

        bus = new Bus(); // Instantiate a new Bus
    }

    public static Bus getBus()
    {
        return bus;
    }
}