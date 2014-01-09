package struts2.board.interceptors;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 샘플 인터셉터
 * @since 2014.01.09
 * @author hiruan
 */
@SuppressWarnings("serial")
public class SampleInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		System.out.println("SampleInterceptor Before ---------------------");
		ai.invoke();
		System.out.println("SampleInterceptor After ---------------------");
		return null;
	}

}
