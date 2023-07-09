package com.pawmap.board.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public void postComment(Map<String, String> commentData, String memberId) {
		// TODO Auto-generated method stub
		MemberEntity memberEntity = memberDao.getMember(memberId); // ȸ�� ���̵�� ȸ�� ��ƼƼ ����
		
		Long articleId = Long.parseLong(commentData.get("articleId")); // �Խñ� id ����ȯ
		String nickname = memberEntity.getNickname(); // ȸ�� �г��� ��������
		String writing = commentData.get("writing"); // ��� ����
		Date currentTime = Calendar.getInstance().getTime(); // ���� �ð�
		
		// ��� ��ƼƼ ����
		// �Ķ���� => ��� id(������), �Խñ� id, ȸ�� id, �г���, ����, ��� �ۼ� �ð�  
		CommentEntity commentEntity = new CommentEntity(null, articleId, memberId, nickname, writing, currentTime);
	
		commentDao.postComment(commentEntity); // dao ȣ��
	}
	
	@Override
	public void putComment(Map<String, String> commentData) {
		// TODO Auto-generated method stub
		Long cmtId = Long.parseLong(commentData.get("cmtId")); // ��� id
		String writing = commentData.get("writing"); // ��� ����
		
		commentDao.putComment(cmtId, writing); // dao ȣ��
	}

	@Override
	public void deleteComment(Long cmtId) {
		// TODO Auto-generated method stub
		commentDao.deleteComment(cmtId); // dao ȣ��
	}

	@Override
	public Page<CommentDto> getComments(Long articleId, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<CommentEntity> commentEntities = commentDao.getComments(articleId, pageable); // dao ȣ���ؼ� ��ƼƼ ����Ʈ �ޱ�
		
		Page<CommentDto> commentDtos = commentEntities.map(CommentDto::new); // Page�� �״�� dto Ŭ������ �����ڸ� �̿��ؼ� ���� ����
		
		return commentDtos;
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

}
