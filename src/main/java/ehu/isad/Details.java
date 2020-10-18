package ehu.isad;

import java.util.Arrays;

public class Details {

    String[] publishers;
    Integer number_of_pages;
    String title;

    @Override
    public String toString() {
        return "Details{" +
                "publishers=" + Arrays.toString(publishers) +
                ", number_of_pages=" + number_of_pages +
                ", title='" + title + '\'' +
                '}';
    }

    public String getTitle() { return this.title; }

    public String[] getPublishers() { return this.publishers; }

    public String getNumber_of_pages() { return this.number_of_pages.toString(); }
}