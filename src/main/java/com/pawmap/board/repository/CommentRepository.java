package com.pawmap.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pawmap.board.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

	Page<CommentEntity> findByArticleIdOrderByPostDateAsc(Long articleId, Pageable pageable);

}