package com.beaconfire.quizsystem.entity.hentity;

import javax.persistence.*;

@Entity
@Table(name = "choice", schema = "bf_project1", catalog = "")
public class ChoiceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "choice_id", nullable = false)
    private Integer choiceId;
    @Basic
    @Column(name = "option_content", nullable = false, length = 100)
    private String optionContent;
    @Basic
    @Column(name = "is_correct", nullable = false)
    private Byte isCorrect;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    @Transient
    private byte isSelected;

    public byte getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(byte isSelected) {
        this.isSelected = isSelected;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    public Integer getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(Integer choiceId) {
        this.choiceId = choiceId;
    }

    public String getOptionContent() {
        return optionContent;
    }

    public void setOptionContent(String optionContent) {
        this.optionContent = optionContent;
    }

    public Byte getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Byte isCorrect) {
        this.isCorrect = isCorrect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChoiceEntity that = (ChoiceEntity) o;

        if (choiceId != null ? !choiceId.equals(that.choiceId) : that.choiceId != null) return false;
        if (optionContent != null ? !optionContent.equals(that.optionContent) : that.optionContent != null)
            return false;
        if (isCorrect != null ? !isCorrect.equals(that.isCorrect) : that.isCorrect != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = choiceId != null ? choiceId.hashCode() : 0;
        result = 31 * result + (optionContent != null ? optionContent.hashCode() : 0);
        result = 31 * result + (isCorrect != null ? isCorrect.hashCode() : 0);
        return result;
    }
}
