package ru.alfabank.dto;

import lombok.Getter;
import lombok.Setter;
import ru.alfabank.dto.giphyEntry.*;

import java.util.ArrayList;

@Getter
@Setter
public class GiphyDto {

    private ArrayList<Datum> data;
    private Pagination pagination;
    private Meta meta;
}
