// CMSC 430 Compiler Theory and Design
// Project 4 
// Andrew France
// 8/5/2024

// This file contains type definitions and the function
// prototypes for the type checking functions

typedef char* CharPtr;

enum Types {MISMATCH, INT_TYPE, CHAR_TYPE, REAL_TYPE, NONE};

void checkAssignment(Types lValue, Types rValue, string message);
void checkListAssignment(Types lValue, Types rValue, string message);
Types checkWhen(Types true_, Types false_);
Types checkSwitch(Types case_, Types when, Types other);
Types checkCases(Types left, Types right);
Types checkArithmetic(Types left, Types right);
Types checkArithmetic(Types right);
Types checkLists(Types left, Types right);
void checkSubscript(Types value);
Types checkRelation(Types left, Types right);
Types checkRemainder(Types left, Types right);
Types checkIf(Types if_, Types elsif_, Types else_);
Types checkIf(Types elsif1, Types elsif2);
Types checkFold(Types list);

