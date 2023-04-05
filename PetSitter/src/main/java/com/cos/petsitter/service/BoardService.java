package com.cos.petsitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.petsitter.model.Boards;
import com.cos.petsitter.model.Member;
import com.cos.petsitter.repository.BoardRepository;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해준다. Ioc를 해준다.
@Service
public class BoardService {

	@Autowired(required=false)
	private BoardRepository boardRepository;

	@Transactional
	public void 글쓰기(Boards board, Member member) { // title, content
		board.setCount(0);
		board.setMember(member);
		boardRepository.save(board);
	}

//	@Transactional(readOnly = true)
//	public Page<Boards> 글목록(Pageable pageable) {
//		return boardRepository.findAll(pageable);
//	}
	
	@Transactional(readOnly = true)
	public Page<Boards> 글목록(Pageable pageable){
		return boardRepository.findAll(pageable);
	}


	@Transactional(readOnly = true)
	public Boards 글상세보기(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
		});
	}

	@Transactional
	public void 글삭제하기(int id) {
		boardRepository.deleteById(id);
	}

	@Transactional
	public void 글수정하기(int id, Boards requestBoard) {
		Boards board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다.");
		}); // 영속화 완료
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		// 해당 함수로 종료(Service 종료)시 트랜젝션이 종료된다.
		// 영속화 되어있는 board의 데이터가 달라졌기 때문에 
		// 이때 더티체킹이 일어나면서 - 자동 업데이트가 된다.(commit = db flush)
	}
}
