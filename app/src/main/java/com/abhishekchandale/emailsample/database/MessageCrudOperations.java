package com.abhishekchandale.emailsample.database;

/**
 * Created by abhishek on 29/8/16.
 */

import android.util.Log;

import com.abhishekchandale.emailsample.model.MessageModel;
import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

public class MessageCrudOperations {

    private final String TAG = MessageCrudOperations.class.getSimpleName();

    private static MessageCrudOperations mMessageCrudOperations = null;


    public static MessageCrudOperations getInstance() {
        if (mMessageCrudOperations == null) {
            synchronized (MessageCrudOperations.class) {
                if (mMessageCrudOperations == null) {
                    mMessageCrudOperations = new MessageCrudOperations();
                    return mMessageCrudOperations;
                }
            }
        }
        return mMessageCrudOperations;
    }

    public boolean updateOrderRecords(List<MessageModel> resp) {
        List<MessageModel> messageModels = null;
        if (resp != null) {
            messageModels = resp;
            ActiveAndroid.beginTransaction();
            try {
                for (MessageModel message : messageModels) {
                    MessageModel messageDB = getMessage(message.getMessageId());
                    if (messageDB == null) {
                        messageDB = message;
                    } else {
                        messageDB.setSubject(message.getSubject());
                        messageDB.setPreview(message.getPreview());
                        messageDB.setTs(message.getTs());
                        Log.d(TAG, "Records updated" + message.getMessageId());
                    }
                    if (messageDB != null) {
                        messageDB.save();
                    }

                }
                ActiveAndroid.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                ActiveAndroid.endTransaction();
            }
        }

        return true;
    }


    public MessageModel getMessage(int id) {
        return new Select()
                .from(MessageModel.class)
                .where("messageId = ?", id)
                .executeSingle();
    }

    public List<MessageModel> getAllMessage() {
        return new Select()
                .from(MessageModel.class)
                .orderBy("messageId")
                .execute();
    }

    public void deleteMessage(int id) {
        MessageModel messageModel = MessageModel.load(MessageModel.class, id);
        messageModel.delete();
    }

}

