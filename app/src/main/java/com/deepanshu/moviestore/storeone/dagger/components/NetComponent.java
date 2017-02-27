package com.deepanshu.moviestore.storeone.dagger.components;

import com.deepanshu.moviestore.storeone.dagger.modules.AppModule;
import com.deepanshu.moviestore.storeone.dagger.modules.NetModule;
import com.deepanshu.moviestore.storeone.views.activities.MainActivity;
import com.deepanshu.moviestore.storeone.views.activities.MovieDetailActivity;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(MainActivity activity);
    void inject(MovieDetailActivity activity);
}
