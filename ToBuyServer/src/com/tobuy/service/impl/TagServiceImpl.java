package com.tobuy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tobuy.constants.ResponseConstants;
import com.tobuy.mapper.TagTableMapper;
import com.tobuy.pojo.vo.tag.TagsResp;
import com.tobuy.service.TagService;

@Service
public class TagServiceImpl implements TagService{

	@Resource
	TagTableMapper tagTableMapper;
	
	@Override
	public TagsResp getAllTagsResp(TagsResp tagsResp) {
		
		tagsResp.setTags(tagTableMapper.getAllTags());
		if (tagsResp.getTags() != null && tagsResp.getTags().size() != 0) {
			tagsResp.setRetcode(Integer.parseInt(ResponseConstants.SUCESS_CODE_END));
		}
		
		return tagsResp;
	}

}
