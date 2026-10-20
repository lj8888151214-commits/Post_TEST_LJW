package com.example.Post.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Page {
    // 필수 입력 값
    private int pageNum;       // 현재 페이지 번호
    private int rowsPerPage;   // 한 페이지당 출력할 게시글 개수
    private int totalCount;    // 전체 게시글 개수

    // 계산되는 값
    private int startPage;     // 네비게이션 시작 페이지 번호 (예: 1, 11, 21...)
    private int endPage;       // 네비게이션 끝 페이지 번호 (예: 10, 20, 30...)
    private int firstPage;     // 첫 페이지 번호 (항상 1)
    private int lastPage;      // 마지막 페이지 번호
    private boolean prev;      // 이전 페이지 블록 이동 가능 여부
    private boolean next;      // 다음 페이지 블록 이동 가능 여부

    public Page() {
        this(1, 10, 0);
    }

    public Page(int pageNum, int rowsPerPage, int totalCount) {
        this.pageNum = Math.max(pageNum, 1);
        this.rowsPerPage = rowsPerPage <= 0 ? 10 : rowsPerPage;
        this.totalCount = totalCount;
        this.firstPage = 1;

        calcPage();
    }

    private void calcPage() {
        int pageBlock = 10; // 하단 네비게이션에 표시할 페이지 개수

        // 끝 페이지 번호 계산
        this.endPage = (int) Math.ceil((double) this.pageNum / pageBlock) * pageBlock;
        // 시작 페이지 번호 계산
        this.startPage = this.endPage - pageBlock + 1;

        // 실제 마지막 페이지 번호 계산
        this.lastPage = (int) Math.ceil((double) this.totalCount / this.rowsPerPage);
        if (this.lastPage == 0) {
            this.lastPage = 1;
        }

        // 끝 페이지가 실제 마지막 페이지를 넘지 않도록 보정
        if (this.endPage > this.lastPage) {
            this.endPage = this.lastPage;
        }

        // 이전/다음 블록 이동 링크 활성화 여부
        this.prev = this.startPage > 1;
        this.next = this.endPage < this.lastPage;
    }

    // MyBatis LIMIT의 OFFSET으로 사용될 시작 인덱스 getter
    public int getIndex() {
        return (this.pageNum - 1) * this.rowsPerPage;
    }
}