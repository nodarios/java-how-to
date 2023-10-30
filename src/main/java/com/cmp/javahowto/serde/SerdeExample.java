package com.cmp.javahowto.serde;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerdeExample {

    public static void main(String[] args) {
        Author author = new Author("asimov", null);
        Book book = new Book("foundation", author);
        author.setBook(book); // set the circular reference

        serializeBook(book);
        Book deserializedBook = deserializeBook();
        System.out.println("deserialized book: " + deserializedBook);
    }

    private static void serializeBook(Book book) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("book.ser"))) {
            oos.writeObject(book);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Book deserializeBook() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("book.ser"))) {
            return (Book) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
class Book implements Serializable {

    private String title;
    private transient Author author;  // mark author as transient

    // implement custom method for serialization
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // serialize non-static and non-transient fields
        out.writeObject(author.getName());  // serialize author's name instead of the entire author
    }

    // implement custom method for deserialization
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // deserialize non-static and non-transient fields
        String authorName = (String) in.readObject();  // deserialize author's name
        author = new Author(authorName, this);  // reconstruct the Author object
    }

    @Override
    public String toString() {
        return "title " + title + ", name " + author.getName();
    }

}

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
class Author {

    private String name;
    private Book book;

    @Override
    public String toString() {
        return "name " + name + ", title " + book.getTitle();
    }

}
