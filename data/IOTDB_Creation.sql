/* 
 * Shoryu Das-Zaman, April 2020
 * www.github.com/ShoryuDZ
 * Created for 41025 Introduction to Software Development, IOTBay Assignment
 */
/**
 * Author:  Rachel Hsia
 * Rerun if you fuck up your db beyond repair, you will lose all the data
 * Created: 26/05/2020
 */

-- erase tables if they exist to recreate them - in case this is run a billion times
drop table IOTBayUser;
drop table IOTBayCustomer;
drop table IOTBayStaff;
drop table IOTBayProduct;
drop table IOTBayAddress;
drop table IOTBayTransaction;
drop table IOTBayCardDetails;
drop table IOTBayCart;

create table IOTBayUser (
    userId char(16) primary KEY,
    firstName char(255),
    lastName char(255),
    phone char(10),
    email char(255) not null
);

create table IOTBayStaff (
    staffId char(16) primary key,
    userId char(16) not null,
    isAdmin boolean,
    staffPassword char(255),
    staffRole char(255),
foreign key (userId) references IOTBayUser(userId)
);

create table IOTBayCustomer (
    customerId char(16) primary key,
    userId char(16) not null,
    addressId char(255) not null,
    customerPassword char(255),
foreign key (userId) references IOTBayUser(userId)
);

create table IOTBayProduct (
    productId char(16) primary key,
    brand char(255),
    productName char(255),
    price decimal(8,2),
    stock integer(8),
    category char(255),
    productDescription char(255)
);

create table IOTBayOrder (
    orderId char(16) primary key,
    customerId char(16) not null,
    transactionId char(16) not null,
    addressId char(16) not null,
    trackingNumber char(8),
    discountCode char(255),
foreign key (customerId) references IOTBayCustomer(customerID),
foreign key (transactionId) references IOTBayTransaction(transactionID),
foreign key (addressId) references IOTBayAddress(addressId)
);
create table IOTBayAddress ( 
    addressId char(16) primary key,
    streetAddress char(255),
    suburb char(255),
    country char(255),
    addressState char(255),
    postcode char(8)
);

create table IOTBayTransaction (
    transactionID char(16) primary key,
    paymentID char(16) not null,
    AddressID char(16) not null,
    transactionTotal char(255),
    paymentApproved boolean,
foreign key (addressId) references IOTBayAddress(addressId),
foreign key (paymentId) references IOTBayPayment(paymentId)
);

create table IOTBayPaymentMethod (
    paymentID char(16) primary key,
    customerID char(16) not null,
    cardNum char(16),
    CVV integer(3),
    cardName char(255),
    expiry date,
foreign key (customerId) references IOTBayCustomer(customerID)
);

create table IOTBayCart (
    cartID char(16) primary key,
    productID char(16) not null,
    orderID char(16) not null,
    totalPrice decimal(8,2),
    quantity integer(8),
foreign key (productId) references IOTBayProduct(productId),
foreign key (orderId) references IOTBayOrder(orderId)
);

create table IOTBayLog (
    logID char(16) primary key,
    customerID char(16) not null,
    LogDate date,
    intime time,
    outtime time,
foreign key (customerID) references IOTBayCustomer(customerID)
);


