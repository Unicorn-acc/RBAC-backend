package rbac.vo;


import javax.validation.constraints.NotNull;

/**
 * @author MiracloW
 */
public class PageParams {

    @NotNull(message = "页号不能为空")
    private Integer pageNum;

    @NotNull(message = "条数不能为空")
    private Integer pageSize;

    public PageParams() {
    }

    public PageParams(@NotNull(message = "页号不能为空") Integer pageNum, @NotNull(message = "条数不能为空") Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
