package lab0;

class AuthorFilter implements BookFilter {
    private String author;

    public AuthorFilter(String author) {
        this.author = author;
    }

    @Override
    public boolean filter(Book book) {
        return book.getAuthor().equalsIgnoreCase(author);
    }
}
