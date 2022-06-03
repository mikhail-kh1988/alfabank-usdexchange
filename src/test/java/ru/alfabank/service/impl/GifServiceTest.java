package ru.alfabank.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.alfabank.client.GiphyClient;
import ru.alfabank.dto.GiphyDto;
import ru.alfabank.dto.giphyEntry.Datum;
import ru.alfabank.dto.giphyEntry.Images;
import ru.alfabank.dto.giphyEntry.Original;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GifServiceTest {

    @Autowired
    private GifService service;

    @MockBean
    private GiphyClient giphyClient;

    @Test
    void getGif() {

        String temp;
        GiphyDto dto = new GiphyDto();
        Datum datum = new Datum();
        Images images = new Images();
        Original original = new Original();
        original.setUrl("test");
        images.setOriginal(original);
        datum.setImages(images);
        ArrayList<Datum> datumArrayList = new ArrayList<>();
        datumArrayList.add(datum);
        dto.setData(datumArrayList);

        Mockito.doReturn(dto)
                        .when(giphyClient)
                        .getGif(Mockito.anyString(), Mockito.anyString());

        temp = service.getGif("test");

        assertNotNull(temp);
    }
}