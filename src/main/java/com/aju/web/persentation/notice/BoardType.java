package com.aju.web.persentation.notice;

/**
 * packageName :  com.aju.web.persentation.notice
 * fileName : BoardType
 * author :  ddh96
 * date : 2023-04-30 
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-04-30                ddh96             최초 생성
 */
public enum BoardType {
    NOTICE(false), PROJECT(true);

    private final boolean isProject;

    BoardType(boolean isProject) {
        this.isProject = isProject;
    }

    public boolean isProject() {
        return isProject;
    }
}
