package com.example.administrator.week02demo.Component;

import com.example.administrator.week02demo.MainActivity;
import com.example.administrator.week02demo.Module.HttpModule;

import dagger.Component;

/**
 * Created by Administrator on 2018/5/19.
 */
@Component(modules = HttpModule.class)
public interface HttpComponent {
    void inject(MainActivity mainActivity);
}
