// Final programming project for Cosc 1337
// Andrew France
// November 28, 2022

#include "main.h"

int main()
{
	string fileLine;				// String to read in each line of the file
	vector<string> parsedLine;		// Array to hold parsed line from file
	vector<Alumni> alum;			// Array of objects from Alumni class

	// Open input file
	ifstream fin;
	OpenFiles(fin); 

	// While loop reads all info from .csv file into an array of objects until end of file
	while (!fin.eof())
		alum.push_back(readFile(fileLine, parsedLine, fin));

	// Display main menu, dowhile loop repeats menu until program is exited
	// Cases represent menu options
	int menuOption;
	do
	{
		menuOption = mainMenu();
		switch (menuOption)
		{
		case 0: cout << "Exiting program...";
			break;
		case 1: reportOne(alum);
			break;
		case 2: reportTwo(alum);
			break;
		case 3: reportThree(alum);
			break;
		default: cout << "Enter a valid menu option...\n";
		}
	} while (menuOption != 0);




	return 0;
}

/********************************************************************************
*							OpenFiles											*
* This function opens the input file 'cosc1337_members.csv' and tests to make	*
* sure the file was opened correctly											*
********************************************************************************/

void OpenFiles(ifstream& in)
{
	in.open("cosc1337_members.csv");
	if (!in)
	{
		cout << "Input file did not open. Program will exit." << endl;
		exit(0);
	}


}

/********************************************************************************
*							readFile											*
* This function reads the .csv file and puts the information into a vector of   *
* objects from the class Alumni		                                            *
********************************************************************************/
Alumni readFile(string& sLine, vector<string>& sParsed,
	ifstream& fin)
{
	getline(fin, sLine);
	stringstream lineStream(sLine);	// A special string class for pre-outputting format
	string field;
	sParsed.clear();	// Empty the Parsed Line for reuse

	while (getline(lineStream, field, ',')) // While there are fields between the ,
	{
		sParsed.push_back(field);	// Push them onto the string array vector
	}
	// Return Alumni object copy
	return Alumni(sParsed[0], sParsed[1], sParsed[2], sParsed[3],
		sParsed[4], sParsed[5], sParsed[6], sParsed[7], sParsed[8]);
}

/********************************************************************************
*							mainMenu											*
* This function displays the main menu options to the screen, prompts the user	*
* to make a menu selection, then returns the menu chioce to be used in main		*
********************************************************************************/
int mainMenu()
{
	int choice;

	cout << "\n		Cow Town College Alumni Reports Menu\n"
		<< "    ======================================================\n\n"
		<< "         1: All members by natural order of input file\n"
		<< "         2: All members sorted ascending by last name\n"
		<< "         3: Members in one state only, sorted ascending by city\n"
		<< "         0: Exit the program\n\n"
		<< " Please select report you wish to be run from menu above(0-3):";
	cin >> choice;

	return choice;
}

/***************************************************************************
*                     generateHTML                                         *
* This function generates the HTML file and takes vector of Alumni objects *
* and the custom filename as arguments                                     *
***************************************************************************/
void generateHTML(vector<Alumni> array, string filename)
{
	// Hard-coded data for table column headers   
	vector<string> headers{ "First Name", "Last Name", "Company Name", "Address",
	 "City", "State", "Zip Code", "Phone", "Email" };


	// Create the HTML table object and set its members
	HTMLTable hTable;
	hTable.setHeaders(headers);

	// vector for Alumni objects
	vector<string> rows;


	for (int i = 0; i < array.size(); i++)
	{
		rows = { array[i].getFirstName() ,
			 array[i].getLastName(),
			 array[i].getCompanyName(),
			 array[i].getAddress(),
			 array[i].getCity(),
			 array[i].getState(),
			 array[i].getZipCode(),
			 array[i].getPhone(),
			 array[i].getEmail()
		};

		hTable.addRow(rows);
	}

	// Open a file and write the HTML code to the file
	ofstream outFile(filename);
	outFile << hTable;
	outFile.close();


	// Use the default browser to view generated HTML table
	const char* c = filename.c_str();
	system(c);
}

/**********************************************************************
*                       ReportOne                                     *
* This report will show all members by natural order of input file    *
**********************************************************************/
void reportOne(vector<Alumni> array)
{
	//Enter custom filename
	string filename;
	cout << "Save file as (automatically saved as .html): ";
	cin >> filename;
	filename = filename + ".html";

	// call function to generate HTML report
	generateHTML(array, filename);
}

/**********************************************************************
*                       ReportTwo                                     *
* This report will show all members sorted ascending by last name     *
**********************************************************************/
void reportTwo(vector<Alumni> array)
{
	//Enter custom filename
	string filename;
	cout << "Save file as (automatically saved as .html): ";
	cin >> filename;
	filename = filename + ".html";

	//call function to sort ascending by last name
	sortReportTwo(array);

	// call function to generate HTML report
	generateHTML(array, filename);
}

/**********************************************************************
*                       ReportThree                                   *
* This report will show members in one state only, sorted             *
* ascending by city												      *
**********************************************************************/
void reportThree(vector<Alumni> array)
{
	//Enter custom filename
	string filename;
	cout << "Save file as (automatically saved as .html): ";
	cin >> filename;
	filename = filename + ".html";

	// call function to select state and sort list for report three
	// newArray will hold objects from selected state
	vector<Alumni> newArray;	 
	sortReportThree(array, newArray);

	// call function to generate HTML report
	generateHTML(newArray, filename);
}

/*********************************************************************
*                   sortReportTwo                                    *
* This function takes in array of Alumni objects and sorts them in   *
* ascending order by last name using bubble sort                     *
*********************************************************************/
void sortReportTwo(vector<Alumni>& array)
{
	Alumni temp;   // holds Alumni object
	bool swap;

	do
	{
		swap = false;
		for (int count = 0; count < (array.size() - 1); count++)
		{
			if (array[count].getLastName() > array[count + 1].getLastName())
			{
				temp = array[count];
				array[count] = array[count + 1];
				array[count + 1] = temp;
				swap = true;
			}
		}
	} while (swap);

}

/*********************************************************************
*                   sortReportThree                                  *
* This function takes in array of Alimni objects, displays a list of *
* states to choose from, then creates new vector of objects for one  *
* state only based on user selection, then sorts new list ascending  *
* by city.															 *
*********************************************************************/
void sortReportThree(vector<Alumni>& array, vector<Alumni> &newArray)
{
	// create and display a list of avalible states to choose from
	vector<string> states;
	for (int i = 0; i < array.size(); i++)
	{
		states.push_back(array[i].getState());  
	}
	sort(states.begin(), states.end());
	vector<string>::iterator unq;
	unq = unique(states.begin(), states.end());
	states.resize(distance(states.begin(), unq));

	cout << "List of States to choose from:\n";
	for (int j = 0; j < states.size(); j++)
	{
		cout << states[j]<< " ";
		if (j == 24)
			cout << endl;
	}


	// user picks a state
	string state;
	cout << "\nEnter a two-character state from the list above: ";
	cin >> state;

	// checks user input to make sure they enter two characters, then converts 
	// choice to uppercase if not already
	while (state.size() != 2)
	{
		cout << "\nThe state selected must be in two-character format.\n"
			<< "Re-enter: ";
		cin >> state;
	}
	transform(state.begin(), state.end(), state.begin(), ::toupper);

	// add objects to new array of objects based on state selected
	for (int k = 0; k < array.size(); k++)
	{
		if (array[k].getState() == state)
		{
			newArray.push_back(array[k]);
		}
	}

	// sort new array
	Alumni temp;   
	bool swap;

	do
	{
		swap = false;
		for (int count = 0; count < (newArray.size() - 1); count++)
		{
			if (newArray[count].getCity() > newArray[count + 1].getCity())
			{
				temp = newArray[count];
				newArray[count] = newArray[count + 1];
				newArray[count + 1] = temp;
				swap = true;
			}
		}
	} while (swap);


}