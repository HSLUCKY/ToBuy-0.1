package com.tobuy.pojo.vo.card;

import java.util.ArrayList;

import com.tobuy.pojo.CardTable;
import com.tobuy.pojo.UserTable;

public class GetSimpleCardList{
	
	public ArrayList<UserTable> by_users;
	public ArrayList<CardTable> cards;
	
	public ArrayList<UserTable> getBy_users() {
		return by_users;
	}
	public void setBy_users(ArrayList<UserTable> by_users) {
		this.by_users = by_users;
	}
	public ArrayList<CardTable> getCards() {
		return cards;
	}
	public void setCards(ArrayList<CardTable> cards) {
		this.cards = cards;
	}
	
	
}
