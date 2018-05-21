package com.tobuy.service;

import com.tobuy.pojo.vo.card.GetSimpleCardListResp;
import com.tobuy.pojo.vo.card.PublishCardResp;

public interface CardService {
	public abstract GetSimpleCardListResp getSimpleCardList(int start, int count, int type);
	public abstract PublishCardResp publishCard(PublishCardResp publishCardResp);
}
