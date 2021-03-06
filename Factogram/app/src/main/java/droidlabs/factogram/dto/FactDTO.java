package droidlabs.factogram.dto;


public class FactDTO {
    private String title;
    private String text;
    private Integer id;

    public FactDTO(){

    }

    public FactDTO(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public FactDTO(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
