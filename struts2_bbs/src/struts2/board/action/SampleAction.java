package struts2.board.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 *  Sample 액션
 * @since 2014.01.09
 * @author hiruan
 */
public class SampleAction extends ActionSupport {

	private static final long serialVersionUID = -7984186696609132970L;
	private String str = "";
	
    public String execute() throws Exception {
    	str = "blog.naver.com/hiruan";
        return SUCCESS;
    }

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}



}
