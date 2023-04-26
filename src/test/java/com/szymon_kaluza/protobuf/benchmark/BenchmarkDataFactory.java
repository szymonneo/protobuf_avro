package com.szymon_kaluza.protobuf.benchmark;

import com.szymon_kaluza.protobuf.proto.model.Author;
import com.szymon_kaluza.protobuf.proto.model.Book;
import com.szymon_kaluza.protobuf.proto.model.Library;
import net.bytebuddy.utility.RandomString;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BenchmarkDataFactory {

    public static Library getLibrary(List<Book> books) {
        return Library.newBuilder()
                .setAddress(getRandomString())
                .addAllBooks(books)
                .build();
    }

    public static List<Book> getManyBooks(int numberOfBooks) {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < numberOfBooks; i++) {
            books.add(getBook());
        }
        return books;
    }

    public static Book getBook() {
        return Book.newBuilder()
                .setTitle(getRandomString())
                .setAuthor(getAuthor())
                .setPages(getRandomInt() * 13L)
                .setAvailable(new Random().nextBoolean())
                .build();
    }

    public static Author getAuthor() {
        return Author.newBuilder()
                .setName(getRandomString())
                .setSurname(getRandomString())
                .setNationality(getRandomString())
                .build();
    }

    public static String getRandomString() {
        return RandomString.make(getRandomInt());
    }

    public static int getRandomInt() {
        return (int) ((Math.random() * (13 - 3)) + 3);
    }
}
