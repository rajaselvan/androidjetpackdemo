package com.example.androidjetpackdemo.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;

import com.example.androidjetpackdemo.model.GetUsersResult;
import com.example.androidjetpackdemo.model.User;
import com.example.androidjetpackdemo.repository.UserRepository;

/**
 *  This class holds the data for our ListFragment.
 *
 * @author Rajaselvan
 */
public class ListViewModel extends ViewModel implements LifecycleObserver {

    private UserRepository mUserRepository;
    private MutableLiveData<GetUsersResult> mUsersResult = new MutableLiveData<>();

    //Extract PagedList data
    private LiveData<PagedList<User>> users = Transformations.switchMap(mUsersResult,
            GetUsersResult::getData
    );

    //Extract network status data
    private LiveData<String> networkErrors = Transformations.switchMap(mUsersResult,
            GetUsersResult::getNetworkErrors
    );

    public ListViewModel(UserRepository userRepository) {
        mUserRepository = userRepository;
    }

    LiveData<PagedList<User>> getUsers() {
        return users;
    }

    LiveData<String> getNetworkErrors() {
        return networkErrors;
    }

    //Load users only when the Activity is visible to the user.
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void loadUsers(){
       mUsersResult.postValue(mUserRepository.getUsers());
    }
}
