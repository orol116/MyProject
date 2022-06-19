package edu.kh.Achieve.member.model.vo;

public class CheckPagination {
	
	//Pagination (페이징 처리 : 쪽 구분)에 필요한 모든 값들을 저장하고 있는 객체
	
	private int currentPage; 	// 현재 페이지 번호
	private int listCount; 		// 전체 게시글 수
	
	private int limit = 10; 	// 한 페이지에 보여질 게시글의 수
	private int pageSize = 5; 	// 목록 하단 페이지 번호의 노출 개수
	
	private int maxPage; 		// 제일 큰 페이지 번호 == 마지막 페이지 번호
	private int startPage; 		// 목록 하단에 노출된 페이지의 시작 번호
	private int endPage; 		// 목록 하단에 노출된 페이지의 끝 번호
	
	private int prevPage;		// 목록 하단에 노출된 번호의 이전 목록 끝 번호
	private int nextPage;		// 목록 하단에 노출된 번호의 다음 목록 시작 번호

	public CheckPagination(int currentPage, int listCount) {
		this.currentPage = currentPage;
		this.listCount = listCount;

		calculatePagination(); // 계산 메서드 호출
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		
		calculatePagination();
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
		
		calculatePagination();
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
		
		calculatePagination();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		
		calculatePagination();
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	@Override
	public String toString() {
		return "Pagination [currentPage=" + currentPage + ", listCount=" + listCount + ", limit=" + limit
				+ ", pageSize=" + pageSize + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage="
				+ endPage + ", prevPage=" + prevPage + ", nextPage=" + nextPage + "]";
	}
	
	// 페이징 처리에 필요한 값을 계산하는 메서드
	private void calculatePagination() {
		 
		// *maxPage 계산 : 최대 페이지 수 == 마지막 페이지 번호
	
		// 전체 게시글 수  : 500 / 보여지는 게시글 수 : 5
		// -> 마지막 페이지 번호는? 500 / 5 = 100
		
		// 전체 게시글 수  : 501 / 보여지는 게시글 수 : 5
		// -> 마지막 페이지 번호는? 501 / 5 = 100(100의 올림 처리)
		
		maxPage = (int)Math.ceil((double)listCount / limit); // 강제 형변환 -> 자동 형변환
		
		// *startPage 계산 :  목록 하단에 노출된 페이지의 시작번호
		// 목록 하단 페이지 번호의 노출 개수가 5개 일때 
		
		//현재 페이지가 1~5인 경우 : 1
		//현재 페이지가 6~10인 경우 : 6
		//현재 페이지가 11~15인 경우 :11
		//현재 페이지가 16~20인 경우 : 16
		
		startPage = (currentPage -1) / pageSize * pageSize +1;
		
		
		// *endPage 계산 :  목록 하단에 노출된 페이지의 끝 번호
		// 목록 하단 페이지 번호의 노출 개수가 5개 일때 
		
		//현재 페이지가 1~5인 경우 : 5
		//현재 페이지가 6~10인 경우 : 10
		//현재 페이지가 11~15인 경우 :15
		
		endPage =  startPage + pageSize-1;
		
		// 만약에 endPage가 maxPage를 초과하는 경우
		if(endPage > maxPage) {
			endPage = maxPage;
			}
			//*prevPage(<) : 목록 하단에 노출된 번호의 이전 목록 끝 번호
			//*nextPage(>) : 목록 하단에 노출된 번호의 이전 목록 시작 번호
			
			if(currentPage <= pageSize) {
				prevPage = 1;
			}else {
				prevPage = startPage -1;
			}
		
			if(endPage == maxPage) {
				nextPage = maxPage;
			}else {
				nextPage = endPage+1;
			}
		
	}
	
}
