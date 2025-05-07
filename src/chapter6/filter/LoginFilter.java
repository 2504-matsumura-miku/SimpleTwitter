package chapter6.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chapter6.beans.User;

@WebFilter(urlPatterns = { "/setting", "/edit" })
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		// セッションにログインユーザーの情報があればユーザー設定が編集の画面に遷移
		if (loginUser != null) {
			// サーブレットを実行
			chain.doFilter(request, response);
		} else {
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			List<String> errorMessages = new ArrayList<String>();

			errorMessages.add("ログインをしてください");
			session.setAttribute("errorMessages", errorMessages);
			httpServletResponse.sendRedirect("login");
		}
	}

	@Override
	public void init(FilterConfig config) {
	}

	@Override
	public void destroy() {
	}

}
