package ru.alfabank.dto.giphyEntry;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {
    private int total_count;
    private int count;
    private int offset;
}
