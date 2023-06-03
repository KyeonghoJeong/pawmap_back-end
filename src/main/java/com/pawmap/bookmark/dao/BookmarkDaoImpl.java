package com.pawmap.bookmark.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pawmap.bookmark.entity.BookmarkEntity;
import com.pawmap.bookmark.repository.BookmarkRepository;

@Repository
public class BookmarkDaoImpl implements BookmarkDao {
	
	@Autowired
	private BookmarkRepository bookmarkRepository;

	@Override
	public String addBookmark(BookmarkEntity bookmarkEntity) {
		// TODO Auto-generated method stub
		Long count = bookmarkRepository.countByMemberIdAndFacilityId(bookmarkEntity.getMemberId(), bookmarkEntity.getFacilityId());

		if(count != 0) {
			return "Duplicated";
		}else {
			bookmarkRepository.save(bookmarkEntity);
			
			return "Success";
		}
	}

}
