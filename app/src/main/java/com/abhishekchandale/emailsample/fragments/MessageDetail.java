package com.abhishekchandale.emailsample.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhishekchandale.emailsample.R;
import com.abhishekchandale.emailsample.apimanager.APIManager;
import com.abhishekchandale.emailsample.apimanager.RetrofitAPIService;
import com.abhishekchandale.emailsample.database.MessageCrudOperations;
import com.abhishekchandale.emailsample.databinding.MesssageDetailBinding;
import com.abhishekchandale.emailsample.model.MessageModel;
import com.abhishekchandale.emailsample.util.CommonUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 30/8/16.
 */
public class MessageDetail extends Fragment {

    private MesssageDetailBinding mDataBinder = null;
    private static final String TAG = MessageDetail.class.getSimpleName();
    MessageModel messageModel = null;
    public MessageDetail() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinder = DataBindingUtil.inflate(inflater, R.layout.messsage_detail, container, false);
        preapareView();
        return mDataBinder.getRoot();
    }

    private void preapareView() {
       int messageId = 0;
        Bundle bundle = getArguments();
        messageModel = (MessageModel) bundle.getParcelable("messageDetail");
        if (messageModel != null) {
            mDataBinder.txtSubject.setText(messageModel.getSubject());
            mDataBinder.txtMessage.setText(messageModel.getPreview());
            mDataBinder.txtTm.setText(CommonUtil.convertDate(messageModel.getTs()));
            mDataBinder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteMessage(messageModel.getMessageId());
                }
            });
        }


    }

    private  void deleteMessage(final int id) {

        RetrofitAPIService retrofitAPIService = APIManager.getRetrofitServiceeInstanse();
        Call<Void> deleteMessageFromServer = retrofitAPIService.deleteMessage(id);
        deleteMessageFromServer.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccess()) {
                    MessageCrudOperations.getInstance().deleteMessage(id);
                    FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                    InBox inBox=new InBox();
                    fragmentTransaction.replace(R.id.frame_main,inBox);
                    fragmentTransaction.addToBackStack(InBox.class.getSimpleName());
                    fragmentTransaction.commitAllowingStateLoss();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });


    }
}
