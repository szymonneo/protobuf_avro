package com.szymon_kaluza.protobuf_avro.benchmark;

import com.szymon_kaluza.protobuf_avro.proto.model.Author;
import com.szymon_kaluza.protobuf_avro.proto.model.Book;
import com.szymon_kaluza.protobuf_avro.proto.model.Library;
import net.bytebuddy.utility.RandomString;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProtoBenchmarkDataFactory {

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

    public static Library getFixedLibrary(List<Book> books) {
        return Library.newBuilder()
                .setAddress("address")
                .addAllBooks(books)
                .build();
    }

    public static List<Book> getManyFixedBooks(int numberOfBooks) {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < numberOfBooks; i++) {
            books.add(getFixedBook(i));
        }
        return books;
    }

    public static Book getFixedBook(int index) {
        return Book.newBuilder()
                .setTitle("title" + index)
                .setAuthor(getFixedAuthor(index))
                .setPages(index)
                .setAvailable(true)
                .build();
    }

    public static Author getFixedAuthor(int index) {
        return Author.newBuilder()
                .setName("name" + index)
                .setSurname("surname" + index)
                .setNationality("nationality" + index)
                .build();
    }
}
