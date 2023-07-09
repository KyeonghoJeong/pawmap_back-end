package com.pawmap.board.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.pawmap.board.entity.CommentEntity;
import com.pawmap.board.repository.CommentRepository;

@Repository
public class CommentDaoImpl implements CommentDao {

	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public void postComment(CommentEntity commentEntity) {
		// TODO Auto-generated method stub
		commentRepository.save(commentEntity); // 댓글 엔티티 DB에 저장
	}
	
	@Override
	public void putComment(Long cmtId, String writing) {
		// TODO Auto-generated method stub
		commentRepository.putComment(cmtId, writing); // cmtid(pk)가 동일한 row 댓글 내용 writing으로 수정
	}
	
	@Override
	public void deleteComment(Long cmtId) {
		// TODO Auto-generated method stub
		commentRepository.deleteById(cmtId); // 입력 댓글 id에 해당하는 댓글 삭제
	}

	@Override
	public Page<CommentEntity> getComments(Long articleId, Pageable pageable) {
		// TODO Auto-generated method stub
		
		// 댓글 테이블에서 게시글 id로 조회하고 댓글 작성일 순차 정렬
		Page<CommentEntity> commentEntities = commentRepository.findByArticleIdOrderByPostDateAsc(articleId, pageable);
		
		return commentEntities;
	}

	@Override
	public Long getCommentNumbers(Long articleId) {
		// TODO Auto-generated method stub
		return commentRepository.countByArticleId(articleId); // 해당하는 게시글 id로 댓글 테이블을 조회하여 해당하는 댓글 수 count 리턴
	}

}
