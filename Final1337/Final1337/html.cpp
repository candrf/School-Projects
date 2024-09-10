#include "html.h"

/*************************************************************
* Writes a row of the table, using the given tag for the    *
* table data. The tag may be td for table data or th for    *
* table header.                                             *
************************************************************/
void HTMLTable::writeRow(ostream& out, string tag,
	vector<string> row)
{
	out << "<tr>\n";
	for (unsigned int k = 0; k < headers.size(); k++)
	{
		out << "<" << tag << "> "
			<< row[k] << " </" << tag << "> ";
	}
	out << "\n</tr>\n";
}


/******************************************************
* Overloaded stream output operator <<                *
******************************************************/
ostream& operator<<(ostream& out, HTMLTable htmlTable)
{
	out << "<table border = \"1\">\n";
	// Title, spans all columns
	out << "<th colspan=\"9\">Cow Town College Alumni</th>\n";
	// Write the headers
	htmlTable.writeRow(out, "th", htmlTable.headers); 
	// Write the rows of the table
	for (unsigned int r = 0; r < htmlTable.rows.size(); r++)
	{
		htmlTable.writeRow(out, "td", htmlTable.rows[r]);
	}
	// Write end tag for table
	out << "</table>\n";
	return out;
}