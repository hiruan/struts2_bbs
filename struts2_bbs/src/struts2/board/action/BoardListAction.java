package struts2.board.action;

import java.util.List;

import struts2.board.vo.BoardVo;
import util.PageNavigator;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import dao.board.BoardDAOImpl;
import dao.board.BoardDAO;

/**
 * 목록 조회 액션
 * @since 2014.01.09
 * @author hiruan
 */
public class BoardListAction extends ActionSupport implements Preparable, ModelDriven<BoardVo> {
	
	private static final long serialVersionUID = 1786040797141970275L;
	
	/** BOARD DAO */
	private BoardDAOImpl boardDAO = null;
	private BoardVo boardVo;
	private int totalCount = 0;
	private String pageNavigator = null;
	private List<BoardVo> boardList = null;
	
    public String execute() throws Exception {
		// 파라미터
		String pageNum = boardVo.getPageNum();
		String searchType = boardVo.getSearchType();
		String searchText = boardVo.getSearchText();
		if (pageNum == null) {
			pageNum = "1";
		}
		if (searchText == null) {
			searchType = "";
			searchText = "";
		}
		String searchTextUTF8 = new String(searchText.getBytes("ISO-8859-1"), "UTF-8");
		// 모델
		boardVo.setPageNum(pageNum);
		boardVo.setSearchType(searchType);
		boardVo.setSearchText(searchTextUTF8);
		// 게시물 (BoardDAO : 일반 JDBC, BoardMyBatisDAO : Mybatis)
		this.boardDAO = new BoardDAO();
		// 게시물 총 수
		totalCount = this.boardDAO.selectCount(boardVo);
		// 게시물 목록을 얻는 쿼리 실행
		boardList = this.boardDAO.selectList(boardVo);
		// 페이지 네비게이터
		pageNavigator = new PageNavigator().getPageNavigator(
			totalCount, boardVo.getListCount(), boardVo.getPagePerBlock(), 
			Integer.parseInt(pageNum), searchType, searchTextUTF8);
        return SUCCESS;
    }

	public BoardVo getBoardVo() {
		return boardVo;
	}

	public void setBoardVo(BoardVo boardVo) {
		this.boardVo = boardVo;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getPageNavigator() {
		return pageNavigator;
	}

	public void setPageNavigator(String pageNavigator) {
		this.pageNavigator = pageNavigator;
	}

	public List<BoardVo> getBoardList() {
		return boardList;
	}

	public void setBoardList(List<BoardVo> boardList) {
		this.boardList = boardList;
	}

	@Override
	public BoardVo getModel() {
		return boardVo;
	}

	@Override
	public void prepare() throws Exception {
		boardVo = new BoardVo();
	}

}
