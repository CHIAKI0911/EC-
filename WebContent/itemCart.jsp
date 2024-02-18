<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="shopping.com.CartBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%
List<CartBean> cartBeanArray = (ArrayList<CartBean>)session.getAttribute("cartlist");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>カート一覧画面</title>
</head>
<body>

    <jsp:include page="header.jsp"></jsp:include>

    <section>
        <h1>カート一覧画面</h1>
        <!-- カート一覧が空かどうか確認 -->
        <% if(cartBeanArray != null && cartBeanArray.size() > 0){ %>
        <form action="../result" method="get">
        <table>
            <thead>
                <tr>
                    <th>商品ID</th>
                    <th>商品名</th>
                    <th>価格</th>
                    <th>在庫数</th>
                    <th>購入数</th>
                </tr>
            </thead>
            <tbody>
                <%for(CartBean cb : cartBeanArray){ %>
                    <tr>
                        <td><%=cb.getItemId() %></td>
                        <td><%=cb.getItemName() %></td>
                        <td><%=cb.getItemPrice() %></td>
                        <td><%=cb.getItemQuantity() %></td>
                        <td><%=cb.getBuyItemCount() %></td>
                    </tr>

                <% } %>


            </tbody>
        </table>
	        <div class="aline-center">
	         	<input type="submit" value="購入">
	        </div>
		</form>

          	<form action="../shopping" method="post">
          	  <div class="aline-center">
     		 	<input type="submit" value="一覧に戻る" >
     		  </div>
             </form>
        <% }else{ %>
             <p>カートは空です</p>
             <form action="/shopping" method="post">
     		 	<input type="submit" value="戻る">
             </form>
        <% } %>
    </section>
    <footer>
    </footer>
</body>
</html>

<script>

</script>
