"""
Lab5
Andrew France
Dr. Parsons
SDEV 300
This program takes in two .csv files for population and housing data
and performs data analysis on them.
"""
import sys
import pandas as pd
import matplotlib.pyplot as plt


def population_change():
    """
    This function presents user with a menu to choose which columns of
    data to display information from population change file
    """
    # import pop change file
    df_pop = pd.read_csv('PopChange.csv')
    # menu loop
    while True:
        print("* Population Data Menu *")
        print("a. Pop Apr 1")
        print("b. Pop Jul 1")
        print("c. Change Pop")
        print("d. Exit")
        choice_pop = input("Select the column you want to analyze: ")
        # Call data_analysis functions for choices with the matching
        # data frame and column names as arguments
        if choice_pop == 'a':
            data_analysis(df_pop, 'Pop Apr 1')
        elif choice_pop == 'b':
            data_analysis(df_pop, 'Pop Jul 1')
        elif choice_pop == 'c':
            data_analysis(df_pop, 'Change Pop')
        elif choice_pop == 'd':
            print("Returning to Main Menu...")
            break
        else:
            print("Invalid selection. Re-enter...")


def housing():
    """
    This function presents user with a menu to choose which columns of
    data to display information from housing file.
    """
    # import housing file
    df_house = pd.read_csv('Housing.csv')
    # menu loop
    while True:
        print("* Housing Data Menu *")
        print("a. AGE")
        print("b. BEDRMS")
        print("c. BUILT")
        print("d. ROOMS")
        print("e. UTILITY")
        print("f. Exit")
        choice_house = input("Select the column you want to analyze: ")
        # Call data_analysis functions for choices with the matching
        # data frame and column names as arguments
        if choice_house == 'a':
            data_analysis(df_house, 'AGE')
        elif choice_house == 'b':
            data_analysis(df_house, 'BEDRMS')
        elif choice_house == 'c':
            data_analysis(df_house, 'BUILT')
        elif choice_house == 'd':
            data_analysis(df_house, 'ROOMS')
        elif choice_house == 'e':
            data_analysis(df_house, 'UTILITY')
        elif choice_house == 'f':
            print("Returning to Main Menu...")
            break
        else:
            print("Invalid selection. Re-enter...")


def data_analysis(file, column):
    """
    This function does calculations for selected data. It takes the specified data frame and
    specific column to be analyzed as arguments.
    """
    # get analysis from df and column
    col_mean = file[column].mean()
    col_max = file[column].max()
    col_min = file[column].min()
    col_count = file[column].count()
    col_median = file[column].median()
    col_std = file[column].std()  # standard deviation
    # print results
    print("You selected: ", column)
    print("Mean: ", str(col_mean))
    print("Max: ", str(col_max))
    print("Min: ", str(col_min))
    print("Count: ", str(col_count))
    print("Median: ", str(col_median))
    print("Standard deviation: ", str(col_std))
    # plot and show histogram
    plt.hist(file[column])
    plt.title(column)
    plt.show()


# Main Menu
print("* * * Welcome to the python data analysis demo * * *")
while True:
    print("Select an option from the menu.")
    print("1. Population Data")
    print("2. Housing Data")
    print("3. Exit the Program")
    choice = input("Selection: ")
    # Call appropriate functions based on choice, or exit
    if choice == '1':
        population_change()
    elif choice == '2':
        housing()
    elif choice == '3':
        break
    else:
        print("Invalid selection. Re-enter...")
# Exit message
print("* * * Thank you for using the app. Goodbye * * *")
sys.exit()
