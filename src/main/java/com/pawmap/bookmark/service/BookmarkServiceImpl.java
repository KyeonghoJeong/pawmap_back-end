package com.pawmap.bookmark.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawmap.bookmark.dao.BookmarkDao;
import com.pawmap.bookmark.dto.BookmarkDto;
import com.pawmap.bookmark.entity.BookmarkEntity;

@Service
public class BookmarkServiceImpl implements BookmarkService {
	
	@Autowired
	private BookmarkDao bookmarkDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String addBookmark(BookmarkDto bookmarkDto) {
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setFieldAccessLevel(AccessLevel.PRIVATE);
		BookmarkEntity bookmarkEntity = modelMapper.map(bookmarkDto, BookmarkEntity.class);
		
		String result = bookmarkDao.addBookmark(bookmarkEntity);
		
		return result;
	}
	
}
