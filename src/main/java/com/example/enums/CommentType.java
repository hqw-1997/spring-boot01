package com.example.enums;

public enum CommentType {
    QUESTION_COMMENT(1),
    COMMENT_COMMENT(2);
    private Integer Type;

    public Integer getType() {
        return Type;
    }


    @Override
    public String toString() {
        return "CommentType{" +
                "Type=" + Type +
                '}';
    }

    CommentType(Integer type) {
        Type = type;
    }

    public static boolean isOrNoExit(Integer type){
        if(type==QUESTION_COMMENT.getType() || type==COMMENT_COMMENT.getType()){
            return true;
        }else {
            return false;
        }
    }
}
