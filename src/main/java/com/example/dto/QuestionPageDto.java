package com.example.dto;


import java.util.ArrayList;
import java.util.List;

public class QuestionPageDto {
    private List<QuestionDto> questionDtoList;
    private Integer page;
    private boolean firstPage;
    private boolean endPage;
    private boolean lastPage;
    private boolean nextPage;
    private List<Integer> listPage=new ArrayList<>();

    public List<QuestionDto> getQuestionDtoList() {
        return questionDtoList;
    }

    public void setQuestionDtoList(List<QuestionDto> questionDtoList) {
        this.questionDtoList = questionDtoList;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isEndPage() {
        return endPage;
    }

    public void setEndPage(boolean endPage) {
        this.endPage = endPage;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public boolean isNextPage() {
        return nextPage;
    }

    public void setNextPage(boolean nextPage) {
        this.nextPage = nextPage;
    }

    public List<Integer> getListPage() {
        return listPage;
    }

    public void setListPage(List<Integer> listPage) {
        this.listPage = listPage;
    }

    public void setQuestionPageDto(Integer titleCount, Integer page, Integer size) {
        this.page=page;
        Integer pageCount=titleCount/size;
        if(titleCount%size!=0){
            pageCount+=1;
        }
        if(page<=3&&page>=pageCount-3) {
            for (Integer i = 1; i <=pageCount; i++) {
                listPage.add(i);
            }
        }

        if(page>3&&page>=pageCount-3) {
            for (Integer i = 1; i <= pageCount-page+3; i++) {
                listPage.add(page-3+i);
            }
        }
        if(page<=3&&page<pageCount-3) {
            for (Integer i = 1; i <= page; i++) {
                listPage.add(1);
            }
            listPage.add(page+1);
            listPage.add(page+2);
        }
        if(page>3&&page<pageCount-3) {
            for (Integer i = 1; i <= 5; i++) {
                listPage.add(page-3+i);
            }
        }


    }
}
