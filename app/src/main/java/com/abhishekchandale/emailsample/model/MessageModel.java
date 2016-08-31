package com.abhishekchandale.emailsample.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhishek on 29/8/16.
 */

@Table(name = "message")
public class MessageModel extends Model implements Parcelable {
    @Expose
    @SerializedName("id")
    @Column(name = "messageId")
    private int messageId;
    @Expose
    @Column(name = "subject")
    private String subject;
    @Expose
    private List<String> participants;
    @Expose
    @Column(name = "preview")
    private String preview;
    @Expose
    @Column(name = "isRead")
    private boolean isRead;
    @Expose
    @Column(name = "isStarted")
    private boolean isStarted;
    @Expose
    @Column(name = "ts")
    private long ts;

    public MessageModel() {
    }

    protected MessageModel(Parcel in) {
        messageId = in.readInt();
        subject = in.readString();
        participants = in.createStringArrayList();
        preview = in.readString();
        isRead = in.readByte() != 0;
        isStarted = in.readByte() != 0;
        ts = in.readLong();
    }

    public static final Creator<MessageModel> CREATOR = new Creator<MessageModel>() {
        @Override
        public MessageModel createFromParcel(Parcel in) {
            return new MessageModel(in);
        }

        @Override
        public MessageModel[] newArray(int size) {
            return new MessageModel[size];
        }
    };

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(messageId);
        dest.writeString(subject);
        dest.writeStringList(participants);
        dest.writeString(preview);
        dest.writeByte((byte) (isRead ? 1 : 0));
        dest.writeByte((byte) (isStarted ? 1 : 0));
        dest.writeLong(ts);
    }
}
