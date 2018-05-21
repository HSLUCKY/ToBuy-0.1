package com.tobuy.pojo.vo.message;

import com.tobuy.pojo.MessageTable;
import com.tobuy.pojo.vo.base.BaseResp;

public class SendMessageResp extends BaseResp {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2860069473365383911L;
	public int from_user_id;
    public int to_id;
    public int type;
    public MessageTable messageTable;

    public int getFrom_user_id() {
        return from_user_id;
    }

    public void setFrom_user_id(int from_user_id) {
        this.from_user_id = from_user_id;
    }

    public MessageTable getMessageTable() {
        return messageTable;
    }

    public void setMessageTable(MessageTable messageTable) {
        this.messageTable = messageTable;
    }

    public int getTo_id() {
        return to_id;
    }

    public void setTo_id(int to_id) {
        this.to_id = to_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
