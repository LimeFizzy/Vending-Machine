/**
 * @file Product.cpp
 * @author Leonardas Sinkevicius
 * @brief Product class methods implementation
 */

#include "Product.h"
using namespace std;

int Product::nextID = 0;

Product::Product(string name, double price, int quantity)
: name(name), price(price), quantity(quantity) {
    id = nextID;
    nextID++;
}

Product::~Product() = default;

ostream& operator<<(ostream& os, const Product& product) {
    os.write(reinterpret_cast<const char*>(&product.id), sizeof(product.id));
    os.write(reinterpret_cast<const char*>(&product.price), sizeof(product.price));
    os.write(reinterpret_cast<const char*>(&product.quantity), sizeof(product.quantity));
    int nameLength = product.name.size();
    os.write(reinterpret_cast<const char*>(&nameLength), sizeof(nameLength));
    os.write(product.name.data(), nameLength);
    return os;
}

istream& operator>>(istream& is, Product& product) {
    is.read(reinterpret_cast<char*>(&product.id), sizeof(product.id));
    is.read(reinterpret_cast<char*>(&product.price), sizeof(product.price));
    is.read(reinterpret_cast<char*>(&product.quantity), sizeof(product.quantity));
    int nameLength;
    is.read(reinterpret_cast<char*>(&nameLength), sizeof(nameLength));
    char* buffer = new char[nameLength + 1];
    is.read(buffer, nameLength);
    buffer[nameLength] = '\0';
    product.name = buffer;
    delete[] buffer;
    return is;
}

string Product::getName(){
    return name;
}

double Product::getPrice(){
    return price;
}

int Product::getQuantity(){
    return quantity;
}

int Product::getID(){
    return id;
}

int Product::getNextID(){
    return Product::nextID;
}

string Product::getInfo(){
    stringstream info;
    info << "ID: " << id << ", Name: " << name << 
    ", Price: €" << price << ", Quantity: " << quantity << endl;
    return info.str();
}

void Product::setPrice(double newPrice){
    price = newPrice;
}

void Product::setQuantity(int newQuantity){
    quantity = newQuantity;
}

