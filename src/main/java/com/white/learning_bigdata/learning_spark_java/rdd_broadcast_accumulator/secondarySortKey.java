package com.white.learning_bigdata.learning_spark_java.rdd_broadcast_accumulator;

import java.io.Serializable;

import scala.math.Ordered;

public class secondarySortKey implements Ordered<secondarySortKey>, Serializable {
    // 首先在自定义key里面，定义需要进行排序的列
    private int first;
    private int second;


    public secondarySortKey(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public int compare(secondarySortKey that) {
        if (this.first - that.getFirst() != 0) {
            return this.first - that.getFirst();
        } else {
            return this.second - that.getSecond();
        }
    }

    @Override
    public boolean $less(secondarySortKey that) {
        if (this.first < that.getFirst()) {
            return true;
        } else if (this.first == that.getFirst() &&
                this.second < that.getSecond()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean $greater(secondarySortKey that) {
        if (this.first > that.getFirst()) {
            return true;
        } else if (this.first == that.getFirst() &&
                this.second > that.getSecond()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean $less$eq(secondarySortKey that) {
        if (this.$less(that)) {
            return true;
        } else if (this.first == that.getFirst() &&
                this.second == that.getSecond()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean $greater$eq(secondarySortKey that) {
        if (this.$greater(that)) {
            return true;
        } else if (this.first == that.getFirst()
                && this.second == that.getSecond()) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(secondarySortKey that) {
        if (this.first - that.getFirst() != 0) {
            return this.first - that.getFirst();
        } else {
            return this.second - that.getSecond();
        }
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + first;
        result = prime * result + second;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        secondarySortKey other = (secondarySortKey) obj;
        if (first != other.first)
            return false;
        if (second != other.second)
            return false;
        return true;
    }
}
