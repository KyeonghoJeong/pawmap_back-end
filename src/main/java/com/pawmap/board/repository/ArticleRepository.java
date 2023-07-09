package com.pawmap.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pawmap.board.entity.ArticleEntity;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long>{
	
	// �Խ��� ���
	
	// @Modifying�� nativeQuery���� ����, ���� �� �ݵ�� �ʿ��� annotation
	// where ���� �Խñ� id(pk)�� ��ġ�ϴ� �Խñ��� ����� ���� ����
	@Modifying
	@Query(value="UPDATE article SET title = :title, writing = :writing WHERE articleId = :articleId", nativeQuery=true)
	void putArticle(@Param("articleId") Long articleId, @Param("title") String title, @Param("writing") String writing);
	
	// �Խñ� id, ȸ�� id�� ��ȸ�Ͽ� �Խñ� ����
	void deleteByArticleIdAndMemberId(Long articleId, String memberId);
	
	// �Խñ� �� �Է� ���� ȸ�� id�� �Խñ� id ���� ������ �ִ� row �� ��ȸ
	@Query(value="SELECT COUNT(*) FROM article WHERE articleId = :articleid AND memberId = :memberid", nativeQuery=true)
	Long identifyMember(@Param("articleid") Long articleId, @Param("memberid") String memberId);
	
	// �Ϲ����� �Խ��� ��ȸ ���
	
	// �Խ��� ��ü �Խñ� ��ȸ
	// �ֽ� �Խñ� �켱 ��ȸ => �Խñ� �ۼ� ��¥ �������� ���� 
	Page<ArticleEntity> findByOrderByPostDateDesc(Pageable pageable);
	
	// ����+���� �˻� �� �ش��ϴ� �Խñ� ��ȸ
	// LIKE�� ����Ͽ� �˻�� ���� �Ǵ� ���뿡 �����ϴ� �Խñ� ��� ��ȸ
	@Query(value="SELECT * FROM article WHERE title LIKE %:title% OR writing LIKE %:writing% ORDER BY postdate desc", nativeQuery=true)
	Page<ArticleEntity> getArticlesByTitleOrWritingOrderByPostDateDesc(@Param("title") String title, @Param("writing") String writing, Pageable pageable);
	
	// ���� �Ǵ� ���� �˻� �� �ش��ϴ� �Խñ� ��ȸ
	// LIKE�� ����Ͽ� �˻�� �����ϴ� �Խñ� ��� ��ȸ
	// ���� �Ǵ� ���� �̹Ƿ� title �Ǵ� writing �Ķ���� �� �� ���� �׻� ���� '' => ��ȸ �� ���Ե��� ����
	@Query(value="SELECT * FROM article WHERE title LIKE %:title% AND writing LIKE %:writing% ORDER BY postdate desc", nativeQuery=true)
	Page<ArticleEntity> getArticlesByTitleAndWritingOrderByPostDateDesc(@Param("title") String title, @Param("writing") String writing, Pageable pageable);
	
	// �г��� �˻� �� �ش��ϴ� �Խñ� ��ȸ
	// �ֽ� �Խñ� �켱 ��ȸ => �Խñ� �ۼ� ��¥ �������� ���� 
	Page<ArticleEntity> findByNicknameOrderByPostDateDesc(String nickname, Pageable pageable);
	
	// Ư�� ȸ�� �Խñ� ��ȸ ���

	// Ư�� ȸ�� ��ü �Խñ� ��ȸ => memberId�� �Խñ� ���̺� ��ȸ
	// �ֽ� �Խñ� �켱 ��ȸ => �Խñ� �ۼ� ��¥ �������� ���� 
	Page<ArticleEntity> findByMemberIdOrderByPostDateDesc(String memberId, Pageable pageable);
	
	// Ư�� ȸ�� ����+���� �˻� �� �ش��ϴ� �Խñ� ��ȸ => where���� memberId
	// LIKE�� ����Ͽ� �˻�� ���� �Ǵ� ���뿡 �����ϴ� �Խñ� ��� ��ȸ
	// �ֽ� �Խñ� �켱 ��ȸ => �Խñ� �ۼ� ��¥ �������� ���� 
	@Query(value="SELECT * FROM article WHERE (title LIKE %:title% OR writing LIKE %:writing%) AND memberId = :memberid ORDER BY postdate desc", nativeQuery=true)
	Page<ArticleEntity> getArticlesByTitleOrWritingWithMemberIdOrderByPostDateDesc(@Param("title") String title, @Param("writing") String writing, @Param("memberid") String memberId, Pageable pageable);

	// Ư�� ȸ�� ���� �Ǵ� ���� �˻� �� �ش��ϴ� �Խñ� ��ȸ => where���� memberId
	// LIKE�� ����Ͽ� �˻�� �����ϴ� �Խñ� ��� ��ȸ
	// ���� �Ǵ� ���� �̹Ƿ� title �Ǵ� writing �Ķ���� �� �� ���� �׻� ���� '' => ��ȸ �� ���Ե��� ����
	@Query(value="SELECT * FROM article WHERE (title LIKE %:title% AND writing LIKE %:writing%) AND memberId = :memberid ORDER BY postdate desc", nativeQuery=true)
	Page<ArticleEntity> getArticlesByTitleAndWritingWithMemberIdOrderByPostDateDesc(@Param("title") String title, @Param("writing") String writing, @Param("memberid") String memberId, Pageable pageable);
	
	// ������ ����
	
	// �Խñ� ���� 
	void deleteByArticleId(Long articleId);

}
