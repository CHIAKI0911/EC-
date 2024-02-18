<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="login.com.LoginUserBean" %>
<%@ page import="history.com.HistoryBean" %>
<%@ page import="java.util.ArrayList" %>

<% LoginUserBean bean = (LoginUserBean)session.getAttribute("user_db"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="style.css">
	<title>購入履歴画面</title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<section>
		<h1><%= bean.getName()%>さんの購入履歴</h1>
			<table>
				<thead>
					<tr>
						<th>商品ID</th>
						<th>商品名</th>
						<th>購入数</th>
					</tr>
				</thead>
				<tbody>
					<% ArrayList<HistoryBean> arrhb = (ArrayList<HistoryBean>)request.getAttribute("historyList"); %>
					<%for(HistoryBean hb : arrhb){ %>
						<tr>
							<td><%=hb.getItemId() %></td>
							<td><%=hb.getItemName() %></td>
							<td><%=hb.getItemByQuantity() %></td>
						</tr>
					<% } %>
				</tbody>
			</table>

			<form action="login" method="get">
				<p><input type="submit" style="margin-left: 0px;" value="戻る" name="submit"></p>
			</form>


	</section>

	<footer>
	</footer>

</body>
</html>