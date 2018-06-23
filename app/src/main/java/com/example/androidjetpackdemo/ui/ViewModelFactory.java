package com.example.androidjetpackdemo.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.androidjetpackdemo.repository.UserRepository;

import java.lang.reflect.InvocationTargetException;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private UserRepository mUserRepository;

    public ViewModelFactory(UserRepository userRepository) {
        this.mUserRepository = userRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (ListViewModel.class.isAssignableFrom(modelClass)) {
            try {
                return modelClass.getConstructor(UserRepository.class).newInstance(mUserRepository);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            } catch (InstantiationException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            }
        }
        throw new IllegalArgumentException("Unknown ViewModel class " + modelClass);
    }
}
