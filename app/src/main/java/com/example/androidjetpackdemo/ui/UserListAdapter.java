package com.example.androidjetpackdemo.ui;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.androidjetpackdemo.R;
import com.example.androidjetpackdemo.databinding.ListItemBinding;
import com.example.androidjetpackdemo.model.User;

/**
 * This class presents paged data from PagedLists in a RecyclerView.
 * It listens to PagedList loading callbacks as pages are loaded and
 * uses DiffUtil to compute updates as new PagedLists are received.
 *
 * @author Rajaselvan
 *
 */
public class UserListAdapter extends PagedListAdapter<User, UserListAdapter.UserViewHolder> {


    private static DiffUtil.ItemCallback<User> USER_COMPARATOR
            = new DiffUtil.ItemCallback<User>() {
        @Override
        public boolean areItemsTheSame(User oldItem, User newItem) {
            return oldItem.id.equals(newItem.id);
        }

        @Override
        public boolean areContentsTheSame(User oldItem, User newItem) {
            return oldItem == newItem;
        }
    };

        UserListAdapter() {
        super(USER_COMPARATOR);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemBinding itemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new UserViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bind(getItem(position));
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {
        private final ListItemBinding mDataBinding;

        UserViewHolder(ListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.mDataBinding = itemBinding;
        }

        void bind(User user) {

            // Paging sometimes loads empty placeholders with null values. Handle them
            if (user == null) {
                mDataBinding.tvFirstName.setText("");
                mDataBinding.tvLastName.setText("");
                Glide.with(mDataBinding.getRoot().getContext()).load(mDataBinding.getRoot().getContext().getResources().getDrawable(R.drawable.profile)).into(mDataBinding.ivProfileImage);

            } else {
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.placeholder(mDataBinding.getRoot().getContext().getResources().getDrawable(R.drawable.profile));
                requestOptions.error(mDataBinding.getRoot().getContext().getResources().getDrawable(R.drawable.profile));

                mDataBinding.setUser(user);
                Glide.with(mDataBinding.getRoot().getContext()).applyDefaultRequestOptions(requestOptions).load(user.avatar).into(mDataBinding.ivProfileImage);
                mDataBinding.executePendingBindings();
            }
        }
    }
}
