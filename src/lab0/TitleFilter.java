package lab0;

class TitleFilter implements BookFilter {
    private String title;

    public TitleFilter(String title) {
        this.title = title;
    }

    @Override
    public boolean filter(Book book) {
        return book.getTitle().equalsIgnoreCase(title);
    }
}