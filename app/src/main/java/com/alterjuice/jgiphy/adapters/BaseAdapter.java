package com.alterjuice.jgiphy.adapters;

import java.util.Collection;

public interface BaseAdapter<T> {
    void updateWithStartPosition(Collection<T> items, int position);
    void update(Collection<T> items);
    void clearItems();

}
