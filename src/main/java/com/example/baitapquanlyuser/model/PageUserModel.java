package com.example.baitapquanlyuser.model;

import com.example.baitapquanlyuser.utils.Common;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageUserModel {
    private int totalUser;
    private int limitUser = 3;
    private int limitPage = 3;
    private int totalPage;
    private List<UserInformation> listUser;
    private SearchData searchData;
    private List<Integer> listPage;

    /**
     * Lấy danh sách các trang cần hiển thị ở chuỗi paging theo trang hiện tại.
     * @return list trang theo trang hiện tại
     */
    public List<Integer> getListPaging() {
        ArrayList<Integer> listPaging = new ArrayList<Integer>();
        //tính tổng số page
        int totalPage = Common.getTotalPage(totalUser, limitUser);
        //Lấy trang đầu tiên trong một offset
        int firstPage = Common.getFirstPage(searchData.getCurrentPage(), limitPage);
        //Lấy trang cuối trong một offset
        int endPage = Common.getEndPage(searchData.getCurrentPage(), limitPage, totalPage);

        for (int i = firstPage; i <= endPage; i++) {
            listPaging.add(i);
        }

        return listPaging;
    }
}
