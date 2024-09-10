#pragma once
#include <string>
using namespace std;

class Alumni
{
	
private:
	string firstName;
	string lastName;
	string companyName;
	string address;
	string city;
	string state;
	string zipCode;
	string phone;
	string email;

public:
	Alumni();
	Alumni(string f,
		string l,
		string comp,
		string a,
		string ci,
		string st,
		string z,
		string p,
		string e);

	//set functions
	bool setFirstName(string f);
	bool setLastName(string l);
	bool setCompanyName(string comp);
	bool setAddress(string a);
	bool setCity(string ci);
	bool setState(string st);
	bool setZipCode(string z);
	bool setPhone(string p);
	bool setEmail(string e);

	//get functions
	string getFirstName();
	string getLastName();
	string getCompanyName();
	string getAddress();
	string getCity();
	string getState();
	string getZipCode();
	string getPhone();
	string getEmail();
};

