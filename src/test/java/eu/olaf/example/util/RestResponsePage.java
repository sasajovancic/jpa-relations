package eu.olaf.example.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class RestResponsePage<T> implements Page<T> {

    private PageImpl<T> pageDelegate = new PageImpl<T>(new ArrayList<T>());

    public List<T> getContent() {
        return pageDelegate.getContent();
    }

    public int getNumber() {
        return pageDelegate.getNumber();
    }

    public int getNumberOfElements() {
        return pageDelegate.getNumberOfElements();
    }

    public int getSize() {
        return pageDelegate.getSize();
    }

    public Sort getSort() {
        return pageDelegate.getSort();
    }

    public long getTotalElements() {
        return pageDelegate.getTotalElements();
    }

    @Override
    public <U> Page<U> map(Function<? super T, ? extends U> function) {
        return pageDelegate.map(function);
    }

    public int getTotalPages() {
        return pageDelegate.getTotalPages();
    }

    public boolean hasContent() {
        return pageDelegate.hasContent();
    }

    public boolean hasNext() {
        return pageDelegate.hasNext();
    }

    public boolean hasPrevious() {
        return pageDelegate.hasPrevious();
    }

    public boolean isFirst() {
        return pageDelegate.isFirst();
    }

    public boolean isLast() {
        return pageDelegate.isLast();
    }

    public Iterator<T> iterator() {
        return pageDelegate.iterator();
    }

    public Pageable nextPageable() {
        return pageDelegate.nextPageable();
    }

    public Pageable previousPageable() {
        return pageDelegate.previousPageable();
    }

    public void setContent(List<T> content) {
        pageDelegate = new PageImpl<T>(content, PageRequest.of(getNumber(), getTotalPages()), getTotalElements());
    }


    public void setTotalElements(int totalElements) {
        pageDelegate = new PageImpl<>(getContent(), PageRequest.of(getNumber(), getTotalPages()), totalElements);
    }

    public String toString() {
        return pageDelegate.toString();
    }
}
