package com.deepanshu.moviestore.storeone.application;

import android.app.Application;

import com.deepanshu.moviestore.storeone.dagger.components.DaggerNetComponent;
import com.deepanshu.moviestore.storeone.dagger.components.NetComponent;
import com.deepanshu.moviestore.storeone.dagger.modules.AppModule;
import com.deepanshu.moviestore.storeone.dagger.modules.NetModule;

public class MyApp extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("http://example.com"))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
