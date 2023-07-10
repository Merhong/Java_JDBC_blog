package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Board {
	
	private Integer bId;
	private String bTitle;
	private String bContent;
	private Integer uId;
	
	// toString() 구현
	// lombok으로 사용하려면 @toString 사용하면 된다.
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
