/**
 * @file VendingMachine.cpp
 * @author Leonardas Sinkevicius
 * @brief VendingMachine class methods implementation
 */

#include "VendingMachine.h"
#include "customParam.h"
#include "Product.h"

int VendingMachine::machinesCount = 0;

VendingMachine::VendingMachine(string name)
: name(name) {
    balance = 0;
    machinesCount++;
    vendingType = new CoffeeMachine();
}

VendingMachine::~VendingMachine() {
    machinesCount--;
}

ostream& operator<<(ostream& os, const VendingMachine& vendingMachine) {
    os.write(reinterpret_cast<const char*>(&vendingMachine.balance), sizeof(vendingMachine.balance));
    os << vendingMachine.name << '\n';

    // Serialize vendingType
    string type = vendingMachine.vendingType->displayType(); // Get type name from displayType()
    int typeLength = type.size();
    os.write(reinterpret_cast<const char*>(&typeLength), sizeof(typeLength));
    os.write(type.data(), typeLength);

    int numProducts = vendingMachine.products.size();
    os.write(reinterpret_cast<const char*>(&numProducts), sizeof(numProducts));
    for (const auto& pair : vendingMachine.products) {
        os << pair.second; // Serialize Product object
    }
    return os;
}


istream& operator>>(istream& is, VendingMachine& vendingMachine) {
    is.read(reinterpret_cast<char*>(&vendingMachine.balance), sizeof(vendingMachine.balance));
    getline(is, vendingMachine.name);

    // Deserialize vendingType
    int typeLength;
    is.read(reinterpret_cast<char*>(&typeLength), sizeof(typeLength));
    char* typeBuffer = new char[typeLength + 1];
    is.read(typeBuffer, typeLength);
    typeBuffer[typeLength] = '\0';
    string type(typeBuffer);
    delete[] typeBuffer;

    if (type == "This is Coffee machine.\n") {
        vendingMachine.vendingType = new CoffeeMachine();
    } else if (type == "This is Snack machine.\n") {
        vendingMachine.vendingType = new SnackMachine();
    } // Add more conditions for other types

    int numProducts;
    is.read(reinterpret_cast<char*>(&numProducts), sizeof(numProducts));
    vendingMachine.products.clear();
    for (int i = 0; i < numProducts; ++i) {
        Product product = Product("", 0, 0);
        is >> product; // Deserialize Product object
        vendingMachine.products.emplace(product.getID(), product);
    }
    return is;
}

string VendingMachine::getType(){
    return vendingType->displayType();
}

void VendingMachine::setType(IVendingType* type){
    vendingType = type;
}

int VendingMachine::getMachinesCount() {
    return VendingMachine::machinesCount;
}

string VendingMachine::getName(){
    return name;
}

double VendingMachine::getBalance(){
    return balance;
}

void VendingMachine::insertMoney(double amount) {
    if(amount > 0){
        balance += amount;
    }
    else throw InvalidValue();
}

void VendingMachine::addProduct(string name, double price, int quantity) {
    if(price < 0)
        throw InvalidValue();
    if(quantity < 0)
        throw InvalidValue();
    products.emplace(Product::getNextID(), Product(name, price, quantity));
}

string VendingMachine::displayProducts(){
    if(products.size() > 0){
        string display = "Available products:\n";
        for (auto& pair : products) {
            if (pair.second.getQuantity() > 0) {
                display += pair.second.getInfo();
            }
        }
        return display;
    }
    else throw DisplayError();
}

void VendingMachine::purchaseProduct(int ID) {
    auto prod = products.find(ID);
    if(prod == products.end())
        throw MissingProduct();
    if (balance < prod->second.getPrice())
        throw InsufficientBalance();
    if (prod->second.getQuantity() == 0)
        throw MissingProduct();

    balance -= prod->second.getPrice();
    prod->second.setQuantity(prod->second.getQuantity() - 1);
    cout << "Dispensing " << prod->second.getName() << endl;
}

void VendingMachine::setProdQuant(int ID, int newQuant) {
    auto prod = products.find(ID);
    if(prod == products.end())
        throw MissingProduct();
    prod->second.setQuantity(newQuant);
}
