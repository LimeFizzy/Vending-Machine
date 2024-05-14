/**
 * @file main.c
 * @author Leonardas Sinkevicius
 * @brief Testing environment
 */


#include "VendingMachine.h"
#include <fstream>
#include <assert.h>
using namespace std;

int main(){
    VendingMachine vendingMachine("Vending Machine 1");
    cout << vendingMachine.getType();
    vendingMachine.setType(new SnackMachine());
    cout << vendingMachine.getType();

    vendingMachine.addProduct("Soda", 1.5, 10);
    vendingMachine.addProduct("Chips", 1.0, 20);
    vendingMachine.addProduct("Chocolate", 2.0, 15);

    cout << "Original vending machine products:\n";
    cout << vendingMachine.displayProducts();
    cout << "-----------------------------------\n";

    // Save the vending machine state to a binary file
    ofstream outFile("vending_machine.bin", ios::binary);
    if (!outFile) {
        cerr << "Error: Unable to open output file\n";
        return 1;
    }
    outFile << vendingMachine;
    outFile.close();

    // Create a new VendingMachine object and load its state from the binary file
    VendingMachine loadedVendingMachine("Vending Machine 2");
    ifstream inFile("vending_machine.bin", ios::binary);
    if (!inFile) {
        cerr << "Error: Unable to open input file\n";
        return 1;
    }
    inFile >> loadedVendingMachine;
    inFile.close();

    // Display the loaded vending machine's products
    cout << "Loaded vending machine products:\n";
    cout << loadedVendingMachine.displayProducts();
    cout << loadedVendingMachine.getType();

    return 0;
}