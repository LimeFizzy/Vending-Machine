/**
 * @file Product.h
 * @author Leonardas Sinkevicius
 * @brief Product class header
 */
#ifndef PRODUCT_H
#define PRODUCT_H

#include <memory>
#include <iostream>
#include <string>
#include <sstream>
using namespace std;

class Product{
    private:
        string name;
        double price;
        int quantity;
        int id;
        static int nextID;
    public:
        /**
         * @brief Construct a new Product object
         * 
         * @param name 
         * @param price 
         * @param quantity 
         */
        Product(string name, double price, int quantity);
        /**
         * @brief Destroy the Product object
         * 
         */
        ~Product();

        /**
         * @brief Product class objects serialization
         * 
         * @param os 
         * @param product 
         * @return ostream& 
         */
        friend ostream& operator<<(ostream& os, const Product& product);
        /**
         * @brief Product class objects deserialization
         * 
         * @param is 
         * @param product 
         * @return istream& 
         */
        friend istream& operator>>(istream& is, Product& product);

        /**
         * @brief Get the Name field
         * 
         * @return string 
         */
        string getName();
        /**
         * @brief Get the Price field
         * 
         * @return double 
         */
        double getPrice();
        /**
         * @brief Get the Quantity field
         * 
         * @return int 
         */
        int getQuantity();
        /**
         * @brief Get products ID
         * 
         * @return int 
         */
        int getID();
        /**
         * @brief Get the Next ID field
         * 
         * @return int 
         */
        static int getNextID();
        /**
         * @brief Returns string with full info about the product
         * 
         * @return string 
         */
        string getInfo();
        /**
         * @brief Set the Price field
         * 
         * @param newPrice 
         */
        void setPrice(double newPrice);
        /**
         * @brief Set the Quantity field
         * 
         * @param newQuantity 
         */
        void setQuantity(int newQuantity);
};

#endif