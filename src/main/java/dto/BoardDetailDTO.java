package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
// 화면에 필요한 것을 걸러주려면?
public class BoardDetailDTO {
	
	private Integer bId;
	private Integer uId;
	private String username;
	private String title;
	private String content;
	
}
