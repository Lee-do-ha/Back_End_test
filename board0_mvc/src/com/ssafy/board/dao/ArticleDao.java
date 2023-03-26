package com.ssafy.board.dao;

import java.util.List;

import com.ssafy.board.dto.ArticleDto;

public interface ArticleDao {
	// 1. 글 작성 저장
	int writeArticle(ArticleDto article);
	
	// 2. 글 목록 불러오기
	List<ArticleDto> ArticleList();
	
	// 3. 글 상세보기
	ArticleDto viewArticle(int articleNo);
	
	// 4. 글 수정하기
	int modifyArticle(ArticleDto articleDto);
	// 5. 글 삭제하기
	
	// 6. 조회수 갱신
	void updateHit(int articleNo);

	// 7. 글 삭제
	int removeArticle(int articleNo);

}
