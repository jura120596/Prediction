package com.example.prediction;

public class YaResult {
    boolean endOwWord;
    int pos;
    String[] text;

    public YaResult(boolean endOwWord, int pos, String[] text) {
        this.endOwWord = endOwWord;
        this.pos = pos;
        this.text = text;
    }
}
