// Alumni calss member function definitions
#include "alumni.h"

Alumni::Alumni()			// default constructor
{
	firstName = "";
	lastName = "";
	companyName = "";
	address = "";
	city = "";
	state = "";
	zipCode = "";
	phone = "";
	email = "";
}

Alumni::Alumni(string f,	// initializing constructor
	string l, string comp,
	string a, string ci,
	string st, string z,
	string p, string e)
{
	setFirstName(f);
	setLastName(l);
	setCompanyName(comp);
	setAddress(a);
	setCity(ci);
	setState(st);
	setZipCode(z);
	setPhone(p);
	setEmail(e);
}

// set functions for member variables, includes error handling

bool Alumni::setFirstName(string f)
{
	if (f.empty())
	{
		firstName = "invalid";
		return false;
	}
	firstName = f;
	return true;
}

bool Alumni::setLastName(string l)
{
	if (l.empty())
	{
		lastName = "invalid";
		return false;
	}
	lastName = l;
	return true;
}

bool Alumni::setCompanyName(string comp)
{
	if (comp.empty())
	{
		companyName = "invalid";
		return false;
	}
	companyName = comp;
	return true;
}

bool Alumni::setAddress(string a)
{
	if (a.empty())
	{
		address = "invalid";
		return false;
	}
	address = a;
	return true;
}

bool Alumni::setCity(string ci)
{
	if (ci.empty())
	{
		city = "invalid";
		return false;
	}
	city = ci;
	return true;
}

bool Alumni::setState(string st)
{
	if (st.empty())
	{
		state = "invalid";
		return false;
	}
	state = st;
	return true;
}

bool Alumni::setZipCode(string z)
{
	if (z.empty())
	{
		zipCode = "invalid";
		return false;
	}
	zipCode = z;
	return true;
}

bool Alumni::setPhone(string p)
{
	if (p.empty())
	{
		phone = "invalid";
		return false;
	}
	phone = p;
	return true;
}

bool Alumni::setEmail(string e)
{
	if (e.empty())
	{
		email = "invalid";
		return false;
	}
	email = e;
	return true;
}

// get functions for member variables

string Alumni::getFirstName()
{
	return firstName;
}

string Alumni::getLastName()
{
	return lastName;
}

string Alumni::getCompanyName()
{
	return companyName;
}

string Alumni::getAddress()
{
	return address;
}

string Alumni::getCity()
{
	return city;
}

string Alumni::getState()
{
	return state;
}

string Alumni::getZipCode()
{
	return zipCode;
}

string Alumni::getPhone()
{
	return phone;
}

string Alumni::getEmail()
{
	return email;
}