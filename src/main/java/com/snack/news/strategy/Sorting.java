package com.snack.news.strategy;

import com.snack.news.domain.Corporation;

import java.util.Comparator;

/**
 * @author delf
 */
public enum Sorting {
    NAME(Comparator.comparing(Corporation::getName));

    private Comparator<Corporation> operator;

    Sorting(Comparator<Corporation> operator) {
        this.operator = operator;
    }

    public Comparator<Corporation> getOperator() {
        return operator;
    }
}
