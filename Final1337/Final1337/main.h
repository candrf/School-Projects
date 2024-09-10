#pragma once
#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <sstream>
#include<algorithm>
#include "alumni.h"
#include "html.h"

using namespace std;

// function prototypes

void OpenFiles(ifstream& in);
Alumni readFile(string& sLine, vector<string>& sParsed,
	ifstream& fin);
int mainMenu();
void generateHTML(vector<Alumni> array, string filename);
void reportOne(vector<Alumni> array);
void reportTwo(vector<Alumni> array);
void reportThree(vector<Alumni> array);
void sortReportTwo(vector<Alumni> &array);
void sortReportThree(vector<Alumni>& array, vector<Alumni> &newArray);