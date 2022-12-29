package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        StringBuilder regex = new StringBuilder();

        regex.append('[');
        for (String delimiter : delimiters) {
            regex.append(delimiter);
        }
        regex.append(']');

        ArrayList<String> splitByDelimiters = new ArrayList<>();

        for (String s : source.split(regex.toString())) {
            if (!s.isEmpty()) {
                splitByDelimiters.add(s);
            }
        }
        return splitByDelimiters;
    }
}
