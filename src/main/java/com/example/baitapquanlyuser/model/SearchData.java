package com.example.baitapquanlyuser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchData {
    private String action;
    private String fullName;
    private String groupId;
    private String sortType;
    private String sortNameValue;
    private String sortLevelValue;
    private String sortEndDateValue;
    private int currentPage;
    private boolean next;
    private boolean previous;
    boolean hiddenPrevious;
    boolean hiddenNext;
}
