package com.tobuy.pojo.vo.card;

import java.util.ArrayList;
import java.util.Date;

import com.tobuy.pojo.CardTable;
import com.tobuy.pojo.UserTable;
import com.tobuy.pojo.vo.base.BaseResp;

public class GetSimpleCardListResp extends BaseResp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2524581231417485651L;
	public ArrayList<BaseUserInfo> baseUserInfos;
	public ArrayList<ArrayList<CardTable>> cardsLists;
	public ArrayList<ArrayList<ArrayList<byte[]>>> cardsListRes;
	public ArrayList<ArrayList<ArrayList<String>>> cardsListTags;
	
	
	
	public GetSimpleCardListResp() {
		super();
		this.baseUserInfos = new ArrayList<GetSimpleCardListResp.BaseUserInfo>();
		this.cardsLists = new ArrayList<ArrayList<CardTable>>();
		this.cardsListRes = new ArrayList<ArrayList<ArrayList<byte[]>>>();
		this.cardsListTags = new ArrayList<ArrayList<ArrayList<String>>>();
	}

	/**
	 * 通过聚合的方式对外提供需要的get set方法
	 * 用户信息
	 * @author Administrator
	 *
	 */
	public class BaseUserInfo {
		
		private UserTable userTable;
		private byte[] userHead;
		
		public BaseUserInfo(UserTable userTable){
			 this.userTable = userTable;
		}
		
		public Integer getId() {
	        return this.userTable.getId();
	    }

	    public void setId(Integer id) {
	        this.userTable.setId(id);
	    }

	    public String getUsername() {
	        return this.userTable.getUsername();
	    }

	    public void setUsername(String username) {
	        this.userTable.setUsername(username);
	    }

	    public Integer getGender() {
	        return this.getGender();
	    }

	    public void setGender(Integer gender) {
	        this.userTable.setGender(gender);
	    }

	    public Date getDate() {
	        return this.getDate();
	    }

	    public void setDate(Date date) {
	        this.setDate(date);
	    }

	    public String getPs() {
	        return this.getPs();
	    }

	    public void setPs(String ps) {
	        this.userTable.setPs(ps);
	    }

	    public Integer getHomeCount() {
	        return this.userTable.getHomeCount();
	    }

	    public void setHomeCount(Integer homeCount) {
	        this.userTable.setHomeCount(homeCount);
	    }

	    public Integer getCardCount() {
	        return this.userTable.getCardCount();
	    }

	    public void setCardCount(Integer cardCount) {
	        this.userTable.setCardCount(cardCount);
	    }

	    public Integer getBrowseCount() {
	        return this.userTable.getBrowseCount();
	    }

	    public void setBrowseCount(Integer browseCount) {
	        this.userTable.setBrowseCount(browseCount);
	    }

	    public Integer getMessageCount() {
	        return this.userTable.getMessageCount();
	    }

	    public void setMessageCount(Integer messageCount) {
	        this.userTable.setMessageCount(messageCount);
	    }

		public byte[] getUserHead() {
			return userHead;
		}

		public void setUserHead(byte[] userHead) {
			this.userHead = userHead;
		}
	    
	}

	public ArrayList<BaseUserInfo> getBaseUserInfos() {
		return baseUserInfos;
	}

	public void setBaseUserInfos(ArrayList<BaseUserInfo> baseUserInfos) {
		this.baseUserInfos = baseUserInfos;
	}

	public ArrayList<ArrayList<CardTable>> getCardsLists() {
		return cardsLists;
	}

	public void setCardsLists(ArrayList<ArrayList<CardTable>> cardsLists) {
		this.cardsLists = cardsLists;
	}

	public ArrayList<ArrayList<ArrayList<byte[]>>> getCardsListRes() {
		return cardsListRes;
	}

	public void setCardsListRes(ArrayList<ArrayList<ArrayList<byte[]>>> cardsListRes) {
		this.cardsListRes = cardsListRes;
	}

	public ArrayList<ArrayList<ArrayList<String>>> getCardsListTags() {
		return cardsListTags;
	}

	public void setCardsListTags(ArrayList<ArrayList<ArrayList<String>>> cardsListTags) {
		this.cardsListTags = cardsListTags;
	}

	
	
}
