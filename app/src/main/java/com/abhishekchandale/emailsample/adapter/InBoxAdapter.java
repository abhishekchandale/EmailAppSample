package com.abhishekchandale.emailsample.adapter;

/**
 * Created by abhishek on 29/8/16.
 */

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abhishekchandale.emailsample.R;
import com.abhishekchandale.emailsample.fragments.InBox;
import com.abhishekchandale.emailsample.fragments.MessageDetail;
import com.abhishekchandale.emailsample.model.MessageModel;
import com.abhishekchandale.emailsample.util.CommonUtil;

import java.util.List;

/**
 * Created by user on 2/11/2016.
 */

public class InBoxAdapter extends RecyclerView.Adapter<InBoxAdapter.MyViewHolder> {

    private final String TAG = InBoxAdapter.class.getSimpleName();
    private List<MessageModel> mMessageList = null;
    private InBox mInBox = null;


    public InBoxAdapter(List<MessageModel> messageList, InBox inBoxAdapter) {
        this.mMessageList = messageList;
        this.mInBox = inBoxAdapter;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inbox_item_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final MessageModel messageModel = mMessageList.get(position);
        if (messageModel != null) {
            holder.mMessageText.setText(messageModel.getSubject());
            holder.mTimeStamp.setText(CommonUtil.convertDate(messageModel.getTs()));
        }
    }

    @Override
    public int getItemCount() {
        if (mMessageList != null) {
            return mMessageList.size();
        }
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mMessageText, mTimeStamp;
        public LinearLayout container;

        public MyViewHolder(View view) {
            super(view);
            mMessageText = (TextView) view.findViewById(R.id.txt_message);
            mTimeStamp = (TextView) view.findViewById(R.id.txt_timeStamp);
            container = (LinearLayout) view.findViewById(R.id.container);
            container.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.container:
                    try {
                        int position = getAdapterPosition();
                        MessageModel messageModel = mMessageList.get(position);
                        FragmentTransaction ft = mInBox.getFragmentManager().beginTransaction();
                        MessageDetail messageDetail = new MessageDetail();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("messageDetail", messageModel);
                        messageDetail.setArguments(bundle);
                        ft.replace(R.id.frame_main, messageDetail, MessageDetail.class.getSimpleName());
                        ft.addToBackStack(MessageDetail.class.getSimpleName());
                        ft.commitAllowingStateLoss();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

}


