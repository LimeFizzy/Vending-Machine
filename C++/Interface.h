/**
 * @file Interface.h
 * @author Leonardas Sinkevicius
 * @brief Interfaces to implement bridge strategy
 */

using namespace std;

/**
 * @brief Abstract interface for the Bridge template implementation
 * 
 */
class IVendingType{
    public:
        virtual string displayType() = 0;
        virtual ~IVendingType() {}
};

/**
 * @brief Concrete interface representing Coffee machine type of a vending machine
 * 
 */
class CoffeeMachine : public IVendingType{
    public:
        string displayType() override {
            return "This is Coffee machine.\n";
        }
};

/**
 * @brief Concrete interface representing Snack machine type of a vending machine
 * 
 */
class SnackMachine : public IVendingType{
    public:
        string displayType() override {
            return "This is Snack machine.\n";
        }
};