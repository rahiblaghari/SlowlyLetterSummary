package app.Slowly.LetterArchive.Letter;

import jakarta.persistence.*;

@Entity
@Table
public class LetterSummary {
    public LetterSummary() {
    }

    public LetterSummary(Integer letterId, String userName, String letterSummary) {
        this.letterId = letterId;
        this.userName = userName;
        this.letterSummary = letterSummary;
    }

    public LetterSummary(String userName, String letterSummary) {
        this.userName = userName;
        this.letterSummary = letterSummary;
    }

    @Id
    @SequenceGenerator(
            name = "letter_sequence",
            sequenceName = "letter_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "letter_sequence"
    )
    private Integer letterId;
    private String userName;
    private String letterSummary;

    public Integer getLetterId() {
        return letterId;
    }

    public void setLetterId(Integer letterId) {
        this.letterId = letterId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLetterSummary() {
        return letterSummary;
    }

    public void setLetterSummary(String letterSummary) {
        this.letterSummary = letterSummary;
    }
}
