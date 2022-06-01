package ru.alfabank.dto.giphyEntry;

import lombok.Getter;
import lombok.Setter;
import ru.alfabank.dto.GiphyDto;

@Getter
@Setter
public class Analytics {
    private Onload onload;
    private Onclick onclick;
    private Onsent onsent;
}
