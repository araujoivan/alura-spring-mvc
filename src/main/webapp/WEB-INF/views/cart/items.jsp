<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
    <head>

        <c:url value="/" var="contextPath" />
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

        <title>Java, SOA, Android, iPhone, Ruby on Rails Books and much more - CodeHouse</title>

        <link rel="icon" href="//cdn.shopify.com/s/files/1/0155/7645/t/177/assets/favicon.ico?11981592617154272979" type="image/ico" />
        <link href="https://plus.googlecom/108540024862647200608" rel="publisher" />
        <link href="${contextPath}resources/css/cssbase-min.css" rel="stylesheet" type="text/css" media="all" />
        <link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700' rel='stylesheet' />
        <link href="${contextPath}resources/css/fonts.css" rel="stylesheet" type="text/css" media="all" />
        <link href="${contextPath}resources/css/fontello-ie7.css" rel="stylesheet" type="text/css" media="all" />
        <link href="${contextPath}resources/css/fontello-embedded.css" rel="stylesheet" type="text/css" media="all" />
        <link href="${contextPath}resources/css/fontello.css" rel="stylesheet" type="text/css" media="all" />
        <link href="${contextPath}resources/css/style.css" rel="stylesheet" type="text/css" media="all" />
        <link href="${contextPath}resources/css/layout-colors.css" rel="stylesheet" type="text/css" media="all" />
        <link href="${contextPath}resources/css/responsive-style.css" rel="stylesheet" type="text/css" media="all" />
        <link href="${contextPath}resources/css/guia-do-programador-style.css" rel="stylesheet" type="text/css" media="all" />
        <link href="${contextPath}resources/css/produtos.css" rel="stylesheet" type="text/css" media="all" />
        <link rel="canonical" href="http://www.casadocodigo.com.br/" />
    </head>
    <body>

        <header id="layout-header">
            <div class="clearfix container">
                <a href="/" id="logo"> </a>
                <div id="header-content">
                    <nav id="main-nav">
                        <ul class="clearfix">
                            <li><a href="${s:mvcUrl('SCC#items').build() }" rel="nofollow">Cart (${shopCart.quantity})</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </header>

        <nav class="categories-nav">
            <ul class="container">
                <li class="category"><a href="#">Home</a></li>
            </ul>
        </nav>

        <section class="container middle">
            <h2 id="cart-title">Your ShopCart</h2>
            <table id="cart-table">
                <colgroup>
                    <col class="item-col" />
                    <col class="item-price-col" />
                    <col class="item-quantity-col" />
                    <col class="line-price-col" />
                    <col class="delete-col" />
                </colgroup>
                <thead>
                    <tr>
                        <th class="cart-img-col"></th>
                        <th width="65%">Item</th>
                        <th width="10%">Price</th>
                        <th width="10%">Quantity</th>
                        <th width="10%">Total</th>
                        <th width="5%"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${shopCart.items}" var="item">
                        <tr>
                            <td class="cart-img-col"><img src="http://cdn.shopify.com/s/files/1/0155/7645/products/css-eficiente-featured_large.png?v=1435245145"
                                                          width="71px" height="100px" />
                            </td>
                            <td class="item-title">${item.product.title}</td>
                            <td class="numeric-cell">${item.price}</td>
                            <td class="quantity-input-cell">
                                <input type="number" min="0" id="quantity" name="quantity" value="${shopCart.getQuantity(item)}" />
                            </td>
                            <td class="numeric-cell">${shopCart.getTotal(item) }</td>
                            <td class="remove-item">
                                <form action="${s:mvcUrl('SCC#remove').arg(0, item.product.id).arg(1, item.priceType).build()}" method="POST">
                                    <input type="image" src="${contextPath}/resources/imagens/excluir.png" 
                                           alt="Delete" title="Delete" />
                                </form>	
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="3">
                            <form action="${s:mvcUrl('PC#closePayment').build()}" method="post">
                                <input type="submit" class="checkout" name="checkout" value="Finalize buying" />                                
                            </form>
                        </td>
                        <td class="numeric-cell">${shopCart.total }</td>
                        <td></td>
                    </tr>
                </tfoot>
            </table>
        </section>
    </body>
</html>