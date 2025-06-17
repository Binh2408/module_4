package org.example.dictionary.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DictionaryRepository {
    private static final Map<String, String> englishToVietnamese = new HashMap<>();
    private static final Map<String, String> vietnameseToEnglish = new HashMap<>();
    static {
        englishToVietnamese.put("hello", "xin chào");
        englishToVietnamese.put("book", "sách");
        englishToVietnamese.put("computer", "máy tính");
        vietnameseToEnglish.put("xin chào", "hello");
        vietnameseToEnglish.put("sách", "book");
        vietnameseToEnglish.put("máy tính", "computer");
    }

    public String translate(String word, String direction) {
        if (word == null || direction == null) return null;
        word = word.toLowerCase();

        if (direction.equals("englishToVn")) {
            return englishToVietnamese.get(word);
        } else if (direction.equals("vnToEnglish")) {
            return vietnameseToEnglish.get(word);
        }
        return null;
    }


}
