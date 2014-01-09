package struts2.board.action;

import struts2.board.vo.BoardVo;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import dao.board.BoardDAOImpl;
import dao.board.BoardDAO;

/**
 * 삭제 액션
 * @since 2014.01.09
 * @author hiruan
 */
public class BoardDeleteAction extends ActionSupport implements Preparable, ModelDriven<BoardVo> {
	
	private static final long serialVersionUID = -3735574988722532285L;
	/** BOARD DAO */
	private BoardDAOImpl boardDAO = null;
	private BoardVo boardVo;
	
    public String execute() throws Exception {    	
		// 파라미터
		String searchText = boardVo.getSearchText();
		String searchTextUTF8 = new String(searchText.getBytes("ISO-8859-1"), "UTF-8");
		//String searchTextUTF8_E = URLEncoder.encode(searchTextUTF8, "UTF-8");
		// 모델
		boardVo.setSearchText(searchTextUTF8);	
		// 게시물 (BoardDAO : 일반 JDBC, BoardMyBatisDAO : Mybatis)
		this.boardDAO = new BoardDAO();
		// 게시물 삭제
		this.boardDAO.delete(boardVo);
        return SUCCESS;
    }

	@Override
	public BoardVo getModel() {
		return getBoardVo();
	}

	@Override
	public void prepare() throws Exception {
		setBoardVo(new BoardVo());
	}

	public BoardVo getBoardVo() {
		return boardVo;
	}

	public void setBoardVo(BoardVo boardVo) {
		this.boardVo = boardVo;
	}
    
}
