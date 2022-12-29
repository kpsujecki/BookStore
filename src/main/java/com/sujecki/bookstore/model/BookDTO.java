package com.sujecki.bookstore.model;

import jakarta.persistence.OneToMany;

import java.util.Set;

public class BookDTO {

    private String isbn;
    private String title;
    private String author;
    private String description;
    private String language;
    private Set<CategoryDTO> category;
    private Set<PublisherDTO> publisher;
    private float price;
    private int totalCount;
    private int sold;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Set<CategoryDTO> getCategory() {
        return category;
    }

    public void setCategory(Set<CategoryDTO> category) {
        this.category = category;
    }

    public Set<PublisherDTO> getPublisher() {
        return publisher;
    }

    public void setPublisher(Set<PublisherDTO> publisher) {
        this.publisher = publisher;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }
}
