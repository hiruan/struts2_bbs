package struts2.board.action;

import struts2.board.vo.BoardVo;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import dao.board.BoardDAOImpl;
import dao.board.BoardDAO;

/**
 * 수정 액션
 * @since 2014.01.09
 * @author hiruan
 */
public class BoardModifyAction extends ActionSupport implements Preparable, ModelDriven<BoardVo> {

	private static final long serialVersionUID = -1474479227539974717L;
	/** BOARD DAO */
	private BoardDAOImpl boardDAO = null;
	private BoardVo boardVo;
	
	/**
	 * 수정 처리
	 */
    public String execute() throws Exception {
		// 파라미터
		String searchText = boardVo.getSearchText();
		//String searchTextUTF8_E = URLEncoder.encode(searchText, "UTF-8");
		String ip = ServletActionContext.getRequest().getRemoteAddr();
		// 모델
		boardVo.setIp(ip);
		boardVo.setSearchText(searchText);
		// 게시물 (BoardDAO : 일반 JDBC, BoardHibernateDAO : Mybatis)
		this.boardDAO = new BoardDAO();
		// 게시물 수정
		this.boardDAO.update(boardVo);
        return SUCCESS;
    }
    
    /**
     * 수정 폼
     * @return
     * @throws Exception
     */
    public String form() throws Exception {
		// 파라미터
		String searchText = getBoardVo().getSearchText();
		String searchTextUTF8 = new String(searchText.getBytes("ISO-8859-1"), "UTF-8");
		// 모델
		getBoardVo().setSearchText(searchTextUTF8);
		// 게시물 (BoardDAO : 일반 JDBC, BoardHibernateDAO : Mybatis)
		this.boardDAO = new BoardDAO();
		// 게시물 상세 조회
		setBoardVo(this.boardDAO.select(getBoardVo()));
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
