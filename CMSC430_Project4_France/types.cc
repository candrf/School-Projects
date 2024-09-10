// CMSC 430 Compiler Theory and Design
// Project 4 
// Andrew France
// 8/5/2024

// This file contains the bodies of the type checking functions

#include <string>
#include <vector>

using namespace std;

#include "types.h"
#include "listing.h"

void checkAssignment(Types lValue, Types rValue, string message) {
	if (lValue != MISMATCH && rValue != MISMATCH && lValue != rValue){
	        if ((lValue == INT_TYPE) && (rValue == REAL_TYPE)){
	        appendError(GENERAL_SEMANTIC, "Illegal Narrowing " + message);
	        } else {
		appendError(GENERAL_SEMANTIC, "Type Mismatch on " + message);
		}
	}	
}

Types checkWhen(Types true_, Types false_) {
	if (true_ == MISMATCH || false_ == MISMATCH)
		return MISMATCH;
	if (true_ != false_){
		appendError(GENERAL_SEMANTIC, "When Types Mismatch ");
		return MISMATCH;
		}
	return true_;
}

Types checkSwitch(Types case_, Types when, Types other) {
	if (case_ != INT_TYPE)
		appendError(GENERAL_SEMANTIC, "Switch Expression Not Integer");
	return checkCases(when, other);
}

Types checkCases(Types left, Types right) {
	if (left == MISMATCH || right == MISMATCH)
		return MISMATCH;
	if (left == NONE || left == right)
		return right;
	appendError(GENERAL_SEMANTIC, "Case Types Mismatch");
	return MISMATCH;
}

Types checkArithmetic(Types left, Types right) {
	if (left == MISMATCH || right == MISMATCH)
		return MISMATCH;
	if (left == INT_TYPE && right == INT_TYPE)
		return INT_TYPE;
	if ((left == INT_TYPE && right == REAL_TYPE) || (left == REAL_TYPE && right == INT_TYPE))
	        return REAL_TYPE;
	if (left == REAL_TYPE && right == REAL_TYPE)
		return REAL_TYPE;
	appendError(GENERAL_SEMANTIC, "Arithmetic Operator Requires Numeric Types");
	return MISMATCH;
}

// Overloaded checkArithmetic function for unary negation
Types checkArithmetic(Types right) {
	if (right == MISMATCH)
		return MISMATCH;
	if (right == INT_TYPE)
		return INT_TYPE;
	if (right == REAL_TYPE)
	        return REAL_TYPE;
	appendError(GENERAL_SEMANTIC, "Arithmetic Operator Requires Numeric Types");
	return MISMATCH;
}

// Function checks if elements in list match each other
Types checkLists(Types left, Types right) {
	if (left == MISMATCH || right == MISMATCH)
		return MISMATCH;
	if (left == right)
	        return right;
	appendError(GENERAL_SEMANTIC, "List Element Types Do Not Match");
	return MISMATCH;
}

// Function checks if elements in list match list type
void checkListAssignment(Types lValue, Types rValue, string message) {
	if (lValue != MISMATCH && rValue != MISMATCH && lValue != rValue)
		appendError(GENERAL_SEMANTIC, message + " Does Not Match Element Type");
}

// Function checks that list subscript is int
void checkSubscript(Types value){
        if(value != INT_TYPE)
	        appendError(GENERAL_SEMANTIC, "List Subscript Must Be Integer");
}

// Function checks chars can be compared to chars but nothing else
Types checkRelation(Types left, Types right) {
	if (left == MISMATCH || right == MISMATCH)
		return MISMATCH;
	if ((left == CHAR_TYPE && right != CHAR_TYPE)||(left != CHAR_TYPE && right == CHAR_TYPE)){
		appendError(GENERAL_SEMANTIC, "Character Literals Cannot be Compared to Numeric Expressions");
		return MISMATCH;
		}
	return right;
}

// Function checks that remainder operands are integers
Types checkRemainder(Types left, Types right) {
	if (left == MISMATCH || right == MISMATCH)
		return MISMATCH;
	if (left == INT_TYPE && right == INT_TYPE)
		return INT_TYPE;
	appendError(GENERAL_SEMANTIC, "Remainder Operator Requires Integer Operands");
	return MISMATCH;
}

// Function checks that all returns for if elsif else statements match
Types checkIf(Types if_, Types elsif_, Types else_) {
        if ((if_ == MISMATCH) || (elsif_ == MISMATCH) ||(else_ == MISMATCH))
		return MISMATCH;
        if ((elsif_ == NONE) && (if_ == else_))
                return else_;
	if ((if_ == elsif_) && (if_ == else_) && (elsif_ == else_)){ 
		return else_;
		}
	appendError(GENERAL_SEMANTIC, "If-Elsif-Else Type Mismatch");
	return MISMATCH;
}

// Overloaded checkIf function checks for multiple elsif statements
Types checkIf(Types elsif1, Types elsif2){
        if (elsif1 == MISMATCH || elsif2 == MISMATCH)
		return MISMATCH;
        if (elsif1 == NONE || elsif1 == elsif2)
		return elsif2;
	return MISMATCH;
}

// Function checks fold to require numeric type
Types checkFold(Types list){
	if (list == MISMATCH)
		return MISMATCH;
        if ((list == INT_TYPE)||(list == REAL_TYPE))
                return list;
        appendError(GENERAL_SEMANTIC, "Fold Requires A Numeric List");
	return MISMATCH;
}
