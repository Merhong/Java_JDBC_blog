package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @AllArgsConstructor
public class Board {
    private Integer bId;
    private String bTitle;
    private String bContent;
    private Integer uId;

    @Override
    public String toString() {
        return "Board{" +
                "bId=" + bId +
                ", bTitle='" + bTitle + '\'' +
                ", bContent='" + bContent + '\'' +
                ", uId=" + uId +
                '}';
    }
}
