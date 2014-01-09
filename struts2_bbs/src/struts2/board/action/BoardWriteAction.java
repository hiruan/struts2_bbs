package struts2.board.action;

import struts2.board.vo.BoardVo;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import dao.board.BoardDAOImpl;
import dao.board.BoardDAO;

/**
 * 등록 액션
 * @since 2014.01.09
 * @author hiruan
 */
public class BoardWriteAction extends ActionSupport implements Preparable, ModelDriven<BoardVo> {

	private static final long serialVersionUID = -7690786777811429722L;
	/** BOARD DAO */
	private BoardDAOImpl boardDAO = null;
	private BoardVo boardVo;
	
	/**
	 * 등록 처리
	 */
    public String execute() throws Exception {
		// 파라미터
		String ip = ServletActionContext.getRequest().getRemoteAddr();
		// 모델
		boardVo.setIp(ip);
		// 게시물 (BoardDAO : 일반 JDBC, BoardMyBatisDAO : Mybatis)
		this.boardDAO = new BoardDAO();
		// 게시물 등록
		this.boardDAO.insert(boardVo);
        return SUCCESS;
    }

    /**
     * 등록 폼
     * @return
     * @throws Exception
     */
    public String form() throws Exception {
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
