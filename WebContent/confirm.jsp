<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="shopping.com.ItemBean" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="style.css">
	<title>商品購入確認画面</title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<section>
		<h1>商品購入確認</h1>
		<form action="result" method="get">
			<table>
				<thead>
					<tr>
						<th>商品ID</th>
						<th>商品名</th>
						<th>価格</th>
						<th>在庫数</th>
						<th>数量</th>
					</tr>
				</thead>
				<tbody>
				<% ItemBean b = (ItemBean)request.getAttribute("item"); %>
					<tr>
						<td><%=b.getItemId()%></td>
						<td><%=b.getItemName()%></td>
						<td><%=b.getItemPrice()%></td>
						<td><%=b.getItemQuantity()%></td>
						<td><%=(String)request.getAttribute("quantity")%></td>
					</tr>
				</tbody>
			</table>
			<input type="hidden" name="itemId" value="<%=b.getItemId()%>">
			<input type="hidden" name="itemName" value="<%=b.getItemName()%>">
			<input type="hidden" name="itemPrice" value="<%=b.getItemPrice()%>">
			<input type="hidden" name="itemQuantity" value="<%=b.getItemQuantity()%>">
			<input type="hidden" name="ordersNumber" value="<%=(String)request.getAttribute("quantity")%>">

			<div class="aline-center">
				<input type="button" onclick="location.href='../shopping'"  value="戻る">
				<input type="submit" value="カートに入れる">
				<input type="hidden" name="cart" value="input">
			</div>
		</form>
	</section>
	<footer>
	</footer>
</body>
</html>