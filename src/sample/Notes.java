package sample;

public class Notes {
    private String note;
    private String description;

    public Notes(){}

    public Notes(String note, String description) {
        this.note = note;
        this.description = description;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
