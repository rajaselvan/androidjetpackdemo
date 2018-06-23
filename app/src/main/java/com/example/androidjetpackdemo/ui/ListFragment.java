package com.example.androidjetpackdemo.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidjetpackdemo.Injection;
import com.example.androidjetpackdemo.R;
import com.example.androidjetpackdemo.databinding.ListFragmentBinding;


public class ListFragment extends Fragment {

    private ListViewModel mViewModel;
    private UserListAdapter mUserListAdapter;
    private ListFragmentBinding mDataBinding;

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(
                inflater, R.layout.list_fragment, container, false);
        mDataBinding.setLifecycleOwner(this);
        View view = mDataBinding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(getActivity())).get(ListViewModel.class);
        initRecyclerView();
        getLifecycle().addObserver(mViewModel);
    }

    private void initRecyclerView() {

        mDataBinding.rvUsers.setHasFixedSize(true);

        mDataBinding.rvUsers.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        mDataBinding.rvUsers.addItemDecoration(dividerItemDecoration);

        initAdapter();
    }


    private void initAdapter() {

        mUserListAdapter = new UserListAdapter();

        mDataBinding.rvUsers.setAdapter(mUserListAdapter);

        mViewModel.getUsers().observe(this, users -> {
            if (users != null) {
                showEmptyList(users.size() == 0);
                mUserListAdapter.submitList(users);
            }
        });

        mViewModel.getNetworkErrors().observe(this, errorMsg -> {
            //TODO
        });
    }

    private void showEmptyList(boolean show) {
        if (show) {
            mDataBinding.rvUsers.setVisibility(View.GONE);
            mDataBinding.emptyList.setVisibility(View.VISIBLE);
        } else {
            mDataBinding.rvUsers.setVisibility(View.VISIBLE);
            mDataBinding.emptyList.setVisibility(View.GONE);
        }
    }


}
