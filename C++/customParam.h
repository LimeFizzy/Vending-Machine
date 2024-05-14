/**
 * @file customParam.h
 * @author Leonardas Sinkevicius
 * @brief File with exceptions and custom output
 */

#ifndef CUSTOMPARAM_H
#define CUSTOMPARAM_H

#include <iostream>
#include <exception>
#include <string>
using namespace std;
/**
 * @brief Missing product exception(Invalid ID provided)
 * 
 */
class MissingProduct : public exception {
public:
    const char* what() const noexcept override {
        return "Exception: Missing product.";
    }
};

/**
 * @brief No products to display exception
 * 
 */
class DisplayError : public exception {
public:
    const char* what() const noexcept override {
        return "Exception: Nothing to display.";
    }
};

/**
 * @brief Invalid value exception
 * 
 */
class InvalidValue : public exception {
public:
    const char* what() const noexcept override {
        return "Exception: Invalid value is provided.";
    }
};

/**
 * @brief Insufficient balance to purchase selected product
 * 
 */
class InsufficientBalance : public exception {
public:
    const char* what() const noexcept override {
        return "Exception: Insufficient balance.";
    }
};

/**
 * @brief Preparation for outputing test results to both txt and std output
 * 
 */
class CustomStreamBuffer : public streambuf {
public:
    CustomStreamBuffer(const string& filename) : file(filename) {}

    virtual streamsize xsputn(const char* s, streamsize n) override {
        // Write to the file
        file.write(s, n);
        file.flush(); // Flush the file stream
        // Write to the standard output
        return cout.rdbuf()->sputn(s, n);
    }

    virtual int overflow(int c) override {
        // Write to the file
        file.put(c);
        file.flush(); // Flush the file stream
        // Write to the standard output
        return cout.rdbuf()->sputc(c);
    }

private:
    ofstream file;
};

/**
 * @brief Custom output to both txt and std output
 * 
 */
class CustomOutputStream : public ostream {
public:
    CustomOutputStream(const string& filename) : ostream(&buffer), buffer(filename) {}

private:
    CustomStreamBuffer buffer;
};

#endif