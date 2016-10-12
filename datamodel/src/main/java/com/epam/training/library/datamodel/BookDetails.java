package com.epam.training.library.datamodel;

public class BookDetails extends AbstractModel {

    private Integer pagesCount;

    private Integer coverColor;

    public Integer getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(Integer pagesCount) {
        this.pagesCount = pagesCount;
    }

    public Integer getCoverColor() {
        return coverColor;
    }

    public void setCoverColor(Integer coverColor) {
        this.coverColor = coverColor;
    }

}
