/**
 * @file VendingMachine.h
 * @author Leonardas Sinkevicius
 * @brief VendingMachine class header
 */

#ifndef VENDINGMACHINE_H
#define VENDINGMACHINE_H

#include <iostream>
#include <string>
#include <sstream>
#include <map>
#include <fstream>
#include "customParam.h"
#include "Interface.h"

using namespace std;

class Product;

class VendingMachine{
    
    private:
        string name;
        double balance;
        map<int, Product> products;
        static int machinesCount;

    protected:
        IVendingType* vendingType;

    public:
        /**
         * @brief Construct a new Vending Machine object
         * 
         * @param name 
         */
        VendingMachine(string name);
        /**
         * @brief Destroy the Vending Machine object
         * 
         */
        ~VendingMachine();

        /**
         * @brief Class serialization
         * 
         * @param os 
         * @param vendingMachine 
         * @return ostream& 
         */
        friend ostream& operator<<(ostream& os, const VendingMachine& vendingMachine);
        /**
         * @brief Class deserialization
         * 
         * @param is 
         * @param vendingMachine 
         * @return istream& 
         */
        friend istream& operator>>(istream& is, VendingMachine& vendingMachine);

        /**
         * @brief Get vending machines type
         * 
         * @return string 
         */
        string getType();
        /**
         * @brief Set vending machines type
         * 
         * @param type 
         */
        void setType(IVendingType* type);

        /**
         * @brief Get the Machines Count field
         *
         * 
         * @return int 
         */
        static int getMachinesCount();
        /**
         * @brief Get the Name field
         *
         * 
         * @return string 
         */
        string getName();
        /**
         * @brief Get the Balance field
         *
         * 
         * @return double 
         */
        double getBalance();
        /**
         * @brief Insert money into the vending machine, increase balance
         * 
         * @param amount 
         */
        void insertMoney(double amount);
        /**
         * @brief Add new product to the vending machine
         * 
         * @param name 
         * @param price 
         * @param quantity 
         */
        void addProduct(string name, double price, int quantity);
        /**
         * @brief Display all existing products
         * 
         * @return string 
         */
        string displayProducts();
        /**
         * @brief Function to complete the purchase of the product
         * 
         * @param ID 
         */
        void purchaseProduct(int ID);
        /**
         * @brief Set the Prod Quant field
         * 
         * @param ID 
         * @param newQuant 
         */
        void setProdQuant(int ID, int newQuant);
};

#endif