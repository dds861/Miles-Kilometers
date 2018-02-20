package com.mathius.dd.milestokm;

/**
 * Created by dds86 on 20-Feb-18.
 */

public class Model {
    String textView1, textView2;

    public String getTextView1() {
        return textView1;
    }

    public String getTextView2() {
        return textView2;
    }

    public void setTextView1(String textView1) {
        this.textView1 = textView1;
    }

    public void setTextView2(String textView2) {
        this.textView2 = textView2;
    }

    public Model(String textView1, String textView2) {
        this.textView1 = textView1;
        this.textView2 = textView2;
    }
}
