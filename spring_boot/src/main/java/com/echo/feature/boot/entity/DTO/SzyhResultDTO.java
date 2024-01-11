package com.echo.feature.boot.entity.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * TODO
 *
 * @author ph.zhang
 * Created by on 2023/12/27 10:23
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SzyhResultDTO<R> implements Serializable {

    private boolean success;

    private String result;

    private int code = 200;

    private String infos;

    private ResultPage page;

    private R data;

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class ResultPage {

        private int page;

        private int pages;

        private int per_page;

        private boolean has_next;

        private boolean has_prev;

        private long total;
    }
}

