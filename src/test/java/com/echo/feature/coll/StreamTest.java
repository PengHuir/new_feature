package com.echo.feature.coll;

import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author ph.zhang
 * Created by on 2023/11/29 14:12
 */
public class StreamTest {

    public static void main(String[] args) {
        coll();
    }

    public static void coll() {

        List<Map<String, Object>> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("key", i);
            item.put("val", i);
            items.add(item);
        }

        items = items.stream().filter(e -> Objects.nonNull(e.get("key"))).collect(Collectors.toList());
        System.out.println(items.get(0).get("key"));
    }
}
