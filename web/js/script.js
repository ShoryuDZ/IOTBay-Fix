function carousel(carouselNumber) {
    var carouselImages = document.getElementsByClassName('carouselImage');
    var counter;
    
    for (counter = 0; counter < carouselImages.length; counter++) {
        carouselImages[counter].style.display = "none";
    }
    
    if (carouselNumber < carouselImages.length) {
        carouselImages[carouselNumber].style.display = "flex";
        carouselNumber++;
    }
    else {
        carouselNumber = 0;
        carouselImages[carouselNumber].style.display = "flex";
    }
    
    setTimeout(carousel, 4000, carouselNumber);
}

function goHome() {
    window.location.href = 'index.html';
}

function logOut() {
    window.location.href = 'logout.jsp';
}

function isPasswordMatching() {
    var password = document.getElementById("password");
    var confirmedPassword = document.getElementById("confirmPassword");
    
    if (password.value !== confirmedPassword.value) {
        confirmedPassword.classList.add("non-matching");
        return false;
    }
    
    return true;
}

function showDetailsAccordion() {
    var panelHeight = document.getElementById("accordionPanel").style.maxHeight;
    if (panelHeight === "0px") {
        document.getElementById("accordionPanel").style.maxHeight = "200px";
    }
    else {
        document.getElementById("accordionPanel").style.maxHeight = "0px";
    }
}

function changeQuantity(change, id) {
    var quantityField = document.getElementById(id).value;
    if (change < 0 && quantityField !== "0" || change > 0) {
        document.getElementById(id).value = (parseInt(quantityField) + change).toString();
    }
}

function clearPayment() {
//    document.getElementById("PaymentForm").reset();
    var cardholder = document.getElementById("order-conf-cardHolder");
    var cardNumber = document.getElementById("order-conf-cardNumber");
    var expiry = document.getElementById("order-conf-expiry");
    var cvv = document.getElementById("order-conf-cvv");
    if (cardholder || cardNumber || expiry || cvv){
        cardholder.value = "";
        cardNumber.value ="";
        expiry.value="";
        cvv.value="";
    }
}
function clearShippingDetailsForm() {
    var StreetAddress = document.getElementById("order-conf-StreetAddress");
    var Suburb = document.getElementById("order-conf-Suburb");
    var State = document.getElementById("order-conf-State");
    var PostCode = document.getElementById("order-conf-PostCode");
    var Country = document.getElementById("order-conf-Country");
    if (StreetAddress || Suburb || State || PostCode || Country){
        StreetAddress.value = "";
        Suburb.value ="";
        State.value="";
        PostCode.value="";
        Country.value="";
        }
}