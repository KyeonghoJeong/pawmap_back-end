package com.pawmap.board.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pawmap.board.dao.CommentDao;
import com.pawmap.board.dto.CommentDto;
import com.pawmap.board.entity.CommentEntity;
import com.pawmap.member.dao.MemberDao;
import com.pawmap.member.entity.MemberEntity;

@Service
@Transactional // Ʈ����� ����
public class CommentServiceImpl implements CommentService {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private CommentDao commentDao;
	
	@Override
	public void postComment(CommentDto commentDto) {
		// TODO Auto-generated method stub
		String memberId = SecurityContextHolder.getContext().getAuthentication().getName(); // SecurityContextHolder���� ȸ�� ���̵� ��������
		MemberEntity memberEntity = memberDao.getMember(memberId); // ȸ�� ���̵�� ȸ�� ��ƼƼ ����
		
		Long articleId = commentDto.getArticleId(); // �Խñ� id ��������
		String nickname = memberEntity.getNickname(); // ȸ�� �г��� ��������
		String writing = commentDto.getWriting(); // ��� ����
		Date currentTime = Calendar.getInstance().getTime(); // ���� �ð�
		
		// ��� ��ƼƼ ����
		// �Ķ���� => ��� id(������), �Խñ� id, ȸ�� id, �г���, ����, ��� �ۼ� �ð�  
		CommentEntity commentEntity = new CommentEntity(null, articleId, memberId, nickname, writing, currentTime);
	
		commentDao.postComment(commentEntity); // dao ȣ��
	}
	
	@Override
	public void putComment(CommentDto commentDto) {
		// TODO Auto-generated method stub
		Long cmtId = commentDto.getCmtId(); // ��� id
		String writing = commentDto.getWriting(); // ��� ����
		
		commentDao.putComment(cmtId, writing); // dao ȣ��
	}

	@Override
	public void deleteComment(Long cmtId) {
		// TODO Auto-generated method stub
		commentDao.deleteComment(cmtId); // dao ȣ��
	}
	
	@Override
	public List<Long> getCommentNumbers(List<Long> articleIds) {
		// TODO Auto-generated method stub
		List<Long> commentNumbers = new ArrayList<Long>();
		
		// �� �������� ����ϴ� �Խñ��� ����ŭ �ݺ��Ͽ� �� �Խñ��� ������ �ִ� ��� �� �޾ƿͼ� list�� ����
		for(int i=0; i<articleIds.size(); i++) {
			commentNumbers.add(commentDao.getCommentNumbers(articleIds.get(i)));
		}

		return commentNumbers;
	}

	@Override
	public Page<CommentDto> getComments(Long articleId, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<CommentEntity> commentEntities = commentDao.getComments(articleId, pageable); // dao ȣ���ؼ� ��ƼƼ ����Ʈ �ޱ�
		
		Page<CommentDto> commentDtos = commentEntities.map(CommentDto::new); // Page�� �״�� dto Ŭ������ �����ڸ� �̿��ؼ� ���� ����
		
		return commentDtos;
	}

}
