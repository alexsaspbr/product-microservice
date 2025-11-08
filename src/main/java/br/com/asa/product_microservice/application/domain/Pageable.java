package br.com.asa.product_microservice.application.domain;

public class Pageable {

    private Integer size;
    private Integer page;
    private String sort;

    public Pageable(Integer size, Integer page, String sort) {
        this.size = size;
        this.page = page;
        this.sort = sort;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
