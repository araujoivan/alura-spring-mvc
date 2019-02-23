<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>


<!DOCTYPE html>
<html>
    <head>
        <c:url value="/" var="contextPath" />
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
        <link rel="icon" href="//cdn.shopify.com/s/files/1/0155/7645/t/177/assets/favicon.ico?11981592617154272979" type="image/ico" />
        <link href="https://plus.googlecom/108540024862647200608" rel="publisher" />

        <title>Books about Java, SOA, Android, iPhone, Ruby on Rails e more - Code House</title>

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
        <h1>${contextPath}</h1>
        <header id="layout-header">
            <div class="clearfix container">
                <a href="/" id="logo"> </a>
                <div id="header-content">
                    <nav id="main-nav">

                        <ul class="clearfix">
                            <li><a href="/carrinho" rel="nofollow">Cart Items (${shopCart.quantity})</a></li>
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

        <article id="livro-css-eficiente">
            <header id="product-highlight" class="clearfix">
                <div id="product-overview" class="container">
                    <img width="280px" height="395px" src="http://cdn.shopify.com/s/files/1/0155/7645/products/css-eficiente-featured_large.png?v=1435245145"
                         class="product-featured-image" />
                    <h1 class="product-title">${product.title }</h1>
                    <p class="product-author">
                        <span class="product-author-link"> </span>
                    </p>
                    <p class="book-description">${product.description }</p>
                </div>
            </header>

            <section class="buy-options clearfix">
                <form action='<c:url value="/cart/add" />' method="post" class="container">
                    <input type="hidden" value="${product.id}" name="productId" >
                    <ul id="variants" class="clearfix">
                        <c:forEach items="${product.prices}" var="price">
                            <li class="buy-option">
                                <input type="radio" name="priceType" class="variant-radio" id="priceType" value="${price.type}" checked="checked" /> 
                                <label class="variant-label" >${price.type}</label> 
                                <small class="compare-at-price">R$ 39,90</small>
                                <p class="variant-price">${price.value }</p>
                            </li>
                        </c:forEach>
                    </ul>
                    <button type="submit" class="icon-basket-alt" alt="Buy it now" title="Buy Now ${product.title}">BUY</button>
                </form>
            </section>

            <div class="container">
                <section class="summary">
                    <ul>
                        <li>
                            <h3>And much more... <a href='/pages/sumario-java8'>see summary</a>.</h3>
                        </li>
                    </ul>
                </section>

                <section class="data product-detail">
                    <h2 class="section-title">About the book:</h2>
                    <p>
                        Pages: <span>${product.pages}</span>
                    </p>
                    <p></p>
                    <p>Publish Date: <fmt:formatDate pattern="yyyy/MM/dd" value="${product.publishedDate.time}" /></p>
                </section>
            </div>
        </article>

        <footer id="layout-footer">
            <div class="clearfix container"></div>
        </footer>
    </body>
</html>