<%-- 
    Author     : ISD AUT2020 G45
--%>

<%@page import="IOTBay.Controllers.ProductDetailsController"%>
<%@page import="IOTBay.Models.ProductModel" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" type="text/css"/>
        <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@300&display=swap" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

        <title>IOTBay ~ Update Product</title>
        
    </head>
    <%
        String ID;
        ProductModel product;
        ProductDetailsController controller = new ProductDetailsController();
        
        ID = request.getParameter("id");
        product = controller.getProduct(ID);
        
        if (ID == null) {
            ID = "";
            product = new ProductModel();
        }
        session.setAttribute("product", product);
    %>
    <body>
        <div class="outerContentBoxTitle">
            <span class="outerContentBoxTitleText">Update/Add product</span>
            <img src="media/IOTBayLogo.png" alt="IOTBay Logo" class="outerContentBoxTitleLogo" onClick="goHome()">
        </div>
        <form action="ProductDetailsServlet" method="POST">
            <div class="outerContentBox"> 
                <div style="width: 50%; float:left"> 
                    <p> Press enter on image, brand, name & price fields to update preview below </p>
                    <div class="productBox">                                
                        <div id="image_div"></div>                           
                        <br>
                        <span class="productPreviewText" id="initbrand">${product.productBrand}</span> 
                        <span class="productPreviewText" id="initname">${product.productName}</span>
                        <span class="productPreviewText">~</span>
                        <span class="productPreviewText" id="initprice">$${product.price}</span>
                    </div>
                </div>

                <div style="width: 50%; float:right">   
                    <label for = "Category" class="fieldTitle">Category:</label>
                    <input type="text" class="field previewField" id="productCategory" name="productCategory" value = ${product.category} required>
                    <br>
                    <br>
                    <label for = "Image" class="fieldTitle">Image URL:</label>
                    <input type="text" class="field previewField" id="productImage" name="productImage" value = ${product.imageName} required>
                    <br>
                    <br>
                    <label for = "Brand" class="fieldTitle">Brand:</label>
                    <input type="text" class="field previewField" id="productBrand" name="productBrand" value = ${product.productBrand} required>
                    <br>
                    <br>
                    <label for = "prodNsmr" class="fieldTitle">Name:</label>
                    <input type="text" class="field previewField" id="productName" name="productName" value = ${product.productName} required>
                    <br>
                    <br>
                    <label for = "price" class="fieldTitle">Price ($XX.XX):</label>
                    <input type="text" class="field previewField" id="productPrice" name="productPrice" value = ${product.price} required>
                    <br>
                    <br>
                    <label for = "stock" class="fieldTitle">Stock:</label>
                    <input type="number" class="field previewField" id="productStock" name="productStock" value = ${product.stock} required>
                    <br>
                    <br>
                    <label for = "desc" class="fieldTitle">Description:</label>
                    <textarea class="field previewField" name="productDesc" required>${product.productDescription}</textarea>
                    <br>
                    <br>
                </div>

                <script>
                    $(document).ready(function(){
                    $('#image_div').append("<img src='' class='prod48'>");
                        $('#productImage').change(function(){        
                            $('.prod48').attr('src',$('#productImage').val());
                        });
                    });

                    $('input[name=productName]').change(function(){        
                        $('#initname').text( this.value );
                    });

                    $('input[name=productBrand]').change(function(){        
                        $('#initbrand').text( this.value );
                    });

                    $('input[name=productPrice]').change(function(){        
                        $('#initprice').text('$' + this.value );
                    });
                </script>                
            </div>
            <div class="buttonFooter">
                <button class="button" id="nextButton" name="ConfirmButton">Confirm ></button>
                <button class="button" id="previousButton" name="CancelButton">< Cancel</button>
            </div>
        </form>    
    </body>
</html>
