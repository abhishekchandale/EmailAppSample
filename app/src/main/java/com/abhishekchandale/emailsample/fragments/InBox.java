package com.abhishekchandale.emailsample.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhishekchandale.emailsample.R;
import com.abhishekchandale.emailsample.adapter.InBoxAdapter;
import com.abhishekchandale.emailsample.apimanager.APIManager;
import com.abhishekchandale.emailsample.apimanager.RetrofitAPIService;
import com.abhishekchandale.emailsample.database.MessageCrudOperations;
import com.abhishekchandale.emailsample.databinding.InboxBinding;
import com.abhishekchandale.emailsample.model.MessageModel;
import com.abhishekchandale.emailsample.util.ConnectionDetector;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 29/8/16.
 */
public class InBox extends Fragment {
    private InboxBinding mDataBinder = null;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final String TAG = InBox.class.getSimpleName();
    private static InBoxAdapter mAdapter = null;

    public InBox() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mDataBinder = DataBindingUtil.inflate(inflater, R.layout.inbox, container, false);
        return mDataBinder.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        mLayoutManager = new LinearLayoutManager(getActivity());
        mDataBinder.inboxList.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (ConnectionDetector.getInstance().checkDataConnection(getActivity())) {
            mDataBinder.downloadProgress.setVisibility(View.VISIBLE);
            getMessageFromServer();
        } else {
            Log.d(TAG, "internet connection");
        }

    }

    private void getMessageFromServer() {

        RetrofitAPIService retrofitAPIService = APIManager.getRetrofitServiceeInstanse();
        final Call<List<MessageModel>> getImages = retrofitAPIService.getMessages();
        getImages.enqueue(new Callback<List<MessageModel>>() {
            @Override
            public void onResponse(Call<List<MessageModel>> call, Response<List<MessageModel>> response) {
                Log.d(TAG, "" + response.code());
                if (response.isSuccess() && response.body() != null) {
                    mDataBinder.downloadProgress.setVisibility(View.GONE);
                    List<MessageModel> messageModels = response.body();
                    MessageCrudOperations.getInstance().updateOrderRecords(messageModels);
                    for (int i = 0; i < messageModels.size(); i++) {
                        Log.d(TAG, messageModels.get(i).getSubject());
                    }
                    try {
                        mAdapter = new InBoxAdapter(MessageCrudOperations.getInstance().getAllMessage(), InBox.this);
                        mDataBinder.inboxList.setAdapter(mAdapter);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.d(TAG, "" + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<MessageModel>> call, Throwable t) {
                t.printStackTrace();
                Log.d(TAG, "" + t.getMessage());
            }
        });

    }


}
