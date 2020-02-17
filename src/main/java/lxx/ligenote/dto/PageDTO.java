package lxx.ligenote.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:PageDTO
 * Package:lxx.ligenote.dto
 * Description:
 *
 * @Date:2019/12/18 22:24
 * @Author:857251389@qq.com
 */
@Data
public class PageDTO<T> {
    private List<T> data;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;


    public void setPagination(Integer totalPage, Integer pageNum) {
        this.totalPage=totalPage;
        page=pageNum;

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i >= 1) {
                pages.add(0,page-i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }
        //是否展示上一页,下一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }

        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }
        //是否展示第一页，最后一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }

    }
}
