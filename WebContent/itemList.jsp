<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="shopping.com.ItemBean" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="style.css">
	<title>商品一覧画面</title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<section>
		<h1>商品一覧</h1>
		<form action="shopping/buy" method="get">
			<table>
				<thead>
					<tr>
						<th>商品ID</th>
						<th>商品名</th>
						<th>価格</th>
						<th>在庫数</th>
						<th>数量</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<% ArrayList<ItemBean> itembeanList = (ArrayList<ItemBean>)request.getAttribute("itembeanList"); %>
				<% if(itembeanList != null){%>
					<% for( ItemBean b :itembeanList){%>

					<tr>
						<td><%=b.getItemId()%></td>
						<td><%=b.getItemName()%></td>
						<td><%=b.getItemPrice()%></td>
						<td><%=b.getItemQuantity()%></td>

						<% if(b.getItemQuantity() != 0 ){ %>
							<td>
								<select name="<%=b.getItemId()%>list">
									<% for(int i = 1 ; i <= b.getItemQuantity() ; i++ ) { %>
										<option value=<%=i%>> <%=i%> </option>
									<% } %>
								</select>
							</td>

							<td>
								<input type="submit" name="<%=b.getItemId()%>" value="カートに入れる">
							<td>

						<% } else { %>
							<td colspan="2">売り切れ</td>
						<% } %>
					</tr>

					<% } %>
				<% } %>
				</tbody>
			</table>
		</form>

		<form action="login" method="get">
			<p><input type="submit" style="margin-left: 0px;" value="戻る" name="submit"></p>
		</form>

	</section>
	<footer>
	</footer>








</body>
</html>